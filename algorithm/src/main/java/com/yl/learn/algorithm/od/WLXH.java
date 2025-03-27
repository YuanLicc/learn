package com.yl.learn.algorithm.od;

import java.util.Scanner;

/**
 * 题目描述
 * 网络信号经过传递会逐层衰减，且遇到阻隔物无法直接穿透，在此情况下需要计算某个位置的网络信号值。
 * 注意:网络信号可以绕过阻隔物。
 *
 * array[m][n] 的二维数组代表网格地图，
 * array[i][j] = 0代表i行j列是空旷位置;
 * array[i][j] = x(x为正整数)代表i行j列是信号源，信号强度是x;
 * array[i][j] = -1代表i行j列是阻隔物。
 * 信号源只有1个，阻隔物可能有0个或多个
 * 网络信号衰减是上下左右相邻的网格衰减1
 * 现要求输出对应位置的网络信号值。
 *
 * 输入描述
 * 输入为三行，
 *
 * 第一行为 m 、n ，代表输入是一个 m × n 的数组。
 * 第二行是一串 m × n 个用空格分隔的整数。每连续 n 个数代表一行，再往后 n 个代表下一行，以此类推。对应的值代表对应的网格是空旷位置，还是信号源，还是阻隔物。
 * 第三行是 i 、 j，代表需要计算array[i][j]的网络信号值。
 * 注意：此处 i 和 j 均从 0 开始，即第一行 i 为 0。
 *
 * 6 5
 * 0 0 0 -1 0 0 0 0 0 0 0 0 -1 4 0 0 0 0 0 0 0 0 0 0 -1 0 0 0 0 0
 * 1 4
 */
public class WLXH {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int m = in.nextInt(), n = in.nextInt();
        int[][] matrix = new int[m][n];
        int x = 0, y = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = in.nextInt();
                if(matrix[i][j] > 0) {
                    x = i;
                    y = j;
                }
            }
        }
        boolean[][] visited = new boolean[m][n];
        dfs(matrix, visited, x, y, matrix[x][y]);
        System.out.println(matrix[in.nextInt()][in.nextInt()]);
    }

    static int[][] fxs = new int[][]{{1,0},{-1, 0},{0,1},{0,-1}};

    private static void dfs(int[][] matrix, boolean[][] visited, int x, int y, int num) {
        if(x < 0 || x >= matrix.length || y < 0 ||  y >= matrix[0].length) return;
        if(matrix[x][y] == -1 || num == 0 || visited[x][y]) return;
        matrix[x][y] = Math.max(matrix[x][y], num);
        visited[x][y] = true;
        for (int[] fx : fxs) {
            dfs(matrix, visited, x + fx[0], y + fx[1], matrix[x][y] - 1);
        }
        visited[x][y] = false;
    }

}
