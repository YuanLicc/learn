package com.yl.learn.algorithm.od;

import java.util.Scanner;

/**
 * 题目描述
 * 某个充电站，可提供 n 个充电设备，每个充电设备均有对应的输出功率。
 * 任意个充电设备组合的输出功率总和，均构成功率集合 P 的 1 个元素。
 * 功率集合 P 的最优元素，表示最接近充电站最大输出功率 p_max 的元素。
 *
 * 输入描述
 * 输入为 3 行：
 *
 * 第 1 行为充电设备个数 n
 * 第 2 行为每个充电设备的输出功率
 * 第 3 行为充电站最大输出功率 p_max
 * 备注
 * 充电设备个数 n > 0
 * 最优元素必须小于或等于充电站最大输出功率 p_max
 * 输出描述
 * 功率集合 P 的最优元素
 *
 * 示例1
 * 输入
 *
 * 4
 * 50 20 20 60
 * 90
 * 输出
 *
 * 90
 */
public class CDZ {

    /**
     * 背包问题映射
     * 容量为 瓦数、选择装下几种充电桩
     * f(i, j) = f(i - 1, j) || f(i - 1, j - ws[i]) + ws[i]
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        int[] ws = new int[num];
        for (int i = 0; i < num; i++) {
            ws[i] = in.nextInt();
        }
        int aimW = in.nextInt();
        int[][] dp = new int[num + 1][aimW + 1];
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[i].length; j++) {
                if(ws[i - 1] > j) {
                    dp[i][j] = dp[i - 1][j];
                }
                else {
                    dp[i][j] = Math.max(dp[i - 1][j - ws[i - 1]] + ws[i - 1], dp[i - 1][j]);
                }
            }
        }
        System.out.println(dp[num][aimW]);
    }
}
