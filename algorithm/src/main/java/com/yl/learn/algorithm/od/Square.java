package com.yl.learn.algorithm.od;

import java.util.*;

/**
 * 题目描述
 * 输入N个互不相同的二维整数坐标，求这N个坐标可以构成的正方形数量。[内积为零的的两个向量垂直]
 *
 * 输入描述
 * 第一行输入为N，N代表坐标数量，N为正整数。N <= 100
 * 之后的 K 行输入为坐标x y以空格分隔，x，y为整数，-10<=x, y<=10
 *
 * 输出描述
 * 输出可以构成的正方形数量。
 *
 * 示例1
 * 输入
 *
 * 3
 * 1 3
 * 2 4
 * 3 1
 * 输出
 * 0
 * 说明
 * （3个点不足以构成正方形）
 *
 * 示例2
 *
 * 输入
 * 4
 * 0 0
 * 1 2
 * 3 1
 * 2 -1
 *
 * 输出
 * 1
 */
public class Square {
    // 向量乘积=0，表示构成直角
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        if(num < 4) {
            System.out.println(0);
            return;
        }
        HashSet<Point> points = new HashSet<>();
        List<Point> pointList = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            Point point = new Point(in.nextInt(), in.nextInt());
            points.add(point);
            pointList.add(point);
        }

        int squareNum = 0;
        for (int i = 0; i < points.size(); i++) {
            for (int j = i + 1; j < points.size(); j++) {
                // 计算向量
                Point p1 = pointList.get(i), p2 = pointList.get(j);
                Point p3 = new Point(p1.x - (p1.y - p2.y), p1.y + (p1.x - p2.x));
                Point p4 = new Point(p2.x - (p1.y - p2.y), p2.y + (p1.x - p2.x));
                if(points.contains(p3) && points.contains(p4)) {
                    squareNum++;
                }

                Point p5 = new Point(p1.x + (p1.y - p2.y), p1.y - (p1.x - p2.x));
                Point p6 = new Point(p2.x + (p1.y - p2.y), p2.y - (p1.x - p2.x));
                if(points.contains(p5) && points.contains(p6)) {
                    squareNum++;
                }
            }
        }
        System.out.println(squareNum / 4);
    }

    static class Point {
        int x, y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x && y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

}
