package edu.westga.cs3211.pirateshipinventorymanagementsystem.viewmodel;

import edu.westga.cs3211.pirateshipinventorymanagementsystem.model.Authenticator;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * The login ViewModel.
 * 
 * @author Ezra Jones
 * @version Fall 2025
 */
public class LoginViewModel {

	private StringProperty nameProperty;
	private StringProperty passwordProperty;
	private Authenticator authenticator;
	
	/**
	 * Instantiates a new login view model
	 */
	public LoginViewModel() {
		this.nameProperty = new SimpleStringProperty("");
		this.passwordProperty = new SimpleStringProperty("");
		this.authenticator = new Authenticator();
	}
	
	/**
	 * Submit entered credentials
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return true if credentials valid, false if not
	 */
	public boolean submitCredentials() {
		String name = this.nameProperty.get();
		String password = this.passwordProperty.get();
		if (!name.isBlank() && !password.isBlank()) {
			return this.authenticator.authenticateUser(name, password);
		}
		return false;
	}
	
	/**
	 * Returns the name string property
	 * 
	 * @return the name string property
	 */
	public StringProperty getNameProperty() {
		return this.nameProperty;
	}

	/**
	 * Returns the password string property
	 * 
	 * @return the password string property
	 */
	public StringProperty getPasswordProperty() {
		return this.passwordProperty;
	}
	
	/**
	 * Returns the authenticator
	 * 
	 * @return the authenticator
	 */
	public Authenticator getAuthenticator() {
		return this.authenticator;
	}
}
