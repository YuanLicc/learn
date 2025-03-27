package com.yl.learn.algorithm.od;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 题目描述
 * 流浪地球计划在赤道上均匀部署了N个转向发动机，按位置顺序编号为0~N-1。
 * 初始状态下所有的发动机都是未启动状态;
 * 发动机启动的方式分为”手动启动"和”关联启动"两种方式;
 * 如果在时刻1一个发动机被启动，下一个时刻2与之相邻的两个发动机就会被”关联启动”;
 * 如果准备启动某个发动机时，它已经被启动了，则什么都不用做;
 * 发动机0与发动机N-1是相邻的;
 * 地球联合政府准备挑选某些发动机在某些时刻进行“手动启动”。当然最终所有的发动机都会被启动。
 * 哪些发动机最晚被启动呢?
 * 输入描述
 * 第一行两个数字N和E，中间有空格
 * N代表部署发动机的总个数，E代表计划手动启动的发动机总个数
 * 1<N<=1000,1<=E<=1000,E<=N
 * 接下来共E行，每行都是两个数字T和P，中间有空格
 * T代表发动机的手动启动时刻，P代表此发动机的位置编号。
 * 0<=T<=N.0<=P<N
 * 输出描述
 * 第一行一个数字N，以回车结束
 * N代表最后被启动的发动机个数
 * 第二行N个数字，中间有空格，以回车结束
 * 每个数字代表发动机的位置编号，从小到大排序
 * 示例1
 * 输入
 *
 * 8 2
 * 0 2
 * 0 6
 * 输出
 *
 * 2
 * 0 4
 * 说明
 *
 * 8个发动机，时刻0启动2和6号发动机
 */
public class LLEarth {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int num = in.nextInt(), startNum = in.nextInt();
        int[] merch = new int[num];
        Arrays.fill(merch, -1);
        for (int i = 0; i < startNum; i++) {
            int time = in.nextInt();
            int number = in.nextInt();
            merch[number] = time;
        }

        int[] copy = new int[num];
        Arrays.fill(copy, Integer.MAX_VALUE);
        for (int i = 0; i < merch.length; i++) {
            if(merch[i] != -1) {
                dfs(copy, i, merch[i]);
            }
        }
        int max = 0;
        for (int i = 0; i < copy.length; i++) {
            max = Math.max(max, copy[i]);
        }
        int count = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < copy.length; i++) {
            if(copy[i] == max) {
                count++;
                sb.append(i).append(" ");
            }
        }
        System.out.println(count + "\n" + sb.substring(0, sb.length() - 1));
    }

    private static void dfs(int[] merch, int start, int time) {
        merch[start] = Math.min(merch[start], time);
        int left = index(merch, start - 1);
        int right = index(merch, start + 1);
        while (left != right) {
            time++;
            merch[left] = Math.min(merch[left], time);
            merch[right] = Math.min(merch[right], time);
            left = index(merch, left - 1);
            right = index(merch, right + 1);
        }
        merch[left] = Math.min(merch[left], time + 1);
    }

    private static int index(int[] merch, int index) {
        if(index == -1) return merch.length - 1;
        if(index == merch.length) return 0;
        return index;
    }
}
