package com.yl.learn.algorithm.od;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 题目描述
 * 单词接龙的规则是：
 * 可用于接龙的单词首字母必须要前一个单词的尾字母相同；
 * 当存在多个首字母相同的单词时，取长度最长的单词，如果长度也相等，则取字典序最小的单词；已经参与接龙的单词不能重复使用。
 * 现给定一组全部由小写字母组成单词数组，并指定其中的一个单词作为起始单词，进行单词接龙，
 * 请输出最长的单词串，单词串是单词拼接而成，中间没有空格。
 *
 * 输入描述
 * 输入的第一行为一个非负整数，表示起始单词在数组中的索引K，0 <= K < N ；
 * 输入的第二行为一个非负整数，表示单词的个数N；
 * 接下来的N行，分别表示单词数组中的单词。
 *
 * 备注：
 * 单词个数N的取值范围为[1, 20]；
 * 单个单词的长度的取值范围为[1, 30]；
 *
 * 输出描述
 * 输出一个字符串，表示最终拼接的单词串。
 *
 * 示例1
 * 输入
 *
 * 0
 * 6
 * word
 * dd
 * da
 * dc
 * dword
 * d
 * 输出
 *
 * worddwordda
 */
public class WordCombine {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int startIndex = in.nextInt(), wordC = in.nextInt();
        in.nextLine();
        String[] words = new String[wordC];
        for (int i = 0; i < wordC; i++) {
            words[i] = in.nextLine();
        }
        String startStr = words[startIndex];
        Arrays.sort(words, (v1, v2) -> {
            char v1Head = v1.charAt(0), v2Head = v2.charAt(0);
            if(v1Head == v2Head) {
                if(v1.length() != v2.length()) return -v1.length() + v2.length();
                else return v1.compareTo(v2);
            }
            return v2.compareTo(v1);
        });
        boolean[] used = new boolean[words.length];
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(startStr)) {
                used[i] = true;
                break;
            }
        }
        StringBuilder rs = new StringBuilder().append(startStr);
        dfs(words, used, startStr, rs);
        System.out.println(rs.toString());
    }

    private static void dfs(String[] words, boolean[] used, String preWord, StringBuilder sb) {
        char end = preWord.charAt(preWord.length() - 1);
        String current = null;
        for (int i = 0; i < words.length; i++) {
            if(!used[i] && end == words[i].charAt(0)) {
                used[i] = true;
                current = words[i];
                break;
            }
        }
        if(current == null) return;
        sb.append(current);
        dfs(words, used, current, sb);
    }
}
