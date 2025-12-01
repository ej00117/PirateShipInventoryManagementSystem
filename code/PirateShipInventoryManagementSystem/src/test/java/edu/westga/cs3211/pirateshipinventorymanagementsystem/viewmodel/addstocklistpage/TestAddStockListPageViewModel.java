package edu.westga.cs3211.pirateshipinventorymanagementsystem.viewmodel.addstocklistpage;

import edu.westga.cs3211.pirateshipinventorymanagementsystem.model.ChangeHistory;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.model.ChangeLog;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.model.Compartment;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.model.Stock;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.viewmodel.AddStockListPageViewModel;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.enums.Change;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.enums.SpecialQuality;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.enums.Condition;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.enums.ItemCategory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TestAddStockListPageViewModel {

    private AddStockListPageViewModel viewModel;
    private ChangeHistory history;
    private Stock stock;
    private String user;

    private Compartment compartment1;
    private Compartment compartment2;

    @BeforeEach
    void setUp() {
        this.history = new ChangeHistory();
        this.user = "Test User";

        ArrayList<SpecialQuality> compartment1Qualities = new ArrayList<SpecialQuality>();
        compartment1Qualities.add(SpecialQuality.FLAMMABLE);
        compartment1 = new Compartment("compartment1", 100.0, compartment1Qualities);

        ArrayList<SpecialQuality> compartment2Qualities = new ArrayList<SpecialQuality>();
        compartment2Qualities.add(SpecialQuality.LIQUID);
        compartment2 = new Compartment("compartment2", 200.0, compartment2Qualities);

        ArrayList<Compartment> compartments = new ArrayList<Compartment>();
        compartments.add(compartment1);
        compartments.add(compartment2);

        ArrayList<SpecialQuality> stockQualities = new ArrayList<>();
        stockQualities.add(SpecialQuality.FLAMMABLE);
        this.stock = new Stock("cannonballs", 50.0, ItemCategory.OTHER, Condition.USABLE, stockQualities);

        this.viewModel = new AddStockListPageViewModel(compartments, this.history, this.stock, this.user);
    }

    @Test
    void testConstructor() {
        assertNotNull(this.viewModel.getCompartments());
        assertEquals(2, this.viewModel.getCompartments().size());
        assertEquals(compartment1, this.viewModel.getSelectedCompartment().get());
        assertEquals("cannonballs", this.viewModel.getStockToAdd().getName());
        assertEquals(this.user, this.viewModel.getUser());
    }

    @Test
    void testAddStock() {
        assertEquals(100.0, compartment1.getFreeSpace());

        this.viewModel.addStock();

        assertTrue(compartment1.getItems().contains(this.stock));

        assertEquals(50.0, compartment1.getFreeSpace());

        assertEquals(1, this.history.getHistory().size());
        ChangeLog changeLog = this.history.getHistory().get(0);
        assertEquals(Change.ADDED, changeLog.getChange());
        assertEquals(this.stock, changeLog.getStockInfo());
        assertEquals("Test User", changeLog.getUserName());
        assertEquals("compartment1", changeLog.getCompartmentName());
    }

    @Test
    void testGetSelectedCompartment() {
        assertEquals(compartment1, this.viewModel.getSelectedCompartment().get());
    }

    @Test
    void testGetCompartments() {
        assertEquals(2, this.viewModel.getCompartments().size());
        assertTrue(this.viewModel.getCompartments().contains(compartment1));
        assertTrue(this.viewModel.getCompartments().contains(compartment2));
    }

    @Test
    void testSelectedCompartmentChange() {
        this.viewModel.getSelectedCompartment().set(compartment2);
        assertEquals(compartment2, this.viewModel.getSelectedCompartment().get());
    }
}
