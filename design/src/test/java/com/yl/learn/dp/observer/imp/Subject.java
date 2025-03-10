package com.yl.learn.dp.observer.imp;

import java.util.List;

public abstract class Subject {
    protected List<Observer> observers;

    public abstract void attach(Observer o);

    public abstract void detach(Observer o);

    public abstract void notifyObservers();
}
