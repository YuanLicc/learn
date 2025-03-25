package com.yl.learn.algorithm.od;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 题目描述
 * 小王设计了一个简单的猜字谜游戏，游戏的谜面是一个错误的单词，比如nesw，玩家需要猜出谜底库中正确的单词。猜中的要求如下：
 * 对于某个谜面和谜底单词，满足下面任一条件都表示猜中：
 * 变换顺序以后一样的，比如通过变换w和e的顺序，“nwes”跟“news”是可以完全对应的；
 * 字母去重以后是一样的，比如“woood”和“wood”是一样的，它们去重后都是“wod”
 * 请你写一个程序帮忙在谜底库中找到正确的谜底。谜面是多个单词，都需要找到对应的谜底，如果找不到的话，返回”not found”
 *
 * 输入描述
 * 谜面单词列表，以“,”分隔
 * 谜底库单词列表，以","分隔
 * 输出描述
 * 匹配到的正确单词列表，以","分隔
 * 如果找不到，返回"not found"
 * 备注
 * 单词的数量N的范围：0 < N < 1000
 * 词汇表的数量M的范围：0 < M < 1000
 * 单词的长度P的范围：0 < P < 20
 * 输入的字符只有小写英文字母，没有其他字符
 * 示例1
 * 输入
 *
 * conection
 * connection,today
 * 输出
 *
 * connection
 */
public class FindWord {

    // 可以暴力求解，也可以先将字符串进行去重排序，然后进行比较
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String[] searches = in.nextLine().split(",");
        String[] words = in.nextLine().split(",");
        StringBuilder sb = new StringBuilder();
        Map<String, String> wordsDistinctSort = new HashMap<>();

        for (int i = 0; i < searches.length; i++) {
            String search = searches[i];
            boolean isFound = false;
            for (int j = 0; j < words.length; j++) {
                String aimWord = words[j];
                if(search.equals(aimWord)) {
                    sb.append(aimWord).append(",");
                    isFound = true;
                    break;
                }
                String aimWordDistinctSort = wordsDistinctSort.getOrDefault(aimWord, distinctSort(aimWord));
                wordsDistinctSort.put(aimWord, aimWordDistinctSort);
                String searchDistinctSort = distinctSort(search);
                if(searchDistinctSort.equals(aimWordDistinctSort)) {
                    sb.append(aimWord).append(",");
                    isFound = true;
                    break;
                }
            }
            if(!isFound) sb.append("Not Found,");
        }
        System.out.println(sb.substring(0, sb.length() - 1));
    }

    private static String distinctSort(String word) {
        int[] wordCount = countWord(word);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < wordCount.length; i++) {
            if(wordCount[i] > 0) {
                sb.append((char)(i + 'a'));
            }
        }
        return sb.toString();
    }

    private static boolean judgeWordCount(int[] wordA, int[] wordB) {
        for (int i = 0; i < wordA.length; i++) {
            if(wordA[i] != wordB[i]) return false;
        }
        return true;
    }

    private static int[] countWord(String word) {
        int[] rs = new int[26];
        for (int i = 0; i < word.length(); i++) {
            rs[word.charAt(i) - 'a']++;
        }
        return rs;
    }

    private static String distinct(String word) {
        int index = 0;
        StringBuilder sb = new StringBuilder();
        while (index < word.length()) {
            char current = word.charAt(index);
            while (index < word.length() && word.charAt(index) == current) {
                index++;
            }
            sb.append(current);
        }
        return sb.toString();
    }

    // Scanner in = new Scanner(System.in);
    //        String[] searches = in.nextLine().split(",");
    //        String[] words = in.nextLine().split(",");
    //        StringBuilder sb = new StringBuilder();
    //        Map<String, int[]> wordsWordCount = new HashMap<>();
    //        Map<String, String> wordsDistinct = new HashMap<>();
    //
    //        for (int i = 0; i < searches.length; i++) {
    //            String search = searches[i];
    //            boolean isFound = false;
    //            for (int j = 0; j < words.length; j++) {
    //                String aimWord = words[j];
    //                if(search.equals(aimWord)) {
    //                    sb.append(aimWord).append(",");
    //                }
    //                else {
    //                    String searchDistinct = distinct(search);
    //                    String aimWordDistinct = wordsDistinct.getOrDefault(aimWord, distinct(aimWord));
    //                    wordsDistinct.put(aimWord, aimWordDistinct);
    //                    if(searchDistinct.equals(aimWordDistinct)) {
    //                        sb.append(aimWord).append(",");
    //                        isFound = true;
    //                        break;
    //                    }
    //                    else {
    //                        int[] aimWordCount = wordsWordCount.getOrDefault(aimWord, countWord(aimWord));
    //                        int[] searchWordCount = countWord(search);
    //                        wordsWordCount.put(aimWord, aimWordCount);
    //                        if(judgeWordCount(aimWordCount, searchWordCount)) {
    //                            sb.append(aimWord).append(",");
    //                            isFound = true;
    //                            break;
    //                        }
    //                    }
    //                }
    //            }
    //            if(!isFound) sb.append("Not Found,");
    //        }
    //        System.out.println(sb.substring(0, sb.length() - 1));

}
