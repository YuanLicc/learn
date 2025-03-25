package com.yl.learn.algorithm.od;

import java.util.Scanner;

/**
 * 题目描述
 * 给定一个正整数数组，设为nums，最大为100个成员，求从第一个成员开始，正好走到数组最后一个成员，所使用的最少步骤数。
 *
 * 要求：
 * 第一步必须从第一元素开始，且1<=第一步的步长<len/2;（len为数组的长度，需要自行解析）。
 * 从第二步开始，只能以所在成员的数字走相应的步数，不能多也不能少, 如果目标不可达返回**-1**，只输出最少的步骤数量。
 * 只能向数组的尾部走，不能往回走。
 * 输入描述
 * 由正整数组成的数组，以空格分隔，数组长度小于100，请自行解析数据数量。
 *
 * 输出描述
 * 正整数，表示最少的步数，如果不存在输出**-1**
 *
 * 示例1
 * 输入
 * 7 5 9 4 2 6 8 3 5 4 3 9
 * 输出
 *
 * 2
 * 说明
 * 第一步： 第一个可选步长选择2，从第一个成员7开始走2步，到达9；
 * 第二步： 从9开始，经过自身数字9对应的9个成员到最后。
 */
public class ArrayThrow {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String[] strings = in.nextLine().split(" ");
        int[] nums = new int[strings.length];
        for (int i = 0; i < nums.length; i++) nums[i] = Integer.parseInt(strings[i]);

        int firstStep = nums.length % 2 == 0 ? nums.length / 2 - 1 : nums.length / 2;
        nums[nums.length - 1] = 1;
        // 从后往前遍历，避免重复计算；也可以改为暴力的dfs来做
        for (int i = nums.length - 2; i >= 0; i--) {
            int step = i == 0 ? firstStep : nums[i];
            if(step == 0) {
                nums[i] = 0;
                continue;
            }
            if(i != 0) { // 不为零时，表示可以走当前元素指定的步数
                if(i + step < nums.length && nums[i + step] >= 1) {
                    nums[i] = nums[i + step] + 1;
                }
                else nums[i] = 0;
            }
            else { // 为零时，表示可以走 1 ~ length / 2 步，都尝试一遍选出最小即可
                int plusStep = 1, index = i + plusStep;
                int min = Integer.MAX_VALUE;
                while (index < nums.length && plusStep <= step) {
                    if(nums[index] != 0) {
                        min = Math.min(min, nums[index] + 1);
                    }
                    plusStep++;
                    index = i + plusStep;
                }
                nums[i] = min == Integer.MAX_VALUE ? 0 : min;
            }
        }
        System.out.println(nums[0] - 1);
    }
}
