package edu.westga.cs3211.pirateshipinventorymanagementsystem.model.perishablestock;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import edu.westga.cs3211.pirateshipinventorymanagementsystem.enums.Condition;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.enums.ItemCategory;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.enums.SpecialQuality;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.model.PerishableStock;

class TestToString {

	@Test
	void testToStringWithSpecialQuality() {
		ArrayList<SpecialQuality> qualities = new ArrayList<SpecialQuality>();
		qualities.add(SpecialQuality.PERISHABLE);
		PerishableStock stock = new PerishableStock("apple", 1.0, ItemCategory.FOOD, Condition.USABLE, qualities, LocalDate.of(2025, 12, 24));
		
		String text = "Name: apple, Quantity: 1.0, Category: FOOD, Condition: USABLE, Special Qualities: [PERISHABLE], Expiration Date: 2025-12-24";
		
				
		assertEquals(stock.toString(), text, "checking toString");
	}

}
