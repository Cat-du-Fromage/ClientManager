// ================================================================================
// File : AccountState.java
// Project name : ClientManager
// Project members :
// - Florian Duruz, Mathieu Rabot
// File created by Florian Duruz, Mathieu Rabot
// ================================================================================
package MCR.entities.Account;

import MCR.entities.*;

/**
 * Abstract class representing the state of a client's account.
 * Defines the common structure and behavior for all account status types (Silver, Gold, Platinum).
 * Implements the State design pattern to allow dynamic behavior changes based on account status.
 */
public abstract class AccountState {
    protected StatusType status;
    protected double money;
    protected double miles;

    /**
     * Constructs a new AccountState with the specified status and balances.
     * @param status the status level of the account (SILVER, GOLD, PLATINUM)
     * @param money the current money balance of the account
     * @param miles the current miles balance of the account
     */
    protected AccountState(StatusType status, double money, double miles) {
        this.money = money;
        this.miles = miles;
        this.status = status;
    }

    /**
     * Factory method that creates an appropriate AccountState based on the miles balance.
     * The account status is determined automatically:
     * - 0-999 miles → Silver status
     * - 1000-9999 miles → Gold status
     * - 10000+ miles → Platinum status
     * @param money the initial money balance
     * @param miles the initial miles balance
     * @return a new AccountState instance of the appropriate status level
     */
    public static AccountState createAccount(double money, double miles) {
        if (miles >= 1000 && miles < 10000) {
            return new GoldAccountState(money, miles);
        } else if (miles >= 10000) {
            return new PlatinumAccountState(money, miles);
        } else {
            return new SilverAccountState(money, miles);
        }
    }

    /**
     * Gets the current status level of the account.
     * @return the account status (SILVER, GOLD, or PLATINUM)
     */
    public StatusType getStatus() { return status; }

    /**
     * Gets the current money balance of the account.
     * @return the money balance
     */
    public double getMoney() { return money; }

    /**
     * Gets the current miles balance of the account.
     * @return the miles balance
     */
    public double getMiles() { return miles; }

    /**
     * Updates the money balance by adding the specified amount.
     * Can accept negative values for deductions.
     * @param money the amount to add to the current balance
     */
    public void updateMoney(double money) {
        this.money += money;
    }

    /**
     * Updates the miles balance by adding the specified amount.
     * Can accept negative values for deductions.
     * @param miles the miles to add to the current balance
     */
    public void updateMiles(double miles) {
        this.miles += miles;
    }

    /**
     * Abstract method that handles state transitions when account data changes.
     * Concrete implementations should check if a status upgrade or downgrade is needed.
     * @return a new AccountState instance reflecting any status changes
     */
    public abstract AccountState onUpdate();
}
