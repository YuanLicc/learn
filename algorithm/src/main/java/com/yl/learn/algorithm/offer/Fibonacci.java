package com.yl.learn.algorithm.offer;

/**
 * Fibonacci 系数：
 * 1 1 2 3 5 8 ....
 * Math: fi(n) = fi(n - 1) + f(n - 2);
 * @author YuanLi
 */
public class Fibonacci {

    public static long fibonacci(int n) {

        if(n == 1 || n == 2) {
            return 1;
        }

        return fibonacci(n - 1) + fibonacci(n - 2);

    }

    public static long fibonacciM(int n) {
        long m[] = new long[n];

        return fibonacciM(n, m);
    }

    private static long fibonacciM(int n, long[] m) {

        if(n == 1 || n == 2) {
            return 1;
        }
        else if(m[n - 1] != 0) {
            return m[n - 1];
        }

        m[n - 2] = fibonacciM(n - 1);
        m[n - 3] = fibonacciM(n - 2);

        return m[n - 2] + m[n - 3];
    }

    /**
     * 非递归形式
     */
    public static long fibonacciA(int n) {

        int count = 2;
        long pre = 1;
        long re = 1;

        while(count != n) {
            long temp = re;
            re = re + pre;
            pre = temp;
            ++count;
        }

        return re;
    }

}
