package com.yl.learn.interview.algorithm.integer;

/**
 * 编写一个函数，求一个数的二进制表示中的1位的个数，例如9的二进制表示为1001，1位的个数为2。
 * @author YuanLi
 */
public class BinaryCount {

    public static int count(int num) {
        int count = 0;
        while(num != 0) {
            if((num & 1) == 1)
                count ++;
            num = num >> 1;
        }
        return count;
    }

}
