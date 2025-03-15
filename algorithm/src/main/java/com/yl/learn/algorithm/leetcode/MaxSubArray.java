package com.yl.learn.algorithm.leetcode;

/**
 * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * 子数组是数组中的一个连续部分。
 *
 * 示例 1：
 * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
 * 输出：6
 * 解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
 */
public class MaxSubArray {

    // 贪心算法：当和与当前下标之和 > 当前下标元素；表示对于包含当前下标的前缀连续和是最大的
    public int maxSubArray(int[] nums) {
        int max = Integer.MIN_VALUE;
        int sum = 0;
        for(int i = 0; i < nums.length; i++) {
            if(sum + nums[i] > nums[i]) {
                sum = sum + nums[i];
            }
            else {
                sum = nums[i];
            }
            max = Math.max(sum, max);
        }
        return max;
    }
}
