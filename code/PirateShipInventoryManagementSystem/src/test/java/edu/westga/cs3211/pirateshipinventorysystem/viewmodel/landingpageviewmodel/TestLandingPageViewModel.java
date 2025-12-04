package edu.westga.cs3211.pirateshipinventorysystem.viewmodel.landingpageviewmodel;

import edu.westga.cs3211.pirateshipinventorymanagementsystem.model.Authenticator;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.viewmodel.LandingPageViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestLandingPageViewModel {

    private Authenticator authenticator;
    private LandingPageViewModel viewModel;

    @BeforeEach
    void setUp() {
        this.authenticator = new Authenticator();
    }

    @Test
    void testConstructorIfAuthenticatorIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new LandingPageViewModel(null, "bob", "pass1234"));
    }

    @Test
    void testConstructorIfNameIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new LandingPageViewModel(authenticator, null, "pass1234"));
    }

    @Test
    void testConstructorIfPasswordIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new LandingPageViewModel(authenticator, "bob", null));
    }

    @Test
    void testValidConstructor() {
        viewModel = new LandingPageViewModel(authenticator, "bob", "pass1234");

        assertEquals("bob", viewModel.getName());
        assertEquals("pass1234", viewModel.getPassword());
        assertNotNull(viewModel.getYourNameProperty());
        assertNotNull(viewModel.getYourRolesProperty());
    }

    @Test
    void testSetLandingPageUserDetails() {
        viewModel = new LandingPageViewModel(authenticator, "bob", "pass1234");
        viewModel.setLandingPageUserDetails();

        assertEquals("Hello bob.", viewModel.getYourNameProperty().getValue());
        assertEquals("Your roles: [CREWMATE]", viewModel.getYourRolesProperty().getValue());
    }

    @Test
    void testCheckIfCrewmateTrue() {
        viewModel = new LandingPageViewModel(authenticator, "bob", "pass1234");
        assertTrue(viewModel.checkIfCrewmate());
    }

    @Test
    void testCheckIfQuartermasterTrue() {
        viewModel = new LandingPageViewModel(authenticator, "stanley", "secretpass");
        assertTrue(viewModel.checkIfQuartermaster());
    }
    
    @Test
    void testCheckIfCookTrue() {
    	viewModel = new LandingPageViewModel(authenticator, "john", "cook1234");
    	assertTrue(viewModel.checkIfCook());
    }
    
    @Test
    void testCheckIfCookFalse() {
    	viewModel = new LandingPageViewModel(authenticator, "bob", "pass1234");
    	assertFalse(viewModel.checkIfCook());
    }
    
    @Test
    void testCheckIfOfficerTrue() {
    	viewModel = new LandingPageViewModel(authenticator, "bill", "officer123");
    	assertTrue(viewModel.checkIfOfficer());
    }
    
    @Test
    void testCheckIfOfficerFalse() {
    	viewModel = new LandingPageViewModel(authenticator, "bob", "pass1234");
    	assertFalse(viewModel.checkIfOfficer());
    }

    @Test
    void testCheckIfQuartermasterFalse() {
        viewModel = new LandingPageViewModel(authenticator, "bob", "pass1234");
        assertFalse(viewModel.checkIfQuartermaster());
    }

    @Test
    void testAddStockTrue() {
        viewModel = new LandingPageViewModel(authenticator, "bob", "pass1234");
        assertTrue(viewModel.addStock());
    }

    @Test
    void testViewHistoryShouldReturnTrueForQuartermaster() {
        viewModel = new LandingPageViewModel(authenticator, "stanley", "secretpass");
        assertTrue(viewModel.viewHistory());
    }

    @Test
    void testViewHistoryShouldReturnFalseForNonQuartermaster() {
        viewModel = new LandingPageViewModel(authenticator, "bob", "pass1234");
        assertFalse(viewModel.viewHistory());
    }

    @Test
    void testGetName() {
        viewModel = new LandingPageViewModel(authenticator, "bob", "pass1234");
        assertEquals("bob", viewModel.getName());
    }

    @Test
    void testGetPassword() {
        viewModel = new LandingPageViewModel(authenticator, "bob", "pass1234");
        assertEquals("pass1234", viewModel.getPassword());
    }

    @Test
    void testGetYourNameProperty() {
        viewModel = new LandingPageViewModel(authenticator, "bob", "pass1234");
        assertNotNull(viewModel.getYourNameProperty());
    }

    @Test
    void testGetYourRolesProperty() {
        viewModel = new LandingPageViewModel(authenticator, "bob", "pass1234");
        assertNotNull(viewModel.getYourRolesProperty());
    }

    @Test
    void testGetInventory() {
        viewModel = new LandingPageViewModel(authenticator, "bob", "pass1234");
        assertNotNull(viewModel.getInventory());
    }

    @Test
    void testGetHistory() {
        viewModel = new LandingPageViewModel(authenticator, "bob", "pass1234");
        assertNotNull(viewModel.getHistory());
    }
}
