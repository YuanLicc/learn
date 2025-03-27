package com.yl.learn.algorithm.od;

import java.util.Scanner;

/**
 * 题目描述
 * 小明从糖果盒中随意抓一把糖果，每次小明会取出一半的糖果分给同学们。
 *
 * 当糖果不能平均分配时，小明可以选择从糖果盒中（假设盒中糖果足够）取出一个糖果或放回一个糖果。
 *
 * 小明最少需要多少次（取出、放回和平均分配均记一次），能将手中糖果分至只剩一颗。
 *
 * 输入描述
 * 抓取的糖果数（<10000000000）：15
 *
 * 输出描述
 * 最少分至一颗糖果的次数：5
 *
 * 示例1
 * 输入
 *
 * 15
 * 输出
 *
 * 5
 * 说明
 *
 * 15+1=16;16/2=8;8/2=4;4/2=2;2/2=1;
 *
 * 示例2
 * 输入
 *
 * 6
 * 输出
 *
 * 3
 */
public class DivideSugar {

    // 基于贪心
    // 如果遇到奇数（除3），如15,15 + 1 / 2 = 8; 15 - 1 / 2 = 7; 那么我们选择+1策略
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();

        int count = 0;
        while (num != 1) {
            if(num == 3) { // 特殊处理
                count += 2;
                break;
            }
            if(num % 2 == 0) {
                num /= 2;
            }
            else {
                if((num + 1) / 2 % 2 == 0) {
                    num++;
                }
                else {
                    num--;
                }
            }
            count++;
        }
        System.out.println(count);
    }


}
