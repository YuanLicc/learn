package com.yl.learn.algorithm.od;

import java.util.HashSet;
import java.util.Scanner;

/**
 * 题目描述
 * 周末小明准备去爬山锻炼，0代表平地，山的高度使用1到9来表示，小明每次爬山或下山高度只能相差k及k以内，每次只能上下左右一个方向上移动一格，小明从左上角(0,0)位置出发
 * 输入描述
 * 第一行输入m n k(空格分隔)
 * 代表m*n的二维山地图，k为小明每次爬山或下山高度差的最大值，
 * 然后接下来输入山地图，一共m行n列，均以空格分隔。取值范围：
 *
 * 0 < m ≤ 500
 * 0< n ≤ 500
 * 0 < k < 5
 * 备注
 * 所有用例输入均为正确格式，且在取值范围内，考生不需要考虑不合法的输入格式。
 *
 * 输出描述
 * 请问小明能爬到的最高峰多高，到该最高峰的最短步数，输出以空格分隔。
 *
 * 同高度的山峰输出较短步数。
 *
 * 如果没有可以爬的山峰，则高度和步数都返回0。
 *
 * 示例1
 *
 * 输入
 * 5 4 1
 * 0 1 2 0
 * 1 0 0 0
 * 1 0 1 2
 * 1 3 1 0
 * 0 0 0 9
 *
 * 输出
 * 2 2
 */
public class Mountain {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int m = in.nextInt(); // m
        int n = in.nextInt(); // n
        int h = in.nextInt(); // 每次最多能够攀登的高度
        int[][] mCeil = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                mCeil[i][j] = in.nextInt();
            }
        }
        dfs(mCeil, 0, 0, 0, 0, h, new HashSet<String>());
        System.out.println(maxH + " " + minStep);
    }

    static int maxH = 0;
    static int minStep = Integer.MAX_VALUE;

    private static void  dfs(int[][] mCeil, int i, int j, int preH, int step, int h, HashSet<String> train) {
        if(i < 0 || i >= mCeil.length || j < 0 || j >= mCeil[0].length) return;
        String key = i + "," + j;
        if(train.contains(key)) {
            return;
        }
        // 满足条件，上或者下坡
        if((preH <= mCeil[i][j] && preH + h >= mCeil[i][j]) || (preH > mCeil[i][j] && preH - h <= mCeil[i][j])) {
            if(mCeil[i][j] >= maxH) {
                minStep = mCeil[i][j] == maxH ? Math.min(minStep, step) : step;
                maxH = mCeil[i][j];
            }
            train.add(key);
            dfs(mCeil, i, j + 1, mCeil[i][j], step + 1, h, train);
            dfs(mCeil, i + 1, j, mCeil[i][j], step + 1, h, train);
            dfs(mCeil, i, j - 1, mCeil[i][j], step + 1, h, train);
            dfs(mCeil, i - 1, j, mCeil[i][j], step + 1, h, train);
            train.remove(key);
        }
        // 不满足直接返回
    }


}
