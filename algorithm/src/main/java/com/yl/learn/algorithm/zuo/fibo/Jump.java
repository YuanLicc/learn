package com.yl.learn.algorithm.zuo.fibo;

/**
 * n 个台阶，一次只能跳 1、2 个台阶。从台阶最下层到最上层有多少种跳法？
 */
public class Jump {

    // 表达式 f(n) = f(n - 1) + f(n - 2)，类似 fibonacci

    // 递归算法
    public static long jumpRecursion(int n) {
        if(n == 1 || n == 2) {
            return n;
        }

        return jumpRecursion(n - 1) + jumpRecursion(n - 2);
    }

    // 非递归算法
    public static long jump(int n) {

        if(n == 1 || n == 2) {
            return n;
        }

        long pre = 1;
        long re = 2;
        int count = 2;

        while (count < n) {
            long temp = re;
            re = re + pre;
            pre = temp;
            count++;
        }

        return re;
    }

}
