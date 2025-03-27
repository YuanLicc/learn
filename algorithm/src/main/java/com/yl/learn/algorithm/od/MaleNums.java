package com.yl.learn.algorithm.od;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 题目描述
 * 学校组织活动，将学生排成一个矩形方阵。
 * 请在矩形方阵中找到最大的位置相连的男生数量。
 * 这个相连位置在一个直线上，方向可以是水平的，垂直的，成对角线的或者呈反对角线的。
 * 注：学生个数不会超过10000
 *
 * 输入描述
 * 输入的第一行为矩阵的行数和列数，接下来的n行为矩阵元素，元素间用”,”分隔。
 *
 * 输出描述
 * 输出一个整数，表示矩阵中最长的位置相连的男生个数。
 *
 * 示例1
 * 输入
 *
 * 3,4
 * F,M,M,F
 * F,M,M,F
 * F,F,F,M
 * 输出
 *
 * 3
 */
public class MaleNums {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[] infos = Arrays.stream(in.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        String[][] matrix = new String[infos[0]][infos[1]];
        for (int i = 0; i < infos[0]; i++) {
            matrix[i] = in.nextLine().split(",");
        }
        int max = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if(matrix[i][j].equals("M")) {
                    for (int[] direction : directions) {
                        max = Math.max(max, dfs(matrix, i, j, direction[0], direction[1]));
                    }
                }
            }
        }
        System.out.println(max);
    }

    private static int dfs(String[][] matrix, int i, int j, int iPlus, int jPlus) {
        if(i < 0 || i >= matrix.length || j < 0 || j >= matrix[0].length) return 0;
        if(matrix[i][j].equals("M")) {
            return dfs(matrix, i + iPlus, j + jPlus, iPlus, jPlus) + 1;
        }
        else return 0;
    }

    static int[][] directions = new int[][]{{1, 0}, {0, 1}, {1, 1}, {1, -1}};
}
