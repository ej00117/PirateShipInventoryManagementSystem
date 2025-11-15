package edu.westga.cs3211.pirateshipinventorymanagementsystem.model.perishablestock;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import edu.westga.cs3211.pirateshipinventorymanagementsystem.enums.Condition;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.enums.SpecialQuality;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.model.PerishableStock;

class TestConstructor {

	@Test
	void testNullPerishableStockLocalDate() {
		ArrayList<SpecialQuality> qualities = new ArrayList<SpecialQuality>();
		qualities.add(SpecialQuality.PERISHABLE);
		assertThrows(IllegalArgumentException.class, ()->{
			new PerishableStock("apple", 2.0, Condition.USABLE, qualities, null);});
	}
	
	@Test
	void testCreatePerishableStockWithoutAddingPerishableQuality() {
		assertThrows(IllegalArgumentException.class, ()->{
			new PerishableStock("apple", 2.0, Condition.USABLE, new ArrayList<SpecialQuality>(), LocalDate.now());});
	}

	@Test
	void testCreateValidPerishableStock() {
		ArrayList<SpecialQuality> qualities = new ArrayList<SpecialQuality>();
		qualities.add(SpecialQuality.PERISHABLE);
		PerishableStock stock = new PerishableStock("apple", 1.0, Condition.USABLE, qualities, LocalDate.of(2025, 12, 24));
		
		assertEquals(stock.getName(), "apple", "checking the name");
		assertEquals(stock.getQuantity(), 1.0, "checking the quantity");
		assertEquals(stock.getCondition(), Condition.USABLE, "checking the condition");
		assertTrue(stock.getSpecialQualities().contains(SpecialQuality.PERISHABLE), "checking special quality");
		assertEquals(stock.getExpirationDate(), LocalDate.of(2025, 12, 24), "checking expiration date");
	}
}
