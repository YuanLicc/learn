package com.yl.learn.algorithm.ds;

import com.yl.learn.util.util.PrintUtil;
import junit.framework.TestCase;

/**
 * N 个数字的最大公约数
 * gcd(a, b, c) = gcd(gcd(a, b), c)
 */
public class GcdN extends TestCase {
    
    public long gcdN(long ...nums) {
        if(nums == null || nums.length == 1) {
            return nums == null ? 0 : nums[0];
        }
        
        long a = nums[0];
        for(int i = 1; i < nums.length; i++) {
            long b = nums[i];
            
            while(b != 0) {
                long newB = a % b;
                a = b;
                b = newB;
            }
        }
        
        return a;
    }
    
    public void testGcdN() {
        PrintUtil.template("gcdN: 10, 20, 100", () -> PrintUtil.println(gcdN(10, 20, 100)));
        PrintUtil.template("gcdN: 10, 25, 100", () -> PrintUtil.println(gcdN(10, 25, 100)));
        PrintUtil.template("gcdN: 27, 32, 81", () -> PrintUtil.println(gcdN(27, 32, 81)));
    }
    
}
