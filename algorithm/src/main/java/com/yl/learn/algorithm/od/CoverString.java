package com.yl.learn.algorithm.od;

import java.util.Scanner;

/**
 * 题目描述
 * 给定两个字符串s1和s2和正整数K，其中s1长度为n1，s2长度为n2，在s2中选一个子串，满足:
 *
 * 该子串长度为n1+k
 * 该子串中包含s1中全部字母，
 * 该子串每个字母出现次数不小于s1中对应的字母，
 * 我们称s2以长度k冗余覆盖s1，给定s1，s2，k，求最左侧的s2以长度k冗余覆盖s1的子串的首个元素的下标，如果没有返回**-1**。
 *
 * 输入描述
 * 输入三行，第一行为s1，第二行为s2，第三行为k，s1和s2只包含小写字母
 *
 * 备注
 * 0 ≤ len(s1) ≤ 1000000
 * 0 ≤ len(s2) ≤ 20000000
 * 0 ≤ k ≤ 1000
 * 输出描述
 * 最左侧的s2以长度k冗余覆盖s1的子串首个元素下标，如果没有返回**-1。**
 *
 * 示例1
 * 输入
 * ab
 * aabcd
 * 1
 * 输出
 *
 * 0
 * 说明
 *
 * 子串aab和abc符合要求，由于aab在abc的左侧，因此输出aab的下标：0
 *
 * 示例2
 * 输入
 *
 * abc
 * dfs
 * 10
 * 输出
 *
 * -1
 * 说明
 *
 * s2无法覆盖s1，输出 -1
 */
public class CoverString {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s1 = in.nextLine(), s2 = in.nextLine();
        int k = in.nextInt();
        if(s2.length() < s1.length() + k) {
            System.out.println(-1);
            return;
        }
        int[] s1Count = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            s1Count[s1.charAt(i) - 'a']++;
        }

        int[] windowsCount = new int[26];
        int right = 0, left = 0;
        // 初始化窗口
        while (right < s2.length() && (right - left + 1) <= s1.length() + k) {
            windowsCount[s2.charAt(right) - 'a']++;
            right++;
        }
        while (right < s2.length()) {
            if(isContains(windowsCount, s1Count)) {
                System.out.println(left);
                return;
            }
            else {
                windowsCount[s2.charAt(right) - 'a']++;
                windowsCount[s2.charAt(left) - 'a']--;
                right++;
                left++;
            }
        }
        System.out.println(-1);
    }

    private static boolean isContains(int[] windowsCount, int[] s1Count) {
        for (int i = 0; i < windowsCount.length; i++) {
            if(windowsCount[i] < s1Count[i]) return false;
        }
        return true;
    }

}
