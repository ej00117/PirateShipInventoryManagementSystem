package edu.westga.cs3211.pirateshipinventorymanagementsystem.model.ExpirationReport;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs3211.pirateshipinventorymanagementsystem.enums.Condition;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.enums.ItemCategory;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.enums.SpecialQuality;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.model.ChangeHistory;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.model.Compartment;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.model.ExpirationDisplayItem;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.model.Inventory;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.model.PerishableStock;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.viewmodel.ViewInventoryPageViewModel;
import javafx.collections.ObservableList;

class TestExpirationReport {

    private Inventory inventory;
    private ViewInventoryPageViewModel vm;

    @BeforeEach
    void setup() {
        ArrayList<Compartment> compartments = new ArrayList<>();

        ArrayList<SpecialQuality> perishable = new ArrayList<>();
        perishable.add(SpecialQuality.PERISHABLE);

        Compartment comp = new Compartment("Food", 100.0, perishable);

        comp.addStock(new PerishableStock(
                "Milk", 2.0, ItemCategory.FOOD, Condition.PERFECT,
                perishable, LocalDate.now().plusDays(3)));

        comp.addStock(new PerishableStock(
                "Bread", 1.0, ItemCategory.FOOD, Condition.PERFECT,
                perishable, LocalDate.now().minusDays(2)));

        compartments.add(comp);

        this.inventory = new Inventory(compartments);
        this.vm = new ViewInventoryPageViewModel(this.inventory, new ChangeHistory(), "cook", "pass");
    }

    @Test
    void testReportContainsCorrectNumberOfItems() {
        ObservableList<ExpirationDisplayItem> report = vm.getExpirationReport();
        assertEquals(2, report.size(), "Should include 2 perishable items");
    }

    @Test
    void testReportSortedByExpiration() {
        ObservableList<ExpirationDisplayItem> report = vm.getExpirationReport();

        ExpirationDisplayItem first = report.get(0);
        ExpirationDisplayItem second = report.get(1);

        assertTrue(first.getDaysRemaining() <= second.getDaysRemaining(),
                "Items should be sorted by expiration date");
    }

    @Test
    void testExpiredItemShowsCorrectStatus() {
        ObservableList<ExpirationDisplayItem> report = vm.getExpirationReport();

        ExpirationDisplayItem expired = report.stream()
                .filter(i -> i.getName().equals("Bread"))
                .findFirst()
                .orElseThrow();

        assertEquals("2 days expired", expired.getStatus());
        assertEquals(-2, expired.getDaysRemaining());
        assertEquals(1.0, expired.getQuantity());
    }

    @Test
    void testFutureItemShowsCorrectStatus() {
        ObservableList<ExpirationDisplayItem> report = vm.getExpirationReport();

        ExpirationDisplayItem milk = report.stream()
                .filter(i -> i.getName().equals("Milk"))
                .findFirst()
                .orElseThrow();

        assertEquals("3 days left", milk.getStatus());
        assertEquals(3, milk.getDaysRemaining());
        assertEquals(2.0, milk.getQuantity());
    }

    @Test
    void testEmptyInventoryReturnsEmptyReport() {
        Inventory emptyInventory = new Inventory(new ArrayList<>());
        ViewInventoryPageViewModel vm2 =
                new ViewInventoryPageViewModel(emptyInventory, new ChangeHistory(), "user", "pass");

        assertEquals(0, vm2.getExpirationReport().size());
    }
    
    @Test
    void testToStringForFutureExpiration() {
        ExpirationDisplayItem item =
                new ExpirationDisplayItem("Milk", 2.0, "3 days left", 3);

        String expected = "Milk Amount: 2.0 Expiration: 3 days left";

        assertEquals(expected, item.toString());
    }

    @Test
    void testToStringForExpiredItem() {
        ExpirationDisplayItem item =
                new ExpirationDisplayItem("Bread", 1.0, "2 days expired", -2);

        String expected = "Bread Amount: 1.0 Expiration: 2 days expired";

        assertEquals(expected, item.toString());
    }

    @Test
    void testToStringForExpiresToday() {
        ExpirationDisplayItem item =
                new ExpirationDisplayItem("Soup", 5.0, "expires today", 0);

        String expected = "Soup Amount: 5.0 Expiration: expires today";

        assertEquals(expected, item.toString());
    }
    
    @Test
    void testItemExpiresToday() {
        ArrayList<Compartment> compartments = new ArrayList<>();

        ArrayList<SpecialQuality> perishable = new ArrayList<>();
        perishable.add(SpecialQuality.PERISHABLE);

        Compartment comp = new Compartment("Food", 100.0, perishable);

        comp.addStock(new PerishableStock(
                "Soup", 5.0, ItemCategory.FOOD, Condition.PERFECT,
                perishable, LocalDate.now()));

        compartments.add(comp);

        Inventory inv = new Inventory(compartments);
        ViewInventoryPageViewModel vm2 =
                new ViewInventoryPageViewModel(inv, new ChangeHistory(), "user", "pass");

        ObservableList<ExpirationDisplayItem> report = vm2.getExpirationReport();

        assertEquals(1, report.size(), "Should contain exactly one item");

        ExpirationDisplayItem item = report.get(0);

        assertEquals("Soup", item.getName());
        assertEquals(5.0, item.getQuantity());
        assertEquals(0, item.getDaysRemaining());
        assertEquals("expires today", item.getStatus());
    }

}
