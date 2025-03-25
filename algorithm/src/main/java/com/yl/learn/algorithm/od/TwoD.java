package com.yl.learn.algorithm.od;

import java.util.*;

/**
 * 题目描述
 * 给一个二维数组 nums，对于每一个元素 nums[i]，找出距离最近的且值相等的元素，
 * 输出横纵坐标差值的绝对值之和，如果没有等值元素，则输出-1。
 * 输入描述
 * 输入第一行为二维数组的行
 * 输入第二行为二维数组的列
 * 输入的数字以空格隔开。
 *
 * 备注
 * 针对数组 nums[i][j]，满足 0 < i ≤ 100，0 < j ≤ 100
 * 对于每个数字，最多存在 100 个与其相等的数字
 * 输出描述
 * 数组形式返回所有坐标值。
 *
 * 示例1
 * 输入
 *
 * 3
 * 5
 * 0 3 5 4 2
 * 2 5 7 8 3
 * 2 5 4 2 4
 * 输出
 *
 * [[-1, 4, 2, 3, 3], [1, 1, -1, -1, 4], [1, 1, 2, 3, 2]]
 */
public class TwoD {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int lineNum = in.nextInt(), lieNum = in.nextInt();
        in.nextLine();
        Map<Integer, List<Point>> map = new HashMap<>();
        int[][] numArray = new int[lineNum][lieNum];
        for (int i = 0; i < lineNum; i++) {
            String[] lines = in.nextLine().split(" ");
            for (int j = 0; j < lieNum; j++) {
                int num = Integer.parseInt(lines[j]);
                List<Point> list = map.getOrDefault(num, new ArrayList<Point>());
                Point point = new Point(i, j);
                list.add(point);
                map.put(num, list);
                numArray[i][j] = num;
            }
        }

        int[][] rs = new int[lineNum][lieNum];
        for (int i = 0; i < lineNum; i++) {
            for (int j = 0; j < lieNum; j++) {
                List<Point> list = map.get(numArray[i][j]);
                int minDistance = Integer.MAX_VALUE;
                for (int z = 0; z < list.size(); z++) {
                    Point point = list.get(z);
                    if(point.i == i && point.j == j) continue;
                    minDistance = Math.min(distance(i, j, point), minDistance);
                }
                if(minDistance == Integer.MAX_VALUE) {
                    rs[i][j] = -1;
                }
                else {
                    rs[i][j] = minDistance;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < lineNum; i++) {
            sb.append("[");
            for (int j = 0; j < lieNum; j++) {
                sb.append(rs[i][j]).append(j == lieNum - 1 ? "" : ",");
            }
            sb.append("]");
        }
        sb.append("]");
        System.out.println(sb.toString());
    }

    private static int distance(int i, int j, Point two) {
        return Math.abs(i - two.i) + Math.abs(j - two.j);
    }

    static class Point {
        int i, j;
        Point(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }
}
