package com.yl.learn.algorithm.od;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 题目描述
 * 为了庆祝中国共产党成立100周年，某公园将举行多场文艺表演，很多演出都是同时进行，一个人只能同时观看一场演出，且不能迟到早退，由于演出分布在不同的演出场地，所以连续观看的演出最少有15分钟的时间间隔，
 *
 * 小明是一个狂热的文艺迷，想观看尽可能多的演出， 现给出演出时间表，请帮小明计算他最多能观看几场演出。
 *
 * 输入描述
 * 第一行为一个数 N，表示演出场数
 *
 * 1 ≤ N ≤ 1000
 * 接下来 N 行，每行有被空格分割的整数，第一个整数 T 表示演出的开始时间，第二个整数 L 表示演出的持续时间
 *
 * T 和 L 的单位为分钟
 * 0 ≤ T ≤ 1440
 * 0 < L ≤ 180
 * 输出描述
 * 输出最多能观看的演出场数。
 *
 * 示例1
 * 输入
 *
 * 2
 * 720 120
 * 840 120
 * 输出
 *
 * 1
 * 说明
 *
 * 示例2
 * 输入
 *
 * 2
 * 0 60
 * 90 60
 * 3
 * 输出
 * 2
 */
public class TVShow {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int showNum = in.nextInt(); // 表演个数
        int[][] shows = new int[showNum][2];
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int i = 0; i < shows.length; i++) {
            shows[i][0] = in.nextInt();
            min = Math.min(min, shows[i][0]);
            shows[i][1] = in.nextInt() + shows[i][0] + 15; // 表演截止时间 + 15 分钟间隙
            max = Math.max(max, shows[i][1]);
        }
        // 下面只需要 point 是否交叉，如果交叉表示不能连续观看
        Arrays.sort(shows, ((o1, o2) -> o1[1] - o2[1]));
        int preEndTime = shows[0][1];
        int cnt = 1;
        for (int[] show : shows) {
            if(show[0] >= preEndTime) {
                cnt++;
                preEndTime = show[1];
            }
        }
        System.out.println(cnt);
    }
//6
//110 10
//1 5
//50 10
//20 30
//80 10
//140 10
}
