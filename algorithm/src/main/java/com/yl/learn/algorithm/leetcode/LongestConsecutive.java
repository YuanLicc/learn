package com.yl.learn.algorithm.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
 * 请你设计并实现时间复杂度为 O(n) 的算法解决此问题。
 *
 * 示例 1：
 * 输入：nums = [100,4,200,1,3,2]
 * 输出：4
 * 解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
 *
 * 示例 2：
 * 输入：nums = [0,3,7,2,5,8,4,6,0,1]
 * 输出：9
 *
 * 示例 3：
 * 输入：nums = [1,0,1,2]
 * 输出：3
 */
public class LongestConsecutive {

    // 理解题目：也就是找出数组中连续递增的数字的个数，且不能重复计算个数
    // 思路1：考虑排序后遍历数组，得到连续数字个数：快排加一次遍历 = n*logn + n
    // 思路2：考虑hash的使用，怎么利用 hash来判断连续，咱们可以考虑用线遍历数组存储到 hash中，再采用深度遍历来计算个数
    public int longestConsecutive(int[] nums) { // 空间复杂度 N，时间复杂度 N + N ~ N + 2N => O(N)
        Set<Integer> set = new HashSet<>(); // 空间复杂度 N
        for (int num : nums) { // 时间复杂度 n
            set.add(num);
        }
        int max = 0;
        // 时间复杂度，最优
        for(int num : set) { // 时间复杂度 n
            if(!set.contains(num - 1)) { // 假设首次进入且全数组连续：N；假设末次进入且全数组连续 N + N
                int currentNum = num + 1;
                int count = 1;
                while(set.contains(currentNum)) { // 时间复杂度 m
                    count++;
                    currentNum++;
                }
                max = Math.max(max, count);
            }
        }
        return max;
    }
}
