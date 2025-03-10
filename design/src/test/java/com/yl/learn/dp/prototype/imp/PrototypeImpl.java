package com.yl.learn.dp.prototype.imp;

import com.yl.learn.dp.prototype.impl.DefaultPrototype;

public class PrototypeImpl<T> extends DefaultPrototype {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    public PrototypeImpl(String name) {
        this.name = name;
    }
}
