package com.yl.learn.algorithm.ds;

import com.yl.learn.util.util.PrintUtil;
import junit.framework.TestCase;

public class Fib extends TestCase {
    
    public int fibRecursion(int n) {
        if(n == 1 || n == 2) {
            return 1;
        }
        
        return fibRecursion(n - 1) + fibRecursion(n - 2);
    }
    
    public int fib(int n) {
        if(n == 1 || n == 2) return 1;
        
        int prepre = 1;
        int pre = 1;
        
        for(int nb = n - 2; nb > 0; nb--) {
            int cur = pre + prepre;
            prepre = pre;
            pre = cur;
        }
        
        return pre;
    }
    
    public void testFibRecursion() {
        PrintUtil.template("fib 10", () -> PrintUtil.println(fibRecursion(10)));
        PrintUtil.template("fib 3", () -> PrintUtil.println(fibRecursion(3)));
        PrintUtil.template("fib 2", () -> PrintUtil.println(fibRecursion(2)));
        PrintUtil.template("fib 20", () -> PrintUtil.println(fibRecursion(20)));
    }
    
    public void testFib() {
        PrintUtil.template("fib 10", () -> PrintUtil.println(fib(10)));
        PrintUtil.template("fib 3", () -> PrintUtil.println(fib(3)));
        PrintUtil.template("fib 2", () -> PrintUtil.println(fib(2)));
        PrintUtil.template("fib 20", () -> PrintUtil.println(fib(20)));
        PrintUtil.template("fib 4", () -> PrintUtil.println(fib(4)));
        PrintUtil.template("fib 5", () -> PrintUtil.println(fib(5)));
    }
}
