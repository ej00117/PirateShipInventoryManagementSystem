package edu.westga.cs3211.pirateshipinventorymanagementsystem.view;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import edu.westga.cs3211.pirateshipinventorymanagementsystem.enums.Condition;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.model.ChangeHistory;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.model.Inventory;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.model.Stock;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.viewmodel.AddStockPageViewModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Add stock page code behind
 * 
 * @author Ezra Jones
 * @version Fall 2025
 */
public class AddStockPageCodeBehind {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button cancelButton;

	@FXML
	private CheckBox checkFlammable;

	@FXML
	private CheckBox checkLiquid;

	@FXML
	private CheckBox checkPerishable;

	@FXML
	private ComboBox<Condition> conditionComboBox;

	@FXML
	private TextField dayTextField;

	@FXML
	private TextField monthTextField;

	@FXML
	private TextField nameTextField;

	@FXML
	private TextField quantityTextField;

	@FXML
	private Button submitButton;

	@FXML
	private TextField yearTextField;

	private AddStockPageViewModel viewModel;

	/**
	 * Instantiates a new add stock page view model
	 * 
	 * @param inventory the inventory to add stock to
	 * @param history the history to log the change in
	 * @param user the user attempting to add 
	 */
	public AddStockPageCodeBehind(Inventory inventory, ChangeHistory history, String user) {
		this.viewModel = new AddStockPageViewModel(inventory, history, user);
	}

	@FXML
	void onCancel(ActionEvent event) {
		Stage stage = (Stage) this.submitButton.getScene().getWindow();
		stage.close();
	}

	@FXML
	void onSubmit(ActionEvent event) {
		try {
			URL fxmlLocation = getClass().getResource("/edu/westga/cs3211/pirateshipinventorymanagementsystem/view/addstocklistpage.fxml");
			FXMLLoader loader = new FXMLLoader(fxmlLocation);
			Stock stockToAdd = this.viewModel.prepareStockForInventory();
			AddStockListPageCodeBehind addStockListPageCodeBehind = new AddStockListPageCodeBehind(
					this.viewModel.getInventory().getCompartmentsAtType(stockToAdd.getSpecialQualities(), stockToAdd.getQuantity()),
					this.viewModel.getHistory(), stockToAdd, this.viewModel.getUser());
			loader.setController(addStockListPageCodeBehind);

			AnchorPane addStockListPage = loader.load();

			Stage newStage = new Stage();
			newStage.setTitle("AddStockListCompartmentsPage");
			Scene scene = new Scene(addStockListPage);
			newStage.setScene(scene);
			newStage.show();
		} catch (Exception exc) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setContentText(exc.getMessage());
			alert.showAndWait();
		}
	}

	@FXML
	void initialize() {
		assert this.cancelButton != null : "fx:id=\"cancelButton\" was not injected: check your FXML file 'addstockpage.fxml'.";
		assert this.checkFlammable != null : "fx:id=\"checkFlammable\" was not injected: check your FXML file 'addstockpage.fxml'.";
		assert this.checkLiquid != null : "fx:id=\"checkLiquid\" was not injected: check your FXML file 'addstockpage.fxml'.";
		assert this.checkPerishable != null : "fx:id=\"checkPerishable\" was not injected: check your FXML file 'addstockpage.fxml'.";
		assert this.conditionComboBox != null : "fx:id=\"conditionComboBox\" was not injected: check your FXML file 'addstockpage.fxml'.";
		assert this.dayTextField != null : "fx:id=\"dayTextField\" was not injected: check your FXML file 'addstockpage.fxml'.";
		assert this.monthTextField != null : "fx:id=\"monthTextField\" was not injected: check your FXML file 'addstockpage.fxml'.";
		assert this.nameTextField != null : "fx:id=\"nameTextField\" was not injected: check your FXML file 'addstockpage.fxml'.";
		assert this.quantityTextField != null : "fx:id=\"quantityTextField\" was not injected: check your FXML file 'addstockpage.fxml'.";
		assert this.submitButton != null : "fx:id=\"submitButton\" was not injected: check your FXML file 'addstockpage.fxml'.";
		assert this.yearTextField != null : "fx:id=\"yearTextField\" was not injected: check your FXML file 'addstockpage.fxml'.";

		ArrayList<Condition> conditions = new ArrayList<Condition>();
		conditions.add(Condition.PERFECT);
		conditions.add(Condition.USABLE);
		conditions.add(Condition.UNUSABLE);
		ObservableList<Condition> observableConditions = FXCollections.observableArrayList(conditions);
		this.conditionComboBox.setItems(observableConditions);
		this.conditionComboBox.setValue(observableConditions.get(0));
		this.bindComponentsToViewModel();

		this.setExpirationDateFields(true);
		this.checkPerishable.selectedProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue) {
				this.setExpirationDateFields(false);
			} else {
				this.setExpirationDateFields(true);
			}
		});

		this.submitButton.setDisable(true);
		this.setUpTextFieldListeners();
		this.setUpExpirationTextFieldListeners();
	}

	private void setUpTextFieldListeners() {
		this.nameTextField.textProperty().addListener((observable, oldValue, newValue) -> {
			this.updateSubmitButtonState();
		});

		this.quantityTextField.textProperty().addListener((observable, oldValue, newValue) -> {
			this.updateSubmitButtonState();
		});
	}

	private void setUpExpirationTextFieldListeners() {
		this.yearTextField.textProperty().addListener((observable, oldValue, newValue) -> {
			this.updateSubmitButtonState();
		});

		this.monthTextField.textProperty().addListener((observable, oldValue, newValue) -> {
			this.updateSubmitButtonState();
		});
		this.dayTextField.textProperty().addListener((observable, oldValue, newValue) -> {
			this.updateSubmitButtonState();
		});
		
		this.checkPerishable.selectedProperty().addListener((observable, oldValue, newValue) -> {
		    this.setExpirationDateFields(!newValue); 
		    this.updateSubmitButtonState();
		});
	}

	private void updateSubmitButtonState() {
		if (!this.nameTextField.getText().isBlank() && !this.quantityTextField.getText().isBlank()) {
			this.submitButton.setDisable(false);
		} else {
			this.submitButton.setDisable(true);
		}

		if (this.checkPerishable.isSelected()) {
			if (!this.yearTextField.getText().isBlank() 
					&& !this.monthTextField.getText().isBlank() 
					&& !this.dayTextField.getText().isBlank()) {
				this.submitButton.setDisable(false);
			} else {
				this.submitButton.setDisable(true);
			}
		}
	}

	private void setExpirationDateFields(boolean bool) {
		this.dayTextField.setDisable(bool);
		this.monthTextField.setDisable(bool);
		this.yearTextField.setDisable(bool);
	}

	private void bindComponentsToViewModel() {
		this.nameTextField.textProperty().bindBidirectional(this.viewModel.getName());
		this.quantityTextField.textProperty().bindBidirectional(this.viewModel.getQuantity());
		this.yearTextField.textProperty().bindBidirectional(this.viewModel.getYear());
		this.monthTextField.textProperty().bindBidirectional(this.viewModel.getMonth());
		this.dayTextField.textProperty().bindBidirectional(this.viewModel.getDay());
		this.checkPerishable.selectedProperty().bindBidirectional(this.viewModel.getIsPerishable());
		this.checkLiquid.selectedProperty().bindBidirectional(this.viewModel.getIsLiquid());
		this.checkFlammable.selectedProperty().bindBidirectional(this.viewModel.getIsFlammable());

		this.conditionComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue != null) {
				this.viewModel.getSelectedCondition().set(newValue);
			}
		});
	}

}
