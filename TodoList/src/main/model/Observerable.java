package model;

import java.util.ArrayList;
import java.util.List;

public abstract class Observerable {

    List<Observer> observers = new ArrayList<>();


    public void notifyObserver(int seizeBefore, int sizeAfter) {
        for (Observer observer : observers) {
            observer.update(seizeBefore, sizeAfter);
        }
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

}
