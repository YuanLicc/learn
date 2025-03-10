package com.yl.learn.common.builder;

public interface Builder<T> {

    Builder<T> buildAll();

    T build();

}
