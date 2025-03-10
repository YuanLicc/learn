package com.yl.learn.algorithm.yuanfudao;

import com.yl.learn.util.util.PrintUtil;
import junit.framework.TestCase;

/**
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 * 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
 * 你可以假设数组中不存在重复的元素。
 * 你的算法时间复杂度必须是 O(log n) 级别。
 *
 * 输入: nums = [4,5,6,7,0,1,2], target = 0
 * 输出: 4
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/search-in-rotated-sorted-array
 */
public class SearchReverseSortedArray extends TestCase {
    
    public int search(int[] nums, int target) {
    
        if(nums == null || nums.length == 0) return -1;
        
        if(nums.length == 1) {
            return nums[0] == target ? 0 : -1;
        }
        
        int start = 0;
        int end = nums.length - 1;
        int middle = (nums.length - 1) / 2;
        
        while (start != end && start >= 0 && end >= 0) {
            
            if(nums[middle] == target) return middle;
            if(nums[start] == target) return start;
            if(nums[end] == target) return end;
            
            // 左边连续
            if(nums[middle] > nums[start]) {
                if(nums[middle] > target && nums[start] <= target) {
                    end = middle - 1;
                }
                else {
                    start = middle + 1;
                    
                    if(start > end) {
                        return -1;
                    }
                }
            }
            // 右边连续且
            else if(nums[middle] < nums[end]) {
                if(nums[middle] < target && nums[end] >= target) {
                    start = middle + 1;
                }
                else {
                    end = middle - 1;
                    
                    if(end < start) {
                        return -1;
                    }
                }
            }
            else {
                return -1;
            }
            
            middle = (start + end) / 2;
        }
        
        return -1;
    }
    
    public void test() {
        int[] arr = {4,5,6,7,0,1,2};
        // PrintUtil.template("find 0 in arr {4,5,6,7,0,1,2}", () -> PrintUtil.println(search(arr, 0)));
        PrintUtil.template("find 0 in arr {4,5,6,7,0,1,2}", () -> PrintUtil.println(search(arr, 0)));
    
        int[] arr1 = new int[]{3,7,9,1,2};
        PrintUtil.template("find 0 in arr {3,7,9,1,2}", () -> PrintUtil.println(search(arr1, 0)));
    }
    
}
