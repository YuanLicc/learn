package com.yl.learn.algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串的长度。
 *
 * 示例 1:
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 *
 * 示例 2:
 * 输入: s = "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 *
 * 示例 3:
 * 输入: s = "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。 请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 */
public class LengthOfLongestSubstring {

    // 理解题目：求最长子串，且子串中的字符不重复，返回最长子串的长度即可
    // 思路：如果子串 [i, j] 为非重复子串，那么[i, j + 1] 为非重复子串的条件为 j+1字符不在 [i, j] 中即可
    public static int lengthOfLongestSubstring(String s) {
        if(s == null || s.equals("")) {
            return 0;
        }
        Map<Character, Integer> map = new HashMap<>(); // 空间复杂度 N
        int leftIndex = 0;
        int max = 1;
        map.put(s.charAt(0), 0);
        for(int i = 1; i < s.length(); i++) { // 时间复杂度 N
            Integer get = map.get(s.charAt(i));
            if(null == get) { // 如果没有获取到数据，说明不重复
                // 不重复的情况下，咱么可以计算一下max
                max = Math.max(i - leftIndex + 1, max);
            }
            else if(get >= leftIndex) { // 如果获取到了，说明重复了，计算一下比较大小
                max = Math.max(i - get, max);
                leftIndex = get + 1;
            }
            else { // 如果获取到了，说明重复了，计算一下比较大小
                max = Math.max(i - leftIndex + 1, max);
            }
            map.put(s.charAt(i), i);
        }
        return max;
    }
}
