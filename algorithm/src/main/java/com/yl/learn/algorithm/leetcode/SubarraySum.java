package com.yl.learn.algorithm.leetcode;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * 给你一个整数数组 nums 和一个整数 k ，请你统计并返回 该数组中和为 k 的子数组的个数 。
 * 子数组是数组中元素的连续非空序列。
 *
 * 示例 1：
 * 输入：nums = [1,1,1], k = 2
 * 输出：2
 *
 * 示例 2：
 * 输入：nums = [1,2,3], k = 3
 * 输出：2
 */
public class SubarraySum {
    // 理解题目：首先时寻找数组中的连续子数组，并且子数组的和为 目标K，另外数组本身不保序
    // 思路：考虑滑动窗口实现，当窗口数值小于K时，咱们扩张窗口数据，当窗口数值等于K时，结果加一，并且移除第一个数字继续滑动判断，此类思路比较难以解决负数问题
    // 思路：考虑暴力解法，第一层循环选定一个数字，然后向后遍历所有连续和
    // 暴力解法也可以翻转执行，也就是第一城循环选定一个数字，然后向前遍历所有连续和
    // 优化暴力解法，考虑存在前面的和，也就是保存每一个前缀和
    public int subarraySum(int[] nums, int k) { // 时间复杂度 N，空间复杂度 N
        int pre = 0, rs = 0;
        Map<Integer, Integer> preSum = new HashMap<>();
        preSum.put(0, 1);
        for (int num : nums) { // 由前至后遍历
            pre += num; // 计算前缀和 pre + num
            if (preSum.containsKey(pre - k)) { // 如果 pre + num = k - 前缀和，那么存在一个移除前缀的连续数组满足目标值
                rs += preSum.get(pre - k);
            }
            preSum.put(pre, preSum.getOrDefault(pre, 0) + 1);
        }
        return rs;
    }


}
