package com.yl.learn.algorithm.leetcode;

/**
 * 给定一个长度为 n 的 0 索引整数数组 nums。初始位置为 nums[0]。
 * 每个元素 nums[i] 表示从索引 i 向后跳转的最大长度。换句话说，如果你在 nums[i] 处，你可以跳转到任意 nums[i + j] 处:
 *
 * 0 <= j <= nums[i]
 * i + j < n
 * 返回到达 nums[n - 1] 的最小跳跃次数。生成的测试用例可以到达 nums[n - 1]。
 *
 * 示例 1:
 * 输入: nums = [2,3,1,1,4]
 * 输出: 2
 * 解释: 跳到最后一个位置的最小跳跃数是 2。
 *      从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
 *
 * 示例 2:
 * 输入: nums = [2,3,0,1,4]
 * 输出: 2
 */
public class CanJumpTwo {

    public int jump(int[] nums) {
        int[] dp = new int[nums.length];
        dp[nums.length - 1] = 0;
        for(int i =  nums.length - 2; i >= 0; i--) {
            dp[i] = Integer.MAX_VALUE;
            for(int num = nums[i]; num >= 1; num--) {
                int sumIndex = num + i;
                if(sumIndex >= nums.length) {
                    dp[i] = 1;
                    break;
                }
                else {
                    dp[i] = dp[i + num] == Integer.MAX_VALUE ? dp[i] : Math.min(dp[i], 1 + dp[i + num]);
                }
            }
        }
        return dp[0];
    }
}
