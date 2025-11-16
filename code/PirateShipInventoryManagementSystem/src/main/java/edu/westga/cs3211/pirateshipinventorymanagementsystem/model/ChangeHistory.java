package edu.westga.cs3211.pirateshipinventorymanagementsystem.model;

import java.util.ArrayList;

/**
 * Holds a list detailing the change log history
 * 
 * @author Ezra Jones
 * @version Fall 2025
 */
public class ChangeHistory {

	private ArrayList<ChangeLog> history;
	
	/**
	 * Creates a new history of logs
	 *
	 * @precondition
	 * @postcondition
	 */
	public ChangeHistory() {
		this.history = new ArrayList<ChangeLog>();
	}

	/**
	 * Returns the list of logs in the history
	 * 
	 * @return the list of logs in history
	 */
	public ArrayList<ChangeLog> getHistory() {
		return this.history;
	}
}
