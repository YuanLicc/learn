package com.yl.learn.algorithm.yuanfudao;

import junit.framework.TestCase;

/**
 * 峰值元素是指其值大于左右相邻值的元素。
 * 给定一个输入数组 nums，其中 nums[i] ≠ nums[i+1]，找到峰值元素并返回其索引。
 * 数组可能包含多个峰值，在这种情况下，返回任何一个峰值所在位置即可。
 * 你可以假设 nums[-1] = nums[n] = -∞。
 *
 * 示例 1:
 * 输入: nums = [1,2,3,1]
 * 输出: 2
 * 解释: 3 是峰值元素，你的函数应该返回其索引 2。
 *
 * 来源：力扣（LeetCode） 162
 * 链接：https://leetcode-cn.com/problems/find-peak-element
 */
public class FindPeakElement extends TestCase {
    public int findPeakElement(int[] nums) {
        
        if(nums == null) return -1;
        if(nums.length == 1) return 0;
        
        int left = 0;
        int right = nums.length - 1;
        
        int middle = (left + right) / 2;
        
        while(!check(nums, middle)) {
            if(middle == 0) left = middle + 1;
            else if(middle == nums.length - 1) right = middle - 1;
            else if(nums[middle - 1] > nums[middle]) right = middle - 1;
            else left = middle + 1;
            
            middle = (left + right) / 2;
        }
        
        return middle;
    }
    
    private boolean check(int[] nums, int i) {
        if(i == 0) return nums[1] < nums[0];
        
        if(i == nums.length - 1) return nums[i] > nums[i - 1];
        
        return nums[i - 1] < nums[i] && nums[i + 1] < nums[i];
    }
    
    public void test() {
        findPeakElement(new int[]{1, 2, 3, 1});
    }
}
