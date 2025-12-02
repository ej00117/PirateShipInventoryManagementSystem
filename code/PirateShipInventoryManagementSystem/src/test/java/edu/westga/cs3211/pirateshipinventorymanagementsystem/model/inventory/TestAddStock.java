package edu.westga.cs3211.pirateshipinventorymanagementsystem.model.inventory;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import edu.westga.cs3211.pirateshipinventorymanagementsystem.enums.Condition;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.enums.ItemCategory;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.enums.SpecialQuality;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.model.Compartment;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.model.Inventory;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.model.Stock;

class TestAddStock {

	@Test
	void testAddStockWithNullCompartmentName() {
		ArrayList<Compartment> compartments = new ArrayList<Compartment>();
		ArrayList<SpecialQuality> qualities = new ArrayList<SpecialQuality>();
		qualities.add(SpecialQuality.LIQUID);
		compartments.add(new Compartment("compartment", 300.0, qualities));
		Inventory inventory = new Inventory(compartments);
		Stock stock = new Stock("mercury", 2.0, ItemCategory.OTHER, Condition.PERFECT, qualities);
		
		assertThrows(IllegalArgumentException.class, ()->{
			inventory.addStock(null, stock);});
	}
	
	@Test
	void testAddStockWithNullStock() {
		ArrayList<Compartment> compartments = new ArrayList<Compartment>();
		ArrayList<SpecialQuality> qualities = new ArrayList<SpecialQuality>();
		qualities.add(SpecialQuality.LIQUID);
		compartments.add(new Compartment("compartment", 300.0, qualities));
		Inventory inventory = new Inventory(compartments);
		
		assertThrows(IllegalArgumentException.class, ()->{
			inventory.addStock("compartment", null);});
	}
	
	@Test
	void testAddStockValidParameters() {
		ArrayList<Compartment> compartments = new ArrayList<Compartment>();
		ArrayList<SpecialQuality> qualities = new ArrayList<SpecialQuality>();
		qualities.add(SpecialQuality.LIQUID);
		compartments.add(new Compartment("compartment", 300.0, qualities));
		Inventory inventory = new Inventory(compartments);
		Stock stock = new Stock("mercury", 2.0, ItemCategory.OTHER, Condition.PERFECT, qualities);
		
		inventory.addStock("compartment", stock);
		assertTrue(inventory.getCompartments().getFirst().getItems().contains(stock));
	}
	
	@Test
	void testAddStockValidParametersButCompartmentNotFound() {
		ArrayList<Compartment> compartments = new ArrayList<Compartment>();
		ArrayList<SpecialQuality> qualities = new ArrayList<SpecialQuality>();
		qualities.add(SpecialQuality.LIQUID);
		compartments.add(new Compartment("compartment", 300.0, qualities));
		Inventory inventory = new Inventory(compartments);
		Stock stock = new Stock("mercury", 2.0, ItemCategory.OTHER, Condition.PERFECT, qualities);
		
		inventory.addStock("compartment2", stock);
		assertTrue(!inventory.getCompartments().getFirst().getItems().contains(stock));
	}

}
