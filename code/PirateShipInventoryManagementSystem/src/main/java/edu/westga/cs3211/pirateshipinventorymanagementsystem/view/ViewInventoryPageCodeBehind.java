package edu.westga.cs3211.pirateshipinventorymanagementsystem.view;

import java.net.URL;
import java.util.ResourceBundle;

import edu.westga.cs3211.pirateshipinventorymanagementsystem.model.Authenticator;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.model.ChangeHistory;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.model.Compartment;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.model.Inventory;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.model.Stock;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.viewmodel.ViewInventoryPageViewModel;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * Code behind for the view inventory page
 * 
 * @author Ryan Stubbs
 * @version Fall 2025
 */
public class ViewInventoryPageCodeBehind {
	
	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    @FXML
    private ListView<Stock> compartmentListView;
    
    @FXML
    private ListView<Compartment> inventoryListView;

    @FXML
    private TextField selectedCompartmentTextField;

    @FXML
    private Text viewStockUserLabel;
    
    private ViewInventoryPageViewModel viewModel;
    
    private Authenticator auth = new Authenticator();
    
    private String username;
    
    private String password;
    
    /**
	 * Instantiates a new view inventory page view model
	 * 
	 * @param inventory the inventory to add stock to
	 * @param history the history to log the change in
	 * @param username username of user logged in
	 * @param password password of user logged in
	 */
    public ViewInventoryPageCodeBehind(Inventory inventory, ChangeHistory history, String username, String password) {
    	this.viewModel = new ViewInventoryPageViewModel(inventory, history, username, password);
    	this.username = username;
    	this.password = password;
    }

    @FXML
    void initialize() {
        assert this.inventoryListView != null : "fx:id=\"InventoryListView\" was not injected: check your FXML file 'viewinventorypage.fxml'.";
        assert this.selectedCompartmentTextField != null : "fx:id=\"selectedCompartmentTextField\" was not injected: check your FXML file 'viewinventorypage.fxml'.";
        assert this.viewStockUserLabel != null : "fx:id=\"viewStockUserLabel\" was not injected: check your FXML file 'viewinventorypage.fxml'.";
        assert this.viewStockUserLabel != null : "fx:id=\"viewStockUserLabel\" was not injected: check your FXML file 'viewinventorypage.fxml'.";
        this.viewStockUserLabel.setText(this.viewStockUserLabel.getText() + this.auth.getRolesForUser(this.username, this.password).toString());
        this.setupInventoryPageBindings();
    }
    
    private void setupInventoryPageBindings() {
    	this.inventoryListView.setItems(this.viewModel.getCompartments());
    	this.inventoryListView.getSelectionModel().selectedItemProperty().addListener(
    	(obs, oldComp, newComp) -> {
    		if (newComp != null) {
    			this.compartmentListView.setItems(this.viewModel.getStock(newComp));
    		} else {
    			this.compartmentListView.setItems(FXCollections.observableArrayList());
    		}
    	});
    	this.selectedCompartmentTextField.textProperty().bind(Bindings.createStringBinding(() -> String.valueOf(this.compartmentListView.getSelectionModel().getSelectedItem()), this.compartmentListView.getSelectionModel().selectedItemProperty()));
    }
}
