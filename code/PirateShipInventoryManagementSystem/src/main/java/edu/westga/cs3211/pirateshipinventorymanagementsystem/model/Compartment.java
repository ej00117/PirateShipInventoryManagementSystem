package edu.westga.cs3211.pirateshipinventorymanagementsystem.model;

import java.util.ArrayList;
import java.util.HashSet;

import edu.westga.cs3211.pirateshipinventorymanagementsystem.enums.SpecialQuality;

/**
 * Defines a compartment for holding stock items
 * 
 * @author Ezra Jones
 * @version Fall 2025
 */
public class Compartment {
	
	private static final String NAME_CANNOT_BE_NULL = "name cannot be null.";
	private static final String STOCK_CANNOT_BE_NULL = "stock cannot be null.";
	private static final String CAPACITY_CANNOT_BE_NULL = "capacity cannot be null.";
	private static final String CAPACITY_CANNOT_BE_BELOW_ZERO = "capacity must be above 0.";
	private static final String TYPE_CANNOT_BE_NULL = "type cannot be null.";
	private static final String STOCK_INCOMPATIBLE = "Stock type not compatible with compartment type.";
	private static final String STOCK_TOO_LARGE = "Stock unable to be added, quantity is too large for remaining capacity.";
	private String name;
	private Double capacity;
	private ArrayList<SpecialQuality> type;
	private ArrayList<Stock> items;

	/**
	 * Creates a new compartment with the specified capacity and type
	 * 
	 * @precondition capacity!=null && capacity>=0 && type!=null
	 * @postcondition getFreeSpace() == capacity && getType() == type
	 * 
	 * @param name the name of the compartment
	 * @param capacity of the compartment
	 * @param type type of the compartment
	 */
	public Compartment(String name, Double capacity, ArrayList<SpecialQuality> type) {
		if (name == null) {
			throw new IllegalArgumentException(NAME_CANNOT_BE_NULL);
		}
		if (capacity == null) {
			throw new IllegalArgumentException(CAPACITY_CANNOT_BE_NULL);
		}
		if (capacity < 0) {
			throw new IllegalArgumentException(CAPACITY_CANNOT_BE_BELOW_ZERO);
		}
		if (type == null) {
			throw new IllegalArgumentException(TYPE_CANNOT_BE_NULL);
		}
		
		this.name = name;
		this.capacity = capacity;
		this.type = type;
		this.items = new ArrayList<Stock>();
	}
	
	/**
	 * Adds a new stock item to the list of items if possible and lowers the capacity accordingly
	 * 
	 * @precondition stock!=null && stock.getQuantity() < this.capacity && types match
	 * @postcondition this.items.contains(stock) && this.capacity -= stock.getCapacity()
	 * 
	 * @param stock the stock to add
	 * @return true if added
	 */
	public boolean addStock(Stock stock) {
		if (stock == null) {
			throw new IllegalArgumentException(STOCK_CANNOT_BE_NULL);
		}
		if (!new HashSet<>(stock.getSpecialQualities()).equals(new HashSet<>(this.type))) {
			throw new IllegalArgumentException(STOCK_INCOMPATIBLE);
		}
		if (stock.getQuantity() <= this.getFreeSpace()) {
			this.items.add(stock);
			this.capacity -= stock.getQuantity();
			return true;
		}
		throw new IllegalArgumentException(STOCK_TOO_LARGE);
	}

	/**
	 * Returns the capacity left in the compartment
	 * 
	 * @return the capacity left in the compartment
	 */
	public Double getFreeSpace() {
		return this.capacity;
	}

	/**
	 * Returns the type of the compartment
	 * 
	 * @return the type of the compartment
	 */
	public ArrayList<SpecialQuality> getType() {
		return this.type;
	}
	
	/**
	 * Returns the items in the compartment
	 * 
	 * @return the items in the compartment
	 */
	public ArrayList<Stock> getItems() {
		return this.items;
	}
	
	/** Returns the name of the compartment
	 * 
	 * @return the name of the compartment
	 */
	public String getName() {
		return this.name;
	}
}
