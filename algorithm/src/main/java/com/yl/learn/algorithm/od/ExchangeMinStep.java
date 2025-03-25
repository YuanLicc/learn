package com.yl.learn.algorithm.od;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 题目描述
 * 给出数字K,请输出所有结果小于K的整数组合到一起的最少交换次数。
 * 组合一起是指满足条件的数字相邻，不要求相邻后在数组中的位置。
 *
 * 数据范围：
 * 100 <= K <= 100
 * 100 <= 数组中数值 <= 100
 *
 * 输入描述
 * 第一行输入数组：1 3 1 4 0
 *
 * 第二行输入K数值：2
 * 输出描述
 * 第一行输出最少交换次数：1
 *
 * 示例1
 * 输入
 *
 * 1 3 1 4 0
 * 2
 * 输出
 * 1 => 3 与 0 交换 或则将 1 与 4 交换，则小于2的数字 1 0 1，放在了一起
 */
public class ExchangeMinStep {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String[] strings = in.nextLine().split(" ");
        int aim = in.nextInt();
        int[] nums = Arrays.stream(strings).mapToInt(Integer::parseInt).toArray();
        int cnt = Arrays.stream(nums).map(num -> num < aim ? 1 : 0).sum();

        // 初始化窗口
        int count = 0, noCount = 0;
        for (int i = 0; i < cnt; i++) {
            if(nums[i] < aim) count++;
            else noCount++;
        }
        int min = noCount;
        int right = cnt, left = 1;
        while (right < nums.length) {
            if(nums[right] < aim) count++;
            else noCount++;
            if(nums[left - 1] < aim) count--;
            else noCount--;
            min = Math.min(noCount, min);
            right++;
        }
        System.out.println(min);
    }

}
