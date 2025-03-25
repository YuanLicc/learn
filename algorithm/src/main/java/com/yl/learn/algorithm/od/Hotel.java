package com.yl.learn.algorithm.od;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 题目描述
 * 放暑假了，小明决定到某旅游景点游玩，他在网上搜索到了各种价位的酒店（长度为n的数组A），他的心理价位是x元，请帮他筛选出k个最接近x元的酒店（n>=k>0）,并由低到高打印酒店的价格。
 * 输入描述
 * 第一行：n, k, x
 * 第二行：A[0] A[1] A[2]…A[n-1]
 * 输出描述
 * 从低到高打印筛选出的酒店价格
 *
 * 示例1
 * 输入
 *
 * 10 5 6
 * 1 2 3 4 5 6 7 8 9 10
 * 输出
 *
 * 4 5 6 7 8
 * 示例2
 * 输入
 *
 * 10 4 6
 * 10 9 8 7 6 5 4 3 2 1
 * 输出
 *
 * 4 5 6 7
 * 示例3
 * 输入
 *
 * 6 3 1000
 * 30 30 200 500 70 300
 * 输出
 *
 * 200 300 500
 */
public class Hotel {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int hotels = in.nextInt();
        int returnNums = in.nextInt();
        int aimPrice = in.nextInt();
        in.nextLine();
        int[] prices = new int[hotels];
        for (int i = 0; i < prices.length; i++) prices[i] = in.nextInt();
        Arrays.sort(prices);

        if(returnNums >= prices.length) {
            print(prices, 0, prices.length - 1);
            return;
        }

        int[] overSum = new int[prices.length];
        overSum[0] = prices[0];
        for (int i = 1; i < prices.length; i++) {
            overSum[i] = prices[i] + overSum[i - 1];
        }
        int left = 1, right = returnNums;
        int minRight = right;
        double avgGap = Math.abs(overSum[returnNums - 1] * 1.0 / returnNums - aimPrice);
        while (right < prices.length) {
            double newAvgGap = Math.abs((overSum[right] - overSum[left - 1]) * 1.0 / returnNums - aimPrice);
            if(newAvgGap < avgGap) {
                avgGap = newAvgGap;
                minRight = right;
            }
            left++;
            right++;
        }
        print(prices, minRight - returnNums + 1, minRight);
    }

    private static void print(int[] prices, int start, int end) {
        StringBuilder sb = new StringBuilder();
        for (int i = start; i <= end; i++) {
            sb.append(prices[i]).append(" ");
        }
        System.out.println(sb.toString());
    }

}
