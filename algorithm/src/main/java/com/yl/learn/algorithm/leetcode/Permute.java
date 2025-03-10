package com.yl.learn.algorithm.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
 *
 * 示例 1：
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 *
 * 示例 2：
 * 输入：nums = [0,1]
 * 输出：[[0,1],[1,0]]
 *
 * 示例 3：
 * 输入：nums = [1]
 * 输出：[[1]]
 */
public class Permute {

    public List<List<Integer>> permute(int[] nums) { // 时间复杂度 N * N!
        List<Integer> copied = new ArrayList<>();
        for (int num : nums) {
            copied.add(num);
        }
        List<List<Integer>> rs = new ArrayList<>();
        permute(rs, copied, 0, nums.length - 1);
        return rs;
    }

    private void permute(List<List<Integer>> rs, List<Integer> copied, int index, int maxIndex) {
        if(index == maxIndex) {
            rs.add(new ArrayList<>(copied)); // 深度拷贝
            return;
        }
        for (int i = index; i <= maxIndex; i++) { // N
            Collections.swap(copied, index, i);
            permute(rs, copied, index + 1, maxIndex); // n!
            Collections.swap(copied, index, i); // 逐级回滚
        }
    }

}
