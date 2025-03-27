package com.yl.learn.algorithm.od;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 题目描述
 * 给定一个字符串s，最多只能进行一次变换，返回变换后能得到的最小字符串（按照字典序进行比较）。
 *
 * 变换规则：交换字符串中任意两个不同位置的字符。
 *
 * 输入描述
 * 一串小写字母组成的字符串s
 *
 * 备注
 * s是都是小写字符组成
 *
 * 1<=s.length<=1000
 *
 * 输出描述
 * 一串小写字母组成的字符串s
 *
 * 示例1
 * 输入
 *
 * abcdef
 * 输出
 *
 * abcdef
 * 说明
 *
 * abcdef已经是最小字符串，不需要交换。
 *
 * 示例2
 * 输入
 *
 * bcdefa
 * 输出
 *
 * acdefb
 * 说明
 *
 * a和b进行位置交换，可以得到最小字符串。
 */
public class ExchangeOnece {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String words = in.nextLine();
        char [] copyArray = words.toCharArray();
        Arrays.sort(copyArray);
        String sorted = String.valueOf(copyArray);
        if(sorted.equals(words)) {
            System.out.println(words);
        }
        else {
            char aimChar = ' ';
            int index = 0;
            char[] coped = words.toCharArray();
            for (int i = 0; i < words.length(); i++) {
                if(words.charAt(i) != sorted.charAt(i)) {
                    aimChar = sorted.charAt(i);
                    index = i;
                    break;
                }
            }
            int otherIndex = 0;
            for (int i = words.length() - 1; i >= 0; i--) {
                if(words.charAt(i) == aimChar) {
                    otherIndex = i;
                    break;
                }
            }
            char tmp = coped[index];
            coped[index] = coped[otherIndex];
            coped[otherIndex] = tmp;
            System.out.println(String.valueOf(coped));
        }
    }

}
