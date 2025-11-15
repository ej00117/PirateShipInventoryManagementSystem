package edu.westga.cs3211.pirateshipinventorymanagementsystem.model.compartment;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import edu.westga.cs3211.pirateshipinventorymanagementsystem.enums.Condition;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.enums.SpecialQuality;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.model.Compartment;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.model.PerishableStock;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.model.Stock;

class TestAddStock {
	
	@Test
	void testAddNullStockItem() {
		ArrayList<SpecialQuality> qualities = new ArrayList<SpecialQuality>();
		qualities.add(SpecialQuality.LIQUID);
		Compartment compartment = new Compartment(300.0, qualities);
		
		assertThrows(IllegalArgumentException.class, ()->{
			compartment.addStock(null);});
	}
	
	@Test
	void testAddStockItemWithIncorrectType() {
		ArrayList<SpecialQuality> qualities = new ArrayList<SpecialQuality>();
		qualities.add(SpecialQuality.LIQUID);
		Compartment compartment = new Compartment(300.0, qualities);
		
		ArrayList<SpecialQuality> qualities2 = new ArrayList<SpecialQuality>();
		qualities2.add(SpecialQuality.PERISHABLE);
		PerishableStock stock = new PerishableStock("apple", 1.0, Condition.USABLE, qualities2, LocalDate.of(2025, 12, 24));
		assertThrows(IllegalArgumentException.class, ()->{
			compartment.addStock(stock);});
	}
	
	@Test
	void testAddStockItemWithCorrectTypeButThereIsNotSpace() {
		ArrayList<SpecialQuality> qualities = new ArrayList<SpecialQuality>();
		qualities.add(SpecialQuality.LIQUID);
		Compartment compartment = new Compartment(5.0, qualities);
		
		Stock stock = new Stock("gold", 10.0, Condition.USABLE, qualities);
		assertThrows(IllegalArgumentException.class, ()->{
			compartment.addStock(stock);});
	}
	
	@Test
	void testAddStockItemWithCorrectType() {
		ArrayList<SpecialQuality> qualities = new ArrayList<SpecialQuality>();
		qualities.add(SpecialQuality.LIQUID);
		Compartment compartment = new Compartment(300.0, qualities);
		
		Stock stock = new Stock("gold", 1.0, Condition.USABLE, qualities);
		
		assertTrue(compartment.addStock(stock));
		assertTrue(compartment.getItems().contains(stock));
	}
}
