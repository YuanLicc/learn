package com.yl.learn.algorithm.yuanfudao;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个整数 n, 返回从 1 到 n 的字典顺序。
 * 例如，
 * 给定 n =1 3，返回 [1,10,11,12,13,2,3,4,5,6,7,8,9] 。
 * 请尽可能的优化算法的时间复杂度和空间复杂度。 输入的数据 n 小于等于 5,000,000。
 *
 * 来源：力扣（LeetCode）386
 * 链接：https://leetcode-cn.com/problems/lexicographical-numbers
 */
public class LexicalOrder {

    public List<Integer> lexicalOrder(int n) {
        List<Integer> rs = new ArrayList<>();
        if(n <= 0) return rs;

        for(int i = 1; i <= 9; i++) {
            dfs(i, n, rs);
        }

        return rs;
    }

    private void dfs(int sonStart, int max, List<Integer> rs) {

        if(sonStart > max) return;

        rs.add(sonStart);

        for(int i = 0; i <= 9; i++) {
            dfs(sonStart * 10 + i, max, rs);
        }

    }
}
