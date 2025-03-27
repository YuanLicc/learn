package com.yl.learn.algorithm.od;

import java.util.Scanner;

/**
 * 题目描述
 * 现有一个机器人，可放置于 M × N 的网格中任意位置，每个网格包含一个非负整数编号，当相邻网格的数字编号差值的绝对值小于等于 1 时，机器人可以在网格间移动。
 *
 * 问题： 求机器人可活动的最大范围对应的网格点数目。
 *
 * 说明：网格左上角坐标为 (0,0) ,右下角坐标为(m−1,n−1)，机器人只能在相邻网格间上下左右移动
 *
 * 输入描述
 * 第 1 行输入为 M 和 N
 *
 * M 表示网格的行数
 * N 表示网格的列数
 * 之后 M 行表示网格数值，每行 N 个数值（数值大小用 k 表示），数值间用单个空格分隔，行首行尾无多余空格。
 *
 * M、 N、 k 均为整数
 * 1 ≤ M，N ≤ 150,
 * 0 ≤ k ≤ 50
 * 输出描述
 * 输出 1 行，包含 1 个数字，表示最大活动区域的网格点数目，
 * 行首行尾无多余空格。
 *
 * 示例1
 * 输入
 *
 * 4 4
 * 1 2 5 2
 * 2 4 4 5
 * 3 5 7 1
 * 4 6 2 4
 * 输出
 *
 * 6
 */
public class RobotArea {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int m = in.nextInt(), n = in.nextInt();
        int[][] matrix = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = in.nextInt();
            }
        }
        int max = 0;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(!visited[i][j]) {
                    max = Math.max(max, dfs(matrix, visited, i, j));
                }
            }
        }
        System.out.println(max);
    }

    static int[][] directions = new int[][]{{1, 0},{-1 ,0}, {0, 1}, {0, -1}};

    private static int dfs(int[][] matrix, boolean[][] visited, int i, int j) {
        visited[i][j] = true;
        int num = 1;
        for (int[] direction : directions) {
            int newI = i + direction[0], newJ = j + direction[1];
            if (newI >= 0 && newI < visited.length && newJ >= 0
                    && newJ < visited[0].length && !visited[newI][newJ]
                    && Math.abs(matrix[i][j] - matrix[newI][newJ]) <= 1) {
                num += dfs(matrix, visited, newI, newJ);
            }
        }
        return num;
    }

}
