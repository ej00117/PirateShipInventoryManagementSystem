package edu.westga.cs3211.pirateshipinventorymanagementsystem.model.compartment;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.enums.SpecialQuality;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.model.Compartment;



class TestConstructor {

	@Test
	void testCompartmentNullCapacity() {
		
		assertThrows(IllegalArgumentException.class, ()->{
			new Compartment(null, new ArrayList<SpecialQuality>());});
	}
	
	@Test
	void testCompartmentCapacityBelowZero() {
		
		assertThrows(IllegalArgumentException.class, ()->{
			new Compartment(-1.0, new ArrayList<SpecialQuality>());});
	}
	
	@Test
	void testCompartmentNullType() {
		
		assertThrows(IllegalArgumentException.class, ()->{
			new Compartment(300.0, null);});
	}
	
	@Test
	void testCompartmentValidParameters() {
		ArrayList<SpecialQuality> qualities = new ArrayList<SpecialQuality>();
		qualities.add(SpecialQuality.LIQUID);
		Compartment compartment = new Compartment(300.0, qualities);
		assertEquals(compartment.getFreeSpace(), 300.0, "checking capacity");
		assertTrue(compartment.getType().contains(SpecialQuality.LIQUID), "checking type");
	}

}
