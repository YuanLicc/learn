package com.yl.learn.algorithm.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给你一个字符串 s，请你将 s 分割成一些 子串，使每个子串都是 回文串 。返回 s 所有可能的分割方案。
 *
 * 示例 1：
 * 输入：s = "aab"
 * 输出：[["a","a","b"],["aa","b"]]
 *
 * 示例 2：
 * 输入：s = "a"
 * 输出：[["a"]]
 */
public class PalindromeStringPartition {

    public List<List<String>> partition(String s) {
        boolean[][] dp = new boolean[s.length()][s.length()];
        for (int i =  0; i < dp.length; i++) {
            Arrays.fill(dp[i], true);
        }
        //  dp[i][j] = dp[i + 1][j - 1] + s[i] == s[j]
        for (int i = s.length() - 1; i >= 0; --i) {
            for (int j = i + 1; j < s.length(); ++j) {
                dp[i][j] = (s.charAt(i) == s.charAt(j)) && dp[i + 1][j - 1];
            }
        }
        List<List<String>> rs = new ArrayList<>();
        List<String> copied = new ArrayList<>();
        dfs(rs, dp, s, 0, copied);
        return rs;
    }

    private void dfs(List<List<String>> rs, boolean[][] dp, String s, int index, List<String> copied) {
        if(index == s.length()) {
            rs.add(new ArrayList<>(copied));
            return;
        }
        for(int i = index; i < s.length(); i++) {
            if(dp[index][i]) {
                copied.add(s.substring(index, i + 1));
                dfs(rs, dp, s, 1 + i, copied);
                copied.remove(copied.size() - 1);
            }
        }
    }

}
