package com.yl.learn.algorithm.ds;

import com.yl.learn.util.util.PrintUtil;
import junit.framework.TestCase;

public class IntegerToBinary extends TestCase {
    
    public String toBinary(int num) {
        
        if(num == 0) return "0";
        
        boolean greaterZero = num > 0;
        num = greaterZero ? num : -num;
        
        StringBuilder sb = new StringBuilder();
        while(num != 0) {
            int minus = (num >> 1) << 1;
            sb.append(num - minus);
            num = num >> 1;
        }
        
        return (greaterZero ? "" : "-") + sb.reverse().toString();
    }
    
    public void testToBinary() {
        PrintUtil.template("Integer to Binary(1234):", () -> PrintUtil.println(toBinary(1234)));
        PrintUtil.template("Integer to Binary(4321):", () -> PrintUtil.println(toBinary(4321)));
        PrintUtil.template("Integer to Binary(-1234):", () -> PrintUtil.println(toBinary(-1234)));
        PrintUtil.template("Integer to Binary(-4321):", () -> PrintUtil.println(toBinary(-4321)));
    }
    
}
