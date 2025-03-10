package com.yl.learn.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
 * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
 *
 * 示例 1：
 * 输入：nums = [1,2,3]
 * 输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 *
 * 示例 2：
 * 输入：nums = [0]
 * 输出：[[],[0]]
 */
public class Subsets {

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> rs = new ArrayList<>();
        List<Integer> copied = new ArrayList<>();
        rs.add(new ArrayList<>());
        dfs(rs, nums, copied, 0, nums.length - 1);
        return rs;
    }

    private void dfs(List<List<Integer>> rs, int[] nums, List<Integer> copied, int start, int end) {
        if(start > end) {
            return;
        }
        List<Integer> newCopied = new ArrayList<>(copied);
        newCopied.add(nums[start]);
        rs.add(newCopied);
        // 每个数字都存在两种情况，选还是不选
        dfs(rs, nums, new ArrayList<>(newCopied), start + 1, end);
        dfs(rs, nums, new ArrayList<>(copied), start + 1, end);
    }
}
