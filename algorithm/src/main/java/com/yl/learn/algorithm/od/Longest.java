package com.yl.learn.algorithm.od;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 题目描述
 * 有N个正整数组成的一个序列。给定整数sum，求长度最长的连续子序列，使他们的和等于sum，返回此子序列的长度，
 * 如果没有满足要求的序列，返回-1。
 * 输入描述
 * 第一行输入是：N个正整数组成的一个序列
 * 第二行输入是：给定整数sum
 * 输出描述
 * 最长的连续子序列的长度
 *
 * 备注
 * 输入序列仅由数字和英文逗号构成，数字之间采用英文逗号分隔
 * 序列长度：1 <= N <= 200
 * 输入序列不考虑异常情况
 * 示例1
 * 输入
 *
 * 1,2,3,4,2
 * 6
 * 输出
 *
 * 3
 * 说明
 *
 * 1,2,3和4,2两个序列均能满足要求，所以最长的连续序列为1,2,3，因此结果为3。
 *
 * 示例2
 * 输入
 *
 * 1,2,3,4,2
 * 20
 * 输出
 *
 * -1
 * 说明
 *
 * 没有满足要求的子序列，返回-1
 */
public class Longest {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[] nums = Arrays.stream(in.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        int aimSum = in.nextInt();

        int left = 0, right = 0, sum = 0, maxNums = -1;
        while (right < nums.length) {
            sum += nums[right];
            if(sum ==  aimSum) {
                maxNums = Math.max(maxNums, right - left + 1);
                right++;
                sum -= nums[left];
                left++;
            }
            else if(sum < aimSum) {
                right++;
            }
            else {
                sum -= nums[left];
                left++;
                if(left > right) right++;
            }
        }
        System.out.println(maxNums);
    }
}
