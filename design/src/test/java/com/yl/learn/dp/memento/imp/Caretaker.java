package com.yl.learn.dp.memento.imp;

public class Caretaker {

    public Memento getMemento() {
        return memento;
    }

    public void setMemento(Memento memento) {
        this.memento = memento;
    }

    private Memento memento;


}
