package com.yl.learn.algorithm.yuanfudao;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个包含 m x n 个元素的矩阵（m 行, n 列），请按照顺时针螺旋顺序，返回矩阵中的所有元素。
 *
 * 输入:
 * [
 *  [ 1, 2, 3 ],
 *  [ 4, 5, 6 ],
 *  [ 7, 8, 9 ]
 * ]
 * 输出: [1,2,3,6,9,8,7,4,5]
 *
 * 来源：力扣（LeetCode）54
 * 链接：https://leetcode-cn.com/problems/spiral-matrix
 */
public class SpiralOrder extends TestCase {
    
    public List<Integer> spiralOrder(int[][] matrix) {
        if(matrix == null || matrix.length == 0) return new ArrayList<>(1);
        
        int len = matrix.length * matrix[0].length;
        List<Integer> rs = new ArrayList<>(len);
        
        int leftFence = -1;
        int rightFence = matrix[0].length;
        int upFence = -1;
        int downFence = matrix.length;
        
        boolean is = false;
        
        while(rs.size() < len) {
            // ->
            for(int i = leftFence + 1; i < rightFence && downFence - upFence > 1; i++) {
                rs.add(matrix[upFence + 1][i]);
                is = true;
            }
            upFence = is ? upFence + 1 : upFence;
            
            is = false;
            
            // |
            for(int i = upFence + 1; i < downFence && rightFence - leftFence > 1; i++) {
                rs.add(matrix[i][rightFence - 1]);
                is = true;
            }
            rightFence = is ? rightFence - 1 : rightFence;
            is = false;
            
            // <-
            for(int i = rightFence - 1; i > leftFence && downFence - upFence > 1; i--) {
                rs.add(matrix[downFence - 1][i]);
                is = true;
            }
            downFence = is ? downFence - 1 : downFence;
            is = false;
            
            // ^
            for(int i = downFence - 1; i > upFence && rightFence - leftFence > 1; i--) {
                rs.add(matrix[i][leftFence + 1]);
                is = true;
            }
            leftFence = is ? leftFence + 1 : leftFence;
            is = false;
        }
        
        return rs;
    }
    
    public void test() {
        spiralOrder(new int[][]{
                { 1, 2, 3 },
                { 4, 5, 6 },
                { 7, 8, 9 }
        });
    }
}
