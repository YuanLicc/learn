package com.yl.learn;

public class InitObject {
    public static final int finalStaticField = 1;
    
    public static final int finalStaticUnField;
    
    public static int staticField;
    
    public int objectField;
    
    static {
        finalStaticUnField = 1;
        System.out.println("INIT");
    }
    
    public static void staticMethod() {
    
    }
}
