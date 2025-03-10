package com.yl.learn.algorithm.yuanfudao;

import com.yl.learn.util.util.PrintUtil;
import junit.framework.TestCase;

/**
 * 215 在未排序的数组中找到第 k 个最大的元素。
 * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 * 输入: [3,2,1,5,6,4] 和 k = 2
 * 输出: 5
 */
public class KLargestNum extends TestCase {
    
    public int findKthLargest(int[] nums, int k) {
        
        
        return quickSortFind(nums, k);
    }
    
    private int quickSortFind(int[] nums, int k) {
        
        if(k > nums.length || k <= 0) {
            return -1;
        }
        
        int startIndex = 0;
        int endIndex = nums.length - 1;
        
        while(true) {
            int index = findIndex(nums, startIndex, endIndex);
            if(index + 1 == k) {
                return nums[k - 1];
            }
            else if(index + 1 > k) {
                endIndex = index - 1;
            }
            else {
                startIndex = index + 1;
            }
        }
    }
    
    private int findIndex(int[] nums, int startIndex, int endIndex) {
        assert startIndex <= endIndex;
        
        if(startIndex == endIndex) return startIndex;
        
        int temp = nums[startIndex];
        
        while(startIndex != endIndex) {
            
            while(startIndex != endIndex) {
                if(nums[endIndex] <= temp) {
                    nums[startIndex++] = nums[endIndex];
                    break;
                }
                endIndex--;
            }
            
            while (startIndex != endIndex) {
                if(nums[startIndex] > temp) {
                    nums[endIndex--] = nums[startIndex];
                    break;
                }
                startIndex++;
            }
        }
        nums[startIndex] = temp;
        
        return startIndex;
    }
    
    public void testQuick() {
        int nums[] = new int[]{1, 20, 33, 25, 18};
        PrintUtil.template("Find Kth(3) Largest num in array 1, 20, 33, 25, 18", () -> {
            PrintUtil.println(findKthLargest(nums, 3));
            PrintUtil.printlnArray(nums);
        });
    
        int nums1[] = new int[]{1, 20, 33, 25, 18, 100, -1, -10, 28};
        PrintUtil.template("Find Kth(8) Largest num in array 1, 20, 33, 25, 18, 100, -1, -10, 28", () -> {
            PrintUtil.println(findKthLargest(nums1, 8));
            PrintUtil.printlnArray(nums1);
        });
    }
}
