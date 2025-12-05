package edu.westga.cs3211.pirateshipinventorymanagementsystem.viewmodel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;

import edu.westga.cs3211.pirateshipinventorymanagementsystem.enums.SpecialQuality;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.model.ChangeHistory;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.model.ChangeLog;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * View model for view change history page
 * 
 * @author Ezra Jones
 * @version Fall 2025
 */
public class ViewChangeHistoryViewModel {
    
    private ObservableList<ChangeLog> logs;
    private ChangeHistory history;
    private BooleanProperty isPerishable;
    private BooleanProperty isLiquid;
    private BooleanProperty isFlammable;
    private ObjectProperty<String> selectedUser;
    private ArrayList<String> users;
    private ArrayList<String> selectedUsers;
    private ArrayList<ChangeLog> allLogs;
    private ArrayList<ChangeLog> filteredLogs;

    private ObjectProperty<LocalDate> startExpirationDate;
    private ObjectProperty<LocalDate> endExpirationDate;

    /**
     * Instantiates a new view change history view model
     * 
     * @param history the change history
     */
    public ViewChangeHistoryViewModel(ChangeHistory history) {
        this.history = history;
        this.logs = FXCollections.observableArrayList(this.history.getHistory());
        this.isPerishable = new SimpleBooleanProperty(false);
        this.isLiquid = new SimpleBooleanProperty(false);
        this.isFlammable = new SimpleBooleanProperty(false);
        this.selectedUser = new SimpleObjectProperty<String>();
        this.users = new ArrayList<String>();
        this.allLogs = this.history.getHistory();
        this.filteredLogs = this.allLogs;
        this.getUsersForComboBox();
        this.selectedUsers = new ArrayList<String>();

        this.startExpirationDate = new SimpleObjectProperty<>(null);
        this.endExpirationDate = new SimpleObjectProperty<>(null);
    }

    /**
     * Sorts list based on qualities selected
     */
    public void sortListBasedOnSpecialQualities() {
        ArrayList<SpecialQuality> qualities = new ArrayList<>();
        if (this.isPerishable.get()) {
            qualities.add(SpecialQuality.PERISHABLE);
        }
        if (this.isLiquid.get()) {
            qualities.add(SpecialQuality.LIQUID);
        }
        if (this.isFlammable.get()) {
            qualities.add(SpecialQuality.FLAMMABLE);
        }
        if (!qualities.isEmpty()) {
            ArrayList<ChangeLog> matchingLogs = new ArrayList<>();
            for (ChangeLog log : this.allLogs) {
                HashSet<SpecialQuality> logQualities = new HashSet<>(log.getStockInfo().getSpecialQualities());
                HashSet<SpecialQuality> selectedQualities = new HashSet<>(qualities);
                if (logQualities.equals(selectedQualities)) {
                    matchingLogs.add(log);
                }
            }
            if (!matchingLogs.isEmpty()) {
                this.logs.setAll(FXCollections.observableArrayList(matchingLogs));
            } else {
                this.logs.setAll(FXCollections.observableArrayList());
            }
        } else {
            this.logs.setAll(FXCollections.observableArrayList(this.allLogs));
        }
    }

    /**
     * Sets the logs based off the users selected
     */
    public void sortListBasedOnUser() {
        if (this.selectedUser.get() != null) {
            ArrayList<ChangeLog> newFilteredList = new ArrayList<ChangeLog>();
            if (!this.selectedUsers.contains(this.selectedUser.get())) {
                this.selectedUsers.add(this.selectedUser.get());
            }
            for (ChangeLog log : this.filteredLogs) {
                if (this.selectedUsers.contains(log.getUserName())) {
                    newFilteredList.add(log);
                }
            }
            this.logs.setAll(newFilteredList);
        }
    }

    /**
     * Resets the selected users
     */
    public void resetSelectedUsers() {
        if (this.selectedUser.get() != null) {
            this.selectedUser.setValue(null);
        }
        if (this.selectedUsers != null) {
            this.selectedUsers.clear();
        }
        this.sortListBasedOnSpecialQualities();
    }

    private void getUsersForComboBox() {
        for (ChangeLog log : this.allLogs) {
            if (!this.users.contains(log.getUserName())) {
                this.users.add(log.getUserName());
            }
        }
    }

    /**
     * Filters logs to show only perishable stocks expiring within a given date range.
     * 
     * @precondition startExpirationDate != null && endExpirationDate != null
     * @postcondition logs list updated to only matching entries
     */
    public void filterByExpirationDateRange() {
        if (this.startExpirationDate.get() == null || this.endExpirationDate.get() == null) {
            return;
        }

        LocalDate start = this.startExpirationDate.get();
        LocalDate end = this.endExpirationDate.get();

        ArrayList<ChangeLog> results = new ArrayList<>();

        for (ChangeLog log : this.allLogs) {
            if (log.getStockInfo().getSpecialQualities().contains(SpecialQuality.PERISHABLE)) {
                LocalDate expiration = log.getStockInfo().getExpirationDate();
                if (expiration != null 
                		&& (expiration.isEqual(start) || expiration.isAfter(start))
                		&& (expiration.isEqual(end) || expiration.isBefore(end))) {
                    results.add(log);
                }
            }
        }

        this.logs.setAll(results);
    }

    /**
     * Returns an observable list of the logs.
     * 
     * @return the logs list
     */
    public ObservableList<ChangeLog> getLogs() {
        return this.logs;
    }

    /**
     * Returns the history log
     * 
     * @return the history
     */
    public ChangeHistory getHistory() {
        return this.history;
    }

    /**
     * Returns isPerishable property
     * 
     * @return the isPerishable property
     */
    public BooleanProperty getIsPerishable() {
        return this.isPerishable;
    }

    /**
     * Returns the isLiquid property
     * 
     * @return the isLiquid property
     */
    public BooleanProperty getIsLiquid() {
        return this.isLiquid;
    }

    /**
     * Returns isFlammable property 
     * 
     * @return the isFlammable property
     */
    public BooleanProperty getIsFlammable() {
        return this.isFlammable;
    }

    /**
     * Returns the selected user
     * 
     * @return the selected user property
     */
    public ObjectProperty<String> getSelectedUser() {
        return this.selectedUser;
    }

    /**
     * Returns a list of users
     * 
     * @return list of users
     */
    public ArrayList<String> getUsers() {
        return this.users;
    }

    /**
     * Returns the chosen start expiration date.
     * 
     * @return start expiration date property
     */
    public ObjectProperty<LocalDate> getStartExpirationDate() {
        return this.startExpirationDate;
    }

    /**
     * Returns the chosen end expiration date. 
     * 
     * @return end expiration date property
     */
    public ObjectProperty<LocalDate> getEndExpirationDate() {
        return this.endExpirationDate;
    }
}
