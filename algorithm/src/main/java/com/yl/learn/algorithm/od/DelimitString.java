package com.yl.learn.algorithm.od;

import java.util.Locale;
import java.util.Scanner;

/**
 * 题目描述
 * 给定一个非空字符串S，其被N个‘-’分隔成N+1的子串，给定正整数K，要求除第一个子串外，其余的子串每K个字符组成新的子串，并用‘-’分隔。
 * 对于新组成的每一个子串，如果它含有的小写字母比大写字母多，则将这个子串的所有大写字母转换为小写字母；
 * 反之，如果它含有的大写字母比小写字母多，则将这个子串的所有小写字母转换为大写字母；大小写字母的数量相等时，不做转换。
 *
 * 输入描述
 * 输入为两行，第一行为参数K，第二行为字符串S。
 * 输出描述
 * 输出转换后的字符串。
 * 示例1
 * 输入
 *
 * 3
 * 12abc-abCABc-4aB@
 * 输出
 * 12abc-abc-ABC-4aB-@
 */
public class DelimitString {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        in.nextLine();
        String[] words = in.nextLine().split(GA);
        StringBuilder sb = new StringBuilder();
        sb.append(words[0]).append(GA);
        char[] chars = toCharArray(words);
        change(chars, sb, num);
        System.out.println(sb.toString());
    }

    private static void change(char[] words, StringBuilder sb, int k) {
        int index = 0, lower = 0, upper = 0, num = 0;
        StringBuilder tmp = new StringBuilder();
        while (index < words.length) {
            char currentChar = words[index];
            if(currentChar >= 'a' && currentChar <= 'z') {
                lower++;
            }
            else if(currentChar >= 'A' && currentChar <= 'Z') {
                upper++;
            }
            tmp.append(words[index]);
            index++;
            num++;
            if(num == k) {
                sb.append(upL(tmp, lower, upper)).append(GA);
                num = 0;
                lower = 0;
                upper = 0;
                tmp = new StringBuilder();
            }
        }
        if(tmp.length() > 0) {
            sb.append(upL(tmp, lower, upper));
        }
        else sb.deleteCharAt(sb.length() - 1);
    }

    private static String upL(StringBuilder sb, int lower, int upper) {
        if(lower == upper) return sb.toString();
        else if(lower > upper) return sb.toString().toLowerCase();
        else return sb.toString().toUpperCase();
    }

    private static char[] toCharArray(String[] words) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < words.length; i++) {
            sb.append(words[i]);
        }
        return sb.toString().toCharArray();
    }

    static final String GA = "-";

}
