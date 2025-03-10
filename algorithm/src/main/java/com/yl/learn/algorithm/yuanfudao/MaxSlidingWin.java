package com.yl.learn.algorithm.yuanfudao;

import java.util.LinkedList;

/**
 * 给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 * 返回滑动窗口中的最大值。
 *
 * 进阶：
 * 你能在线性时间复杂度内解决此题吗？
 *
 * 来源：力扣（LeetCode） 239
 * 链接：https://leetcode-cn.com/problems/sliding-window-maximum
 */
public class MaxSlidingWin {
    
    public int[] maxSlidingWindow(int[] nums, int k) {
        if(nums == null || k <= 0) return nums;
        
        // 4 2 3 4
        int[] rs = new int[nums.length - k + 1];
        
        LinkedList<Integer> maxQueue = new LinkedList<>();
        
        for(int i = 0; i < nums.length; i++) {
            
            while(!maxQueue.isEmpty() && nums[maxQueue.peekLast()] < nums[i]) {
                maxQueue.pollLast();
            }
            
            maxQueue.addLast(i);
            
            if(maxQueue.peekFirst() <= i - k) {
                maxQueue.pollFirst();
            }
            
            if(i >= k - 1) {
                rs[i - k + 1] = nums[maxQueue.peekFirst()];
            }
            
        }
        
        return rs;
    }
    
}
