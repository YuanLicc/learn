package com.yl.learn.algorithm.yuanfudao;

/**
 * 给定一个正整数 n，将其拆分为至少两个正整数的和，并使这些整数的乘积最大化。 返回你可以获得的最大乘积。
 *
 * 示例 1:
 * 输入: 2
 * 输出: 1
 * 解释: 2 = 1 + 1, 1 × 1 = 1。
 *
 * 来源：力扣（LeetCode）343
 * 链接：https://leetcode-cn.com/problems/integer-break
 */
public class IntegerBreak {
    
    public int integerBreak(int n) {
        if(n == 1) return 0;
        if(n == 2) return 1;
        if(n == 3) return 2;
        if(n == 4) return 4;
        
        return calculate(n);
    }
    
    private int calculate(int n) {
        if(n == 1) return 0;
        if(n <= 4) return n;
        
        int a = calculate(n - 3);
        
        return a * 3;
    }
    
}
