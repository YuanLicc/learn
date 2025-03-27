package com.yl.learn.algorithm.od;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 题目描述
 * 小明和朋友们一起玩跳格子游戏，每个格子上有特定的分数 score = [1, -1, -6, 7, -17, 7]，
 * 从起点score[0]开始，每次最大的步长为k，请你返回小明跳到终点 score[n-1] 时，能得到的最大得分。
 *
 * 输入描述
 * 第一行输入总的格子数量 n
 * 第二行输入每个格子的分数 score[i]
 * 第三行输入最大跳的步长 k
 *
 * 备注
 * 格子的总长度 n 和步长 k 的区间在 [1, 100000]
 * 每个格子的分数 score[i] 在 [-10000, 10000] 区间中
 *
 * 输出描述
 * 输出最大得分
 *
 * 示例1
 * 输入
 *
 * 6
 * 1 -1 -6 7 -17 7
 * 2
 * 输出
 *
 * 14
 */
public class JumpScore {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        int[] score = new int[num];
        for (int i = 0; i < num; i++) score[i] = in.nextInt();
        int step = in.nextInt();
        int[] memory = new int[score.length];
        Arrays.fill(memory, Integer.MIN_VALUE);
        System.out.println(dfs(score, 0, step, memory));
    }

    private static int dfs(int[] score, int index, int step, int[] memory) {
        if(index >= score.length) return 0;
        if(memory[index] != Integer.MIN_VALUE) return memory[index];
        if(index == score.length - 1) return score[score.length - 1];
        int max = Integer.MIN_VALUE;
        for (int i = 1; i <= step; i++) {
            max = Math.max(dfs(score, index + i, step, memory) + score[index], max);
        }
        memory[index] = max;
        return max;
    }


}
