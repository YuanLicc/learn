package com.yl.learn.algorithm.od;

import java.util.Scanner;

/**
 * 题目描述
 * A、B两个人把苹果分为两堆，A希望按照他的计算规则等分苹果，他的计算规则是按照二进制加法计算，并且不计算进位
 * 12+5=9（1100 + 0101 = 9），B的计算规则是十进制加法，包括正常进位，B希望在满足A的情况下获取苹果重量最多。
 * 输入苹果的数量和每个苹果重量，输出满足A的情况下B获取的苹果总重量。
 * 如果无法满足A的要求，输出-1。
 *
 * 数据范围
 * 1 <= 总苹果数量 <= 20000
 * 1 <= 每个苹果重量 <= 10000
 * 输入描述
 * 输入第一行是苹果数量：3
 * 输入第二行是每个苹果重量：3 5 6
 * 输出描述
 * 输出第一行是B获取的苹果总重量：11
 * 示例1
 * 输入
 * 3
 * 3 5 6
 * 输出
 * 11
 * 说明
 * 示例2
 * 输入
 *
 * 8
 * 7258 6579 2602 6716 3050 3564 5396 1773
 * 输出
 * 35165
 */
public class DivideApple {

    // 想要将一组数字中分为两部分异或 = 总数，那么 数字逐一异或得到的结果必须为零
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        int sum = 0, yh = 0, min = Integer.MAX_VALUE;
        for (int i = 0; i < num; i++) {
            int apple = in.nextInt();
            sum += apple;
            yh = yh ^ apple;
            min = Math.min(min, apple);
        }
        if(yh == 0) {
            System.out.println(sum - min);
        }
        else {
            System.out.println(-1);
        }
    }
}
