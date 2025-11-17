package edu.westga.cs3211.pirateshipinventorymanagementsystem.view;

import java.net.URL;
import java.util.ResourceBundle;

import edu.westga.cs3211.pirateshipinventorymanagementsystem.model.Authenticator;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.viewmodel.LandingPageViewModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Landing page code behind
 * 
 * @author Ezra Jones
 * @version Fall 2025
 */
public class LandingPageCodeBehind {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addStockButton;

    @FXML
    private Button viewHistoryButton;

    @FXML
    private Label yourNameLabel;

    @FXML
    private Label yourRolesLabel;
    
    private LandingPageViewModel viewModel;
    
    /**
     * Instantiates a new landing page code behind
     * 
     * @param authenticator the authenticator for authenticating role privileges
     * @param name the name of the user logged in
     * @param password the password of the user logged in
     */
    public LandingPageCodeBehind(Authenticator authenticator, String name, String password) {
    	this.viewModel = new LandingPageViewModel(authenticator, name, password);
    }
    
    @FXML
    void onAddStock(ActionEvent event) {
    	try {
            URL fxmlLocation = getClass().getResource("/edu/westga/cs3211/pirateshipinventorymanagementsystem/view/addstockpage.fxml");
            FXMLLoader loader = new FXMLLoader(fxmlLocation);

            AddStockPageCodeBehind addStockPageCodeBehind = new AddStockPageCodeBehind(
                this.viewModel.getInventory(),
                this.viewModel.getHistory()
            );
            loader.setController(addStockPageCodeBehind);

            AnchorPane addStockPage = loader.load();
            
            Stage newStage = new Stage();
            newStage.setTitle("LandingPage");
            Scene scene = new Scene(addStockPage);
            newStage.setScene(scene);
            newStage.show();
		} catch (Exception exc) {
			exc.printStackTrace();
		}
    }

    @FXML
    void onViewHistory(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert this.addStockButton != null : "fx:id=\"addStockButton\" was not injected: check your FXML file 'landingpage.fxml'.";
        assert this.viewHistoryButton != null : "fx:id=\"viewHistoryButton\" was not injected: check your FXML file 'landingpage.fxml'.";
        assert this.yourNameLabel != null : "fx:id=\"yourNameLabel\" was not injected: check your FXML file 'landingpage.fxml'.";
        assert this.yourRolesLabel != null : "fx:id=\"yourRolesLabel\" was not injected: check your FXML file 'landingpage.fxml'.";
        this.bindComponentsToViewModel();
        this.viewModel.setLandingPageUserDetails();
        this.enableButtonsBasedOnRoles();
    }
    
    private void bindComponentsToViewModel() {
    	this.yourNameLabel.textProperty().bind(this.viewModel.getYourNameProperty());
    	this.yourRolesLabel.textProperty().bind(this.viewModel.getYourRolesProperty());
    }
    
    private void enableButtonsBasedOnRoles() {
    	this.addStockButton.setDisable(true);
    	this.viewHistoryButton.setDisable(true);
    	
    	if (this.viewModel.checkIfCrewmate()) {
    		this.addStockButton.setDisable(false);
    	}
    	if (this.viewModel.checkIfQuartermaster()) {
    		this.viewHistoryButton.setDisable(false);
    	}
    }

}
