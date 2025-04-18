package com.yl.learn.lambda;

import junit.framework.TestCase;

import java.util.Date;


public class ThreadLocalTest extends TestCase {
    public void testThreadLocal() {
        ThreadLocal<Date> local = ThreadLocal.withInitial(Date::new);
        System.out.println(local.get());
    }
}
