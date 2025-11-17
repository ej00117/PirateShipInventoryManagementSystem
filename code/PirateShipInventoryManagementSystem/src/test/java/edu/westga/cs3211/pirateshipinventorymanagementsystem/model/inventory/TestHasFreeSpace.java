package edu.westga.cs3211.pirateshipinventorymanagementsystem.model.inventory;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs3211.pirateshipinventorymanagementsystem.enums.Condition;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.enums.SpecialQuality;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.model.Compartment;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.model.Inventory;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.model.Stock;

class TestHasFreeSpace {
	
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
	void testHasFreeSpaceNullType() {
		assertThrows(IllegalArgumentException.class, ()->{
			inventory.hasFreeSpace(null);});
	}
	
	@Test
	void testHasFreeSpaceButThereIsNoFreeSpace() {
		Stock stock = new Stock("water", 300.0, Condition.PERFECT, this.qualities);
		this.inventory.getCompartmentsAtType(this.qualities, 300.0).getFirst().addStock(stock);
		
		assertTrue(!inventory.hasFreeSpace(this.qualities));
	}
	
	@Test
	void testHasFreeSpaceButTheCompartmentSearchedForIsNotInTheSystem() {
		assertTrue(!inventory.hasFreeSpace(new ArrayList<SpecialQuality>()));
	}
	
	@Test
	void testHasFreeSpaceWhenThereIsFreeSpace() {
		assertTrue(inventory.hasFreeSpace(this.qualities));
	}
}
