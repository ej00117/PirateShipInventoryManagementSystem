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
	private static final String NAME_CANNOT_BE_NULL = "compartment name cannot be null.";
	private static final String COMPARTMENTS_CANNOT_HAVE_DUPLICATE_NAME = "compartments cannot have duplicate name identifier.";
	private static final String STOCK_CANNOT_BE_NULL = "stock cannot be null.";
	private static final String TYPE_CANNOT_BE_NULL = "special quality types cannot be null.";
	private static final String SPACE_CANNOT_BE_NULL = "space required cannot be null.";
	private static final String SPACE_CANNOT_BE_BELOW_ZERO = "space required cannot be less than zero.";
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
	 * Adds stock to a compartment based on the compartments name
	 * 
	 * @param compartmentName the name of the compartment
	 * @param stock the stock to add
	 */
	public void addStock(String compartmentName, Stock stock) {
		if (compartmentName == null) {
			throw new IllegalArgumentException(NAME_CANNOT_BE_NULL);
		}
		if (stock == null) {
			throw new IllegalArgumentException(STOCK_CANNOT_BE_NULL);
		}
		for (Compartment compartment : this.compartments) {
			if (compartment.getName().equals(compartmentName)) {
				compartment.addStock(stock);
			}
		}
	}

	/**
	 * Gets the compartments with the desired type if it has enough space.
	 * 
	 * @precondition type!=null && spaceRequired!=null && spaceRequired >= 0 && compartment can be found
	 * @postcondition none
	 * 
	 * @param type the type of the compartment
	 * @param spaceRequired the space required in the desired compartment
	 * @return the compartments 
	 */
	public ArrayList<Compartment> getCompartmentsAtType(ArrayList<SpecialQuality> type, Double spaceRequired) {
	    ArrayList<Compartment> compartments = new ArrayList<Compartment>();
	    if (type == null) {
	        throw new IllegalArgumentException(TYPE_CANNOT_BE_NULL);
	    }
	    if (spaceRequired == null) {
	        throw new IllegalArgumentException(SPACE_CANNOT_BE_NULL);
	    }
	    if (spaceRequired < 0) {
	        throw new IllegalArgumentException(SPACE_CANNOT_BE_BELOW_ZERO);
	    }

	    double maxFreeSpaceForType = 0.0;

	    for (Compartment compartment : this.compartments) {
	        if (new HashSet<>(compartment.getType()).equals(new HashSet<>(type))) {
	            double freeSpace = compartment.getFreeSpace();
	            if (freeSpace >= spaceRequired) {
	                compartments.add(compartment);
	            }
	            if (freeSpace > maxFreeSpaceForType) {
	                maxFreeSpaceForType = freeSpace;
	            }
	        }
	    }

	    if (!compartments.isEmpty()) {
	        return compartments;
	    }

	    throw new IllegalArgumentException(
	        "No compartment has enough space. Maximum remaining capacity for this type is: " + maxFreeSpaceForType + " units.");
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
