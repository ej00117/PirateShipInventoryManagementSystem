package edu.westga.cs3211.pirateshipinventorymanagementsystem.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import edu.westga.cs3211.pirateshipinventorymanagementsystem.enums.SpecialQuality;

/**
 * Defines the inventory system that handles compartments
 * 
 * @author Ezra Jones
 * @version Fall 2025
 */
public class Inventory {

	private static final String COMPARTMENTS_CANNOT_BE_NULL = "compartments cannot be null.";
	private static final String COMPARTMENTS_CANNOT_HAVE_DUPLICATE_NAME = "compartments cannot have duplicate name identifier.";
	private static final String TYPE_CANNOT_BE_NULL = "special quality types cannot be null.";
	private static final String SPACE_CANNOT_BE_NULL = "space required cannot be null.";
	private static final String SPACE_CANNOT_BE_BELOW_ZERO = "space required cannot be less than zero.";
	private static final String COMPARTMENT_CANNOT_BE_FOUND = "compartment with enough space of specified type could not be found.";
	private ArrayList<Compartment> compartments;

	/**
	 * Creates the inventory with the compartments given
	 * 
	 * @precondition compartments!=null && compartments do not have duplicate names in list
	 * @postcondition this.getCompartments() == compartments
	 * 
	 * @param compartments the compartments of the inventory
	 */
	public Inventory(ArrayList<Compartment> compartments) {
		if (compartments == null) {
			throw new IllegalArgumentException(COMPARTMENTS_CANNOT_BE_NULL);
		}
		this.validateNoDuplicateNames(compartments);
		
		this.compartments = compartments;
	}
	
	private void validateNoDuplicateNames(ArrayList<Compartment> compartments) {
		Set<String> names = new HashSet<>();
		
		for (Compartment compartment : compartments) {
			if (!names.add(compartment.getName())) {
				throw new IllegalArgumentException(COMPARTMENTS_CANNOT_HAVE_DUPLICATE_NAME);
			}
		}
	}
	
	/**
	 * Gets the compartment with the desired type if it has enough space.
	 * 
	 * @precondition type!=null && spaceRequired!=null && spaceRequired >= 0 && compartment can be found
	 * @postcondition none
	 * 
	 * @param type the type of the compartment
	 * @param spaceRequired the space required in the desired compartment
	 * @return the compartment 
	 */
	public Compartment getCompartmentAtType(ArrayList<SpecialQuality> type, Double spaceRequired) {
		if (type == null) {
			throw new IllegalArgumentException(TYPE_CANNOT_BE_NULL);
		}
		if (spaceRequired == null) {
			throw new IllegalArgumentException(SPACE_CANNOT_BE_NULL);
		}
		if (spaceRequired < 0) {
			throw new IllegalArgumentException(SPACE_CANNOT_BE_BELOW_ZERO);
		}
		
		for (Compartment compartment : this.compartments) {
			if (new HashSet<>(compartment.getType()).equals(new HashSet<>(type))) {
				if (compartment.getFreeSpace() >= spaceRequired) {
					return compartment;
				}
			}
		}
		throw new IllegalArgumentException(COMPARTMENT_CANNOT_BE_FOUND);
	}
	
	/**
	 * Returns true or false depending on whether or not there is space in the specified compartment
	 * 
	 * @precondition type!=null
	 * @postcondition none
	 * 
	 * @param type the type of the compartment to check
	 * @return true if there is space, false if there is no space
	 */
	public boolean hasFreeSpace(ArrayList<SpecialQuality> type) {
		if (type == null) {
			throw new IllegalArgumentException(TYPE_CANNOT_BE_NULL);
		}
		for (Compartment compartment : this.compartments) {
			if (new HashSet<>(compartment.getType()).equals(new HashSet<>(type))) {
				if (compartment.getFreeSpace() > 0) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Return the compartments in the inventory
	 * 
	 * @return the compartments in the inventory
	 */
	public ArrayList<Compartment> getCompartments() {
		return this.compartments;
	}
}
