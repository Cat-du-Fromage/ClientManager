// ================================================================================
// File : Observer.java
// Project name : ClientManager
// Project members :
// - Florian Duruz, Mathieu Rabot
// File created by Florian Duruz, Mathieu Rabot
// ================================================================================
package MCR.windows;

import MCR.entities.Subject;

/**
 * Abstract class representing an observer in the Observer design pattern.
 * Observers are notified of changes in the subject they are observing.
 */
public abstract class Observer {
    /**
     * Updates the observer with the current state of the subject.
     * This method is called when the subject's state changes.
     * @param subject the subject being observed
     */
    public abstract void update(Subject subject);
}
