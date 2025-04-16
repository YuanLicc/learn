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
        // 菜品数量
        int nums = in.nextInt();
        // 手速，即间隔几秒才能再次夹菜
        int speed = in.nextInt();
        in.nextLine();
        // 第一个与最后一个煮好的菜的时间点，利用这两个时间点来作为起始时间点与结束时间点
        int lastSecond = 0, firstSecond = Integer.MAX_VALUE;
        // 使用哈希表存储煮好的菜的时间点，用于判定时间点推进时，是否有菜可夹
        HashSet<Integer> completeSeconds = new HashSet<>();
        for (int i = 0; i < nums; i++) {
            String[] line = in.nextLine().split(" ");
            // 计算菜煮好的时间点
            int completeSecond = Integer.parseInt(line[0]) + Integer.parseInt(line[1]);
            completeSeconds.add(completeSecond);
            lastSecond = Math.max(lastSecond, completeSecond);
            firstSecond = Math.min(firstSecond, completeSecond);
        }
        dfs(completeSeconds, firstSecond, lastSecond, speed, 0);
        System.out.println(max);
    }

    static int max = 0;

    /**
     * 深度遍历，时间点推进
     * @param completeSeconds 菜煮好的时间点集合
     * @param second 时间点
     * @param lastSecond 截止时间点
     * @param speed 手速
     * @param num 吃到的菜数量
     */
    private static void dfs(HashSet<Integer> completeSeconds, int second, int lastSecond, int speed, int num) {
        // 当时间点推进到截止时间点时，结束时间点推进
        if(second > lastSecond) {
            // 计算最多吃到多少菜
            max = Math.max(max, num);
            return;
        }
        // 当前时间点有菜可夹
        if(completeSeconds.contains(second)) {
            // 不夹菜的情况，下一秒咱们也可以选择是否夹菜
            dfs(completeSeconds, second + 1, lastSecond, speed, num);
            // 夹菜的情况，需要保证手速间隔才能选择是否夹菜
            dfs(completeSeconds, second + speed, lastSecond, speed, num + 1);
        }
        // 当前时间点无菜可夹
        else dfs(completeSeconds, second + 1, lastSecond, speed, num); // 没得选
    }
}
