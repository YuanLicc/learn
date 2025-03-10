package com.yl.learn.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 *
 * 示例 1：
 * 输入：n = 3
 * 输出：["((()))","(()())","(())()","()(())","()()()"]
 *
 * 示例 2：
 * 输入：n = 1
 * 输出：["()"]
 */
public class GenerateParenthesis {

    public List<String> generateParenthesis(int n) {
        List<String> rs = new ArrayList<>();
        dfs(0, 0, n, new StringBuilder(), rs);
        return rs;
    }

    private void dfs(int left, int right, int n, StringBuilder sb, List<String> rs) {
        if(left == right && left == n) {
            rs.add(sb.toString());
            return;
        }
        if(right > left || left > n) {
            return;
        }
        sb.append('(');
        dfs(left + 1, right, n, sb, rs);
        sb.delete(sb.length() - 1, sb.length());
        sb.append(')');
        dfs(left, right + 1, n, sb, rs);
        sb.delete(sb.length() - 1, sb.length());
    }

}
