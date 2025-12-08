package edu.westga.cs3211.pirateshipinventorymanagementsystem.model.stock;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import edu.westga.cs3211.pirateshipinventorymanagementsystem.enums.Condition;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.enums.ItemCategory;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.enums.SpecialQuality;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.model.Stock;


class TestConstructor {

	@Test
	void testNullStockName() {
		assertThrows(IllegalArgumentException.class, ()->{new Stock(null, 2.0, ItemCategory.OTHER, Condition.PERFECT, new ArrayList<SpecialQuality>());});
	}
	
	@Test
	void testNullStockQuantity() {
		assertThrows(IllegalArgumentException.class, ()->{new Stock("gold", null, ItemCategory.OTHER, Condition.PERFECT, new ArrayList<SpecialQuality>());});
	}
	
	@Test
	void testQuantityAtOrBelowZero() {
		assertThrows(IllegalArgumentException.class, ()->{new Stock("gold", 0.0, ItemCategory.OTHER, Condition.PERFECT, new ArrayList<SpecialQuality>());});
	}
	
	@Test
	void testNullItemCategory() {
		assertThrows(IllegalArgumentException.class, ()->{new Stock("gold", 2.0, null, Condition.PERFECT, new ArrayList<SpecialQuality>());});
	}
	
	@Test
	void testNullStockCondition() {
		assertThrows(IllegalArgumentException.class, ()->{new Stock("gold", 2.0, ItemCategory.OTHER, null, new ArrayList<SpecialQuality>());});
	}

	@Test
	void testNullStockSpecialQualities() {
		assertThrows(IllegalArgumentException.class, ()->{new Stock("gold", 2.0, ItemCategory.OTHER, Condition.PERFECT, null);});
	}
	
	@Test
	void testGiveRegularStockPerishableQuality() {
		ArrayList<SpecialQuality> qualities = new ArrayList<SpecialQuality>();
		qualities.add(SpecialQuality.PERISHABLE);
		assertThrows(IllegalArgumentException.class, ()->{new Stock("gold", 2.0, ItemCategory.OTHER, Condition.PERFECT, qualities);});
	}
	
	@Test
	void testCreateValidStock() {
		ArrayList<SpecialQuality> qualities = new ArrayList<SpecialQuality>();
		qualities.add(SpecialQuality.LIQUID);
		Stock stock = new Stock("mercury", 2.0, ItemCategory.OTHER, Condition.PERFECT, qualities);
		
		assertEquals(stock.getName(), "mercury", "checking the name");
		assertEquals(stock.getQuantity(), 2.0, "checking the quantity");
		assertEquals(stock.getCategory(), ItemCategory.OTHER, "checking the category");
		assertEquals(stock.getExpirationDate(), null, "checking the expirationDate");
		assertEquals(stock.getCondition(), Condition.PERFECT, "checking the condition");
		assertTrue(stock.getSpecialQualities().contains(SpecialQuality.LIQUID), "checking special quality");
	}
}
