package com.yl.learn.algorithm.od;

import java.util.Scanner;

/**
 * 每个数字关联多个字母，关联关系如下：
 *
 * 0 关联 “a”,”b”,”c”
 * 1 关联 “d”,”e”,”f”
 * 2 关联 “g”,”h”,”i”
 * 3 关联 “j”,”k”,”l”
 * 4 关联 “m”,”n”,”o”
 * 5 关联 “p”,”q”,”r”
 * 6 关联 “s”,”t”
 * 7 关联 “u”,”v”
 * 8 关联 “w”,”x”
 * 9 关联 “y”,”z”
 * 输入一串数字后，通过数字和字母的对应关系可以得到多个字母字符串（要求按照数字的顺序组合字母字符串）；
 *
 * 屏蔽字符串：屏蔽字符串中的所有字母不能同时在输出的字符串出现，如屏蔽字符串是abc，则要求字符串中不能同时出现a,b,c，但是允许同时出现a,b或a,c或b,c等；
 *
 * 给定一个数字字符串和一个屏蔽字符串，输出所有可能的字符组合；
 *
 * 例如输入数字字符串78和屏蔽字符串ux，输出结果为uw，vw，vx；数字字符串78，可以得到如下字符串uw，ux，vw，vx；由于ux是屏蔽字符串，因此排除ux，最终的输出是uw，vw，vx;
 *
 * 输入描述
 * 第一行输入为一串数字字符串，数字字符串中的数字不允许重复，数字字符串的长度大于0，小于等于5；
 *
 * 第二行输入是屏蔽字符串，屏蔽字符串的长度一定小于数字字符串的长度，屏蔽字符串中字符不会重复；
 *
 * 输出描述
 * 输出可能的字符串组合
 *
 * 注：字符串之间使用逗号隔开，最后一个字符串后携带逗号
 *
 * 示例1
 * 输入
 *
 * 78
 * ux
 * 1
 * 2
 * 输出
 *
 * uw,vw,vx,
 * 1
 * 说明
 *
 * ux完全包含屏蔽字符串ux，因此剔除
 *
 * 示例2
 * 输入
 *
 * 78
 * x
 * 1
 * 2
 * 输出
 *
 * uw,vw,
 */
public class StringCombine {

    static String[] map = {"abc", "def", "ghi", "jkl", "mno", "pqr", "st", "uv", "wx", "yz"};

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String numLine = in.nextLine();
        char[] nums = numLine.toCharArray(); // 获取第一行数据，表示输入的数字
        String ext = in.nextLine(); // 获取第二行数据表示输入的 排除在外的串

        StringBuilder rs = new StringBuilder(), sb = new StringBuilder();
        dfs(rs, sb, nums, 0, ext);
        System.out.println(rs);
    }

    private static void dfs(StringBuilder rs, StringBuilder sb, char[] nums, int index, String ext) {
        if(index == nums.length) {
            if(!sb.toString().contains(ext)) {
                rs.append(sb).append(',');
            }
            return;
        }
        char[] currentChars = map[nums[index] - '0'].toCharArray();
        for (char currentChar : currentChars) {
            sb.append(currentChar);
            dfs(rs, sb, nums, index + 1, ext);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
