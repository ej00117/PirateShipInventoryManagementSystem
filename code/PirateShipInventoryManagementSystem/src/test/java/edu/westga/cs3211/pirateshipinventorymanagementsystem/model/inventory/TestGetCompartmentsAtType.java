package edu.westga.cs3211.pirateshipinventorymanagementsystem.model.inventory;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs3211.pirateshipinventorymanagementsystem.enums.SpecialQuality;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.model.Compartment;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.model.Inventory;

class TestGetCompartmentsAtType {

	private Inventory inventory;
	private ArrayList<Compartment> compartments;
	private ArrayList<SpecialQuality> qualities;
	
	@BeforeEach
	void setUp() {
		this.compartments = new ArrayList<Compartment>();
		this.qualities = new ArrayList<SpecialQuality>();
		this.qualities.add(SpecialQuality.LIQUID);
		this.compartments.add(new Compartment("compartment", 300.0, qualities));
		this.inventory = new Inventory(this.compartments);
	}
	
	@Test
	void testGetCompartmentAtTypeNullSpecialQualities() {
		assertThrows(IllegalArgumentException.class, ()->{
			this.inventory.getCompartmentsAtType(null, 30.0);});
	}
	
	@Test
	void testGetCompartmentAtTypeNullSpaceRequired() {
		assertThrows(IllegalArgumentException.class, ()->{
			this.inventory.getCompartmentsAtType(this.qualities, null);});
	}
	
	@Test
	void testGetCompartmentAtTypeWithSpaceRequiredBelowZero() {
	
		assertThrows(IllegalArgumentException.class, ()->{
			this.inventory.getCompartmentsAtType(this.qualities, -1.0);});
	}
	
	@Test
	void testGetCompartmentAtTypeButCompartmentSpecifiedIsNotInSystem() {
	
		assertThrows(IllegalArgumentException.class, ()->{
			this.inventory.getCompartmentsAtType(new ArrayList<SpecialQuality>(), 10.0);});
	}
	
	@Test
	void testGetCompartmentAtTypeButSpaceRequiredIsGreaterThanSpaceAvailable() {
	
		assertThrows(IllegalArgumentException.class, ()->{
			this.inventory.getCompartmentsAtType(this.qualities, 301.0);});
	}
	
	@Test
	void testGetCompartmentAtTypeWithtValidParametersAndInSystem() {
	
		ArrayList<Compartment> compartments = this.inventory.getCompartmentsAtType(this.qualities, 10.0);
		assertTrue(this.inventory.getCompartments().contains(compartments.getFirst()), "checking that compartment is in list");
	}

}
