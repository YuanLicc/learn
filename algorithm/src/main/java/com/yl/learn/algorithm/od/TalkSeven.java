package com.yl.learn.algorithm.od;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 题目描述
 * 喊7是一个传统的聚会游戏，N个人围成一圈，按顺时针从1到N编号。
 * 编号为1的人从1开始喊数，下一个人喊的数字为上一个人的数字加1，
 * 但是当将要喊出来的数字是7的倍数或者数字本身含有7的话，不能把这个数字直接喊出来，而是要喊”过”。
 * 假定玩这个游戏的N个人都没有失误地在正确的时机喊了”过”，当喊到数字K时，可以统计每个人喊”过”的次数。
 * 现给定一个长度为N的数组，存储了打乱顺序的每个人喊”过”的次数，
 * 请把它还原成正确的顺序，即数组的第i个元素存储编号i的人喊”过”的次数。
 *
 * 输入描述
 * 输入为一行，为空格分隔的喊”过”的次数，注意K并不提供，K不超过200，而数字的个数即为N。
 *
 * 输出描述
 * 输出为一行，为顺序正确的喊”过”的次数，也由空格分隔。
 *
 * 示例1
 * 输入
 *
 * 0 1 0
 * 输出
 *
 * 1 0 0
 * 说明
 *
 * 一共只有一次喊”过”，那只会发生在需要喊7时，按顺序，编号为1的人会遇到7，故输出1 0 0。
 */
public class TalkSeven {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ArrayList<Integer> list = new ArrayList<>();
        int sum = 0;
        String[] strs = in.nextLine().split(" ");
        int[] nums = new int[strs.length];
        for (int i = 0; i < nums.length; i++) {
            int num = Integer.parseInt(strs[i]);
            sum += num;
            nums[i] = 0;
        }
        dfs(nums, 7, 0, sum);
        print(nums);
    }

    private static void print(int[] nums) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nums.length; i++) {
            sb.append(i == nums.length - 1 ? nums[i] : nums[i] + " ");
        }
        System.out.println(sb.toString());
    }

    // 7 14 17 21 27 28
    private static void dfs(int[] nums, int aim, int num, int sum) {
        if(num == sum) return;
        nums[aim % nums.length - 1] += 1;
        if(aim % 7 == 0) {
            int n1 = aim / 10 * 10 + 7;
            int n2 = aim + 7;
            if(n1 > aim && n1 < n2) dfs(nums, n1, num + 1, sum);
            else dfs(nums, n2, num + 1, sum);
        }
        else {
            dfs(nums, (aim / 7 + 1) * 7, num + 1, sum);
        }
    }
}
