// ================================================================================
// File : GoldAccountState.java
// Project name : ClientManager
// Project members :
// - Florian Duruz, Mathieu Rabot
// File created by Florian Duruz, Mathieu Rabot
// ================================================================================
package MCR.entities.Account;

import MCR.entities.StatusType;

/**
 * Represents the Gold status level of a client account.
 * This is the intermediate status level between Silver and Platinum.
 * Automatically handles status transitions when the account is updated.
 */
public class GoldAccountState extends AccountState {

    /**
     * Constructs a new GoldAccountState with the specified balances.
     * @param money the current money balance of the account
     * @param miles the current miles balance of the account
     */
    protected GoldAccountState(double money, double miles) {
        super(StatusType.GOLD, money, miles);
    }

    /**
     * Evaluates if a status change is needed after account updates.
     * The status will change if:
     * - miles fall below 1000 → downgrade to Silver
     * - miles reach 10000 or more → upgrade to Platinum
     * - miles remain between 1000-9999 → maintain Gold status
     * @return the appropriate AccountState based on current miles:
     *         - SilverAccountState if miles < 1000
     *         - PlatinumAccountState if miles >= 10000
     *         - this GoldAccountState otherwise
     */
    @Override
    public AccountState onUpdate() {
        if(miles < 1000)
            return new SilverAccountState(money, miles);
        else if(miles >= 10000)
            return new PlatinumAccountState(money, miles);
        else
            return this;
    }
}
