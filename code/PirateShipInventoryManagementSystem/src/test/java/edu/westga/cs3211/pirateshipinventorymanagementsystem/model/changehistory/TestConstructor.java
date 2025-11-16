package edu.westga.cs3211.pirateshipinventorymanagementsystem.model.changehistory;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import edu.westga.cs3211.pirateshipinventorymanagementsystem.model.ChangeHistory;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.model.ChangeLog;

class TestConstructor {

	@Test
	void testConstructor() {
		ChangeHistory history = new ChangeHistory();
		
		assertEquals(history.getHistory(), new ArrayList<ChangeLog>());
	}

}
