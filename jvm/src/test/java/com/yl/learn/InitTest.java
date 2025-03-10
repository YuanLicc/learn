package com.yl.learn;

import junit.framework.TestCase;

public class InitTest extends TestCase {
    
    // 引用静态变量时触发初始化
    public void testInvokeStaticField() {
        int a = InitObject.staticField + 1;
    }
    
    // 引用静态常量（直接赋值）时不触发初始化
    public void testInvokeFinalStaticField() {
        int c = InitObject.finalStaticField + 1;
    }
    
    // 引用静态常量（static 块中赋值）时触发初始化
    public void testInvokeFinalStaticUnField() {
        int b = InitObject.finalStaticUnField + 1;
    }
    
    // 调用静态方法时触发初始化
    public void testInvokeStaticMethod() {
        InitObject.staticMethod();
    }
    
    // class.forname 时触发初始化
    public void testReflectionInvokeStaticMethod() throws Exception{
        Class.forName("com.yl.learn.InitObject");
    }
}
