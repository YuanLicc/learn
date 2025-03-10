package com.yl.learn.algorithm.offer.singleton;

public class SingletonDoubleCheck {
    private static volatile SingletonDoubleCheck single = null;

    private SingletonDoubleCheck(){}

    public static SingletonDoubleCheck getInstance() {
        if(single == null) {
            synchronized (SingletonDoubleCheck.class) {
                if(single == null) {
                    single = new SingletonDoubleCheck();
                }
            }
        }
        return single;
    }
}
