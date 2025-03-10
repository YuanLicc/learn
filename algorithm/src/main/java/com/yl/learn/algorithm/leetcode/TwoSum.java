package com.yl.learn.algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
 * 你可以假设每种输入只会对应一个答案，并且你不能使用两次相同的元素。
 * 你可以按任意顺序返回答案。
 *
 * 示例 1：
 *
 * 输入：nums = [2,7,11,15], target = 9
 * 输出：[0,1]
 * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
 *
 * 示例 2：
 * 输入：nums = [3,2,4], target = 6
 * 输出：[1,2]
 *
 * 示例 3：
 * 输入：nums = [3,3], target = 6
 * 输出：[0,1]
 *
 *
 * 提示：
 * 2 <= nums.length <= 104
 * -109 <= nums[i] <= 109
 * -109 <= target <= 109
 * 只会存在一个有效答案
 * 进阶：你可以想出一个时间复杂度小于 O(n2) 的算法吗？
 */
public class TwoSum {
    // [2, 7, 11, 15], target = 9 => [0,1]
    // 理解题目：找到两个数的下标，使得 num1 + num2  = target
    // 思路：暴力算法 => 从起点开始遍历，每次遍历都遍历一遍后续节点
    // 优化：hash 保存数据，避免多次遍历
    public int[] twoSum(int[] nums, int target) {// 时间复杂度 N， 空间复杂度：N
        Map<Integer, Integer> numMapIndex = new HashMap<>(); // 空间复杂度：N
        for(int i = 0; i < nums.length; i++) { // 时间复杂度 N
            Integer otherIndex = numMapIndex.get(target - nums[i]);
            // 如果存在则表示找到了目标数据
            // 如果不存在则表示未找到目标数据，需要将当前值put到 MAP 中
            if(null != otherIndex) {
                return new int[]{otherIndex, i};
            }
            else {
                numMapIndex.put(nums[i], i);
            }
        }
        return new int[]{-1, -1};
     }

}
