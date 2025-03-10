package com.yl.learn.algorithm.zuo;


import java.util.Stack;

/**
 * 给定一个整型矩阵map，其中的值只有0和1两种，求其中全是1的所有矩形区域中，最大的矩形区域为1的数量。
 * 例如：
 * 1110
 * 其中，最大的矩形区域有3个1，所以返回3。
 *
 * <p>
 * 再如：
 * 1011
 * 1111
 * 1110
 * 其中，最大的矩形区域有 6 个 1，所以返回 6。
 */
public class MaxRectangle {


    public static int maxRectangle(byte[][] cell) {

        if (cell == null || cell.length == 0) {
            return -1;
        }

        int max = Integer.MIN_VALUE;

        for (int i = 0; i < cell.length; i++) {
            int[] row = new int[cell[i].length];

            for (int j = 0; j < cell[i].length; j++) {
                row[j] = i == 0 ? cell[i][j] : cell[i][j] + cell[i - 1][j];
            }

            int rowMax = rowMaxArea(row);
            max = max > rowMax ? max : rowMax;
        }

        return max;

    }

    private static int rowMaxArea(int[] row) {

        Stack<Integer> indexStack = new Stack<>();

        int maxArea = Integer.MIN_VALUE;

        for (int i = 0; i < row.length; i++) {

            while (!indexStack.isEmpty() && row[i] <= row[indexStack.peek()]) {
                int j = indexStack.pop();
                int k = indexStack.isEmpty() ? -1 : indexStack.peek();
                int curArea = (i - k - 1) * row[j];
                maxArea = Math.max(maxArea, curArea);
            }

            indexStack.push(i);
        }

        while (!indexStack.isEmpty()) {
            int j = indexStack.pop();
            int k = indexStack.isEmpty() ? -1 : indexStack.peek();
            int curArea = (row.length - k - 1) * row[j];
            maxArea = Math.max(maxArea, curArea);
        }
        return maxArea;
    }

}
