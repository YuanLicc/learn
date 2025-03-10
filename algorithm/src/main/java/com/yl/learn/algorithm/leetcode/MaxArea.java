package com.yl.learn.algorithm.leetcode;

/**
 * 给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) 。
 * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 * 返回容器可以储存的最大水量。
 * 说明：你不能倾斜容器。
 *
 * 输入：[1,8,6,2,5,4,8,3,7]
 * 输出：49
 * 解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
 */
public class MaxArea {

    // 理解题目，已知数组为高度，数组下标差值为宽度，求最大的面积（两个高度取短的作为高度计算面积）
    // 思路1，暴力解法，遍历获取高度，并遍历其后续的高度做面积，选出最大
    // 思路2，area = width * min(height)，那么对于最大宽度来说，可能是最大面积；
    // 计算后，咱们要考虑指针的移动，若左指针的高度小于右指针的高度，那么我们应该舍弃小的高度，
    // 才能确保指针包含的区间内可能存在比当前面积更大的面积
    public int maxArea(int[] height) { // 时间复杂度 N、空间复杂度 3
        if(null == height || height.length <= 1) {
            return 0;
        }
        int left = 0;
        int right = height.length - 1;
        int max = 0;
        while(left < right) {
            int area = (right - left) * Math.min(height[right], height[left]);
            max = Math.max(area, max);
            if(height[left] > height[right]) {
                right--;
            }
            else {
                left++;
            }
        }
        return max;
    }
}
