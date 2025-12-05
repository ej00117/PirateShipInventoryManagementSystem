package edu.westga.cs3211.pirateshipinventorymanagementsystem.view;

import java.net.URL;
import java.util.ResourceBundle;

import edu.westga.cs3211.pirateshipinventorymanagementsystem.model.ChangeHistory;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.model.ChangeLog;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.viewmodel.ViewChangeHistoryViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

/**
 * Code behind for the view change history page
 * 
 * @author Ezra Jones
 * @version Fall 2025
 */
public class ViewChangeHistoryCodeBehind {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backButton;

    @FXML
    private ListView<ChangeLog> changeLogListView;

    @FXML
    private CheckBox isFlammable;

    @FXML
    private CheckBox isLiquid;

    @FXML
    private CheckBox isPerishable;

    @FXML
    private Button resetButton;

    @FXML
    private Button sortButton;

    @FXML
    private ComboBox<String> userComboBox;

    @FXML
    private DatePicker startExpirationDatePicker;

    @FXML
    private DatePicker endExpirationDatePicker;

    @FXML
    private Button expirationFilterButton;

    private ViewChangeHistoryViewModel viewModel;

    /**
     * Instantiates a new view change history view model
     * 
     * @param history the history of logs
     */
    public ViewChangeHistoryCodeBehind(ChangeHistory history) {
        this.viewModel = new ViewChangeHistoryViewModel(history);
    }

    @FXML
    void onCancel(ActionEvent event) {
        Stage stage = (Stage) this.resetButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void onReset(ActionEvent event) {
        this.viewModel.resetSelectedUsers();
    }

    @FXML
    void onSort(ActionEvent event) {
        this.viewModel.sortListBasedOnUser();
    }

    @FXML
    void onFilterByExpiration(ActionEvent event) {
        this.viewModel.filterByExpirationDateRange();
    }

    @FXML
    void initialize() {
        this.bindComponentsToViewModel();
        this.setUpCheckBoxListeners();
        this.userComboBox.setItems(javafx.collections.FXCollections.observableArrayList(this.viewModel.getUsers()));

        this.sortButton.setDisable(true);
        this.userComboBox.setOnAction(event -> this.checkSelection());

        this.expirationFilterButton.setDisable(true);
        this.startExpirationDatePicker.valueProperty().addListener((o, ov, nv) -> this.updateExpirationButton());
        this.endExpirationDatePicker.valueProperty().addListener((o, ov, nv) -> this.updateExpirationButton());
    }

    private void updateExpirationButton() {
        boolean ready = this.startExpirationDatePicker.getValue() != null
                     && this.endExpirationDatePicker.getValue() != null;
        this.expirationFilterButton.setDisable(!ready);
    }

    private void checkSelection() {
        if (this.userComboBox.getSelectionModel().getSelectedItem() != null) {
            this.sortButton.setDisable(false);
        } else {
            this.sortButton.setDisable(true);
        }
    }

    private void bindComponentsToViewModel() {
        this.changeLogListView.setItems(this.viewModel.getLogs());
        this.isPerishable.selectedProperty().bindBidirectional(this.viewModel.getIsPerishable());
        this.isLiquid.selectedProperty().bindBidirectional(this.viewModel.getIsLiquid());
        this.isFlammable.selectedProperty().bindBidirectional(this.viewModel.getIsFlammable());
        this.startExpirationDatePicker.valueProperty().bindBidirectional(this.viewModel.getStartExpirationDate());
        this.endExpirationDatePicker.valueProperty().bindBidirectional(this.viewModel.getEndExpirationDate());
    }

    private void setUpCheckBoxListeners() {
        this.isFlammable.selectedProperty().addListener((o, ov, nv) -> this.viewModel.sortListBasedOnSpecialQualities());
        this.isLiquid.selectedProperty().addListener((o, ov, nv) -> this.viewModel.sortListBasedOnSpecialQualities());
        this.isPerishable.selectedProperty().addListener((o, ov, nv) -> this.viewModel.sortListBasedOnSpecialQualities());
    }
}
