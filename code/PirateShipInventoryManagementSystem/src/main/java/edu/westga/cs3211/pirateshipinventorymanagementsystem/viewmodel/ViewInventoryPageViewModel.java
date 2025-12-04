package edu.westga.cs3211.pirateshipinventorymanagementsystem.viewmodel;

import edu.westga.cs3211.pirateshipinventorymanagementsystem.enums.ItemCategory;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.enums.Role;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.model.Authenticator;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.model.ChangeHistory;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.model.Compartment;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.model.Inventory;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.model.Stock;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * View Model for the View Inventory Page
 * 
 * @author Ryan Stubbs
 * @version Fall 2025
 */
public class ViewInventoryPageViewModel {
	private Inventory inventory;
	@SuppressWarnings("unused")
	private ChangeHistory history;
	private String username;
	private String password;
	private Authenticator auth;
	
	/**
	 * Instantiates a new ViewInventoryPageViewModel object with inventory, history, username, and password
	 * 
	 * @param inventory the inventory of the system
	 * @param history the history log of the inventory
	 * @param username	the username of the signed in user
	 * @param password	the password of the signed in user
	 */
	public ViewInventoryPageViewModel(Inventory inventory, ChangeHistory history, String username, String password) {
		this.inventory = inventory;
		this.history = history;
		this.username = username;
		this.password = password;
		this.auth = new Authenticator();
	}
	
	/**
	 * Returns an ObservableList of the compartments within the inventory.
	 * 
	 * @return an Observable List of Compartments in the inventory
	 */
	public ObservableList<Compartment> getCompartments() {
		return FXCollections.observableArrayList(this.inventory.getCompartments());
	}
	
	/**
	 * Returns an ObservableList of the stock items within the compartment.
	 * 
	 * @param compartment the compartment to return the list of stock items from
	 * @return ObservableList of the stock items within the given compartment.
	 */
	public ObservableList<Stock> getStock(Compartment compartment) {
		if (compartment == null) {
			return FXCollections.observableArrayList();
		} else if (this.auth.getRolesForUser(this.username, this.password).contains(Role.QUARTERMASTER)) {
			return FXCollections.observableArrayList(compartment.getItems());
		} else if (this.auth.getRolesForUser(this.username, this.password).contains(Role.COOK)) {
			return FXCollections.observableArrayList(compartment.getItems().stream().filter(stock -> stock.getCategory() == ItemCategory.FOOD).toList());
		}
		return FXCollections.observableArrayList(compartment.getItems());
	}
	
	/**
	 * Removes a quantity of stock from a stock item within the given compartment.
	 * 
	 * @param stock the stock item remove quantity from
	 * @param compartment the compartment that the stock item is held in
	 * @param quantity the quantity of the stock item to remove
	 */
	public void removeStock(Stock stock, Compartment compartment, int quantity) {
		if (quantity < 1 || quantity > 300) {
			throw new IllegalArgumentException("Quantity Must Be Greater Than 0 and Less than 300!");
		} else if (quantity > stock.getQuantity()) {
			throw new IllegalArgumentException("Removal Quantity Must be Less than or Equal To Stock's Quantity");
		} else if (stock.getQuantity() == quantity) {
			compartment.removeStock(stock);
		} else {
			stock.setQuantity(stock.getQuantity() - quantity);
		}
	}
}
