package com.yl.learn.algorithm.od;

import java.util.Scanner;

/**
 * 题目描述
 * 一个图像有n个像素点，存储在一个长度为n的数组img里，每个像素点的取值范围[0,255]的正整数。
 *
 * 请你给图像每个像素点值加上一个整数k（可以是负数），得到新图newImg，使得新图newImg的所有像素平均值最接近中位值128。
 *
 * 请输出这个整数k。
 *
 * 输入描述
 * n个整数，中间用空格分开
 *
 * 备注
 * • 1 <= n <= 100
 * • 如有多个整数k都满足，输出小的那个k；
 * • 新图的像素值会自动截取到[0,255]范围。当新像素值<0，其值会更改为0；当新像素值>255，其值会更改为255；
 *
 * 例如newImg=”-1 -2 256″,会自动更改为”0 0 255″
 *
 * 输出描述
 * 一个整数k
 *
 * 示例1
 * 输入
 *
 * 129 130 129 130
 * 输出
 *
 * -2
 * 说明
 *
 * -1的均值128.5，-2的均值为127.5，输出较小的数-2
 */
public class NPoint {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String[] numStr = in.nextLine().split(" ");
        int[] nums = new int[numStr.length];
        for (int i = 0; i < numStr.length; i++) {
            int num = Integer.parseInt(numStr[i]);
            nums[i] = num;
        }
        double avgGap = 255;
        double avg = 255;
        int k = 255;
        for (int i = -127; i <= 128; i++) {
            double sum = 0;
            for (int j = 0; j < nums.length; j++) {
                int newVal = nums[j] + i;
                newVal = Math.max(Math.min(newVal, 255), 0);
                sum += newVal;
            }
            double newAvg = sum / nums.length;
            double newAvgGap = Math.abs(newAvg - 128);
            if(newAvgGap < avgGap) {
                avg = newAvg;
                avgGap = newAvgGap;
                k = i;
            }
        }
        System.out.println(k);
    }
}
