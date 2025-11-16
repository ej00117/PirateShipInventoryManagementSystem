package edu.westga.cs3211.pirateshipinventorymanagementsystem.model;

import java.time.LocalDateTime;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.enums.Change;

/**
 * Defines a change log for when items are added or removed from the system
 * 
 * @author Ezra Jones
 * @version Fall 2025
 */
public class ChangeLog {

	private static final String USER_NAME_CANNOT_BE_NULL = "username cannot be null.";
	private static final String STOCK_INFO_CANNOT_BE_NULL = "stock info cannot be null.";
	private static final String CHANGE_CANNOT_BE_NULL = "change cannot be null.";
	private static final String COMPARTMENT_NAME_CANNOT_BE_NULL = "compartment name cannot be null.";
	private static final String CAPACITY_CANNOT_BE_NULL = "capacity cannot be null.";
	private static final String TIME_CANNOT_BE_NULL = "time of change cannot be null.";
	private String userName;
	private Stock stockInfo;
	private Change change;
	private String compartmentName;
	private Double capacityRemaining;
	private LocalDateTime timeOfChange;
	
	/**
	 * Creates a new change log with the specified details
	 * 
	 * @precondition userName!=null && stockInfo!=null && compartmentName!=null && capacityRemaining!=null && timeOfChange!=null
	 * @postcondition getUserName() == userName && getStockInfo == stockInfo && getCompartmentName() == compartmentName &&
	 * 	getCapacityRemaining() == capacityRemaining && getTimeOfChange() == timeOfChange
	 * 
	 * @param userName the name of the user that the change pertains to
	 * @param stockInfo the stock that the change pertains to
	 * @param change the type of change occurring in the log
	 * @param compartmentName the name of the compartment that the change took place in
	 * @param capacityRemaining the capacity remaining in the compartment where the change occurred
	 * @param timeOfChange the time that the change happened
	 */
	public ChangeLog(String userName, Stock stockInfo, Change change, String compartmentName, Double capacityRemaining, LocalDateTime timeOfChange) {
		if (userName == null) {
			throw new IllegalArgumentException(USER_NAME_CANNOT_BE_NULL);
		}
		if (stockInfo == null) {
			throw new IllegalArgumentException(STOCK_INFO_CANNOT_BE_NULL);
		}
		if (change == null) {
			throw new IllegalArgumentException(CHANGE_CANNOT_BE_NULL);
		}
		if (compartmentName == null) {
			throw new IllegalArgumentException(COMPARTMENT_NAME_CANNOT_BE_NULL);
		}
		if (capacityRemaining == null) {
			throw new IllegalArgumentException(CAPACITY_CANNOT_BE_NULL);
		}
		if (timeOfChange == null) {
			throw new IllegalArgumentException(TIME_CANNOT_BE_NULL);
		}
		
		this.userName = userName;
		this.stockInfo = stockInfo;
		this.change = change;
		this.compartmentName = compartmentName;
		this.capacityRemaining = capacityRemaining;
		this.timeOfChange = timeOfChange;
	}

	/**
	 * Returns the user's name
	 * 
	 * @return the user's name
	 */
	public String getUserName() {
		return this.userName;
	}
	
	/**
	 * Returns the stock info
	 * 
	 * @return the stock info
	 */
	public Stock getStockInfo() {
		return this.stockInfo;
	}
	
	/**
	 * Returns the type of change
	 * 
	 * @return the type of change
	 */
	public Change getChange() {
		return this.change;
	}

	/**
	 * Returns the name of the compartment
	 * 
	 * @return the compartment name
	 */
	public String getCompartmentName() {
		return this.compartmentName;
	}

	/**
	 * Returns the capacity remaining
	 * 
	 * @return the capacity remaining
	 */
	public Double getCapacityRemaining() {
		return this.capacityRemaining;
	}

	/**
	 * Returns the time that the change occurred
	 * 
	 * @return the time the change occurred
	 */
	public LocalDateTime getTimeOfChange() {
		return this.timeOfChange;
	}
}
