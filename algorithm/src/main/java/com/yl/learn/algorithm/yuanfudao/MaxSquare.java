package com.yl.learn.algorithm.yuanfudao;

import com.yl.learn.util.util.PrintUtil;
import junit.framework.TestCase;

/**
 * 在一个由 0 和 1 组成的二维矩阵内，找到只包含 1 的最大正方形，并返回其面积。
 * 示例:
 *
 * 输入:
 * 1 0 1 0 0
 * 1 0 1 1 1
 * 1 1 1 1 1
 * 1 0 0 1 0
 * 输出: 4
 *
 * 来源：力扣（LeetCode） 221
 */
public class MaxSquare extends TestCase {
    
    public int maximalSquare(char[][] matrix) {
        if(matrix == null || matrix.length == 0) return 0;
        
        int[][] cell = new int[matrix.length][matrix[0].length];
        
        int max = 0;
        for(int i = 0; i < matrix.length; i++) {
            cell[i][0] = matrix[i][0] - '0';
            if(matrix[i][0] == '1') max = 1;
        }
        
        for(int i = 0; i < matrix[0].length; i++) {
            cell[0][i] = matrix[0][i] - '0';
            if(matrix[0][i] == '1') max = 1;
        }
        
        for(int i = 1; i < matrix.length; i++) {
            
            for(int j = 1; j < matrix[i].length; j++) {
                if(matrix[i][j] == '0') cell[i][j] = 0;
                else if(is(matrix, i, j)) {
                    cell[i][j] = matrixM(cell, i, j) + 1;
                }
                else {
                    cell[i][j] = 1;
                }
                max = Math.max(max, cell[i][j]);
            }
        }
        
        return max * max;
    }
    
    private boolean is(char[][] matrix, int i, int j) {
        return matrix[i - 1][j] == '1' && matrix[i - 1][j - 1] == '1' &&
                matrix[i][j - 1] == '1';
    }
    
    private int matrixM(int[][] cell, int i, int j) {
        return Math.min(Math.min(cell[i][j - 1], cell[i - 1][j]), cell[i - 1][j - 1]);
    }
    
    public void test() {
        char[][] matrix = new char[][]{{'1','0','1','0','0'},{'1','0','1','1','1'}, {'1','1','1','1','1'}, {'1','0','0','1','0'}};
        PrintUtil.println(maximalSquare(matrix));
    }

}
