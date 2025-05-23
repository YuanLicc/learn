package com.yl.learn.algorithm.leetcode;

/**
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 *
 * 示例 1：
 * 输入：n = 2
 * 输出：2
 * 解释：有两种方法可以爬到楼顶。
 * 1. 1 阶 + 1 阶
 * 2. 2 阶
 */
public class ClimbStairs {

    public int climbStairs(int n) {
        if(n == 1) return 1;
        int pre = 1;
        int cur = 2;
        while(n > 2) {
            int tmp = cur + pre;
            pre = cur;
            cur = tmp;
            n--;
        }
        return cur;
    }

}
