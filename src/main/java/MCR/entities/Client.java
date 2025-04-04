// ================================================================================
// File : Client.java
// Project name : ClientManager
// Project members :
// - Florian Duruz, Mathieu Rabot
// File created by Florian Duruz, Mathieu Rabot
// ================================================================================
package MCR.entities;

import MCR.entities.Account.AccountState;

/**
 * Represents a client with personal information, account state, and transaction capabilities.
 * Extends Subject for observer pattern implementation and implements Comparable for sorting.
 * Each client has a unique identifier generated automatically.
 */
public class Client extends Subject implements Comparable<Client> {

    private static int id = 0;

    private String name;
    private String lastName;
    private int uniqueId;
    private AccountState accountState;
    private String lastAction = "";

    /**
     * Constructs a new Client with specified parameters and initial account state.
     * @param name  the client's first name
     * @param lastName the client's last name
     * @param money initial amount of money in the account
     * @param miles initial amount of miles in the account
     */
    public Client(String name, String lastName, double money, double miles) {
        this.name = name;
        this.lastName = lastName;
        accountState = AccountState.createAccount(money, miles);
        uniqueId = id++;
    }

    /**
     * Constructs a new Client with zero initial balance.
     * @param name the client's first name
     * @param lastName the client's last name
     */
    public Client(String name, String lastName) {
        this(name, lastName, 0, 0);
    }

    /**
     * Gets the client's first name.
     * @return the first name
     */
    public String getName() { return name; }

    /**
     * Gets the client's last name.
     * @return the last name
     */
    public String getLastName() { return lastName; }

    /**
     * Gets the client's unique identifier.
     * @return the unique ID
     */
    public int getUniqueId() { return uniqueId; }

    /**
     * Gets the current money balance in the account.
     * @return the money balance
     */
    public double getMoney() { return accountState.getMoney(); }

    /**
     * Gets the current miles balance in the account.
     * @return the miles balance
     */
    public double getMiles() { return accountState.getMiles(); }

    /**
     * Gets the current status of the account.
     * @return the account status
     */
    public StatusType getStatus() { return accountState.getStatus(); }

    /**
     * Gets the description of the last action performed.
     * @return the last action description
     */
    public String getLastAction() { return lastAction; }

    /**
     * Sets the last action description and notifies observers.
     * @param lastAction description of the last action performed
     */
    public void setLastAction(String lastAction) {
        this.lastAction = lastAction;
        this.notifyObservers();
    }

    /**
     * Updates the money balance and recalculates account state.
     * Notifies observers after the update.
     * @param amount the amount to add to the current balance (can be negative)
     */
    public void updateCredit(double amount) {
        accountState.updateMoney(amount);
        accountState = accountState.onUpdate();
        this.notifyObservers();
    }

    /**
     * Updates the miles balance and recalculates account state.
     * Notifies observers after the update.
     * @param miles the miles to add to the current balance (can be negative)
     */
    public void updateMiles(double miles) {
        accountState.updateMiles(miles);
        accountState = accountState.onUpdate();
        this.notifyObservers();
    }

    /**
     * Performs a complete update of money, miles and last action.
     * Recalculates account state and notifies observers.
     * @param money the new money balance
     * @param miles the new miles balance
     * @param lastAction description of the last action performed
     */
    public void updateInfos(double money, double miles, String lastAction) {
        accountState.updateMoney(money);
        accountState.updateMiles(miles);
        this.lastAction = lastAction;
        this.accountState = accountState.onUpdate();
        this.notifyObservers();
    }

    /**
     * Compares this client to another for sorting purposes.
     * Comparison is case-insensitive and based on last name then first name.
     * @param other the client to compare to
     * @return a negative integer, zero, or a positive integer as this client
     *         is less than, equal to, or greater than the specified client
     */
    @Override
    public int compareTo(Client other) {
        int cmpNom = this.lastName.compareToIgnoreCase(other.lastName);
        return (cmpNom != 0) ? cmpNom : this.name.compareToIgnoreCase(other.name);
    }

    /**
     * Returns a string representation of the client in the format "FirstName LastName".
     * @return the formatted client name
     */
    @Override
    public String toString() {
        return name + " " + lastName;
    }
}
