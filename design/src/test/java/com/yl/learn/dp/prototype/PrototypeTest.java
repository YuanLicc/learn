package com.yl.learn.dp.prototype;

import com.yl.learn.dp.prototype.imp.PrototypeImpl;
import junit.framework.TestCase;

public class PrototypeTest extends TestCase {

    public void testPrototype() {
        Prototype prototype = new PrototypeImpl("YuanLi");
        System.out.println(prototype == prototype.deepClone());
    }

}
