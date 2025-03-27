package com.yl.learn.algorithm.od;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 题目描述
 * 某云短信厂商，为庆祝国庆，推出充值优惠活动。
 * 现在给出客户预算，和优惠售价序列，求最多可获得的短信总条数。
 *
 * 输入描述
 * 第一行客户预算M，其中 0 ≤ M ≤ 10^6
 * 第二行给出售价表， P1, P2, … Pn , 其中 1 ≤ n ≤ 100 ,
 * Pi为充值 i 元获得的短信条数。1 ≤ Pi ≤ 1000 , 1 ≤ n ≤ 100
 *
 * 输出描述
 * 最多获得的短信条数
 *
 * 示例1
 * 输入
 *
 * 6
 * 10 20 30 40 60
 * 1
 * 2
 * 输出
 *
 * 70
 * 1
 * 说明
 *
 * 分两次充值最优， 1 元、 5 元各充一次。总条数 10 + 60 = 70
 *
 * 示例2
 * 输入
 *
 * 15
 * 10 20 30 40 60 60 70 80 90 150
 * 输出
 * 210
 * 说明
 *
 * 分两次充值最优， 10 元 5 元各充一次，总条数 150 + 60 = 210
 */
public class YunSale {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int money = Integer.parseInt(in.nextLine());
        int[] sales = Arrays.stream(in.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int[][] dp = new int[sales.length + 1][money + 1];

        for (int i = 1; i < dp.length; i++) {
            int mm = i, num = sales[i - 1];
            for (int j = 1; j < dp[i].length; j++) {
                if(j >= i) { // 能够购买当前短信包
                    dp[i][j] = Math.max(Math.max(dp[i - 1][j], dp[i][j - mm] + num), dp[i - 1][j - mm] + num);
                }
                else { // 不足以购买
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        System.out.println(dp[sales.length][money]);
    }

}
