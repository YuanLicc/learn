package com.yl.learn.algorithm.od;

import java.util.Scanner;

/**
 * 题目描述
 * 给出3组点坐标(x, y, w, h)，-1000<x,y<1000，w,h为正整数。
 * (x, y, w, h)表示平面直角坐标系中的一个矩形：
 * x, y为矩形左上角坐标点，w, h向右w，向下h。
 * (x, y, w, h)表示x轴(x, x+w)和y轴(y, y-h)围成的矩形区域；
 * (0, 0, 2, 2)表示 x轴(0, 2)和y 轴(0, -2)围成的矩形区域；
 * (3, 5, 4, 6)表示x轴(3, 7)和y轴(5, -1)围成的矩形区域；
 *
 * 求3组坐标构成的矩形区域重合部分的面积。
 * 输入描述
 * 3行输入分别为3个矩形的位置，分别代表“左上角x坐标”，“左上角y坐标”，“矩形宽”，“矩形高” -1000 <= x,y < 1000
 *
 * 输出描述
 * 输出3个矩形相交的面积，不相交的输出0。
 *
 * 示例1
 * 输入
 *
 * 1 6 4 4
 * 3 5 3 4
 * 0 3 7 3
 * 输出
 *
 * 2
 */
public class JxCombine {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[][] points = new int[3][4];
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < points[i].length; j++) {
                points[i][j] = in.nextInt();
            }
        }
        int[] com = points[0];
        for (int i = 1; i < points.length; i++) {
            com = combine(com, points[i]);
            if(com == null) {
                System.out.println(0);
                return;
            }
        }
        System.out.println(com[2] * com[3]);
    }

    private static int[] combine(int[] one, int[] two) {
        int maxX = Math.max(one[0], two[0]);
        int minX = Math.min(one[0] + one[2], two[0] + two[2]);
        if(minX <= maxX) return null;
        int maxY = Math.max(one[1] - one[3], two[1] - two[3]);
        int minY = Math.min(one[1], two[1]);
        if(minY <= maxY) return null;
        int[] rs = new int[4];
        rs[0] = maxX;
        rs[1] = minY;
        rs[2] = minX - maxX;
        rs[3] = minY - maxY;
        return rs;
    }
}
