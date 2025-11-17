package edu.westga.cs3211.pirateshipinventorymanagementsystem.viewmodel;

import java.time.LocalDate;
import java.util.ArrayList;

import edu.westga.cs3211.pirateshipinventorymanagementsystem.enums.Condition;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.enums.SpecialQuality;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.model.ChangeHistory;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.model.Inventory;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.model.PerishableStock;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.model.Stock;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * The add stock page view model
 * 
 * @author Ezra Jones
 * @version Fall 2025
 */
public class AddStockPageViewModel {

	private Inventory inventory;
	private ChangeHistory history;
	private StringProperty name;
	private StringProperty quantity;
	private StringProperty year;
	private StringProperty month;
	private StringProperty day;
	private BooleanProperty isPerishable;
	private BooleanProperty isLiquid;
	private BooleanProperty isFlammable;
	private ObjectProperty<Condition> selectedCondition;
	private ArrayList<SpecialQuality> qualities;
	private Stock stock;
	
	/**
	 * Instantiates a new add stock view model
	 * 
	 * @param inventory the inventory to add stock to
	 * @param history the change log history for logging new stock
	 */
	public AddStockPageViewModel(Inventory inventory, ChangeHistory history) {
		this.inventory = inventory;
		this.history = history;
		this.name = new SimpleStringProperty("");
		this.quantity = new SimpleStringProperty("");
		this.year = new SimpleStringProperty("");
		this.month = new SimpleStringProperty("");
		this.day = new SimpleStringProperty("");
		this.isPerishable = new SimpleBooleanProperty(false);
		this.isLiquid = new SimpleBooleanProperty(false);
		this.isFlammable = new SimpleBooleanProperty(false);
		this.selectedCondition = new SimpleObjectProperty<>(Condition.PERFECT);
	}
	
	/**
	 * Prepares stock for the inventory
	 * 
	 * @precondition none
	 * @postcondition stock prepared to be added to inventory
	 * @return Stock the stock to add
	 */
	public Stock prepareStockForInventory() {
		this.setUpQualities();
		
		if (this.qualities.contains(SpecialQuality.PERISHABLE)) {
			this.stagePerishableStockForAdding();
		} else {
			this.stageRegularStockForAdding();
		}
		return this.stock;
	}
	
	private void stageRegularStockForAdding() {
		Double quantity = Double.parseDouble(this.quantity.get());
		this.stock = new Stock(
				this.name.get(), quantity, this.getSelectedCondition().get(), this.qualities);
	}	
	
	private void stagePerishableStockForAdding() {
		Double quantity = Double.parseDouble(this.quantity.get());
		this.stock = new PerishableStock(
				this.name.get(), quantity, this.getSelectedCondition().get(), this.qualities, 
				LocalDate.of(Integer.parseInt(this.year.get()), Integer.parseInt(this.month.get()), Integer.parseInt(this.day.get())));
	}
	
	private void setUpQualities() {
		ArrayList<SpecialQuality> qualities = new ArrayList<SpecialQuality>();
		if (this.isFlammable.get()) {
			qualities.add(SpecialQuality.FLAMMABLE);
		}
		if (this.isPerishable.get()) {
			qualities.add(SpecialQuality.PERISHABLE);
		}
		if (this.isLiquid.get()) {
			qualities.add(SpecialQuality.LIQUID);
		}
		this.qualities = qualities;
	}

	/**
	 * Returns the name property
	 * 
	 * @return the name property
	 */
	public StringProperty getName() {
		return this.name;
	}

	/**
	 * Returns the quantity property
	 * 
	 * @return the quantity property
	 */
	public StringProperty getQuantity() {
		return this.quantity;
	}

	/**
	 * Returns the year property
	 * 
	 * @return the year property
	 */
	public StringProperty getYear() {
		return this.year;
	}

	/**
	 * Returns the month property
	 * 
	 * @return the month property
	 */
	public StringProperty getMonth() {
		return this.month;
	}

	/**
	 * Returns the day property
	 * 
	 * @return the day property
	 */
	public StringProperty getDay() {
		return this.day;
	}

	/**
	 * Returns the isPerishable property
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
	 * Returns the isFlammable property
	 * 
	 * @return the isFlammable property
	 */
	public BooleanProperty getIsFlammable() {
		return this.isFlammable;
	}

	/**
	 * Returns the selectedCondition property
	 * 
	 * @return the selectedCondition property
	 */
	public ObjectProperty<Condition> getSelectedCondition() {
		return this.selectedCondition;
	}

	/**
	 * Returns the change history
	 * 
	 * @return the change history
	 */
	public ChangeHistory getHistory() {
		return this.history;
	}

	/**
	 * Returns the inventory
	 * 
	 * @return the inventory
	 */
	public Inventory getInventory() {
		return this.inventory;
	}
}
