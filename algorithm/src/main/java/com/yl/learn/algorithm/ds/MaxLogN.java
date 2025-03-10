package com.yl.learn.algorithm.ds;

import com.yl.learn.util.util.PrintUtil;
import junit.framework.TestCase;

public class MaxLogN extends TestCase {
    
    public int maxLog(int n) {
    
        if(n <= 1) {
            return n < 0 ? -1 : n;
        }
        
        int middle = n >> 1;
        
        while(true) {
            if(middle * middle < n) return middle;
            middle--;
        }
    
    }
    
    public void testMaxLog() {
        PrintUtil.template("不大于 log10 的最大整数", () -> PrintUtil.println(maxLog(10)));
        PrintUtil.template("不大于 log100 的最大整数", () -> PrintUtil.println(maxLog(100)));
        PrintUtil.template("不大于 log17 的最大整数", () -> PrintUtil.println(maxLog(17)));
        PrintUtil.template("不大于 log25 的最大整数", () -> PrintUtil.println(maxLog(25)));
    }
    
}
