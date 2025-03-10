package com.yl.learn.algorithm.ds;

import com.yl.learn.util.util.PrintUtil;
import junit.framework.TestCase;

public class BasicTest extends TestCase {
    
    public void testAbs() {
        PrintUtil.template("abs Integer.MIN_VALUE", () -> PrintUtil.println(Math.abs(Integer.MIN_VALUE)));
    }
    
    public void testDivideZero() {
        PrintUtil.template("1 divide 0.0", () -> PrintUtil.println(1.0 / 0.0));
        PrintUtil.template("-1 divide 0.0", () -> PrintUtil.println(-1.0 / 0.0));
    }
    
}
