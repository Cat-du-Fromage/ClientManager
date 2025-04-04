// ================================================================================
// File : TicketType.java
// Project name : ClientManager
// Project members :
// - Florian Duruz, Mathieu Rabot
// File created by Florian Duruz, Mathieu Rabot
// ================================================================================
package MCR.entities;

/**
 * Represents the different types of airline tickets available.
 * Each ticket type has different multipliers for money, miles, and coefficients.
 */
public enum TicketType {
    Economy,
    Business,
    First;

    /**
     * Returns the money multiplier associated with this ticket type.
     * The multiplier represents how much more expensive this ticket type is compared to Economy.
     * @return the money multiplier value:
     *         - 1 for Economy
     *         - 2 for Business
     *         - 5 for First
     */
    public int moneyMultiplicator(){
        return switch (this){
            case Economy -> 1;
            case Business -> 2;
            case First -> 5;
        };
    }

    /**
     * Returns the miles multiplier associated with this ticket type.
     * The multiplier represents how many more miles are earned compared to Economy.
     * @return the miles multiplier value:
     *         - 1 for Economy
     *         - 5 for Business
     *         - 30 for First
     */
    public int milesMultiplicator() {
        return switch (this) {
            case Economy -> 1;
            case Business -> 5;
            case First -> 30;
        };
    }

    /**
     * Returns the coefficient associated with this ticket type.
     * The coefficient represents a weighting factor for this ticket type.
     * @return the coefficient value:
     *         - 0.1 for Economy
     *         - 0.5 for Business
     *         - 1.0 for First
     */
    public double coefficient() {
        return switch (this) {
            case Economy -> 0.1;
            case Business -> 0.5;
            case First -> 1;
        };
    }
}
