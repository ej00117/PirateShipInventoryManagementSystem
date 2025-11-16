package edu.westga.cs3211.pirateshipinventorymanagementsystem.model.inventory;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import edu.westga.cs3211.pirateshipinventorymanagementsystem.enums.SpecialQuality;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.model.Compartment;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.model.Inventory;


class TestConstructor {

	@Test
	void testInventoryNullCompartments() {
		assertThrows(IllegalArgumentException.class, ()->{
			new Inventory(null);});
	}
	
	@Test
	void testInventoryDuplicateCompartmentNames() {
		ArrayList<Compartment> compartments = new ArrayList<Compartment>();
		ArrayList<SpecialQuality> qualities = new ArrayList<SpecialQuality>();
		qualities.add(SpecialQuality.LIQUID);
		compartments.add(new Compartment("compartment", 300.0, qualities));
		compartments.add(new Compartment("compartment", 300.0, qualities));
		
		assertThrows(IllegalArgumentException.class, ()->{
			new Inventory(compartments);});
	}
	
	@Test
	void testInventoryValidCompartments() {
		ArrayList<Compartment> compartments = new ArrayList<Compartment>();
		ArrayList<SpecialQuality> qualities = new ArrayList<SpecialQuality>();
		qualities.add(SpecialQuality.LIQUID);
		compartments.add(new Compartment("compartment", 300.0, qualities));
		Inventory inventory = new Inventory(compartments);
		
		assertTrue(inventory.getCompartments().size() > 0);
	}

}
