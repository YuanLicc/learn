package com.yl.learn.algorithm.od;

import java.util.Scanner;

/**
 * 题目描述
 * 服务之间交换的接口成功率作为服务调用关键质量特性，某个时间段内的接口失败率使用一个数组表示，
 * 数组中每个元素都是单位时间内失败率数值，数组中的数值为0~100的整数，
 * 给定一个数值(minAverageLost)表示某个时间段内平均失败率容忍值，即平均失败率小于等于minAverageLost，
 * 找出数组中最长时间段，如果未找到则直接返回NULL。
 *
 * 输入描述
 * 输入有两行内容，第一行为{minAverageLost}，第二行为{数组}，数组元素通过空格(” “)分隔，
 * minAverageLost及数组中元素取值范围为0~100的整数，数组元素的个数不会超过100个。
 *
 * 输出描述
 * 找出平均值小于等于minAverageLost的最长时间段，输出数组下标对，格式{beginIndex}-{endIndx}(下标从0开始)，
 * 如果同时存在多个最长时间段，则输出多个下标对且下标对之间使用空格(” “)拼接，多个下标对按下标从小到大排序。
 *
 * 用例
 * 输入
 * 1
 * 0 1 2 3 4
 * 输出
 * 0-2
 * 说明
 * 输入解释：minAverageLost=1，数组[0, 1, 2, 3, 4]
 * 前3个元素的平均值为1，因此数组第一个至第三个数组下标，即0-2
 */
public class InterfaceInvoke {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int avg = in.nextInt();
        in.nextLine();
        int[] nums = toInt(in.nextLine());
        int left = 0, right = 0, sum = 0, max = 0, rsL = 0, rsR = 0;
        StringBuilder sb = new StringBuilder();
        for (; right < nums.length; right++) {
            sum += nums[right];
            while (sum * 1.0 / (right - left + 1) > avg) {
                sum -= nums[left];
                left++;
            }
            if(right - left + 1 > max) {
                max = right - left + 1;
                sb = new StringBuilder();
                sb.append(left).append("-").append(right).append(" ");
            }
            else if(right - left + 1 == max) {
                sb.append(left).append("-").append(right).append(" ");
            }
        }
        System.out.println(sb);
    }

    private static int[] toInt(String s) {
        String[] ss = s.split(" ");
        int[] rs = new int[ss.length];
        for (int i = 0; i < ss.length; i++) rs[i] = Integer.parseInt(ss[i]);
        return rs;
    }
}
