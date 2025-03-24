package com.yl.learn.algorithm.od;

import java.util.HashSet;
import java.util.Scanner;

/**
 * 题目描述
 * 服务器连接方式包括直接相连，间接连接。
 *
 * A和B直接连接，B和C直接连接，则A和C间接连接。
 *
 * 直接连接和间接连接都可以发送广播。
 *
 * 给出一个N*N数组，代表N个服务器，
 *
 * matrix[i][j] == 1，
 * 则代表i和j直接连接；不等于 1 时，代表i和j不直接连接。
 *
 * matrix[i][i] == 1，
 * 即自己和自己直接连接。
 * matrix[i][j] == matrix[j][i]。
 *
 * 计算初始需要给几台服务器广播， 才可以使每个服务器都收到广播。
 *
 * 输入描述
 * 输入为N行，每行有N个数字，为0或1，由空格分隔，
 *
 * 构成N*N的数组，N的范围为 1 <= N <= 40
 *
 * 输出描述
 * 输出一个数字，为需要广播的服务器的数量
 *
 * 示例1
 * 输入
 *
 * 1 0 0
 * 0 1 0
 * 0 0 1
 * 输出
 *
 * 3
 * 说明
 *
 * 3 台服务器互不连接，所以需要分别广播这 3 台服务器
 *
 * 示例2
 * 输入
 *
 * 1 1
 * 1 1
 * 输出
 * 1
 * 说明
 * 2 台服务器相互连接，所以只需要广播其中一台服务器
 */
public class ServerLink {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[] line = toIntArray(in.nextLine());
        int[][] dp = new int[line.length][line.length];
        dp[0] = line;
        for (int i = 1; i < line.length; i++) {
            line = toIntArray(in.nextLine());
            dp[i] = line;
        }
        HashSet<Integer> set = new HashSet<>();
        int rs = 0;
        for (int i = 0; i < dp.length; i++) {
            if(!set.contains(i)) {
                dfs(dp, set, i);
                rs++;
            }
        }
        System.out.println(rs);
    }

    private static void dfs(int[][] dp, HashSet<Integer> set, int index) {
        set.add(index);
        for (int i = index + 1; i < dp[index].length; i++) {
            if(dp[index][i] == 1 && !set.contains(dp[index][i])) {
                dfs(dp, set, dp[index][i]);
            }
        }
    }

    private static int[] toIntArray(String str) {
        String[] strs = str.split(" ");
        int[] rs = new int[strs.length];
        for (int i = 0; i < rs.length; i++) rs[i] = Integer.parseInt(strs[i]);
        return rs;
    }
}
