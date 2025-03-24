package com.yl.learn.algorithm.od;

import java.util.Scanner;

/**
 * 题目描述
 * 有一种简易压缩算法：针对全部为小写英文字母组成的字符串， 将其中连续超过两个相同字母的部分压缩为连续个数加该字母 其他部分保持原样不变.
 *
 * 例如字符串aaabbccccd 经过压缩变成字符串 3abb4cd
 *
 * 请您编写解压函数,根据输入的字符串,判断其是否为合法压缩过的字符串
 *
 * 若输入合法则输出解压缩后的字符串
 * 否则输出字符串!error来报告错误
 * 输入描述
 * 输入一行，为一个 ASCII 字符串
 *
 * 长度不超过100字符
 *
 * 用例保证输出的字符串长度也不会超过100字符串
 *
 * 输出描述
 * 若判断输入为合法的经过压缩后的字符串
 *
 * 则输出压缩前的字符串
 *
 * 若输入不合法 则输出字符串!error
 *
 * 示例1
 * 输入
 *
 * 4dff
 * 输出
 *
 * ddddff
 * 说明
 *
 * 4d 扩展为 4 个 d ，故解压后的字符串为 ddddff
 *
 * 示例2
 * 输入
 *
 * 2dff
 * 输出
 *
 * !error
 * 说明
 *
 * 2 个 d 不需要压缩 故输入不合法
 */
public class CompressString {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String numS = in.nextLine();
        StringBuilder sb = new StringBuilder();
        int index = 0;
        int num = 1;
        while (index < numS.length()){
            char current = numS.charAt(index);
            if(Character.isDigit(current)) {
                if(current == '0') {
                    System.out.println("!error");
                    return;
                }
                else {
                    StringBuilder numSb = new StringBuilder();
                    while (index < numS.length() && Character.isDigit(numS.charAt(index))) {
                        numSb.append(numS.charAt(index));
                        index++;
                    }
                    num = Integer.parseInt(numSb.toString());
                    if(num <= 2) {
                        System.out.println("!error");
                        return;
                    }
                }
            }
            else if(current >= 'a' && current <= 'z') {
                for (int i = 0; i < num; i++) {
                    sb.append(current);
                }
                num = 1;
                index++;
            }
            else {
                System.out.println("!error");
                return;
            }
        }
        System.out.println(sb.toString());
    }
}
