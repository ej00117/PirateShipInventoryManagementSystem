package edu.westga.cs3211.pirateshipinventorymanagementsystem.model.compartment;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.enums.SpecialQuality;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.model.Compartment;



class TestConstructor {
	
	@Test
	void testCompartmentNullName() {
		
		assertThrows(IllegalArgumentException.class, ()->{
			new Compartment(null, 300.0, new ArrayList<SpecialQuality>());});
	}
	
	@Test
	void testCompartmentNullCapacity() {
		
		assertThrows(IllegalArgumentException.class, ()->{
			new Compartment("compartment", null, new ArrayList<SpecialQuality>());});
	}
	
	@Test
	void testCompartmentCapacityBelowZero() {
		
		assertThrows(IllegalArgumentException.class, ()->{
			new Compartment("compartment", -1.0, new ArrayList<SpecialQuality>());});
	}
	
	@Test
	void testCompartmentNullType() {
		
		assertThrows(IllegalArgumentException.class, ()->{
			new Compartment("compartment", 300.0, null);});
	}
	
	@Test
	void testCompartmentValidParameters() {
		ArrayList<SpecialQuality> qualities = new ArrayList<SpecialQuality>();
		qualities.add(SpecialQuality.LIQUID);
		Compartment compartment = new Compartment("compartment", 300.0, qualities);
		assertEquals(compartment.getName(), "compartment", "checking name");
		assertEquals(compartment.getFreeSpace(), 300.0, "checking capacity");
		assertTrue(compartment.getType().contains(SpecialQuality.LIQUID), "checking type");
	}

}
