package com.yl.learn.algorithm.yuanfudao;

import junit.framework.TestCase;

/**
 * 给定一个未排序的整数数组，找到最长递增子序列的个数。
 *
 * 示例 1:
 *
 * 输入: [1,3,5,4,7]
 * 输出: 2
 * 解释: 有两个最长递增子序列，分别是 [1, 3, 4, 7] 和[1, 3, 5, 7]。
 *
 * 来源：力扣（LeetCode）673
 * 链接：https://leetcode-cn.com/problems/number-of-longest-increasing-subsequence
 */
public class FindNumberOfLIS extends TestCase {
    
    public int findNumberOfLIS(int[] nums) {
        
        int cell[] = new int[nums.length];
        int cnts[] = new int[nums.length];
        
        cell[0] = 1;
        cnts[0] = 1;
        int max = 1;
        for(int i = 1; i < cell.length; i++) {
            cell[i] = 1;
            for(int j = i - 1; j >= 0; j--) {
                if(nums[j] < nums[i]) {
                    if(max == cell[j] + 1) {
                        cnts[i] += cnts[j];
                    }
                    else if(max < cell[j] + 1) {
                        cnts[i] = cnts[j];
                        max = cell[j] + 1;
                    }
                    cell[i] = max;
                }
            }
            cnts[i] = cnts[i] == 0 ? 1 : cnts[i];
            max = 1;
        }

        int rs = 1;
        max = 1;
        for(int i = 1; i < cnts.length; i++) {
            if(cell[i] > max) {
                max = cell[i];
                rs = cnts[i];
            }
            else if(cell[i] == max){
                rs += cnts[i];
            }
        }
        return rs;
    }
    
    public void test() {
        findNumberOfLIS(new int[]{1,2,4,3,5,4,7,2});
    }
    
}
