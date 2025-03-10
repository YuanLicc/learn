package com.yl.learn.dp.memento.imp;

class Memento {

    public Memento(Originator o) {
        this.state = o.getState();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    private String state;

}
