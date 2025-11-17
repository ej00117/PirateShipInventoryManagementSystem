package edu.westga.cs3211.pirateshipinventorysystem.viewmodel.loginviewmodel;

import edu.westga.cs3211.pirateshipinventorymanagementsystem.viewmodel.LoginViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestLoginViewModel {

    private LoginViewModel viewModel;

    @BeforeEach
    void setUp() {
        this.viewModel = new LoginViewModel();
    }

    @Test
    void testConstructor() {
        assertNotNull(this.viewModel.getNameProperty());
        assertNotNull(this.viewModel.getPasswordProperty());
        assertNotNull(this.viewModel.getAuthenticator());
        assertTrue(this.viewModel.getNameProperty().get().isEmpty());
        assertTrue(this.viewModel.getPasswordProperty().get().isEmpty());
    }

    @Test
    void testSubmitCredentialsValidCredentials() {
        this.viewModel.getNameProperty().set("bob");
        this.viewModel.getPasswordProperty().set("pass1234");

        assertTrue(this.viewModel.submitCredentials());
    }

    @Test
    void testSubmitCredentialsInvalidName() {
        this.viewModel.getNameProperty().set("");
        this.viewModel.getPasswordProperty().set("pass1234");

        assertFalse(this.viewModel.submitCredentials());
    }

    @Test
    void testSubmitCredentialsInvalidPassword() {
        this.viewModel.getNameProperty().set("bob");
        this.viewModel.getPasswordProperty().set("");

        assertFalse(this.viewModel.submitCredentials());
    }
    
    @Test
    void testSubmitCredentialsBlankNameAndPassword() {
        this.viewModel.getNameProperty().set("");
        this.viewModel.getPasswordProperty().set("");

        assertFalse(this.viewModel.submitCredentials());
    }

    @Test
    void testSubmitCredentialsInvalidCredentials() {
        this.viewModel.getNameProperty().set("bob");
        this.viewModel.getPasswordProperty().set("wrongpass");

        assertFalse(this.viewModel.submitCredentials());
    }

    @Test
    void testGetNameProperty() {
        assertNotNull(this.viewModel.getNameProperty());
        assertEquals("", this.viewModel.getNameProperty().get());
    }

    @Test
    void testGetPasswordProperty() {
        assertNotNull(this.viewModel.getPasswordProperty());
        assertEquals("", this.viewModel.getPasswordProperty().get());
    }

    @Test
    void testGetAuthenticator() {
        assertNotNull(this.viewModel.getAuthenticator());
    }
}
