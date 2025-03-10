package com.yl.learn.algorithm.leetcode;

import java.util.HashSet;

/**
 * 给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 *
 * 示例 1：
 * 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
 * 输出：true
 */
public class WordExist {

    public boolean exist(char[][] board, String word) {

        for(int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if(is) return is;
                dfs(board, new HashSet<String>(), i, j, 0, word);
            }
        }
        return is;
    }
    boolean is = false;

    private void dfs(char[][] board, HashSet<String> copied, int i, int j, int index, String word) {
        if(i < 0 || i >= board.length || j < 0 || j >= board[0].length || copied.contains(String.valueOf(i) + j)) {
            return;
        }
        if(word.charAt(index) == board[i][j]) {
            if(index == word.length() - 1) {
                is = true;
            }
            else {
                copied.add(String.valueOf(i) + j);
                dfs(board, copied, i + 1, j, index + 1, word);
                dfs(board, copied, i, j + 1, index + 1, word);
                dfs(board, copied, i - 1, j, index + 1, word);
                dfs(board, copied, i, j - 1, index + 1, word);
                copied.remove(String.valueOf(i) + j);
            }
        }
    }

}
