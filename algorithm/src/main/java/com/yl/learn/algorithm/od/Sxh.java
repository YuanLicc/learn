package com.yl.learn.algorithm.od;

import java.util.Scanner;

/**
 * 题目描述
 * 所谓水仙花数，是指一个n位的正整数，其各位数字的n次方和等于该数本身。
 * 例如153是水仙花数，153是一个3位数，并且153 = 1^3 + 5^3 + 3^3。
 * 输入描述
 * 第一行输入一个整数n，表示一个n位的正整数。n在3到7之间，包含3和7。
 * 第二行输入一个正整数m，表示需要返回第m个水仙花数。
 *
 * 输出描述
 * 返回长度是n的第m个水仙花数。个数从0开始编号。
 *
 * 若m大于水仙花数的个数，返回最后一个水仙花数和m的乘积。
 *
 * 若输入不合法，返回-1。
 *
 * 示例1
 * 输入
 *
 * 3
 * 0
 * 输出
 *
 * 153
 * 说明
 *
 * 153是第一个水仙花数
 *
 * 示例2
 * 输入
 *
 * 9
 * 输出
 *
 * -1
 * 说明
 *
 * 9超出范围
 */
public class Sxh {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int index = in.nextInt();
        if(n < 3 || n > 7) {
            System.out.println(-1);
            return;
        }

        int start = (int) Math.pow(10, n - 1), end = (int) Math.pow(10, n);

        int cnt = -1;
        for (int i = start; i < end; i++) {
            int sum = 0, num = i;
            while (num > 0) {
                sum += Math.pow(num % 10, n);
                num /= 10;
            }
            if(sum == i) {
                cnt++;
            }
            if(cnt == index) {
                System.out.println(i);
                return;
            }
        }
        System.out.println(-1);
    }
}
