package com.yl.learn.dp.memento.imp;

public class Originator {

    private String state;
    
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }


    public Memento createMemento() {
        return new Memento(this);
    }

    public void restoreMemento(Memento memento) {
        this.state = memento.getState();
    }

    public String toString() {
        return state;
    }
}
