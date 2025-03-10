package com.yl.learn.algorithm.zuo;

import com.yl.learn.algorithm.test.TestSuper;
import com.yl.learn.algorithm.zuo.fibo.Cow;
import com.yl.learn.algorithm.zuo.fibo.Fibonacci;
import com.yl.learn.algorithm.zuo.fibo.Jump;
import com.yl.learn.util.util.PrintUtil;
import org.junit.Test;

public class FibonacciTest extends TestSuper {

    int n = 10;

    @Test
    public void testFibonacci() {
        PrintUtil.template("Fibonacci -> " + n, () -> {

            PrintUtil.println(Fibonacci.fibonacci(n));
        });
    }

    @Test
    public void testJumpRecursion() {
        PrintUtil.template("Jump (Recursion) -> " + n, () -> {

            PrintUtil.println(Jump.jumpRecursion(n));
        });
    }

    @Test
    public void testJump() {

        PrintUtil.template("Jump (non-recursion) -> " + n, () -> {

            PrintUtil.println(Jump.jump(n));
        });
    }


    @Test
    public void testCowsRecursion() {
        PrintUtil.template("Cows (Recursion) -> " + n, () -> {

            PrintUtil.println(Cow.cowsRecursion(10, n));
        });
    }

    @Test
    public void testCows() {

        PrintUtil.template("Cows (non-recursion) -> " + n, () -> {

            PrintUtil.println(Cow.cows(10, n));
        });
    }

}
