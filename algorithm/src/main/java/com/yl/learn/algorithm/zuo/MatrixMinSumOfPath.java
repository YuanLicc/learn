package com.yl.learn.algorithm.zuo;

/**
 * 矩阵最小路径和，如下矩阵：
 * 1 3 5 9
 * 8 1 3 4
 * 5 0 6 1
 * 8 8 4 0
 * 从左上角开始，只能往右边走或者往下走，直到右下角，在这条路径中，每个点对应数字的和。
 */
public class MatrixMinSumOfPath {

    public static int minSumOfPath(int[][] cell) {
        if(cell == null || cell.length == 0) {
            return -1;
        }

        return recursion(cell, cell.length - 1, cell[0].length - 1);
    }

    private static int recursion(int[][] cell, int row, int column) {

        if(row == 0 && column == 0) {
            return cell[0][0];
        }
        else if(row == 0) {
            return cell[row][column] + recursion(cell, row, column - 1);
        }
        else if(column == 0) {
            return cell[row][column] + recursion(cell, row - 1, column);
        }
        else {
            return Math.min(recursion(cell, row - 1, column), recursion(cell, row, column - 1)) + cell[row][column];
        }
    }

}
