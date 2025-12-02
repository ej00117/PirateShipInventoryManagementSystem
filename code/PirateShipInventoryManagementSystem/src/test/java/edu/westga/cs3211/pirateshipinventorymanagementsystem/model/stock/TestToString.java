package edu.westga.cs3211.pirateshipinventorymanagementsystem.model.stock;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import edu.westga.cs3211.pirateshipinventorymanagementsystem.enums.Condition;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.enums.ItemCategory;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.enums.SpecialQuality;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.model.Stock;

class TestToString {

	@Test
	void testToStringWithSpecialQuality() {
		ArrayList<SpecialQuality> qualities = new ArrayList<SpecialQuality>();
		qualities.add(SpecialQuality.LIQUID);
		Stock stock = new Stock("mercury", 2.0, ItemCategory.OTHER, Condition.PERFECT, qualities);
		
		String text = "Name: mercury, Quantity: 2.0, Category: OTHER, Condition: PERFECT, Special Qualities: [LIQUID]";
		
				
		assertEquals(stock.toString(), text, "checking toString");
	}
	
	@Test
	void testToStringWithoutSpecialQuality() {
		Stock stock = new Stock("mercury", 2.0, ItemCategory.OTHER, Condition.PERFECT, new ArrayList<SpecialQuality>());
		
		String text = "Name: mercury, Quantity: 2.0, Category: OTHER, Condition: PERFECT, Special Qualities: NONE";
		
				
		assertEquals(stock.toString(), text, "checking toString");
	}

}
