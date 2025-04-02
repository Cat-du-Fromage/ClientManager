package MCR.entities;

import MCR.windows.Observer;

import java.util.LinkedList;

public abstract class Subject {
    protected LinkedList<Observer> observers = new LinkedList<>();

    public void addObserver(Observer observer) {
        observers.add(observer);
    }
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(this);
        }
    }
}
