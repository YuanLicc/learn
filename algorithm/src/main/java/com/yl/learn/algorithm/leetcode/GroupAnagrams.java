package com.yl.learn.algorithm.leetcode;

import java.util.*;

/**
 * 给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
 * 字母异位词是由重新排列源单词的所有字母得到的一个新单词。
 *
 * 示例 1:
 * 输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
 * 输出: [["bat"],["nat","tan"],["ate","eat","tea"]]
 *
 * 示例 2:
 * 输入: strs = [""]
 * 输出: [[""]]
 *
 * 示例 3:
 * 输入: strs = ["a"]
 * 输出: [["a"]]
 */
public class GroupAnagrams {
    // strs = ["eat", "tea", "tan", "ate", "nat", "bat"] => [["bat"],["nat","tan"],["ate","eat","tea"]]
    // 理解题目：找到数组中的字母异位词，并将他们组成数组返回
    // 思路：需要判定一个单词内的字母跟另一个单词的字母是否相同；大致分为两类：
    // 第一类，假设单词的字母不存在相同字母，咱们可以映射为 质数相乘，如果乘积相等，那么证明两个单词包含的字母一样；
    // 第二类，存在重复字母的情况，
    // - 考虑用 hashmap 存储字母及字母的个数。
    // - 考虑按照 字母+字母的数量作为 key 来存储 | 或则字母排序后作为 key
    public List<List<String>> groupAnagrams(String[] strs) { // 时间复杂度 n * （k + 26）, 空间复杂度 n + (n * (k + num + 26)) => 当字母不重复时，n + (n * (k + k + 26))
        // 定义数据结构
        Map<String, List<String>> kv = new HashMap<>(); // 空间复杂度 n
        // 遍历字符数组
        for (String str : strs) { // 时间复杂度 N，空间复杂度
            String key = toMapKey(str); // 时间复杂度 k + 26，空间复杂度 k + num + 26
            List<String> list = kv.get(key);
            if(list == null) {
                list = new ArrayList<>();
                list.add(str);
                kv.put(key, list);
            }
            else {
                list.add(str);
            }
        }
        return new ArrayList<>(kv.values());
    }

    private String toMapKey(String str) { // 时间复杂度 k + 26, 空间复杂度 k + num + 26
        int[] cCounts = new int[26]; // 空间复杂度 26
        for(int i = 0; i < str.length(); i++) { // 时间复杂度 k
            char currentChar = str.charAt(i);
            int cIndex = currentChar - 'a';
            cCounts[cIndex] += 1;
        }
        return cCountsToString(cCounts); // 时间复杂度 26, 空间复杂度 k + num
    }

    private String cCountsToString(int[] cCounts) {
        StringBuilder sb = new StringBuilder(); // 空间复杂度 k + num
        for (int i = 0; i < cCounts.length; i++) { // 时间复杂度 26
            if(cCounts[i] != 0) {
                sb.append((char)('a' + i)).append(cCounts[i]);
            }
        }
        return sb.toString();
    }
}
