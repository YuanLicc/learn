package com.yl.learn.algorithm.ds;

import com.yl.learn.util.util.PrintUtil;
import junit.framework.TestCase;

// 两数最大公约数，辗转相除法 gcd(a, b) = gcd(b, a % b)
public class Gcd extends TestCase {
    
    public long gcd(long a, long b) {
        
        while(b != 0) {
            long new_b = a % b;
            a = b;
            b = new_b;
        }
        
        return a;
    }
    
    public long gcdRecursion(long a, long b) {
        if(b == 0) return a;
        
        return gcd(b, a % b);
    }
    
    public void testGcdRecursion() {
        PrintUtil.template("gcd recursion: 10, 100", () -> PrintUtil.println(gcdRecursion(10, 100)));
        PrintUtil.template("gcd recursion: 100, 17", () -> PrintUtil.println(gcdRecursion(100, 17)));
        PrintUtil.template("gcd recursion: 11232424, 3232424", () -> PrintUtil.println(gcdRecursion(11232424, 3232424)));
    }
    
    public void testGcd() {
        PrintUtil.template("gcd: 10, 100", () -> PrintUtil.println(gcd(10, 100)));
        PrintUtil.template("gcd: 100, 17", () -> PrintUtil.println(gcd(100, 17)));
        PrintUtil.template("gcd: 11232424, 3232424", () -> PrintUtil.println(gcd(11232424, 3232424)));
    }
    
    
}
