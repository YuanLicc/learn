package com.yl.learn.algorithm.offer.singleton;

public class SingletonHungry {

    private static final SingletonHungry singleton = new SingletonHungry();

    private SingletonHungry(){}

    public static SingletonHungry getInstance() {
        return singleton;
    }

}
