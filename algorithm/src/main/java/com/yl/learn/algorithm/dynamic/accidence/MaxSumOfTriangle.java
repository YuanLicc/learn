package com.yl.learn.algorithm.dynamic.accidence;

public class MaxSumOfTriangle {

    public static int maxSum(int[][] triangle) {

        int[][] cell = new int[triangle.length][];

        for(int i = 0; i < triangle.length; i++) {
            int rowLength = triangle[i].length;

            cell[i] = new int[rowLength];

            for(int j = 0; j < rowLength; j++) {
                int upLeft = get(cell, i - 1, j - 1);
                int upRight = get(cell, i - 1 , j);

                if(upLeft != -1 && upRight != -1) {
                    cell[i][j] = Math.max(upLeft, upRight) + triangle[i][j];
                }
                else if(upLeft != -1 && upRight == -1) {
                    cell[i][j] = upLeft + triangle[i][j];
                }
                else if(upLeft == -1 && upRight != -1) {
                    cell[i][j] = upRight + triangle[i][j];
                }
                else {
                    cell[i][j] = triangle[i][j];
                }

            }

        }

        return max(cell);
    }

    public static int max(int[][] cell) {
        int max = 0;

        int lastRowIndex = cell.length - 1;

        for(int i = 0; i < cell[lastRowIndex].length; i++) {
            if(max < cell[lastRowIndex][i]) {
                max = cell[lastRowIndex][i];
            }
        }

        return max;
    }

    public static int get(int[][] cell, int i, int j) {
        if(i < 0 || j < 0) {
            return -1;
        }

        if(j >= cell[i].length) {
            return -1;
        }

        return cell[i][j];
    }

}
