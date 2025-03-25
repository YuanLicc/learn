package com.yl.learn.algorithm.od;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 题目描述
 * 给定一组整数（非负），排列组合后输出一个最大的整数。
 *
 * 示例1
 * 输入：[10,9]
 *
 * 输出：910
 * 说明:输出结果可能非常大，所以你需要返回一个字符串而不是整数。
 *
 * 输入描述
 * 数字组合
 * 输出描述
 * 最大的整数
 *
 * 示例1
 * 输入
 *
 * 10 9
 * 输出
 *
 * 910
 */
public class ArrangeNums {

    // 优先考虑将数字中高位最大的放在最前面，相同则需要考虑次高位...，即按照字典序逆序排列即可
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String[] nums = in.nextLine().split(" ");
        Arrays.sort(nums, (num1, num2) -> {
            String pre = num1 + num2, after = num2 + num1;
            return after.compareTo(pre);
        });
        StringBuilder sb = new StringBuilder();
        for (String str : nums) {
            sb.append(str);
        }
        System.out.println(sb.toString());
    }

}
