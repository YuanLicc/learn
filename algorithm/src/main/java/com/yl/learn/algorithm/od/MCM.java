package com.yl.learn.algorithm.od;

import java.util.Scanner;

/**
 * 题目描述
 * 有一种特殊的加密算法，明文为一段数字串，经过密码本查找转换，生成另一段密文数字串。
 *
 * 规则如下：
 *
 * 明文为一段数字串由 0~9 组成
 *
 * 密码本为数字 0~9 组成的二维数组
 *
 * 需要按明文串的数字顺序在密码本里找到同样的数字串，密码本里的数字串是由相邻的单元格数字组成，
 * 上下和左右是相邻的，注意：对角线不相邻，同一个单元格的数字不能重复使用。
 * 每一位明文对应密文即为密码本中找到的单元格所在的行和列序号（序号从0开始）组成的两个数宇。
 * 如明文第 i 位 Data[i] 对应密码本单元格为 Book[x][y]，则明文第 i 位对应的密文为X Y，X和Y之间用空格隔开。
 * 如果有多条密文，返回字符序最小的密文。
 * 如果密码本无法匹配，返回"error"。
 *
 * 请你设计这个加密程序。
 *
 * 示例1：
 *
 * 密码本：
 *
 * 0 0 2
 *
 * 1 3 4
 *
 * 6 6 4
 *
 * 明文：“3”，密文：“1 1”
 *
 * 示例2：
 *
 * 密码本：
 *
 * 0 0 2
 *
 * 1 3 4
 *
 * 6 6 4
 *
 * 明文：“0 3”，密文：“0 1 1 1”
 *
 * 示例3：
 *
 * 密码本：
 *
 * 0 0 2 4
 *
 * 1 3 4 6
 *
 * 3 4 1 5
 *
 * 6 6 6 5
 *
 * 明文：“0 0 2 4”，密文：“0 0 0 1 0 2 0 3” 和 “0 0 0 1 0 2 1 2”，返回字典序最小的"0 0 0 1 0 2 0 3"
 *
 * 明文：“8 2 2 3”，密文：“error”，密码本中无法匹配
 *
 * 输入描述
 * 第一行输入 1 个正整数 N，代表明文的长度（1 ≤ N ≤ 200）
 *
 * 第二行输入 N 个明文组成的序列 Data[i]（0 ≤ Data[i] ≤ 9）
 *
 * 第三行输入 1 个正整数 M，代表密文的长度
 *
 * 接下来 M 行，每行 M 个数，代表密文矩阵
 *
 * 输出描述
 * 输出字典序最小密文，如果无法匹配，输出"error"
 *
 * 用例1
 * 输入
 *
 * 2
 * 0 3
 * 3
 * 0 0 2
 * 1 3 4
 * 6 6 4
 * 1
 * 2
 * 3
 * 4
 * 5
 * 6
 * 输出
 *
 * 0 1 1 1
 * 1
 * 用例2
 * 输入
 *
 * 2
 * 0 5
 * 3
 * 0 0 2
 * 1 3 4
 * 6 6 4
 * 输出
 *
 * error
 * 1
 * 说明
 *
 * 找不到 0 5 的序列，返回error
 */
public class MCM {

    static int[][] directions = new int[][]{{0,1},{0,-1},{1,0},{-1,0}};

    private static void dfs(int[][] mw, int i, int j, int[] mNums, int index, StringBuilder sb, boolean[][] visited) {
        if(i < 0 || i >= mw.length || j < 0 || j >= mw[0].length || visited[i][j] || index >= mNums.length) return;
        if(mNums[index] != mw[i][j]) return;
        visited[i][j] = true;
        sb.append(i).append(' ').append(j).append(' ');
        for (int[] direction : directions) {
            dfs(mw, i + direction[0], j + direction[1], mNums, index + 1, sb, visited);
        }
        visited[i][j] = false;
    }

    private static String toMing(int[][] mw, int[] mNums) {
        for (int i = 0; i < mw.length; i++) {
            for (int j = 0; j < mw[i].length; j++) {
                if(mw[i][j] == mNums[0]) {
                    StringBuilder sb = new StringBuilder();
                    boolean[][] visited = new boolean[mw.length][mw[0].length];
                    dfs(mw, i, j, mNums, 0, sb, visited);
                    if(sb.length() == mNums.length * 4) {
                        return sb.substring(0, sb.length() - 1);
                    }
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int mCount = in.nextInt();
        int[] mNums = new int[mCount];
        for (int i =  0; i < mCount; i++) mNums[i] = in.nextInt();
        int mwCount = in.nextInt();
        int[][] mw = new int[mwCount][mwCount];
        for (int i = 0; i < mwCount; i++) {
            for (int j = 0; j < mwCount; j++) {
                mw[i][j] = in.nextInt();
            }
        }
        String rs = toMing(mw, mNums);
        System.out.println(rs ==  null ? "error" : rs);
    }



}
