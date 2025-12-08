package edu.westga.cs3211.pirateshipinventorymanagementsystem.view;

import java.net.URL;
import java.util.ResourceBundle;

import edu.westga.cs3211.pirateshipinventorymanagementsystem.model.Authenticator;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.model.ChangeHistory;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.model.Compartment;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.model.ExpirationDisplayItem;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.model.Inventory;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.model.Stock;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.viewmodel.ViewInventoryPageViewModel;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
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
    
    @FXML
    private TextField quantityTextField;

    @FXML
    private Button removeStockButton;
    
    @FXML
    private CheckBox showExpirationCheckBox;
    
    @FXML
    private ListView<ExpirationDisplayItem> expirationListView;
    
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
    void onRemoveStock(ActionEvent event) {
    	if (this.compartmentListView.getSelectionModel().getSelectedItem() != null) {
    		int quantity;
    		try {
    			quantity = Integer.parseInt(this.quantityTextField.getText());
    			this.viewModel.removeStock(this.compartmentListView.getSelectionModel().getSelectedItem(), this.inventoryListView.getSelectionModel().getSelectedItem(), quantity);
    			this.refreshListViews();
    		} catch (NumberFormatException ex) {
    			Alert alert = new Alert(AlertType.INFORMATION);
        		alert.setTitle("Invalid Quantity Input");
        		alert.setHeaderText("Invalid Quantity");
        		alert.setContentText("Quantity must be a whole number!");
        		alert.showAndWait();
    		} catch (Exception ex) {
    			Alert alert = new Alert(AlertType.INFORMATION);
        		alert.setTitle("Invalid Quantity Input");
        		alert.setHeaderText("Invalid Quantity");
        		alert.setContentText(ex.getMessage());
        		alert.showAndWait();
    		}
    		
    	} else {
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("Cannot Remove Stock");
    		alert.setHeaderText("No Stock Item Chosen");
    		alert.setContentText("Please select a stock item to remove and try again.");
    		alert.showAndWait();
    	}
    }

    @FXML
    void initialize() {
        assert this.inventoryListView != null : "fx:id=\"InventoryListView\" was not injected: check your FXML file 'viewinventorypage.fxml'.";
        assert this.selectedCompartmentTextField != null : "fx:id=\"selectedCompartmentTextField\" was not injected: check your FXML file 'viewinventorypage.fxml'.";
        assert this.viewStockUserLabel != null : "fx:id=\"viewStockUserLabel\" was not injected: check your FXML file 'viewinventorypage.fxml'.";
        assert this.viewStockUserLabel != null : "fx:id=\"viewStockUserLabel\" was not injected: check your FXML file 'viewinventorypage.fxml'.";
        assert this.quantityTextField != null : "fx:id=\"quantityTextField\" was not injected: check your FXML file 'viewinventorypage.fxml'.";
        assert this.removeStockButton != null : "fx:id=\"removeStockButton\" was not injected: check your FXML file 'viewinventorypage.fxml'.";
        this.showExpirationCheckBox.selectedProperty().addListener(
        	    (obs, oldVal, newVal) -> this.switchToExpirationReport(newVal)
        	);
        this.viewStockUserLabel.setText(this.viewStockUserLabel.getText() + this.auth.getRolesForUser(this.username, this.password).toString());
        this.setupInventoryPageBindings();
    }

	private void switchToExpirationReport(boolean showReport) {
		if (showReport) {
	        this.compartmentListView.setVisible(false);
	        this.expirationListView.setVisible(true);
	        this.expirationListView.setItems(this.viewModel.getExpirationReport());
	        this.removeStockButton.setDisable(true);
	    } else {
	        this.expirationListView.setVisible(false);
	        this.compartmentListView.setVisible(true);
	        this.removeStockButton.setDisable(false);
	    }
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

    private void refreshListViews() {
    	this.inventoryListView.setItems(this.viewModel.getCompartments());
    }
}