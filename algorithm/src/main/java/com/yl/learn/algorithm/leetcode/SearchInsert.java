package com.yl.learn.algorithm.leetcode;

/**
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。
 * 如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 * 请必须使用时间复杂度为 O(log n) 的算法。
 *
 * 示例 1:
 * 输入: nums = [1,3,5,6], target = 5
 * 输出: 2
 *
 * 示例 2:
 * 输入: nums = [1,3,5,6], target = 2
 * 输出: 1
 */
public class SearchInsert {

    public int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1; // 3
        while(left <= right) {
            int middle = (left + right) / 2; // 1
            if(nums[middle] == target) return middle;
            if(nums[middle] > target) {
                right = middle - 1;
            }
            else {
                left = middle + 1;
            }
        }
        return left;
    }

}
