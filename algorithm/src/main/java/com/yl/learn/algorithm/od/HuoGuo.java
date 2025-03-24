package com.yl.learn.algorithm.od;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;

/**
 * 题目描述
 * 入职后，导师会请你吃饭，你选择了火锅。
 * 火锅里会在不同时间下很多菜。
 * 不同食材要煮不同的时间，才能变得刚好合适。
 * 你希望吃到最多的刚好合适的菜，但你的手速不够快，用m代表手速，每次下手捞菜后至少要过m秒才能再捞（每次只能捞一个）。
 * 那么用最合理的策略，最多能吃到多少刚好合适的菜？
 *
 * 输入描述
 * 第一行两个整数n，m，其中n代表往锅里下的菜的个数，m代表手速。（1 < n, m < 1000）
 * 接下来有n行，每行有两个数x，y代表第x秒下的菜过y秒才能变得刚好合适。（1 < x, y < 1000）
 *
 * 输出描述
 * 输出一个整数代表用最合理的策略，最多能吃到刚好合适的菜的数量。
 *
 * 示例1
 * 输入
 *
 * 2 1
 * 1 2
 * 2 1
 * 1
 * 2
 * 3
 * 输出
 *
 * 1
 */
public class HuoGuo {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int nums = in.nextInt();
        int speed = in.nextInt();
        in.nextLine();
        int maxSecond = 0;
        HashSet<Integer> completeNums = new HashSet<>();
        for (int i = 0; i < nums; i++) {
            String[] line = in.nextLine().split(" ");
            int comSecond = Integer.parseInt(line[0]) + Integer.parseInt(line[1]);
            completeNums.add(comSecond);
            maxSecond = Math.max(maxSecond, comSecond);
        }
        dfs(completeNums, 1, maxSecond, speed, 0);
        System.out.println(max);
    }

    static int max = 0;

    private static void dfs(HashSet<Integer> completeNums, int second, int maxSecond, int speed, int num) {
        if(second > maxSecond) {
            max = Math.max(max, num);
            return;
        }
        if(completeNums.contains(second)) {
            // 不选
            dfs(completeNums, second + 1, maxSecond, speed, num);
            // 选
            dfs(completeNums, second + speed, maxSecond, speed, num + 1);
        }
        else dfs(completeNums, second + 1, maxSecond, speed, num); // 没得选
    }
}
