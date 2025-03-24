package com.yl.learn.algorithm.od;

import java.util.Scanner;

/**
 * 题目描述
 * 给定参数n，从1到n会有n个整数：1,2,3,…,n,这n个数字共有n!种排列。
 * 按大小顺序升序列出所有排列的情况，并一一标记，
 * 当n=3时,所有排列如下:
 * “123” “132” “213” “231” “312” “321”
 * 给定n和k，返回第k个排列。
 * 输入描述
 * 输入两行，第一行为n，第二行为k，
 * 给定n的范围是[1,9],给定k的范围是[1,n!]。
 * 输出描述
 * 输出排在第k位置的数字。
 *
 * 示例1
 * 输入
 *
 * 3
 * 3
 * 输出
 *
 * 213
 * 说明
 * 3的排列有123,132,213…,那么第三位置就是213
 */
public class NK {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        int k = in.nextInt();
        int[] nums = new int[num];
        StringBuilder sb = new StringBuilder();
        dfs(nums, k, sb);
        System.out.println(sb.toString());
    }

    static int num = 0;

    private static void dfs(int[] nums, int aimNum, StringBuilder sb) {
        if(sb.length() == nums.length) {
            num++;
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if(nums[i] != 1) {
                sb.append(i + 1);
                nums[i] = 1;
                dfs(nums, aimNum, sb);
                if(num == aimNum) return;
                nums[i] = 0;
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }
}
