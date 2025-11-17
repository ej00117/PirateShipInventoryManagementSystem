package edu.westga.cs3211.pirateshipinventorymanagementsystem.viewmodel.addstockpageviewmodel;

import edu.westga.cs3211.pirateshipinventorymanagementsystem.enums.Condition;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.enums.SpecialQuality;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.model.*;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.viewmodel.AddStockPageViewModel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TestAddStockPageViewModel {

    private AddStockPageViewModel viewModel;
    private Inventory inventory;
    private ChangeHistory history;

    @BeforeEach
    void setUp() {
        ArrayList<Compartment> compartments = new ArrayList<>();

        ArrayList<SpecialQuality> qualities1 = new ArrayList<>();
        qualities1.add(SpecialQuality.FLAMMABLE);
        compartments.add(new Compartment("compartment1", 100.0, qualities1));

        ArrayList<SpecialQuality> qualities2 = new ArrayList<>();
        qualities2.add(SpecialQuality.PERISHABLE);
        compartments.add(new Compartment("compartment2", 50.0, qualities2));

        ArrayList<SpecialQuality> qualities3 = new ArrayList<>();
        qualities3.add(SpecialQuality.LIQUID);
        compartments.add(new Compartment("compartment3", 30.0, qualities3));

        this.inventory = new Inventory(compartments);
        this.history = new ChangeHistory();
        this.viewModel = new AddStockPageViewModel(this.inventory, this.history, "Test User");
    }

    @Test
    void testPrepareStockForInventoryWithRegularStock() {
        this.viewModel.getName().set("oil");
        this.viewModel.getQuantity().set("100");
        this.viewModel.getIsPerishable().set(false);
        this.viewModel.getIsLiquid().set(false);
        this.viewModel.getIsFlammable().set(true);
        this.viewModel.getSelectedCondition().set(Condition.USABLE);

        Stock stock = this.viewModel.prepareStockForInventory();

        assertNotNull(stock);
        assertTrue(stock instanceof Stock);
        assertEquals("oil", stock.getName());
        assertEquals(100.0, stock.getQuantity());
        assertEquals(Condition.USABLE, stock.getCondition());
        assertFalse(stock.getSpecialQualities().contains(SpecialQuality.LIQUID));
        assertTrue(stock.getSpecialQualities().contains(SpecialQuality.FLAMMABLE));
        assertFalse(stock.getSpecialQualities().contains(SpecialQuality.PERISHABLE));

        assertTrue(this.inventory.getCompartments().get(0).addStock(stock));
        assertEquals(0.0, this.inventory.getCompartments().get(0).getFreeSpace());
    }

    @Test
    void testPrepareStockForInventoryWithPerishableStock() {
        this.viewModel.getName().set("apple");
        this.viewModel.getQuantity().set("40");
        this.viewModel.getIsPerishable().set(true);
        this.viewModel.getIsLiquid().set(false);
        this.viewModel.getIsFlammable().set(false);
        this.viewModel.getYear().set("2025");
        this.viewModel.getMonth().set("11");
        this.viewModel.getDay().set("15");

        Stock stock = this.viewModel.prepareStockForInventory();

        assertNotNull(stock);
        assertTrue(stock instanceof PerishableStock);
        assertEquals("apple", stock.getName());
        assertEquals(40.0, stock.getQuantity());
        assertEquals(Condition.PERFECT, stock.getCondition());
        assertTrue(stock.getSpecialQualities().contains(SpecialQuality.PERISHABLE));
        assertEquals(LocalDate.of(2025, 11, 15), ((PerishableStock) stock).getExpirationDate());

        boolean added = this.inventory.getCompartments().get(1).addStock(stock);
        assertTrue(added);
        assertEquals(10.0, this.inventory.getCompartments().get(1).getFreeSpace());
    }

    @Test
    void testAddStockToCompartmentWithIncompatibleType() {
        this.viewModel.getName().set("fish");
        this.viewModel.getQuantity().set("20");
        this.viewModel.getIsPerishable().set(false);
        this.viewModel.getIsLiquid().set(true);
        this.viewModel.getIsFlammable().set(false);
        this.viewModel.getSelectedCondition().set(Condition.USABLE);

        Stock stock = this.viewModel.prepareStockForInventory();

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            this.inventory.getCompartments().get(1).addStock(stock);
        });

        assertEquals("Stock type not compatible with compartment type.", thrown.getMessage());
    }

    @Test
    void testPrepareStockForInventoryWithExcessiveQuantity() {
        this.viewModel.getName().set("water");
        this.viewModel.getQuantity().set("40");
        this.viewModel.getIsPerishable().set(false);
        this.viewModel.getIsLiquid().set(true);
        this.viewModel.getIsFlammable().set(false);
        this.viewModel.getSelectedCondition().set(Condition.PERFECT);

        Stock stock = this.viewModel.prepareStockForInventory();

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            this.inventory.getCompartments().get(2).addStock(stock);
        });

        assertEquals("Stock unable to be added, quantity is too large for remaining capacity.", thrown.getMessage());
    }
    
    

    @Test
    void testGetName() {
        assertNotNull(this.viewModel.getName());
    }

    @Test
    void testGetQuantity() {
        assertNotNull(this.viewModel.getQuantity());
    }

    @Test
    void testGetYear() {
        assertNotNull(this.viewModel.getYear());
    }

    @Test
    void testGetMonth() {
        assertNotNull(this.viewModel.getMonth());
    }

    @Test
    void testGetDay() {
        assertNotNull(this.viewModel.getDay());
    }

    @Test
    void testGetIsPerishable() {
        assertNotNull(this.viewModel.getIsPerishable());
    }

    @Test
    void testGetIsLiquid() {
        assertNotNull(this.viewModel.getIsLiquid());
    }

    @Test
    void testGetIsFlammable() {
        assertNotNull(this.viewModel.getIsFlammable());
    }

    @Test
    void testGetSelectedCondition() {
        assertNotNull(this.viewModel.getSelectedCondition());
    }

    @Test
    void testGetHistory() {
        assertNotNull(this.viewModel.getHistory());
    }

    @Test
    void testGetInventory() {
        assertNotNull(this.viewModel.getInventory());
    }

    @Test
    void testGetUser() {
        assertNotNull(this.viewModel.getUser());
    }
}
