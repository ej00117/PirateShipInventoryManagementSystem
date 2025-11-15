package edu.westga.cs3211.pirateshipinventorymanagementsystem.model;

import java.util.ArrayList;

import edu.westga.cs3211.pirateshipinventorymanagementsystem.enums.Role;

/**
 * Defines Authentication for user credentials entered into the system
 * 
 * @author Ezra Jones
 * @version Fall 2025
 */
public class Authenticator {
	
	private static final String USER_MUST_BE_IN_SYSTEM = "user must exist in system.";
	private ArrayList<User> users;
	
	/**
	 * Initializes the Authenticator and the example accounts
	 * 
	 * @precondition none
	 * @postcondition this.users initialized
	 * 
	 */
	public Authenticator() {
		this.users = new ArrayList<User>();
		
		this.initializeExampleAccounts();
		
	}
	
	/**
	 * Authenticates whether a combined user name and password is valid
	 * 
	 * @precondition name!=null && password!=null
	 * @postcondition none
	 * 
	 * @param name the name of the user
	 * @param password the password of the user
	 * @return true if credentials valid, false if invalid
	 */
	public boolean authenticateUser(String name, String password) {
		if (name == null) {
			throw new IllegalArgumentException(User.NAME_CANNOT_BE_NULL);
		}
		if (password == null) {
			throw new IllegalArgumentException(User.PASSWORD_CANNOT_BE_NULL);
		}
		for (User user : this.users) {
			if (user.getName().equals(name) && user.getPassword().equals(password)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Returns the role(s) of a user given their name and password
	 * 
	 * @precondition name!=null && password!=null
	 * @postcondition none
	 * 
	 * @param name the name of the user
	 * @param password the password of the user
	 * @return returns the user's role(s)
	 */
	public ArrayList<Role> getRolesForUser(String name, String password) {
		ArrayList<Role> roles = new ArrayList<Role>();
		if (name == null) {
			throw new IllegalArgumentException(User.NAME_CANNOT_BE_NULL);
		}
		if (password == null) {
			throw new IllegalArgumentException(User.PASSWORD_CANNOT_BE_NULL);
		}
		for (User user : this.users) {
			if (user.getName().equals(name) && user.getPassword().equals(password)) {
				roles = user.getRoles();
			}
		}
		if (!this.authenticateUser(name, password)) {
			throw new IllegalArgumentException(USER_MUST_BE_IN_SYSTEM);
		}
		return roles;
	}
	
	private void initializeExampleAccounts() {
		ArrayList<Role> crewmateOnly = new ArrayList<Role>();
		crewmateOnly.add(Role.CREWMATE);
		
		ArrayList<Role> crewmateAndQuarterMaster = new ArrayList<Role>();
		crewmateAndQuarterMaster.add(Role.CREWMATE);
		crewmateAndQuarterMaster.add(Role.QUARTERMASTER);
		
		this.users.add(new User("bob", "pass1234", crewmateOnly));
		this.users.add(new User("stanley", "secretpass", crewmateAndQuarterMaster));
	}
}
