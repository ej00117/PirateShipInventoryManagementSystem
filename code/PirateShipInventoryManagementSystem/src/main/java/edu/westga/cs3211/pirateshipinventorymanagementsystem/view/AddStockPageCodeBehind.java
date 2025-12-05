package edu.westga.cs3211.pirateshipinventorymanagementsystem.view;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import edu.westga.cs3211.pirateshipinventorymanagementsystem.enums.Condition;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.enums.ItemCategory;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.model.ChangeHistory;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.model.Inventory;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.model.Stock;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.viewmodel.AddStockPageViewModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
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
    private ComboBox<ItemCategory> categoryComboBox;

    @FXML
    private ComboBox<Condition> conditionComboBox;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField quantityTextField;

    @FXML
    private DatePicker expirationDatePicker;

    @FXML
    private Button submitButton;

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
            URL fxmlLocation = getClass().getResource(
                "/edu/westga/cs3211/pirateshipinventorymanagementsystem/view/addstocklistpage.fxml"
            );
            FXMLLoader loader = new FXMLLoader(fxmlLocation);

            Stock stockToAdd = this.viewModel.prepareStockForInventory();

            AddStockListPageCodeBehind controller = new AddStockListPageCodeBehind(
                this.viewModel.getInventory().getCompartmentsAtType(
                    stockToAdd.getSpecialQualities(),
                    stockToAdd.getQuantity()
                ),
                this.viewModel.getHistory(),
                stockToAdd,
                this.viewModel.getUser()
            );

            loader.setController(controller);
            AnchorPane page = loader.load();

            Stage newStage = new Stage();
            newStage.setTitle("Select Compartment");
            newStage.setScene(new Scene(page));
            newStage.show();
        } catch (Exception exc) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setContentText(exc.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    void initialize() {
        this.initializeComboBoxes();
        this.bindComponentsToViewModel();
        this.expirationDatePicker.setDisable(true);

        this.checkPerishable.selectedProperty().addListener((obs, oldV, newV) -> {
            this.expirationDatePicker.setDisable(!newV);
            this.updateSubmitButtonState();
        });

        this.nameTextField.textProperty().addListener((obs, o, n) -> this.updateSubmitButtonState());
        this.quantityTextField.textProperty().addListener((obs, o, n) -> this.updateSubmitButtonState());
        this.expirationDatePicker.valueProperty().addListener((obs, o, n) -> this.updateSubmitButtonState());

        this.submitButton.setDisable(true);
    }

    private void initializeComboBoxes() {
        ArrayList<Condition> conditions = new ArrayList<>();
        conditions.add(Condition.PERFECT);
        conditions.add(Condition.USABLE);
        conditions.add(Condition.UNUSABLE);
        this.conditionComboBox.setItems(FXCollections.observableArrayList(conditions));
        this.conditionComboBox.setValue(Condition.PERFECT);

        ArrayList<ItemCategory> categories = new ArrayList<>();
        categories.add(ItemCategory.OTHER);
        categories.add(ItemCategory.FOOD);
        categories.add(ItemCategory.MUNITIONS);
        this.categoryComboBox.setItems(FXCollections.observableArrayList(categories));
        this.categoryComboBox.setValue(ItemCategory.OTHER);
    }

    private void updateSubmitButtonState() {
        boolean validBase = !this.nameTextField.getText().isBlank()
                && !this.quantityTextField.getText().isBlank();

        if (!validBase) {
            this.submitButton.setDisable(true);
            return;
        }

        if (this.checkPerishable.isSelected()) {
            boolean hasDate = this.expirationDatePicker.getValue() != null;
            this.submitButton.setDisable(!hasDate);
            return;
        }

        this.submitButton.setDisable(false);
    }

    private void bindComponentsToViewModel() {
        this.nameTextField.textProperty().bindBidirectional(this.viewModel.getName());
        this.quantityTextField.textProperty().bindBidirectional(this.viewModel.getQuantity());
        this.expirationDatePicker.valueProperty().bindBidirectional(this.viewModel.getExpirationDate());

        this.checkPerishable.selectedProperty().bindBidirectional(this.viewModel.getIsPerishable());
        this.checkLiquid.selectedProperty().bindBidirectional(this.viewModel.getIsLiquid());
        this.checkFlammable.selectedProperty().bindBidirectional(this.viewModel.getIsFlammable());

        this.conditionComboBox.valueProperty().addListener((obs, oldV, newV) -> {
            if (newV != null) {
                this.viewModel.getSelectedCondition().set(newV);
            }
        });

        this.categoryComboBox.valueProperty().addListener((obs, oldV, newV) -> {
            if (newV != null) {
                this.viewModel.getSelectedCategory().set(newV);
            }
        });
    }
}
