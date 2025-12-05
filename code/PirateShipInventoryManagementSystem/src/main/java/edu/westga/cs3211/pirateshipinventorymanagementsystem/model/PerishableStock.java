package edu.westga.cs3211.pirateshipinventorymanagementsystem.model;

import java.time.LocalDate;
import java.util.ArrayList;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.enums.Condition;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.enums.ItemCategory;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.enums.SpecialQuality;

/**
 * Defines a perishable stock item, extends stock
 * 
 * @author Ezra Jones
 * @version Fall 2025
 */
public class PerishableStock extends Stock {
	
	public static final String EXPIRATION_CANNOT_BE_NULL = "expiration date cannot be null.";
	private static final String SPECIAL_QUALITIES_MUST_HAVE_PERISHABLE = "perishable stock must have perishable quality.";
	private LocalDate expirationDate;
	
	/**
	 * Creates the new perishable stock with the given parameters.
	 * 
	 * @precondition same as stock && expirationDate!=null
	 * @postcondition same as stock && getExpirationDate() == expirationDate
	 * 
	 * @param name the name of the perishable stock
	 * @param quantity the quantity of the perishable stock
	 * @param category the category of the perishable stock
	 * @param condition the condition of the perishable stock
	 * @param specialQualities the special qualities of the perishable stock
	 * @param expirationDate the expiration date of the stock
	 */
	public PerishableStock(String name, Double quantity, ItemCategory category, Condition condition,
			ArrayList<SpecialQuality> specialQualities, LocalDate expirationDate) {
		super(name, quantity, category, condition, specialQualities);
		
		this.validateSpecialQualities(specialQualities);
		if (expirationDate == null) {
			throw new IllegalArgumentException(EXPIRATION_CANNOT_BE_NULL);
		}
		this.expirationDate = expirationDate;
	}
	
	@Override
	protected void validateSpecialQualities(ArrayList<SpecialQuality> specialQualities) {
		if (!specialQualities.contains(SpecialQuality.PERISHABLE)) {
			throw new IllegalArgumentException(SPECIAL_QUALITIES_MUST_HAVE_PERISHABLE);
		}
	}
	
	@Override
	public LocalDate getExpirationDate() {
		return this.expirationDate;
	}
	
	/**
	 * To string for stock items
	 * 
	 * @return formatted string
	 */
	@Override
	public String toString() {
		String text = super.toString();
		text += ", Expiration Date: " + this.expirationDate.toString();
		return text;
	}

}
