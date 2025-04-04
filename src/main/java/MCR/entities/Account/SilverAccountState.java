// ================================================================================
// File : SilverAccountState.java
// Project name : ClientManager
// Project members :
// - Florian Duruz, Mathieu Rabot
// File created by Florian Duruz, Mathieu Rabot
// ================================================================================
package MCR.entities.Account;

import MCR.entities.StatusType;

/**
 * Represents the Silver status level of a client account.
 * This is the entry-level status that automatically upgrades to Gold status
 * when the miles balance reaches the required threshold.
 */
public class SilverAccountState extends AccountState {

    /**
     * Constructs a new SilverAccountState with specified balances.
     * @param money the current money balance of the account
     * @param miles the current miles balance of the account
     */
    public SilverAccountState(double money, double miles) {
        super(StatusType.SILVER, money, miles);
    }

    /**
     * Evaluates if a status upgrade is needed after account updates.
     * The account will upgrade to Gold status when miles reach or exceed 1000,
     * otherwise maintains Silver status.
     * @return the appropriate AccountState:
     *         - new GoldAccountState if miles >= 1000
     *         - this SilverAccountState if miles < 1000
     */
    @Override
    public AccountState onUpdate() {
        return miles < 1000 ? this : new GoldAccountState(money, miles);
    }
}
