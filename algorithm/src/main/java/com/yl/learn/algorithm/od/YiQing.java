package com.yl.learn.algorithm.od;

import java.util.Scanner;

/**
 * 题目描述
 * 在一个地图中(地图由n*n个区域组成），有部分区域被感染病菌。 感染区域每天都会把周围（上下左右）的4个区域感染。
 * 请根据给定的地图计算，多少天以后，全部区域都会被感染。 如果初始地图上所有区域全部都被感染，或者没有被感染区域，返回-1
 *
 * 输入描述
 * 一行N*N个数字（只包含0,1，不会有其他数字）表示一个地图，数字间用,分割，0表示未感染区域，1表示已经感染区域
 *
 * 每N个数字表示地图中一行，输入数据共表示N行N列的区域地图。
 *
 * 例如输入1,0,1,0,0,0,1,0,1，表示地图
 *
 * 1,0,1
 * 0,0,0
 * 1,0,1
 * 1
 * 2
 * 3
 * 输出描述
 * 一个整数，表示经过多少天以后，全部区域都被感染 1<=N<200
 *
 * 示例1
 * 输入
 *
 * 1,0,1,0,0,0,1,0,1
 * 1
 * 输出
 *
 * 2
 * 1
 * 说明
 *
 * 1天以后，地图中仅剩余中心点未被感染；2天以后，全部被感染。
 */
public class YiQing {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String[] numsStr = in.nextLine().split(",");
        int n = (int) Math.sqrt(numsStr.length);
        int[][] ceil = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                ceil[i][j] = Integer.parseInt(numsStr[j * n + i]);
            }
        }

        for (int i = 0; i < n; i ++) {
            for (int j = 0; j < n; j++) {
                if(ceil[i][j] == 1) {
                    dfs(ceil, i + 1, j, 1);
                    dfs(ceil, i - 1, j, 1);
                    dfs(ceil, i, j + 1, 1);
                    dfs(ceil, i, j - 1, 1);
                }
            }
        }

        int min = 0;
        for (int i = 0; i < n; i ++) {
            for (int j = 0; j < n; j++) {
                if(ceil[i][j] < 0) {
                    min = Math.min(min, ceil[i][j]);
                }
            }
        }
        System.out.println(min == 0 ? -1 : -min);
    }

    private static void dfs(int[][] ceil, int i, int j, int step) {
        if(i < 0 || i >= ceil.length || j < 0 || j >= ceil[0].length) return;
        if(ceil[i][j] == 1) return;
        if(ceil[i][j] < 0) {
            if(-step > ceil[i][j]) {
                ceil[i][j] = Math.max(ceil[i][j], -step);
            }
            else {
                return;
            }
        }
        else if (ceil[i][j] == 0) {
            ceil[i][j] = -step;
        }
        dfs(ceil, i + 1, j, step + 1);
        dfs(ceil, i - 1, j, step + 1);
        dfs(ceil, i, j + 1, step + 1);
        dfs(ceil, i, j - 1, step + 1);
    }
}
