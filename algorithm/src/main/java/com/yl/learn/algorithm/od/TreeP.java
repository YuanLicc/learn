package com.yl.learn.algorithm.od;

import java.util.Scanner;

/**
 * 题目描述
 * 近些年来，我国防沙治沙取得显著成果。某沙漠新种植N棵胡杨（编号1-N），排成一排。
 * 一个月后，有M棵胡杨未能成活。
 * 现可补种胡杨K棵，请问如何补种（只能补种，不能新种），可以得到最多的连续胡杨树？
 *
 * 输入描述
 * N 总种植数量，1 <= N <= 100000
 * M 未成活胡杨数量，M 个空格分隔的数，按编号从小到大排列，1 <= M <= N
 * K 最多可以补种的数量，0 <= K <= M
 *
 * 输出描述
 * 最多的连续胡杨棵树
 *
 * 示例1
 * 输入
 *
 * 5
 * 2
 * 2 4
 * 1
 * 输出
 *
 * 3
 * 说明
 * 补种到2或4结果一样，最多的连续胡杨棵树都是3。
 */
public class TreeP {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int allTree = in.nextInt(), deadTree = in.nextInt();
        int[] trees = new int[allTree + 1];
        for (int i = 0; i < deadTree; i++) {
            trees[in.nextInt()] = 1;
        }
        int newTree = in.nextInt();
        int left = 1, right = 1, sumDead = 0, lx = 0;
        for (; right < trees.length; right++) {
            sumDead += trees[right];
            while (sumDead > newTree) {
                sumDead -= trees[left];
                left++;
            }
            lx = Math.max(lx, right - left + 1);
        }
        System.out.println(lx);
    }
}
