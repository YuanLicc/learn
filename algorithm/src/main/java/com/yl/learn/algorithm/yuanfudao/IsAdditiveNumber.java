package com.yl.learn.algorithm.yuanfudao;

import com.yl.learn.util.util.PrintUtil;
import junit.framework.TestCase;

/**
 * 累加数是一个字符串，组成它的数字可以形成累加序列。
 * 一个有效的累加序列必须至少包含 3 个数。除了最开始的两个数以外，字符串中的其他数都等于它之前两个数相加的和。
 * 给定一个只包含数字 '0'-'9' 的字符串，编写一个算法来判断给定输入是否是累加数。
 * 说明: 累加序列里的数不会以 0 开头，所以不会出现 1, 2, 03 或者 1, 02, 3 的情况。
 *
 * 输入: "112358"
 * 输出: true
 * 解释: 累加序列为: 1, 1, 2, 3, 5, 8 。1 + 1 = 2, 1 + 2 = 3, 2 + 3 = 5, 3 + 5 = 8
 *
 * 链接：https://leetcode-cn.com/problems/additive-number
 */
public class IsAdditiveNumber extends TestCase {
    
    public boolean isAdditiveNumber(String num) {
        if (num == null || num.length() < 3) return false;
        
        int numLength = num.length();
        
        for(int firstLen = 1; firstLen < (numLength - firstLen); firstLen++) {
            for(int secondLen = 1; secondLen <= (numLength - firstLen - secondLen); secondLen++) {
                if(check(num, firstLen, secondLen)) {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    private boolean check(String num, int firstLen, int secondLen) {
        
        long first = getNum(num, 0, firstLen);
        long second = getNum(num, firstLen, secondLen);
        
        if(first == -1 || second == -1) return false;
        
        int index = firstLen + secondLen;
        while(index < num.length()) {
            long third = first + second;
            if(!isContains(num, index, third)) {
                return false;
            }
            else {
                first = second;
                second = third;
                index = index + String.valueOf(third).length();
            }
        }
        
        return true;
    }
    
    private boolean isContains(String num, int startIndex, long aim) {
        
        if(startIndex >= num.length()) return false;
        String aimStr = String.valueOf(aim);
        // 1 2 3 4
        if(startIndex + aimStr.length() > num.length()) return false;
        
        int index = startIndex;
        while(index < num.length() && (index - startIndex) < aimStr.length()) {
            if(num.charAt(index) != aimStr.charAt(index - startIndex)) {
                return false;
            }
            index++;
        }
        
        return true;
    }
    
    // 12344 0 2
    private long getNum(String num, int startIndex, int len) {
        
        if(startIndex + len > num.length()) return -1;
        if(num.charAt(startIndex) == '0' && len != 1) return -1;
        
        long rs = 0;
        
        int index = startIndex;
        while(index < startIndex + len) {
            rs = rs * 10 + (num.charAt(index) - '0');
            index++;
        }
        
        return rs;
    }
    
    public void test() {
        PrintUtil.println(isAdditiveNumber("199100199"));
    }
}
