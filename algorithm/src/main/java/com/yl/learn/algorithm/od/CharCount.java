package com.yl.learn.algorithm.od;

import java.util.*;

/**
 * 题目描述
 * 给出一个仅包含字母的字符串，不包含空格，统计字符串中各个字母（区分大小写）出现的次数，
 * 并按照字母出现次数从大到小的顺序。输出各个字母及其出现次数。
 * 如果次数相同，按照自然顺序进行排序，且小写字母在大写字母之前。
 * 输入描述
 * 输入一行，为一个仅包含字母的字符串。
 *
 * 输出描述
 * 按照字母出现次数从大到小的顺序输出各个字母和字母次数，用英文分号分隔，注意末尾的分号；
 *
 * 字母和次数间用英文冒号分隔。
 *
 * 示例1
 * 输入
 * xyxyXX
 *
 * 输出
 * x:2;y:2;X:2;
 *
 * 示例2
 * 输入
 * abababb
 * 输出
 * b:4;a:3;
 * 说明  b的出现个数比a多，故b排在a之前
 */
public class CharCount {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        char[] chars = in.nextLine().toCharArray();

        Map<Character, Integer> map = new HashMap<>();
        for (char ch : chars) {
            Integer num = map.getOrDefault(ch, 0);
            map.put(ch, num + 1);
        }

        List<CharCnt> list = new ArrayList<>();
        map.forEach((k, v) -> {
            list.add(new CharCnt(v, k));
        });
        Collections.sort(list);
        print(list);
    }

    private static void print(List<CharCnt> list) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            CharCnt charCnt = list.get(i);
            sb.append(charCnt.c).append(":").append(charCnt.cnt).append(i == list.size() - 1 ? "" : ";");
        }
        System.out.println(sb.toString());
    }

    static class CharCnt implements Comparable<CharCnt> {
        int cnt;
        char c;
        CharCnt(int cnt, char c) {
            this.c = c;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(CharCnt o) {
            // 数量大排在前
            if(o.cnt != this.cnt) return o.cnt - this.cnt;
            boolean isUpperOne = isUpper(this);
            boolean isUpperTwo = isUpper(o);
            // 字典序排列，小排在前
            if(isUpperOne && isUpperTwo || (!isUpperOne && !isUpperTwo)) {
                return this.c - o.c;
            }
            // 小写排在前
            return isUpperOne ? 1 : -1;
        }
    }

    private static boolean isUpper(CharCnt charCnt) {
        if(charCnt.c >= 'A' && charCnt.c <= 'Z') return true;
        return false;
    }
}
