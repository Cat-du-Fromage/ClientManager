// ================================================================================
// File : PlatinumAccountState.java
// Project name : ClientManager
// Project members :
// - Florian Duruz, Mathieu Rabot
// File created by Florian Duruz, Mathieu Rabot
// ================================================================================
package MCR.entities.Account;

import MCR.entities.StatusType;

/**
 * Represents the highest status level (Platinum) of a client account.
 * Includes special locking mechanism when account reaches exceptional balance.
 * Automatically handles status downgrade when criteria are no longer met.
 */
public class PlatinumAccountState extends AccountState {

    private boolean locked;

    /**
     * Constructs a new PlatinumAccountState with specified balances.
     * Initializes the account in unlocked state.
     * @param money the current money balance of the account
     * @param miles the current miles balance of the account
     */
    public PlatinumAccountState(double money, double miles) {
        super(StatusType.PLATINUM, money, miles);
    }

    /**
     * Checks and updates the locked status of the account.
     * The account becomes permanently locked when money balance reaches or exceeds 100,000.
     * @return true if the account is locked, false otherwise
     */
    private boolean checkLocked() {
        if(!locked && getMoney() >= 100000) {
            locked = true;
        }
        return locked;
    }

    /**
     * Evaluates if a status change is needed after account updates.
     * Behavior depends on locked status:
     * - Locked accounts remain Platinum permanently
     * - Unlocked accounts downgrade to Gold if miles fall below 10,000
     * - Otherwise maintains Platinum status
     * @return the appropriate AccountState:
     *         - this instance if account is locked or miles >= 10,000
     *         - new GoldAccountState if unlocked and miles < 10,000
     */
    @Override
    public AccountState onUpdate() {
        if(checkLocked()) return this;
        return miles >= 10000 ? this : new GoldAccountState(money, miles);
    }
}
