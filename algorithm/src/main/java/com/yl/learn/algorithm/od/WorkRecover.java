package com.yl.learn.algorithm.od;

import java.util.Scanner;

/**
 * 题目描述
 * 小明每周上班都会拿到自己的工作清单，工作清单内包含 n 项工作，
 * 每项工作都有对应的耗时时间（单位 h）和报酬，工作的总报酬为所有已完成工作的报酬之和，
 * 那么请你帮小明安排一下工作，保证小明在指定的工作时间内工作收入最大化。
 *
 * 输入描述
 * T 代表工作时长（单位 h， 0 < T < 1000000），
 * n 代表工作数量（ 1 < n ≤ 3000）。
 * 接下来是 n 行，每行包含两个整数 t，w。
 * t 代表该工作消耗的时长（单位 h， t > 0），w 代表该项工作的报酬。
 *
 * 输出描述
 * 输出小明指定工作时长内工作可获得的最大报酬。
 *
 * 示例1
 * 输入
 * 40 3
 * 20 10
 * 20 20
 * 20 5
 * 输出 30
 */
public class WorkRecover {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int allHours = in.nextInt();
        int nums = in.nextInt();
        int[][] works = new int[nums][2];
        for (int i = 0; i < nums; i++) {
            works[i][0] = in.nextInt();
            works[i][1] = in.nextInt();
        }

        int[][] dp = new int[nums + 1][allHours + 1];

        for (int i = 1; i < dp.length; i++) {
            int workHour = works[i - 1][0];
            int recover = works[i - 1][1];
            for (int j = 1; j < dp[i].length; j++) {
                if(j >= workHour) { // 此时表示j小时可以接受此工作
                    // 要么选择
                    int recoverOne = recover + dp[i - 1][j - workHour];
                    // 要么不选择
                    int recoverTwo = dp[i - 1][j];
                    dp[i][j] = Math.max(recoverOne, recoverTwo);
                }
                else { // 表示 j 小时不能接受此工作
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        System.out.println(dp[nums][allHours]);
    }

}
