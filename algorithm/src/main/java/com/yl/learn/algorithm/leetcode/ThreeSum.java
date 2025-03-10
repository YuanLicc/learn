package com.yl.learn.algorithm.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给你一个整数数组 nums
 * 判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k
 * 同时还满足 nums[i] + nums[j] + nums[k] == 0 。请你返回所有和为 0 且不重复的三元组。
 * 注意：答案中不可以包含重复的三元组。
 *
 * 示例 1：
 * 输入：nums = [-1,0,1,2,-1,-4]
 * 输出：[[-1,-1,2],[-1,0,1]]
 * 解释：
 * nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0 。
 * nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0 。
 * nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0 。
 * 不同的三元组是 [-1,0,1] 和 [-1,-1,2] 。
 * 注意，输出的顺序和三元组的顺序并不重要。
 *
 * 示例 2：
 * 输入：nums = [0,1,1]
 * 输出：[]
 * 解释：唯一可能的三元组和不为 0 。
 */
public class ThreeSum {

    // 理解题目：从数组中找到三个数字，使得三数之和 = 0
    // 思路1：暴力解法，三层循环遍历，判断三数之和
    // 思路2: 固定第一个数，后续两个数采用双指针贪心判定（需要排序），排序后的重复问题只需要移动指针即可
    // 思路3：固定第一个数，后续两个数转化为两数之和 =  0 - 第一个数的两数之和，可以用 MAP 来存储；但是需要考虑重复问题
    public List<List<Integer>> threeSum(int[] nums) { //时间复杂度：N*logN + N*N
        List<List<Integer>> rs = new ArrayList<>();
        // 排序
        Arrays.sort(nums); // 时间复杂度 N*logN
        for(int i = 0; i < nums.length - 1; ) { // 时间复杂度 N
            int first = nums[i];
            int target = -first;
            // 采用双指针来获得后续两个数
            int left = i + 1;
            int right = nums.length - 1;
            // 排序的数组中用前后指针进行贪心推进
            while(left < right) {// 时间复杂度 N
                int sum = nums[left] + nums[right];
                // 满足条件情况
                if(sum == target) {
                    rs.add(Arrays.asList(first, nums[left], nums[right]));
                    // 考虑重复问题，咱们要分别移动 左指针和右指针
                    left = move(nums, left, right, false);
                    right = move(nums, left, right, true);
                }
                // 不满足的情况下，贪心
                else if(sum < target) {
                    left = move(nums, left, right, false);
                }
                else {
                    right = move(nums, left, right, true);
                }
            }
            i = move(nums, i, nums.length - 1, false);
        }
        return rs;
    }

    private int move(int[] nums, int left, int right, boolean isLeftMove) {
        int currentNum = isLeftMove ? nums[right] : nums[left];
        if(isLeftMove) {
            while(left < right && nums[right] == currentNum) {
                right--;
            }
            return right;
        }
        else {
            while(left < right && nums[left] == currentNum) {
                left++;
            }
            return left;
        }
    }
}
