package com.yl.learn.algorithm.offer.singleton;

public class SingletonSync {

    private static SingletonSync SINGLE = null;

    private SingletonSync() {
    }

    public static synchronized SingletonSync getInstance() {

        if(SINGLE == null) {
            SINGLE = new SingletonSync();
        }

        return SINGLE;
    }

}
