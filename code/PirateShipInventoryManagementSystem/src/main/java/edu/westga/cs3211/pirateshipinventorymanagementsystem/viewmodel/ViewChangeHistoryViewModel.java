package edu.westga.cs3211.pirateshipinventorymanagementsystem.viewmodel;

import java.util.ArrayList;

import edu.westga.cs3211.pirateshipinventorymanagementsystem.enums.Condition;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.model.ChangeHistory;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.model.ChangeLog;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * View model for view change history page
 * 
 * @author Ezra Jones
 * @version Fall 2025
 */
public class ViewChangeHistoryViewModel {
	private ObservableList<ChangeLog> logs;
	private ChangeHistory history;
	private BooleanProperty isPerishable;
	private BooleanProperty isLiquid;
	private BooleanProperty isFlammable;
	private ObjectProperty<String> selectedUser;
	private ArrayList<String> users;
	
	/**
	 * Instantiates a new view change history view model
	 * 
	 * @param history the change history
	 */
	public ViewChangeHistoryViewModel(ChangeHistory history) {
		this.history = history;
		this.logs = FXCollections.observableArrayList(this.history.getHistory());
		this.isPerishable = new SimpleBooleanProperty(false);
		this.isLiquid = new SimpleBooleanProperty(false);
		this.isFlammable = new SimpleBooleanProperty(false);
		this.selectedUser = new SimpleObjectProperty<String>();
		this.users = new ArrayList<String>();
	}

	/**
	 * Returns the logs list
	 * 
	 * @return the logs list
	 */
	public ObservableList<ChangeLog> getLogs() {
		return this.logs;
	}

	/**
	 * Returns the history
	 * 
	 * @return the history
	 */
	public ChangeHistory getHistory() {
		return this.history;
	}
	
	/**
	 * Returns the isPerishable property
	 * 
	 * @return the isPerishable property
	 */
	public BooleanProperty getIsPerishable() {
		return this.isPerishable;
	}

	/**
	 * Returns the isLiquid property
	 * 
	 * @return the isLiquid property
	 */
	public BooleanProperty getIsLiquid() {
		return this.isLiquid;
	}

	/**
	 * Returns the isFlammable property
	 * 
	 * @return the isFlammable property
	 */
	public BooleanProperty getIsFlammable() {
		return this.isFlammable;
	}
	
	/**
	 * Returns the selected user property
	 * 
	 * @return the selected user property
	 */
	public ObjectProperty<String> getSelectedUser() {
		return this.selectedUser;
	}

	/**
	 * Returns the list of users
	 * 
	 * @return the list of users
	 */
	public ArrayList<String> getUsers() {
		return this.users;
	}
}
