package com.yl.learn.algorithm.od;

import java.util.*;

/**
 * 题目描述
 * 给定一个字符串s，找出这样一个子串：
 * 该子串中任意一个字符最多出现2次
 * 该子串不包含指定某个字符
 * 请你找出满足该条件的最长子串的长度
 *
 * 输入描述
 * 第一行为：要求不包含的指定字符，为单个字符，取值范围[0-9a-zA-Z]
 * 第二行为：字符串s，每个字符范围[0-9a-zA-Z]，长度范围[1, 10000]
 *
 * 输出描述
 * 最长子串长度
 *
 * 示例1
 * 输入
 *
 * D
 * ABC123
 * 输出
 *
 * 6
 * 说明
 *
 * 无
 *
 * 示例2
 * 输入
 *
 * D
 * ABACA123D
 * 输出
 * 7
 */
public class SonString {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        char notContain = in.nextLine().charAt(0);
        String aimString = in.nextLine();

        int left = 0, right = 0;
        int max = 1;
        Map<Character, List<Integer>> charIndexMap = new HashMap<>();
        while (right < aimString.length()) {
            char currentChar = aimString.charAt(right);
            if(currentChar == notContain) { // 不能包含的字符串
                max = Math.max(max, right - left);
                right++;
                left = right;
            }
            else { // 可以包含的字符，但是需要考虑是否满足字符的个数不超过2
                List<Integer> indexes = charIndexMap.getOrDefault(currentChar, new ArrayList<>());
                if(indexes.size() < 2) { // 不包含 或则不超过2
                    indexes.add(right);
                    charIndexMap.put(currentChar, indexes);
                    max = Math.max(max, right - left + 1);
                    right++;
                }
                else if(indexes.size() == 2) { // 已经有两个了，需要移除第一个
                    int index = indexes.remove(0); // 移除第一个
                    for (int i = left; i < index; i++) { // 窗口需要滑动来满足要求
                        charIndexMap.get(aimString.charAt(i)).remove(0);
                    }
                    left = index + 1;
                    max = Math.max(max, right - left + 1);
                    right++;
                }
            }
        }

        System.out.println(max);
    }

}
