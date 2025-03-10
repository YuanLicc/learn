package com.yl.learn;

import junit.framework.TestCase;

public class StringTest extends TestCase {
    
    public static final String aaa = "aaa";
    
    public static void main(String[] args) {
        String ccc = "aaa";
        System.out.println(aaa == ccc.intern());
    }
}
