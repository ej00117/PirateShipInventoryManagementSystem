package edu.westga.cs3211.pirateshipinventorymanagementsystem.view;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.model.ChangeHistory;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.model.Compartment;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.model.Stock;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.viewmodel.AddStockListPageViewModel;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

/**
 * Add stock compartment selection list page code behind
 * 
 * @author Ezra Jones
 * @version Fall 2025
 */
public class AddStockListPageCodeBehind {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backButton;

    @FXML
    private ListView<Compartment> compartmentListView;

    @FXML
    private Button submitButton;
    
    private AddStockListPageViewModel viewModel;
    
    /**
     * Instantiates a new add stock list compartments page view model
     * 
     * @param compartments the compartments to choose from
     * @param history the history to log the changes in
     * @param stockToAdd the stock to add
     */
    public AddStockListPageCodeBehind(ArrayList<Compartment> compartments, ChangeHistory history, Stock stockToAdd) {
    	this.viewModel = new AddStockListPageViewModel(compartments, history, stockToAdd);
    }
    
    @FXML
    void onAdd(ActionEvent event) {
    	this.viewModel.addStock();
    	
    	Stage stage = (Stage) this.submitButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void onCancel(ActionEvent event) {
    	Stage stage = (Stage) this.submitButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void initialize() {
        assert this.backButton != null : "fx:id=\"backButton\" was not injected: check your FXML file 'addstocklistpage.fxml'.";
        assert this.compartmentListView != null : "fx:id=\"compartmentListView\" was not injected: check your FXML file 'addstocklistpage.fxml'.";
        assert this.submitButton != null : "fx:id=\"submitButton\" was not injected: check your FXML file 'addstocklistpage.fxml'.";
        this.bindComponentsToViewModel();
    }
    
    private void bindComponentsToViewModel() {
    	this.compartmentListView.setItems(this.viewModel.getCompartments());

    	this.compartmentListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                this.viewModel.getSelectedCompartment().set(newValue); 
            }
        });

        this.submitButton.disableProperty().bind(Bindings.isNull(this.viewModel.getSelectedCompartment()));
    }

}
