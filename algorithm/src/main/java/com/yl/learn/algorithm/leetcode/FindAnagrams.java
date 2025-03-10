package com.yl.learn.algorithm.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
 *
 * 示例 1:
 * 输入: s = "cbaebabacd", p = "abc"
 * 输出: [0,6]
 * 解释:
 * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
 * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
 *
 *  示例 2:
 * 输入: s = "abab", p = "ab"
 * 输出: [0,1,2]
 * 解释:
 * 起始索引等于 0 的子串是 "ab", 它是 "ab" 的异位词。
 * 起始索引等于 1 的子串是 "ba", 它是 "ab" 的异位词。
 * 起始索引等于 2 的子串是 "ab", 它是 "ab" 的异位词。
 */
public class FindAnagrams {
    // 题目理解，首先异位词即时相同字符的不同组合情况，判断 S 内的子串是不是 P 的异位词
    // 思路：可以拆解一下，先将P的多种异位词给弄出来，但是呢题目说明P的字母数目比较多
    // 考虑通过Map将P的字母存起来判断S的子串是否符合目标，然后判断子串是否符合目标
    // 考虑将P进行排序，然后子串排序进行equals判断
    // 考虑滑动窗口进行判定
    public List<Integer> findAnagrams(String s, String p) { // 时间复杂度 N + M - N = M，空间复杂度 (0~M-N) + 26 * 2
        int sLength = s.length();
        int pLength = p.length();
        List<Integer> rs = new ArrayList<>(); // 空间复杂度：0~M-N
        if(sLength < pLength) {
            return rs;
        }
        // 定义俩数组来保存数据，本质上还是hash
        int[] hop = new int[26]; // 空间复杂度：26
        int[] pCount = new int[26]; // 空间复杂度：26
        for(int i = 0; i < pLength; i++) { // 时间复杂度 N
            ++hop[s.charAt(i) - 'a'];
            ++pCount[p.charAt(i) - 'a'];
        }
        if(Arrays.equals(hop, pCount)) {
            rs.add(0);
        }
        for(int i = 0; i < sLength - pLength; i++) { // 时间复杂度：M - N
            --hop[s.charAt(i) - 'a'];
            ++hop[s.charAt(i + pLength) - 'a'];
            if(Arrays.equals(hop, pCount)) { // 时间复杂度：26
                rs.add(i + 1);
            }
        }
        return rs;
    }
}
