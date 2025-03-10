package com.yl.learn.dp.proxy.entity;

import com.yl.learn.dp.proxy.Person;

public class PersonA implements Person {
    private String name;

    public PersonA(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void trimName() {
        name = name.trim();
    }

    public String toString() {
        String str = "name:" + name;
        return str;
    }
}
