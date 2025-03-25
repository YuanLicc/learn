package com.yl.learn.algorithm.od;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 题目描述
 * 在斗地主扑克牌游戏中， 扑克牌由小到大的顺序为：3,4,5,6,7,8,9,10,J,Q,K,A,2，玩家可以出的扑克牌阵型有：单张、对子、顺子、飞机、炸弹等。
 *
 * 其中顺子的出牌规则为：由至少5张由小到大连续递增的扑克牌组成，且不能包含2。
 *
 * 例如：{3,4,5,6,7}、{3,4,5,6,7,8,9,10,J,Q,K,A}都是有效的顺子；而{J,Q,K,A,2}、 {2,3,4,5,6}、{3,4,5,6}、{3,4,5,6,8}等都不是顺子。
 *
 * 给定一个包含13张牌的数组，如果有满足出牌规则的顺子，请输出顺子。
 *
 * 如果存在多个顺子，请每行输出一个顺子，且需要按顺子的第一张牌的大小（必须从小到大）依次输出。
 *
 * 如果没有满足出牌规则的顺子，请输出No。
 *
 * 输入描述
 * 13张任意顺序的扑克牌，每张扑克牌数字用空格隔开，每张扑克牌的数字都是合法的，并且不包括大小王：
 * 2 9 J 2 3 4 K A 7 9 A 5 6
 * 不需要考虑输入为异常字符的情况
 *
 * 输出描述
 * 组成的顺子，每张扑克牌数字用空格隔开：
 * 3 4 5 6 7
 * 示例1
 * 输入
 * 2 9 J 2 3 4 K A 7 9 A 5 6
 * 输出
 * 3 4 5 6 7
 * 说明
 *
 * 13张牌中，可以组成的顺子只有1组：3 4 5 6 7。
 *
 * 示例2
 * 输入：
 * 2 9 J 10 3 4 K A 7 Q A 5 6
 * 输出：
 * 3 4 5 6 7
 * 9 10 J Q K A
 */
public class DaPai {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String[] cards = in.nextLine().split(" ");
        int[] cardsArray = toArray(cards);

        int start = 0;
        StringBuilder sb = new StringBuilder();
        while (start < cardsArray.length - 5) {
            while (start < 12 && cardsArray[start]  == 0) start++;
            int end = start;
            while (end < 12 && cardsArray[end] != 0) end++;
            end--;
            if(end - start >= 4) {
                // 打印
                sb.append(toString(cardsArray, start, end)).append("\n");
                // 将区间值减一
                minus(cardsArray, start, end);
                start = 0; // 重新搜索（）
            }
            else start++;
        }
        System.out.println(sb.length() > 0 ? sb : "No");
    }

    private static String toString(int[] cards, int start, int end) {
        StringBuilder sb = new StringBuilder();
        for (int i = start; i <= end; i++) {
            sb.append(toString(i)).append(" ");
        }
        return sb.toString();
    }

    private static void minus(int[] cards, int start, int end) {
        for (int i = start; i <= end; i++) {
            cards[i]--;
        }
    }

    private static int[] toArray(String[] cards) {
        int[] array = new int[13];
        for (int  i = 0; i < cards.length; i++) {
            array[toNum(cards[i])]++;
        }
        return array;
    }


    private static String toString(int num) {
        if(num == 8) return "J";
        if(num == 9) return "Q";
        if(num == 10) return "K";
        if(num == 11) return "A";
        if(num == 12) return "2";
        return String.valueOf(num + 3);
    }

    private static int toNum(String ch) {
        if(ch.equals("J")) return 8;
        if(ch.equals("Q")) return 9;
        if(ch.equals("K")) return 10;
        if(ch.equals("A")) return 11;
        int num = Integer.parseInt(ch);
        return num == 2 ? 12 : num - 3;
    }
}
