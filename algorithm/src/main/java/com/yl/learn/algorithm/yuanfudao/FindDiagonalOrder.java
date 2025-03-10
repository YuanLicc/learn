package com.yl.learn.algorithm.yuanfudao;

import junit.framework.TestCase;

/**
 * 给定一个含有 M x N 个元素的矩阵（M 行，N 列），请以对角线遍历的顺序返回这个矩阵中的所有元素，对角线遍历如下图所示。
 *
 * 示例:
 * 输入:
 *  [
 *  [ 1, 2, 3 ],
 *  [ 4, 5, 6 ],
 *  [ 7, 8, 9 ]
 *  ]
 *
 * 输出:  [1,2,4,7,5,3,6,8,9]
 *
 * 来源：力扣（LeetCode）498
 * 链接：https://leetcode-cn.com/problems/diagonal-traverse
 */
public class FindDiagonalOrder extends TestCase {

    public int[] findDiagonalOrder(int[][] matrix) {
    
        if(matrix == null || matrix.length == 0) return new int[0];
    
        int[] rs = new int[matrix.length * matrix[0].length];
        int index = 0;
        int i = 0;
        int j = 0;
        boolean isUp = true;
        while(index < rs.length) {
            if(isUp) {
                while (i >= 0 && j <= matrix[0].length - 1) {
                    rs[index++] = matrix[i][j];
                    i--;
                    j++;
                }
                i++;
                j--;
                if(j == matrix[0].length - 1) {
                    i++;
                }
                else {
                    j++;
                }
                isUp = false;
            }
            else {
                while (j >= 0 && i <= matrix.length - 1) {
                    rs[index++] = matrix[i][j];
                    i++;
                    j--;
                }
                i--;
                j++;
            
                if(i == matrix.length - 1) {
                    j++;
                }
                else {
                    i++;
                }
                isUp = true;
            }
        }
        return rs;
    }

    public void test() {
        int [][] a = new int[][]{
                { 1, 2, 1},
                { 3, 4, 2} // 1 2 1 3 4 2
                           // 1 2 3 4 1 2
        };

        findDiagonalOrder(a);
    }
}
