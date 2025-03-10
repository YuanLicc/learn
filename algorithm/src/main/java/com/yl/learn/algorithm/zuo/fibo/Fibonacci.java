package com.yl.learn.algorithm.zuo.fibo;

public class Fibonacci {

    public static long fibonacci(int n) {

        long pre = 0;
        int count = 1;

        long re = 1;

        while(count < n) {
            long tempPre = re;
            re = re + pre;
            pre = tempPre;
            count ++;
        }

        return re;
    }
}
