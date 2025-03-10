package com.yl.learn.dp.observer;

import com.yl.learn.dp.observer.imp.Observer;
import com.yl.learn.dp.observer.imp.Subject;

import java.util.ArrayList;

public class SubjectA extends Subject {

    public SubjectA() {
        this.observers = new ArrayList<>();
    }

    public void attach(Observer o) {
        observers.add(o);
    }

    public void detach(Observer o) {
        observers.remove(o);
    }

    public void notifyObservers() {
        for(Observer o : observers) {
            o.update();
        }
    }
}
