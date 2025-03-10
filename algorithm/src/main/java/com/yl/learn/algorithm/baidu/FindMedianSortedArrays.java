package com.yl.learn.algorithm.baidu;

public class FindMedianSortedArrays {
    
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        
        // 确保小数组为 nums1
        exchange(nums1, nums2);
        int[] minArr = nums1;
        
        int[] maxArr = nums2;
        
        int minArrLen = minArr.length;
        int maxArrLen = maxArr.length;
        
        int start = 0;
        int end = minArrLen;
        int middle = (minArrLen + maxArrLen + 1) / 2;
        
        while(start <= end) {
            int minArrIndex = (start + end) / 2;
            int maxArrIndex = middle - minArrIndex;
            
            if(minArrIndex < end && minArr[minArrIndex] < maxArr[maxArrIndex - 1]) {
                start++;
            }
            else if(maxArrIndex > start && minArr[minArrIndex - 1] > maxArr[maxArrIndex]) {
                end--;
            }
            else {
            
            }
            
        }
        
        return 1d;
    }
    
    private void exchange(int[] nums1, int[] nums2) {
        if(nums1.length > nums2.length) {
            int[] tmp = nums1;
            nums1 = nums2;
            nums2 = tmp;
        }
    }
    
    public static double findMedianSortedArray(int[] arrayA, int[] arrayB) {
        int m = arrayA.length;
        int n = arrayB.length;
        //如果数组A的长度大于等于数组B，则交换数组
        if (m > n) {
            int[] temp = arrayA;
            arrayA = arrayB;
            arrayB = temp;
            int tmp = m;
            m = n;
            n = tmp;
        }
        int start = 0;
        int end = m;
        int mid = (m + n + 1) / 2;
        while (start <= end) {
            int i = (start + end) / 2;
            int j = mid - i;
            if (i < end && arrayB[j - 1] > arrayA[i]) {
                //i偏小了，需要右移
                start = i + 1;
            } else if (i > start && arrayA[i - 1] > arrayB[j]) {
                //i偏大了，需要左移
                end = i - 1;
            } else {
                //i刚好合适，或i已达到数组边界
                int maxLeft;
                if (i == 0) {
                    maxLeft = arrayB[j - 1];
                } else if (j == 0) {
                    maxLeft = arrayA[i - 1];
                } else {
                    maxLeft = Math.max(arrayA[i - 1], arrayB[j - 1]);
                }
                if ((m + n) % 2 == 1) {
                    //如果大数组的长度是奇数，中位数就是左半部分的最大值
                    return maxLeft;
                }
                int minRight;
                if (i == m) {
                    minRight = arrayB[j];
                } else if (j == n) {
                    minRight = arrayA[i];
                } else {
                    minRight = Math.min(arrayB[j], arrayA[i]);
                }
                //如果大数组的长度是偶数，取左侧最大值和右侧最小值的平均
                return (maxLeft + minRight) / 2.0;
            }
        }
        return 0.0;
        
    }
    public static void main(String[] args) {
        int[] arrayB = new int[]{3, 5, 6, 7, 8, 12, 20};
        int[] arrayA = new int[]{1, 10, 17, 18};
        System.out.println(findMedianSortedArray(arrayA, arrayB));
    }
    
}
