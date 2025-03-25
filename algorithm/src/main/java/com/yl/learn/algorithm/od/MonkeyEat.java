package com.yl.learn.algorithm.od;

import java.util.Arrays;
import java.util.OptionalInt;
import java.util.Scanner;

/**
 * 题目描述
 * 孙悟空爱吃蟠桃，有一天趁着蟠桃园守卫不在来偷吃。已知蟠桃园有 N 棵桃树，每颗树上都有桃子，守卫将在 H 小时后回来。
 * 孙悟空可以决定他吃蟠桃的速度K（个/小时），每个小时选一颗桃树，并从树上吃掉 K 个，
 * 如果树上的桃子少于 K 个，则全部吃掉，并且这一小时剩余的时间里不再吃桃。
 * 孙悟空喜欢慢慢吃，但又想在守卫回来前吃完桃子。
 * 请返回孙悟空可以在 H 小时内吃掉所有桃子的最小速度 K（K为整数）。如果以任何速度都吃不完所有桃子，则返回0。
 * 输入描述
 * 一行输入为 N 个数字，N 表示桃树的数量，这 N 个数字表示每颗桃树上蟠桃的数量。
 * 第二行输入为一个数字，表示守卫离开的时间 H。
 * 其中数字通过空格分割，N、H为正整数，每颗树上都有蟠桃，且 0 < N < 10000，0 < H < 10000。
 * 输出描述
 * 吃掉所有蟠桃的最小速度 K，无解或输入异常时输出 0。
 *
 * 示例1
 * 输入
 * 2 3 4 5
 * 4
 * 输出
 * 5
 */
public class MonkeyEat {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[] trees = Arrays.stream(in.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int sum = 0, max = 0;
        for (int i = 0; i < trees.length; i++) {
            sum += trees[i];
            max = Math.max(max, trees[i]);
        }
        int hours = in.nextInt();
        if(hours < trees.length) {
            System.out.println(0);
        }
        else if(hours == trees.length) {
            System.out.println(max);
        }
        else {
            int left = sum / hours;
            int right = max;
            int minK = Integer.MAX_VALUE;
            while (left < right) {
                int middle = left + (right - left) / 2;
                int eatHours = eat(trees, middle);
                if(eatHours <= hours) { // 如果能够吃下，表示可以压缩右边界限，但是不能吃掉右边界
                    minK = Math.min(minK, middle);
                    right = middle;
                }
                else { // 吃不下，表示给定K太小了
                    left = middle + 1;
                }
            }
            System.out.println(minK);
        }
    }

    private static int eat(int[] trees, int k) {
        int eatHours = 0;
        for (int apple : trees) {
            eatHours += apple % k == 0 ? apple / k : apple / k + 1;
        }
        return eatHours;
    }
}
