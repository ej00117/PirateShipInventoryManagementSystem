package edu.westga.cs3211.pirateshipinventorymanagementsystem.viewmodel;

import java.util.ArrayList;

import edu.westga.cs3211.pirateshipinventorymanagementsystem.model.ChangeHistory;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.model.Compartment;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.model.Stock;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * View model for the add stock list page
 * 
 * @author Ezra Jones
 * @version Fall 2025
 */
public class AddStockListPageViewModel {
	private ObservableList<Compartment> compartments;
	private ObjectProperty<Compartment> selectedCompartment;
	private Stock stockToAdd;
	
	/**
	 * Instantiates a new add stock compartment list page view model
	 * 
	 * @param compartments the compartments to list out
	 * @param history the history to log changes to
	 * @param stockToAdd the stock to add
	 */
	public AddStockListPageViewModel(ArrayList<Compartment> compartments, ChangeHistory history, Stock stockToAdd) {
		this.compartments = FXCollections.observableArrayList(compartments);
		this.selectedCompartment = new SimpleObjectProperty<Compartment>(this.compartments.getFirst());
		this.stockToAdd = stockToAdd;
	}
	
	/**
	 * Adds stock to the selected compartment
	 */
	public void addStock() {
		this.selectedCompartment.get().addStock(this.stockToAdd);
	}

	/**
	 * Returns the selected compartment property
	 * 
	 * @return the selected compartment property
	 */
	public ObjectProperty<Compartment> getSelectedCompartment() {
		return this.selectedCompartment;
	}
	
	/**
	 * Returns the compartments property
	 * 
	 * @return the compartments property
	 */
	public ObservableList<Compartment> getCompartments() {
		return this.compartments;
	}
}
