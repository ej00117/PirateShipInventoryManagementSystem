package edu.westga.cs3211.pirateshipinventorymanagementsystem.viewmodel;

import java.util.ArrayList;

import edu.westga.cs3211.pirateshipinventorymanagementsystem.enums.Role;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.enums.SpecialQuality;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.model.Authenticator;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.model.ChangeHistory;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.model.Compartment;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.model.Inventory;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.model.User;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * The landing page view model
 * 
 * @author Ezra Jones
 * @version Fall 2025
 */
public class LandingPageViewModel {

	private static final String AUTHENTICATOR_CANNOT_BE_NULL = "authenticator cannot be null.";
	private String name;
	private String password;
	private StringProperty yourNameProperty;
	private StringProperty yourRolesProperty;
	private Authenticator authenticator;
	private Inventory inventory;
	private ChangeHistory history;
	
	/**
	 * Instantiates a new landing page view model
	 * 
	 * @param authenticator the authenticator use for authenticating role privileges
	 * @param name the name of the user logging in
	 * @param password the password of the user logging in
	 */
	public LandingPageViewModel(Authenticator authenticator, String name, String password) {
		if (authenticator == null) {
			throw new IllegalArgumentException(AUTHENTICATOR_CANNOT_BE_NULL);
		}
		if (name == null) {
			throw new IllegalArgumentException(User.NAME_CANNOT_BE_NULL);
		}
		if (password == null) {
			throw new IllegalArgumentException(User.PASSWORD_CANNOT_BE_NULL);
		}
		
		this.name = name;
		this.password = password;
		this.yourNameProperty = new SimpleStringProperty("");
		this.yourRolesProperty = new SimpleStringProperty("");
		this.authenticator = authenticator;
		this.history = new ChangeHistory();
		this.instantiateAnExampleInventory();
	}
	
	private void instantiateAnExampleInventory() {
		ArrayList<Compartment> compartments = new ArrayList<Compartment>();
		ArrayList<SpecialQuality> qualities1 = new ArrayList<SpecialQuality>();
		qualities1.add(SpecialQuality.LIQUID);
		
		ArrayList<SpecialQuality> qualities2 = new ArrayList<SpecialQuality>();
		qualities2.add(SpecialQuality.PERISHABLE);
		
		ArrayList<SpecialQuality> qualities3 = new ArrayList<SpecialQuality>();
		qualities3.add(SpecialQuality.FLAMMABLE);
		
		ArrayList<SpecialQuality> qualities4 = new ArrayList<SpecialQuality>();
		qualities4.add(SpecialQuality.PERISHABLE);
		qualities4.add(SpecialQuality.LIQUID);
		
		ArrayList<SpecialQuality> qualities5 = new ArrayList<SpecialQuality>();
		qualities5.add(SpecialQuality.FLAMMABLE);
		qualities5.add(SpecialQuality.LIQUID);
		
		compartments.add(new Compartment("regular", 300.0, new ArrayList<SpecialQuality>()));
		compartments.add(new Compartment("liquids", 300.0, qualities1));
		compartments.add(new Compartment("perishables", 300.0, qualities2));
		compartments.add(new Compartment("flammables", 300.0, qualities3));
		compartments.add(new Compartment("perishable-liquids", 300.0, qualities4));
		compartments.add(new Compartment("flammable-liquids", 300.0, qualities5));
		
		this.inventory = new Inventory(compartments);
	}
	
	/**
	 * Initializes the welcome message details in the view
	 * 
	 * @precondition none
	 * @postcondition details in code behind set accordingly
	 */
	public void setLandingPageUserDetails() {
		this.yourNameProperty.setValue("Hello " + this.name + ".");
		this.yourRolesProperty.setValue("Your roles: " + this.authenticator.getRolesForUser(this.name, this.password).toString());
	}
	
	/**
	 * Returns true if user is a crewmate
	 * Note: Current implementation does not allow for users without crewmate role.
	 * 
	 * @return true if is a crewmate
	 */
	public boolean checkIfCrewmate() {
		if (this.authenticator.getRolesForUser(this.name, this.password).contains(Role.CREWMATE)) {
			return true;
		}
		return false;
	}
	
	/**
	 * Returns true if user is a quartermaster
	 * 
	 * @return true if user is quartermaster
	 */
	public boolean checkIfQuartermaster() {
		if (this.authenticator.getRolesForUser(this.name, this.password).contains(Role.QUARTERMASTER)) {
			return true;
		}
		return false;
	}
	
	/**
	 * Returns true if user is a cook
	 * 
	 * @return true if user is cook
	 */
	public boolean checkIfCook() {
		if (this.authenticator.getRolesForUser(this.name, this.password).contains(Role.COOK)) {
			return true;
		}
		return false;
	}
	
	/**
	 * Returns true if user is a officer
	 * 
	 * @return true if user is officer
	 */
	public boolean checkIfOfficer() {
		if (this.authenticator.getRolesForUser(this.name, this.password).contains(Role.OFFICER)) {
			return true;
		}
		return false;
	}
	
	/**
	 * Ensure that user can add stock.
	 * Note: Current Implementation does not allow for users unable to.
	 * 
	 * @return true if user can add stock, false if not
	 */
	public boolean addStock() {
		if (this.authenticator.getRolesForUser(this.name, this.password).contains(Role.CREWMATE)) {
			return true;
		}
		return false;
	}
	
	/**
	 * Ensure that user can view stock changes
	 * 
	 * @return true if user can view stock changes, false if not
	 */
	public boolean viewHistory() {
		if (this.authenticator.getRolesForUser(this.name, this.password).contains(Role.QUARTERMASTER)) {
			return true;
		}
		return false;
	}
	
	/**
	 * Returns the logged in user's name
	 * 
	 * @return the logged in user's name
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Returns the logged in user's password
	 * 
	 * @return the logged in user's password
	 */
	public String getPassword() {
		return this.password;
	}
	
	/**
	 * Returns the yourName property
	 * 
	 * @return the yourName property
	 */
	public StringProperty getYourNameProperty() {
		return this.yourNameProperty;
	}
	
	/**
	 * Returns the yourRoles property
	 * 
	 * @return the yourRoles property
	 */
	public StringProperty getYourRolesProperty() {
		return this.yourRolesProperty;
	}

	/**
	 * Returns the inventory 
	 * 
	 * @return the inventory
	 */
	public Inventory getInventory() {
		return this.inventory;
	}

	/**
	 * Returns the change history
	 * 
	 * @return the change history
	 */
	public ChangeHistory getHistory() {
		return this.history;
	}
}
