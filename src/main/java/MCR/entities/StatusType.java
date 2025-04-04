// ================================================================================
// File : StatusType.java
// Project name : ClientManager
// Project members :
// - Florian Duruz, Mathieu Rabot
// File created by Florian Duruz, Mathieu Rabot
// ================================================================================
package MCR.entities;

/**
 * Represents the different status levels a client can achieve.
 * The status levels are hierarchical, from SILVER to PLATINUM.
 */
public enum StatusType {
    SILVER,
    GOLD,
    PLATINUM;

    /**
     * Returns the next status level in the hierarchy.
     * The progression is SILVER → GOLD → PLATINUM.
     * Once PLATINUM is reached, it remains the highest possible status.
     *
     * @return the next status level:
     *         - SILVER returns GOLD
     *         - GOLD returns PLATINUM
     *         - PLATINUM returns PLATINUM (no higher status available)
     */
    public StatusType next(){
        return switch(this){
            case SILVER -> GOLD;
            case GOLD -> PLATINUM;
            case PLATINUM -> PLATINUM;
        };
    }
}
