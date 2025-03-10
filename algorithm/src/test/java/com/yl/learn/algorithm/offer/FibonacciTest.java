package com.yl.learn.algorithm.offer;

import com.yl.learn.util.util.PrintUtil;
import com.yl.learn.algorithm.test.TestSuper;
import com.yl.learn.util.util.PrintUtil;
import org.junit.Test;


public class FibonacciTest extends TestSuper {

    private static int caseT = 40;

    @Test
    public void testRecursion() {

        PrintUtil.template("Fibonacci: \n" + caseT, () -> {
            PrintUtil.println(Fibonacci.fibonacci(caseT), "Recurision: ", "");
        });
    }

    @Test
    public void testRecursionM() {

        PrintUtil.template("Fibonacci: \n" + caseT, () -> {
            PrintUtil.println(Fibonacci.fibonacciM(caseT), "RecurisionM: ", "");
        });
    }

    @Test
    public void testWhile() {

        PrintUtil.template("Fibonacci: \n" + caseT, () -> {
            PrintUtil.println(Fibonacci.fibonacciA(caseT), "While: ", "");
        });
    }

}
