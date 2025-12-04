package edu.westga.cs3211.pirateshipinventorymanagementsystem.model;

import java.util.ArrayList;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.enums.Condition;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.enums.ItemCategory;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.enums.SpecialQuality;

/**
 * Defines a general stock item, non-perishable
 * 
 * @author Ezra Jones
 * @version Fall 2025
 */
public class Stock {
	
	public static final String STOCK_NAME_CANNOT_BE_NULL = "stock name cannot be null.";
	public static final String QUANTITY_CANNOT_BE_NULL = "quantity cannot be null.";
	public static final String QUANTITY_MUST_BE_GREATER_THAN_ZERO = "quantity must be greater than 0.";
	public static final String CONDITION_CANNOT_BE_NULL = "condition cannot be null.";
	private static final String CATEGORY_CANNOT_BE_NULL = "category cannot be null.";
	private static final String SPECIAL_QUALITIES_CANNOT_BE_NULL = "special qualities cannot be null.";
	private static final String SPECIAL_QUALITIES_CANNOT_HAVE_PERISHABLE = "regular stock cannot have perishable quality.";
	private String name;
	private Double quantity;
	private ItemCategory category;
	private Condition condition;
	private ArrayList<SpecialQuality> specialQualities;
	
	/**
	 * Constructor creates a new stock with the given parameters.
	 * 
	 * @precondition name!=null && quantity!=null && quantity>0 && condition!=null &&
	 * 	specialQualities!=null && !specialQualities.contains(SpecialQuality.Perishable)
	 * @postcondition getName() == name && getQuantity() == quantity && getCondition() == condition &&
	 * 	getSpecialQualities() == specialQualities
	 * 
	 * @param name the name of the stock
	 * @param quantity the quantity of the stock
	 * @param category the category of the stock
	 * @param condition the condition of the stock
	 * @param specialQualities the special qualities of the stock
	 */
	public Stock(String name, Double quantity, ItemCategory category, Condition condition, ArrayList<SpecialQuality> specialQualities) {
		if (name == null) {
			throw new IllegalArgumentException(STOCK_NAME_CANNOT_BE_NULL);
		}
		if (quantity == null) {
			throw new IllegalArgumentException(QUANTITY_CANNOT_BE_NULL);
		}
		if (quantity <= 0) {
			throw new IllegalArgumentException(QUANTITY_MUST_BE_GREATER_THAN_ZERO);
		}
		if (category == null) {
			throw new IllegalArgumentException(CATEGORY_CANNOT_BE_NULL);
		}
		if (condition == null) {
			throw new IllegalArgumentException(CONDITION_CANNOT_BE_NULL);
		}
		if (specialQualities == null) {
			throw new IllegalArgumentException(SPECIAL_QUALITIES_CANNOT_BE_NULL);
		}
		this.validateSpecialQualities(specialQualities);
		
		this.name = name;
		this.quantity = quantity;
		this.category = category;
		this.condition = condition;
		this.specialQualities = specialQualities;
	}
	
	protected void validateSpecialQualities(ArrayList<SpecialQuality> specialQualities) {
		if (specialQualities.contains(SpecialQuality.PERISHABLE)) {
			throw new IllegalArgumentException(SPECIAL_QUALITIES_CANNOT_HAVE_PERISHABLE);
		}
	}
	
	/**
	 * Returns the stock name
	 * 
	 * @return the stock name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Returns the stock quantity
	 * 
	 * @return the stock quantity
	 */
	public Double getQuantity() {
		return this.quantity;
	}
	
	/**
	 * Returns the stock category
	 * 
	 * @return the stock category
	 */
	public ItemCategory getCategory() {
		return this.category;
	}

	/**
	 * Returns the condition of the stock
	 * 
	 * @return the condition of the stock
	 */
	public Condition getCondition() {
		return this.condition;
	}
	
	/**
	 * Sets the quantity of the stock
	 * 
	 * @param quantity the quantity for the stock to be set to
	 */
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	
	/**
	 * Returns the stock's special qualities
	 * 
	 * @return the stock's special qualities
	 */
	public ArrayList<SpecialQuality> getSpecialQualities() {
		return this.specialQualities;
	}
	
	/**
	 * To string for stock items
	 * 
	 * @return formatted string
	 */
	public String toString() {
		String text = "Name: " + this.name + ", ";
		text += "Quantity: " + this.quantity + ", ";
		text += "Category: " + this.category + ", ";
		text += "Condition: " + this.condition + ", ";
		if (!this.specialQualities.isEmpty()) {
			text += "Special Qualities: " + this.specialQualities;
		} else {
			text += "Special Qualities: NONE";
		}
		return text;
	}
}
