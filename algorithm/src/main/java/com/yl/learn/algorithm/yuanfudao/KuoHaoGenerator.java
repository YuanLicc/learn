package com.yl.learn.algorithm.yuanfudao;

import java.util.ArrayList;
import java.util.List;

/**
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 * 输入：n = 3
 * 输出：[
 *        "((()))",
 *        "(()())",
 *        "(())()",
 *        "()(())",
 *        "()()()"
 *      ]
 *
 * 来源：力扣（LeetCode） 22
 * 链接：https://leetcode-cn.com/problems/generate-parentheses
 */
public class KuoHaoGenerator {
    public List<String> generateParenthesis(int n) {
        List<String> rs = new ArrayList<>();

        dfs(0, 0, n, rs, "");

        return rs;
    }

    private void dfs(int left, int right, int n, List<String> rs, String str) {

        if(right > left) return;

        if(left == n && right == n) {
            rs.add(str);
        }
        else if(left > n){
            return;
        }
        else {
            dfs(left + 1, right, n, rs, str + "(");
            dfs(left, right + 1, n, rs, str + ")");
        }
    }
}
