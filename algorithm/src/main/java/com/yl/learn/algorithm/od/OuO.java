package com.yl.learn.algorithm.od;

import java.util.Scanner;

/**
 *题目描述
 * 给你一个字符串 s，字符串s首尾相连成一个环形 ，请你在环中找出 ‘o’ 字符出现了偶数次最长子字符串的长度。
 * 输入描述
 * 输入是一串小写字母组成的字符串
 *
 * 备注
 * 1 <= s.length <= 5 x 10^5
 * s 只包含小写英文字母
 *
 * 输出描述
 * 输出是一个整数
 *
 * 示例1
 * 输入
 *
 * alolobo
 * 输出
 *
 * 6
 * 说明
 *
 * 最长子字符串之一是 “alolob”，它包含’o’ 2个。
 */
public class OuO {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        int sum = 0;
        for (int i = 0; i < line.length(); i++) {
            if(line.charAt(i) == 'o') sum++;
        }
        if(sum % 2 == 0) System.out.println(line.length());
        else System.out.println(line.length() - 1);
    }
}
