// ================================================================================
// File : Subject.java
// Project name : ClientManager
// Project members :
// - Florian Duruz, Mathieu Rabot
// File created by Florian Duruz, Mathieu Rabot
// ================================================================================
package MCR.entities;

import MCR.windows.Observer;

import java.util.LinkedList;

/**
 * Abstract class representing a subject in the observer pattern.
 * It maintains a list of observers and provides methods to add, remove, and notify them.
 */
public abstract class Subject {
    protected LinkedList<Observer> observers = new LinkedList<>();

    /**
     * Adds an observer to the list.
     * @param observer the observer to add
     */
    public void addObserver(Observer observer) {
        observers.add(observer);
    }
    
    /**
     * Removes an observer from the list.
     * @param observer the observer to remove
     */
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    /**
     * Notifies all observers of a change.
     * This method should be called whenever the subject's state changes.
     */
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(this);
        }
    }
}
