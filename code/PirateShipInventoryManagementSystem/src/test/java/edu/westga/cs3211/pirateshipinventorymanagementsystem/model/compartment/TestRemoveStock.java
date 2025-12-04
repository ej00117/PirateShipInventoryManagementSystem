package edu.westga.cs3211.pirateshipinventorymanagementsystem.model.compartment;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import edu.westga.cs3211.pirateshipinventorymanagementsystem.enums.SpecialQuality;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.model.Compartment;

class TestRemoveStock {

	@Test
	void test() {
		ArrayList<SpecialQuality> qualities = new ArrayList<SpecialQuality>();
		qualities.add(SpecialQuality.LIQUID);
		Compartment compartment = new Compartment("compartment", 300.0, qualities);
		assertThrows(IllegalArgumentException.class, () -> compartment.removeStock(null));
	}

}
