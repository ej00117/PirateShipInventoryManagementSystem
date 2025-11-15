package edu.westga.cs3211.pirateshipinventorymanagementsystem.model;

import java.util.ArrayList;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.enums.Role;

/**
 * Defines a user
 * 
 * @author Ezra Jones
 * @version Fall 2025
 */
public class User {
	
	public static final String NAME_CANNOT_BE_NULL = "name cannot be null.";
	public static final String PASSWORD_CANNOT_BE_NULL = "password cannot be null.";
	private static final String ROLES_CANNOT_BE_NULL = "roles cannot be null.";
	private static final String ROLES_MUST_CONTAIN_CREWMATE = "must at least be a crewmate.";
	private String name;
	private String password;
	private ArrayList<Role> roles;
	
	/**
	 * Creates a new user with the specified name and password
	 * 
	 * @precondition name!=null && password!=null && roles!=null
	 * @postcondition getName() == name, getPassword == password, getRoles == roles
	 * 
	 * @param name the name of the user
	 * @param password the password for the user
	 * @param roles the role(s) the user has in the system
	 */
	public User(String name, String password, ArrayList<Role> roles) {
		if (name == null) {
			throw new IllegalArgumentException(NAME_CANNOT_BE_NULL);
		}
		if (password == null) {
			throw new IllegalArgumentException(PASSWORD_CANNOT_BE_NULL);
		}
		if (roles == null) {
			throw new IllegalArgumentException(ROLES_CANNOT_BE_NULL);
		}
		if (!roles.contains(Role.CREWMATE)) {
			throw new IllegalArgumentException(ROLES_MUST_CONTAIN_CREWMATE);
		}
		
		this.name = name;
		this.password = password;
		this.roles = roles;
	}

	/**
	 * Returns the user's name
	 * 
	 * @return the user's name
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Returns the user's password
	 * 
	 * @return the user's password
	 */
	public String getPassword() {
		return this.password;
	}
	
	/**
	 * Returns the user's roles
	 * 
	 * @return the user's roles
	 */
	public ArrayList<Role> getRoles() {
		return this.roles;
	}
}
