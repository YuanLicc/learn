package com.yl.learn.algorithm.od;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 题目描述
 * MELON有一堆精美的雨花石（数量为n，重量各异），准备送给S和W。MELON希望送给俩人的雨花石重量一致，请你设计一个程序，帮MELON确认是否能将雨花石平均分配。
 *
 * 输入描述
 * 第1行输入为雨花石个数: n，0 < n < 31.
 * 第2行输入为空格分割的各雨花石重量: m[0] m[1] … m[n - 1]， 0 < m[k] < 1001
 *
 * 不需要考虑异常输入的情况。
 *
 * 输出描述
 * 如果可以均分，从当前雨花石中最少拿出几块，可以使两堆的重量相等:如果不能均分，则输出-1。
 *
 * 用例1
 * 输入
 *
 * 4
 * 1 1 2 2
 * 输出
 *
 * 2
 * 说明
 * 输入第一行代表共4颗雨花石，第二行代表4颗雨花石重量分别为1、1、2、2。均分时只能分别为1,2，需要拿出重量为1和2的两块雨花石，所以输出2。
 *
 * 用例2
 * 输入
 *
 * 10
 * 1 1 1 1 1 9 8 3 7 10
 * 输出
 *
 * 3
 * 说明
 * 输入第一行代表共10颗雨花石，第二行代表4颗雨花石重量分别为1、1、1、1、1、9、8、3、7、10 。
 * 均分时可以1,1,1,1,1,9,7和10,8,3，也可以1,1,1,1,9.8和10,7,3,1，
 * 或者其他均分方式，但第一种只需要拿出重量为10.8,3的3块雨花石，第二种需要拿出4块，所以输出3(块数最少)。
 */
public class YHS {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        int[] nums = new int[num];
        int sum = 0;

        for (int i = 0; i < num; i++) {
            nums[i] = in.nextInt();
            sum += nums[i];
        }


        if(sum % 2 == 1) {
            System.out.println(-1);
        }
        else {
            int min = Integer.MAX_VALUE;
            int[] dp = new int[sum / 2 + 1]; // 表示最少石头数
            Arrays.fill(dp, num);
            dp[0] = 0;

            for (int i = 1; i < dp.length; i++) {
                // 遍历每个石头看看是否能够装下
                for (int cNum : nums) {
                    if (cNum == i) dp[i] = 1; // 如果容量与石头重量刚好相等，直接等于1
                    else if (i > cNum) { // 如果石头能够装下
                        dp[i] = Math.min(dp[i - cNum] + 1, dp[i]);
                    }
                    // 如果装不下，不动
                }
            }

            if(dp[dp.length - 1] == num) {
                System.out.println(-1);
            }
            else {
                System.out.println(dp[dp.length - 1]);
            }
        }
    }

}
