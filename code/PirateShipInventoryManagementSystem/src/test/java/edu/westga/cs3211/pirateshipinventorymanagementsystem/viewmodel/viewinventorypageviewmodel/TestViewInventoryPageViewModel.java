package edu.westga.cs3211.pirateshipinventorymanagementsystem.viewmodel.viewinventorypageviewmodel;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import edu.westga.cs3211.pirateshipinventorymanagementsystem.enums.Condition;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.enums.ItemCategory;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.enums.SpecialQuality;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.model.ChangeHistory;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.model.Compartment;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.model.Inventory;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.model.Stock;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.viewmodel.ViewInventoryPageViewModel;
import javafx.collections.FXCollections;

class TestViewInventoryPageViewModel {

	@Test
	void testViewIventoryPageViewModelConstructor() {
		ArrayList<Compartment> compartments = new ArrayList<>();

        ArrayList<SpecialQuality> qualities1 = new ArrayList<>();
        qualities1.add(SpecialQuality.FLAMMABLE);
        Compartment compartment = new Compartment("compartment1", 100.0, qualities1);
        Stock stock = new Stock("mercury", 2.0, ItemCategory.OTHER, Condition.PERFECT, qualities1);
        compartment.addStock(stock);
        compartments.add(compartment);

        ArrayList<SpecialQuality> qualities2 = new ArrayList<>();
        qualities2.add(SpecialQuality.PERISHABLE);
        compartments.add(new Compartment("compartment2", 50.0, qualities2));

        ArrayList<SpecialQuality> qualities3 = new ArrayList<>();
        qualities3.add(SpecialQuality.LIQUID);
        compartments.add(new Compartment("compartment3", 30.0, qualities3));

        Inventory inventory = new Inventory(compartments);
        ChangeHistory history = new ChangeHistory();
		ViewInventoryPageViewModel vm = new ViewInventoryPageViewModel(inventory, history, "stanley", "secretpass");
		assertAll("Valid Inventory Returns",
				() -> assertEquals(vm.getCompartments(), FXCollections.observableArrayList(compartments), "Compartments should be set."),
				() -> assertEquals(vm.getStock(compartment), compartment.getItems(), "Compartment with items should return items."),
				() -> assertEquals(vm.getStock(null), FXCollections.observableArrayList(), "Compartment without items should return empty list.")
				);
	}
	
	@Test
	void testViewInventoryGetStockCookFiltering() {
		ArrayList<Compartment> compartments = new ArrayList<>();

        ArrayList<SpecialQuality> qualities1 = new ArrayList<>();
        qualities1.add(SpecialQuality.FLAMMABLE);
        Compartment compartment = new Compartment("compartment1", 100.0, qualities1);
        Stock stock = new Stock("mercury", 2.0, ItemCategory.OTHER, Condition.PERFECT, qualities1);
        Stock stock2 = new Stock("Flammable Apples", 2.0, ItemCategory.FOOD, Condition.PERFECT, qualities1);
        compartment.addStock(stock);
        compartment.addStock(stock2);
        compartments.add(compartment);

        ArrayList<SpecialQuality> qualities2 = new ArrayList<>();
        qualities2.add(SpecialQuality.PERISHABLE);
        compartments.add(new Compartment("compartment2", 50.0, qualities2));

        ArrayList<SpecialQuality> qualities3 = new ArrayList<>();
        qualities3.add(SpecialQuality.LIQUID);
        compartments.add(new Compartment("compartment3", 30.0, qualities3));

        Inventory inventory = new Inventory(compartments);
        ChangeHistory history = new ChangeHistory();
		ViewInventoryPageViewModel vm = new ViewInventoryPageViewModel(inventory, history, "john", "cook1234");
		assertEquals(vm.getStock(compartment).size(), 1, "Get Stock as a Cook Should only Return Food Items.");
	}
	
	@Test
	void testViewInventoryRemoveStock() {
		ArrayList<Compartment> compartments = new ArrayList<>();
		ArrayList<SpecialQuality> qualities1 = new ArrayList<>();
        qualities1.add(SpecialQuality.FLAMMABLE);
        Compartment compartment = new Compartment("compartment1", 100.0, qualities1);
        Stock stock = new Stock("mercury", 2.0, ItemCategory.OTHER, Condition.PERFECT, qualities1);
        Stock stock2 = new Stock("Flammable Apples", 2.0, ItemCategory.FOOD, Condition.PERFECT, qualities1);
        compartment.addStock(stock);
        compartment.addStock(stock2);
        compartments.add(compartment);
        
        Inventory inventory = new Inventory(compartments);
        ChangeHistory history = new ChangeHistory();
		ViewInventoryPageViewModel vm = new ViewInventoryPageViewModel(inventory, history, "stanley", "secretpass");
		vm.removeStock(stock, compartment, 1);
		vm.removeStock(stock2, compartment, 2);
		assertAll("Valid Inventory Returns",
				() -> assertEquals(stock.getQuantity(), 1.0 ,"Stock should update with removed quantity."),
				() -> assertFalse(compartment.getItems().contains(stock2), "Stock should delete if all quantity is removed."),
				() -> assertThrows(IllegalArgumentException.class, () -> {
					vm.removeStock(stock, compartment, 3000);
					}),
				() -> assertThrows(IllegalArgumentException.class, () -> {
					vm.removeStock(stock, compartment, 30);
					})
				);
	}

}
