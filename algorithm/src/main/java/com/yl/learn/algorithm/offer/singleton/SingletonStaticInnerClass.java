package com.yl.learn.algorithm.offer.singleton;

public class SingletonStaticInnerClass {

    private SingletonStaticInnerClass() {}

    static class StaticInnerClass {

        private static final SingletonStaticInnerClass single
                = new SingletonStaticInnerClass();
    }

    public static SingletonStaticInnerClass getInstance() {

        return StaticInnerClass.single;
    }

}
