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
import javafx.scene.control.ListView;

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
    private ComboBox<String> userComboBox;
    
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

    }

    @FXML
    void initialize() {
        assert this.backButton != null : "fx:id=\"backButton\" was not injected: check your FXML file 'viewchangehistory.fxml'.";
        assert this.changeLogListView != null : "fx:id=\"changeLogListView\" was not injected: check your FXML file 'viewchangehistory.fxml'.";
        assert this.isFlammable != null : "fx:id=\"isFlammable\" was not injected: check your FXML file 'viewchangehistory.fxml'.";
        assert this.isLiquid != null : "fx:id=\"isLiquid\" was not injected: check your FXML file 'viewchangehistory.fxml'.";
        assert this.isPerishable != null : "fx:id=\"isPerishable\" was not injected: check your FXML file 'viewchangehistory.fxml'.";
        assert this.userComboBox != null : "fx:id=\"userComboBox\" was not injected: check your FXML file 'viewchangehistory.fxml'.";
        this.bindComponentsToViewModel();
    }
    
    private void bindComponentsToViewModel() {
    	this.changeLogListView.setItems(this.viewModel.getLogs());
    }

}
