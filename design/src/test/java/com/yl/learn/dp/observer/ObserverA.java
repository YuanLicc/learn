package com.yl.learn.dp.observer;

import com.yl.learn.dp.observer.imp.Observer;

public class ObserverA implements Observer {
    @Override
    public void update() {
        System.out.println("update");
    }
}
