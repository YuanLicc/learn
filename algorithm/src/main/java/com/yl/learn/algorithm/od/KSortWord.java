package com.yl.learn.algorithm.od;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 题目描述
 * 给定一个字符串，只包含大写字母，求在包含同一字母的子串中，长度第 k 长的子串的长度，相同字母只取最长的那个子串。
 *
 * 输入描述
 * 第一行有一个子串(1<长度<=100)，只包含大写字母。
 *
 * 输出描述
 * 输出连续出现次数第k多的字母的次数。
 *
 * 示例1
 * 输入
 *
 * AAAAHHHBBCDHHHH
 * 3
 * 输出
 *
 * 2
 * 说明
 *
 * 同一字母连续出现的最多的是A和H，四次；
 * 第二多的是H，3次，但是H已经存在4个连续的，故不考虑；
 * 下个最长子串是BB，所以最终答案应该输出2。
 *
 * 示例2
 * 输入
 *
 * AABAAA
 * 2
 * 输出
 *
 * 1
 * 说明
 *
 * 同一字母连续出现的最多的是A，三次；
 * 第二多的还是A，两次，但A已经存在最大连续次数三次，故不考虑；
 * 下个最长子串是B，所以输出1。
 *
 * 示例3
 * 输入
 *
 * ABC
 * 4
 * 输出
 *
 * -1
 * 说明
 * 只含有3个包含同一字母的子串，小于k，输出-1
 *
 * 示例4
 * 输入
 *
 * ABC
 * 2
 * 1
 * 2
 * 输出
 *
 * 1
 * 1
 * 说明
 *
 * 三个子串长度均为1，所以此时k = 1，k=2，k=3这三种情况均输出1。
 */
public class KSortWord {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String words = in.nextLine();
        int k = in.nextInt();
        Word[] nums = new Word[26];
        int index = 0;
        while (index < words.length()) {
            int num = 0;
            char currentChar = words.charAt(index);
            while (index < words.length() && words.charAt(index) == currentChar) {
                num++;
                index++;
            }
            int charIndex = currentChar - 'A';
            if(nums[charIndex] == null) {
                nums[charIndex] = new Word(currentChar, num);
            }
            else {
                nums[charIndex].num = Math.max(nums[charIndex].num, num);
            }
        }
        Arrays.sort(nums, (o1, o2) -> {
            if(o1 == null && o2 == null) return 0;
            if(o1 == null || o2 == null) return o1 == null ? 1 : -1;
            return o2.num - o1.num;
        });
        System.out.println(nums[k - 1] == null ? -1 : nums[k - 1].num);
    }

    // 也可以不要此内部类，直接对个数排序即可
    static class Word {
        char word;
        int num;
        Word(char word, int num) {
            this.word = word;
            this.num = num;
        }
    }

}
