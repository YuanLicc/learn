package com.yl.learn.algorithm.leetcode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * 输入：height = [4,2,0,3,2,5]
 * 输出：9
 */
public class RainWater {
    // 理解题目：数组中的数字表示柱子，数组中的下标差表示柱子之间的间隔，柱子之间组成的空间可以存储雨水，求雨水的量
    // 思路：咱们要下判断当前点位能够存储多少水，需要知道左边的最大值以及右边的最大值：
    // 如：2, 0, 1, 7 中，0点位就能存储两个单位的水，1点位能存储1个单位的水
    public static int trap(int[] height) { // 空间复杂度 N + N，// 时间复杂度 N + N + N
        int max = 0;
        int[] leftMaxs = new int[height.length]; // 空间复杂度 N
        for(int i = 0; i < height.length; i++) { // 时间复杂度 N
            max = Math.max(max, height[i]);
            leftMaxs[i] = max;
        }
        max = 0;
        int[] rightMaxs = new int[height.length]; // 空间复杂度 N
        for(int i = height.length - 1; i >= 0; i--) { // 时间复杂度 N
            max = Math.max(max, height[i]);
            rightMaxs[i] = max;
        }
        int area = 0;
        for(int i = 0; i < height.length; i++) { // 时间复杂度 N
            area += Math.min(leftMaxs[i], rightMaxs[i]) - height[i];
        }
        return area;
    }

    // 考虑栈来实现，当当前点位比栈顶小时，入栈
    // 当当前点位比栈顶大时，表示出现了凹槽，那么需要出栈，并判断栈内的栈顶与当前点位组成的凹槽时多大
    public static int trapStack(int[] height) { // 时间复杂度 N ~ 2N，空间复杂度 N
        Deque<Integer> minStack = new LinkedList<>(); // N
        int area = 0;

        for(int i = 0; i < height.length; i++) { // 时间复杂度 N
            // 当前点位大于前面的点位时，出栈计算；可以想像栈内存储的是凹槽的左边斜坡，只要当前点位大于的斜坡都要取出来
            while(!minStack.isEmpty() && height[i] > height[minStack.peek()]) { // 假设是一个递减的数组，那么时间复杂度为0，假设是一个递增的数组，那么为 N
                int aoDianIndex = minStack.pop();
                if(minStack.isEmpty()) {
                    break;
                }
                // 计算面积
                int leftIndex = minStack.peek();
                int width = i - leftIndex - 1;
                int high = Math.min(height[leftIndex], height[i]) - height[aoDianIndex];
                area += width * high;
            }
            minStack.push(i);
        }
        return area;
    }
}
