package edu.westga.cs3211.pirateshipinventorymanagementsystem.view;

import java.net.URL;
import java.util.ResourceBundle;

import edu.westga.cs3211.pirateshipinventorymanagementsystem.viewmodel.LoginViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * Login code behind
 * 
 * @author Ezra Jones
 * @version Fall 2025
 */
public class LoginCodeBehind {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label nameLabel;

    @FXML
    private TextField nameTextField;

    @FXML
    private Label passwordLabel;

    @FXML
    private TextField passwordTextField;

    @FXML
    private Button submitButton;
    
    private LoginViewModel viewModel;
    
    /**
     * Instantiates a new login code behind
     * 
     * @precondition none
     * @postcondition none
     */
    public LoginCodeBehind() {
    	this.viewModel = new LoginViewModel();
    }

    @FXML
    void onSubmit(ActionEvent event) {
    	if (!this.viewModel.submitCredentials()) {
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("Please Acknowledge");
    		alert.setHeaderText("Account could not be found");
    		alert.setContentText("No account could be found matching the credentials provided.\nPlease try again.");
    		alert.showAndWait();
    	}
    }

    @FXML
    void initialize() {
        assert this.nameLabel != null : "fx:id=\"nameLabel\" was not injected: check your FXML file 'login.fxml'.";
        assert this.nameTextField != null : "fx:id=\"nameTextField\" was not injected: check your FXML file 'login.fxml'.";
        assert this.passwordLabel != null : "fx:id=\"passwordLabel\" was not injected: check your FXML file 'login.fxml'.";
        assert this.passwordTextField != null : "fx:id=\"passwordTextField\" was not injected: check your FXML file 'login.fxml'.";
        assert this.submitButton != null : "fx:id=\"submitButton\" was not injected: check your FXML file 'login.fxml'.";
        this.bindComponentsToViewModel();
        
        this.submitButton.setDisable(true);
        this.nameTextField.textProperty().addListener((observable, oldValue, newValue) -> this.checkName());
        this.passwordTextField.textProperty().addListener((observable, oldValue, newValue) -> this.checkPassword());
    }
    
    private void bindComponentsToViewModel() {
    	this.nameTextField.textProperty().bindBidirectional(this.viewModel.getNameProperty());
    	this.passwordTextField.textProperty().bindBidirectional(this.viewModel.getPasswordProperty());
    }
    
    private void checkName() {
    	if (this.nameTextField.getText().isBlank()) {
    		this.nameLabel.setText("Username must not be empty or blank");
    		this.submitButton.setDisable(true);
    	} else {
    		this.nameLabel.setText("");
    		this.checkCredentialsReadyForSubmit();
    	}
    }
    
    private void checkPassword() {
    	if (this.passwordTextField.getText().isBlank()) {
    		this.passwordLabel.setText("Username must not be empty or blank");
    		this.submitButton.setDisable(true);
    	} else {
    		this.passwordLabel.setText("");
    		this.checkCredentialsReadyForSubmit();
    	}
    }
    
    private void checkCredentialsReadyForSubmit() {
    	if (!this.passwordTextField.getText().isBlank() && !this.nameTextField.getText().isBlank()) {
    		this.submitButton.setDisable(false);
    	}
    }

}
