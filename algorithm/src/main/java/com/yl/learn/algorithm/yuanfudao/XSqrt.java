package com.yl.learn.algorithm.yuanfudao;

/**
 * 实现 int sqrt(int x) 函数。
 *
 * 计算并返回 x 的平方根，其中 x 是非负整数。
 *
 * 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
 *
 * 来源：力扣（LeetCode）69
 * 链接：https://leetcode-cn.com/problems/sqrtx
 */
public class XSqrt {
    
    public int mySqrt(int x) {
        if(x <= 1) return x;
    
        double left = 0d;
        double right = x;
    
        while(left < right) {
            double middle = (left + right) / 2.0;
        
            if((int)(x / middle) == (int)middle) {
                break;
            }
            else if((int)(x / middle) >= (int)middle) {
                left = middle;
            }
            else {
                right = middle;
            }
        }
    
        return (int)(left + right) / 2;
    }

}
