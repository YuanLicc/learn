package com.yl.learn.common.parse;

public interface Parser<T, R> {
    R parse(T parsed);
}
