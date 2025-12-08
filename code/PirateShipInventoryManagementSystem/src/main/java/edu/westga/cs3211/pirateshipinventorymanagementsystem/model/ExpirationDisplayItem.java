package edu.westga.cs3211.pirateshipinventorymanagementsystem.model;

/**
 * makes the string to diplay on expiration report
 *@author ns00184
 *@version 2025
 */
public class ExpirationDisplayItem {

    private String name;
    private double quantity;
    private String status;
    private int daysRemaining;

    /**
     * Creates a new ExpirationDisplayItem with the given values.
     *
     * @precondition name != null && status != null
     * @postcondition getName() == name && getQuantity() == quantity &&
     *                getStatus() == status && getDaysRemaining() == daysRemaining
     *
     * @param name          the name of the stock item
     * @param quantity      the quantity of the stock item
     * @param status        the formatted expiration status text
     * @param daysRemaining the number of days until expiration (negative if expired)
     */
    public ExpirationDisplayItem(String name, double quantity, String status, int daysRemaining) {
        this.name = name;
        this.quantity = quantity;
        this.status = status;
        this.daysRemaining = daysRemaining;
    }

    
    /**
     * gets the name
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * gets the quantity
     * @return the quantity
     */
    public double getQuantity() {
        return this.quantity;
    }

    /**
     * gets the status
     * @return the status
     */
    public String getStatus() {
        return this.status;
    }

    /**
     * gets the days reaming
     * @return the days reaming
     */
    public int getDaysRemaining() {
        return this.daysRemaining;
    }

    @Override
    public String toString() {
        return this.name + " Amount: " + this.quantity + " Expiration: " + this.status;
    }
}
