package com.yl.learn.interview.inner;

import junit.framework.TestCase;

public class InnerClassTest extends TestCase {

    public void testInnerClass() {

        InnerClass innerClass = new InnerClass();

        InnerClass.InnerInnerClass innerInnerClass
                = innerClass.new InnerInnerClass();

        InnerClass.InnerStaticClass innerStaticClass =
                new InnerClass.InnerStaticClass();
        innerStaticClass.a();


    }

}
