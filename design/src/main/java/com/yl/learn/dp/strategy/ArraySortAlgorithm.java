package com.yl.learn.dp.strategy;

import java.util.Objects;

public interface ArraySortAlgorithm<T> {
    T[] algorithm(T... array);

    default void checkArray(T... array) {
        Objects.requireNonNull(array);
        if(array.length == 0) {
            throw new IllegalArgumentException("empty parameter");
        }
    }
}
