package com.yl.learn.algorithm.bytedance;

import java.util.Stack;

/**
 * 1.世界杯开幕式会在球场C举行，球场C的球迷看台可以容纳M*N个球迷。在球场售票完成后，现官方想统计此次开幕式一共有多少个球队球迷群体，
 * 最大的球队球迷群体有多少人。经调研发现，球迷群体在选座时有以下特性：
 * 同球队的球迷群体会选择相邻座位，不同球队的球迷群体会选择不相邻的座位（注解：相邻包括前后相邻，左右相邻，斜对角相邻）
 * 给定一个M*N的二维球场，0代表该位置没有坐人，1代表该位置已有选择，希望输出球队群体个数P，最大的球队群体人数Q
 *
 * 输入描述：
 * 第一行，2个数字，M及N，使用英文逗号分隔
 * 接下来M行，每行N的数字，使用英文逗号分隔
 *
 * 输出描述：
 * 一行，2个数字，P及Q，使用英文逗号分隔
 * 其中P表示球队群体个数，Q表示最大的球队群体人数
 *
 * 例：输入
 *
 * 10，10
 * 0,0,0,0,0,0,0,0,0,0
 * 0,0,0,1,1,0,1,0,0,0
 * 0,1,0,0,0,0,0,1,0,1
 * 1,0,0,0,0,0,0,0,1,1
 * 0,0,0,1,1,1,0,0,0,1
 * 0,0,0,0,0,0,1,0,1,1
 * 0,1,1,0,0,0,0,0,0,0
 * 0,0,0,1,0,1,0,0,0,0
 * 0,0,1,0,0,1,0,0,0,0
 * 0,1,0,0,0,0,0,0,0,0
 *
 * 输出：6,8
 */
public class FootballFans {


    public static int[] football(int m, int n, byte[][] cell) {

        int count = 0;
        int max = 0;

        for(int i = 0; i < m; i++) {

            for(int j = 0; j < n; j++) {
                byte b = cell[i][j];

                if(b == 1) {
                    count++;
                    int num = deep(i, j, cell);
                    if(num > max) {
                        max = num;
                    }
                }
            }
        }
        int[] a = {count, max};
        return a;
    }

    private static int deep(int m, int n, byte[][] cell) {
        int count = 0;
        Stack<int[]> stack = new Stack<>();
        cell[m][n] = 0;
        int[] first = {m, n};
        stack.add(first);

        while(!stack.empty()) {
            int[] curr = stack.pop();
            count++;
            add(stack, curr[0], curr[1], cell);
        }
        return count;
    }

    private static void add(Stack<int[]> stack, int i, int j, byte[][] cell) {

        if(get(cell, i - 1, j - 1)) {
            cell[i- 1][j - 1] = 0;
            add(stack, i - 1, j - 1);
        }
        if(get(cell, i - 1, j)) {
            cell[i- 1][j] = 0;
            add(stack, i - 1, j);
        }
        if(get(cell, i - 1, j + 1)) {
            cell[i- 1][j + 1] = 0;
            add(stack, i - 1, j + 1);
        }
        if(get(cell, i, j - 1)) {
            cell[i][j - 1] = 0;
            add(stack, i, j - 1);
        }
        if(get(cell, i, j + 1)) {
            cell[i][j + 1] = 0;
            add(stack, i, j + 1);
        }
        if(get(cell, i + 1, j - 1)) {
            cell[i + 1][j - 1] = 0;
            add(stack, i + 1, j - 1);
        }
        if(get(cell, i + 1, j)) {
            cell[i + 1][j] = 0;
            add(stack, i - 1, j);
        }
        if(get(cell, i + 1, j + 1)) {
            cell[i + 1][j + 1] = 0;
            add(stack, i + 1, j + 1);
        }

    }

    private static void add(Stack<int[]> stack, int i, int j) {
        int[] a = {i, j};
        stack.push(a);
    }

    private static boolean get(byte[][] cell, int i, int j) {

        if(i < 0 || j < 0 || i >= cell.length ||  j >= cell[i].length) {
            return false;
        }

        if(cell[i][j] == 0) {
            return false;
        }

        return true;
    }

}