package com.yl.learn.algorithm.leetcode;

/**
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * 请注意 ，必须在不复制数组的情况下原地对数组进行操作。
 *
 * 示例 1:
 * 输入: nums = [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 *
 * 示例 2:
 * 输入: nums = [0]
 * 输出: [0]
 */
public class MoveZeroes {
    // 题目理解 => 数字0移动到末端，且保证非零原数的顺序性
    // 思路1：双指针，一个指向0的指针，一个指向非零的指针实现
    public void moveZeroes(int[] nums) { // 时间复杂度 N，空间复杂度 (0 ~ N) + 2
        int zeroIndex = 0;
        int noZeroIndex = 0;
        while(noZeroIndex < nums.length) {
            if(nums[noZeroIndex] != 0) { // 空间复杂度：假设没有0，空间 N，都为0时空间 0
                int temp = nums[noZeroIndex];
                nums[noZeroIndex] = nums[zeroIndex];
                nums[zeroIndex] = temp;
                zeroIndex++;
            }
            noZeroIndex++;
        }

        // 下面的实现会导致noZeroIndex 扫描过多
        /*int zeroIndex = 0;
        while(true) {
            while(zeroIndex < nums.length && nums[zeroIndex] != 0) {
                zeroIndex++;
            }
            if(zeroIndex >= nums.length - 1) {
                break;
            }
            int noZeroIndex = zeroIndex;
            while(noZeroIndex < nums.length && nums[noZeroIndex] == 0) {
                noZeroIndex++;
            }
            if(noZeroIndex >= nums.length) {
                break;
            }
            int temp = nums[zeroIndex];
            nums[zeroIndex] = nums[noZeroIndex];
            nums[noZeroIndex] = temp;
            zeroIndex++;
        }*/
    }

    // 0,1,0,3,5  0,1,0,3,5
    // 1,0,0,3,5  1，
    // 1,3,0,0,5
    // 1,3,5,0,0  0, 1, 0,1,0, 1

}
