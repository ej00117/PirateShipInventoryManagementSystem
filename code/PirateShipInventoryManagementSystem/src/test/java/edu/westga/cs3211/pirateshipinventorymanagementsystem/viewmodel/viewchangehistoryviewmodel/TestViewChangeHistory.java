package edu.westga.cs3211.pirateshipinventorymanagementsystem.viewmodel.viewchangehistoryviewmodel;

import edu.westga.cs3211.pirateshipinventorymanagementsystem.model.ChangeHistory;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.model.ChangeLog;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.model.Stock;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.model.PerishableStock;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.viewmodel.ViewChangeHistoryViewModel;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.enums.Change;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.enums.SpecialQuality;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.enums.Condition;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.enums.ItemCategory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

public class TestViewChangeHistory {

    private ViewChangeHistoryViewModel viewModel;
    private ChangeHistory history;
    private ChangeLog log1;
    private ChangeLog log2;
    private ChangeLog log3;
    private Stock regularStock;
    private PerishableStock perishableStock;

    @BeforeEach
    void setUp() {
        this.regularStock = new Stock("cannonballs", 50.0, ItemCategory.OTHER, Condition.USABLE, new ArrayList<SpecialQuality>());
        this.log1 = new ChangeLog("user1", regularStock, Change.ADDED, "compartment1", 100.0, LocalDateTime.now());
        this.log2 = new ChangeLog("user2", regularStock, Change.REMOVED, "compartment2", 50.0, LocalDateTime.now());
        this.perishableStock = new PerishableStock("spoiled food", 30.0, ItemCategory.FOOD, Condition.USABLE, 
            new ArrayList<SpecialQuality>(Collections.singletonList(SpecialQuality.PERISHABLE)), LocalDateTime.now().toLocalDate());
        this.log3 = new ChangeLog("user3", perishableStock, Change.ADDED, "compartment3", 30.0, LocalDateTime.now());

        this.history = new ChangeHistory();
        this.history.getHistory().add(log1);
        this.history.getHistory().add(log2);
        this.history.getHistory().add(log3);

        this.viewModel = new ViewChangeHistoryViewModel(history);
        this.viewModel.getSelectedUser().set("user1");
    }

    @Test
    void testConstructor() {
        assertNotNull(this.viewModel.getLogs());
        assertEquals(3, this.viewModel.getLogs().size());
        assertTrue(this.viewModel.getUsers().contains("user1"));
        assertTrue(this.viewModel.getUsers().contains("user2"));
        assertTrue(this.viewModel.getUsers().contains("user3"));
    }

    @Test
    void testSortListBasedOnSpecialQualities() {
        this.viewModel.getIsPerishable().set(true);
        this.viewModel.getIsLiquid().set(false);
        this.viewModel.getIsFlammable().set(false);
        this.viewModel.sortListBasedOnSpecialQualities();
        assertEquals(1, this.viewModel.getLogs().size());
        assertTrue(this.viewModel.getLogs().contains(log3));

        this.viewModel.getIsPerishable().set(false);
        this.viewModel.sortListBasedOnSpecialQualities();
        assertEquals(3, this.viewModel.getLogs().size());
        assertTrue(this.viewModel.getLogs().contains(log1));
        assertTrue(this.viewModel.getLogs().contains(log2));
    }

    @Test
    void testSortListBasedOnUser() {
        this.viewModel.getSelectedUser().set("user1");
        this.viewModel.sortListBasedOnUser();
        assertEquals(1, this.viewModel.getLogs().size());
        assertTrue(this.viewModel.getLogs().contains(log1));

        
    }


    @Test
    void testResetSelectedUsers() {
        this.viewModel.getSelectedUser().set("user1");
        this.viewModel.sortListBasedOnUser();
        assertEquals(1, this.viewModel.getLogs().size());

        this.viewModel.resetSelectedUsers();
        assertNull(this.viewModel.getSelectedUser().get());
        assertEquals(3, this.viewModel.getLogs().size());
    }

    @Test
    void testGetters() {
        assertNotNull(this.viewModel.getLogs());
        assertNotNull(this.viewModel.getIsPerishable());
        assertNotNull(this.viewModel.getIsLiquid());
        assertNotNull(this.viewModel.getIsFlammable());
        assertNotNull(this.viewModel.getSelectedUser());
        assertNotNull(this.viewModel.getUsers());
    }
}
