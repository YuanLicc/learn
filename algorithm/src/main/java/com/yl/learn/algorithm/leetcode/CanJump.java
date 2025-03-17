package com.yl.learn.algorithm.leetcode;

/**
 * 给你一个非负整数数组 nums ，你最初位于数组的 第一个下标 。数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 判断你是否能够到达最后一个下标，如果可以，返回 true ；否则，返回 false 。
 *
 * 示例 1：
 * 输入：nums = [2,3,1,1,4]
 * 输出：true
 * 解释：可以先跳 1 步，从下标 0 到达下标 1, 然后再从下标 1 跳 3 步到达最后一个下标。
 *
 * 示例 2：
 * 输入：nums = [3,2,1,0,4]
 * 输出：false
 * 解释：无论怎样，总会到达下标为 3 的位置。但该下标的最大跳跃长度是 0 ， 所以永远不可能到达最后一个下标。
 */
public class CanJump {

    // 贪心跳跃
    public boolean canJump(int[] nums) {
        int max = nums[0];
        for (int i = 1; i <= nums.length - 1; i++) {
            if(max <= 0) return false;
            if(nums[i] == 0) {
                max--;
            }
            else {
                max = Math.max(max - 1, nums[i]);
            }
        }
        return true;
    }

    // dfs 暴力破解 - 超时
    private boolean canJump(int[] nums, int index) {
        if(index >= nums.length - 1) return true;
        for(int i = nums[index]; i >= 1; i--) {
            if(canJump(nums, index + i)) return true;
        }
        return false;
    }
}
