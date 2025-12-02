package edu.westga.cs3211.pirateshipinventorymanagementsystem.model.changelog;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.Assert.assertThrows;
import java.time.LocalDateTime;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.enums.Change;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.enums.Condition;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.enums.ItemCategory;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.enums.SpecialQuality;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.model.ChangeLog;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.model.Stock;

class TestConstructor {
	
	private Stock stock;
	
	@BeforeEach
	void setup() {
		this.stock = new Stock("gold", 2.0, ItemCategory.OTHER, Condition.PERFECT, new ArrayList<SpecialQuality>());
	}

	@Test
	void testChangeLogNullUser() {
		assertThrows(IllegalArgumentException.class, ()->{
			new ChangeLog(null, this.stock, Change.ADDED, "compartment", 250.0, LocalDateTime.now());});
	}
	
	@Test
	void testChangeLogNullStock() {
		assertThrows(IllegalArgumentException.class, ()->{
			new ChangeLog("user", null, Change.ADDED, "compartment", 250.0, LocalDateTime.now());});
	}
	
	@Test
	void testChangeLogNullChangeType() {
		assertThrows(IllegalArgumentException.class, ()->{
			new ChangeLog("user", this.stock, null, "compartment", 250.0, LocalDateTime.now());});
	}
	
	@Test
	void testChangeLogNullCompartmentName() {
		assertThrows(IllegalArgumentException.class, ()->{
			new ChangeLog("user", this.stock, Change.ADDED, null, 250.0, LocalDateTime.now());});
	}
	
	@Test
	void testChangeLogNullCapacityRemaining() {
		assertThrows(IllegalArgumentException.class, ()->{
			new ChangeLog("user", this.stock, Change.ADDED, "compartment", null, LocalDateTime.now());});
	}
	
	@Test
	void testChangeLogNullDateTime() {
		assertThrows(IllegalArgumentException.class, ()->{
			new ChangeLog("user", this.stock, Change.ADDED, "compartment", 250.0, null);});
	}
	
	@Test
	void testValidChangeLog() {
		ChangeLog log = new ChangeLog("user", this.stock, Change.ADDED, "compartment", 250.0, LocalDateTime.of(2025, 12, 24, 14, 30));
		
		assertEquals(log.getUserName(), "user", "checking the username");
		assertEquals(log.getStockInfo(), this.stock, "checking the stock info");
		assertEquals(log.getChange(), Change.ADDED, "checking the change type");
		assertEquals(log.getCompartmentName(), "compartment", "checking the compartment name");
		assertEquals(log.getCapacityRemaining(), 250.0, "checking the capacity remaining");
		assertEquals(log.getTimeOfChange(), LocalDateTime.of(2025, 12, 24, 14, 30), "checking the time of change");
		assertTrue(log.toString().length() > 0);
	}
}
