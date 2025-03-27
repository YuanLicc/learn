package com.yl.learn.algorithm.od;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 题目描述
 * 跳房子，也叫跳飞机，是一种世界性的儿童游戏。
 * 游戏参与者需要分多个回合按顺序跳到第1格直到房子的最后一格。
 * 跳房子的过程中，可以向前跳，也可以向后跳。
 * 假设房子的总格数是count，小红每回合可能连续跳的步教都放在数组steps中，请问数组中是否有一种步数的组合，可以让小红两个回合跳到量后一格?
 * 如果有，请输出索引和最小的步数组合。
 *
 * 注意：
 * 数组中的步数可以重复，但数组中的元素不能重复使用。
 * 提供的数据保证存在满足题目要求的组合，且索引和最小的步数组合是唯一的。
 * 输入描述
 * 第一行输入为每回合可能连续跳的步数，它是int整数数组类型。
 * 第二行输入为房子总格数count，它是int整数类型。
 *
 * 备注
 * count ≤ 1000
 * 0 ≤ steps.length ≤ 5000
 * -100000000 ≤ steps ≤ 100000000
 * 输出描述
 * 返回索引和最小的满足要求的步数组合（顺序保持steps中原有顺序）
 *
 * 示例1
 * 输入
 * [1,4,5,2,2]
 * 7
 * 输出
 *
 * [5, 2]
 */
public class JumpStep {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[] steps = Arrays.stream(in.nextLine().replaceAll("[^0-9,]", "").split(",")).mapToInt(Integer::parseInt).toArray();
        int aimStep = in.nextInt();

        int min = steps.length * 2;
        int iIndex = 0, jIndex = 0;
        for (int i = 0; i < steps.length; i++) {
            for (int j = i + 1; j < steps.length; j++) {
                if(steps[i] + steps[j] == aimStep) {
                    if(i + j < min) {
                        min = i + j;
                        iIndex = steps[i];
                        jIndex = steps[j];
                    }
                }
            }
        }

        System.out.println(min == steps.length * 2 ? "-1" : ("[" + iIndex + "," + jIndex + "]"));
    }

    // int[][] dp = new int[steps.length + 1][aimStep + 1];
    //        for (int i = 0; i < dp.length; i++) Arrays.fill(dp[i], aimStep);
    //
    //        for (int i = 1; i < dp.length; i++) {
    //            for (int j = 1; j < dp[i].length; j++) {
    //                if(j == steps[i - 1]) dp[i][j] = 1;
    //                else if(j > steps[i - 1]) {
    //                    dp[i][j] = Math.min(dp[i - 1][j], dp[i  - 1][j - steps[i - 1]] + 1);
    //                }
    //                else dp[i][j] = dp[i - 1][j];
    //            }
    //        }

}
