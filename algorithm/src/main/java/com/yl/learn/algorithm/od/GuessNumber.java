package com.yl.learn.algorithm.od;

import java.util.*;

/**
 * 题目描述
 * 一个人设定一组四码的数字作为谜底，另一方猜。
 * 每猜一个数，出数者就要根据这个数字给出提示，提示以XAYB形式呈现，直到猜中位置。
 * 其中X表示位置正确的数的个数（数字正确且位置正确），而Y表示数字正确而位置不对的数的个数。
 * 例如，当谜底为8123，而猜谜者猜1052时，出题者必须提示0A2B。
 * 例如，当谜底为5637，而猜谜者才4931时，出题者必须提示1A0B。
 * 当前已知N组猜谜者猜的数字与提示，如果答案确定，请输出答案，不确定则输出NA。
 *
 * 输入描述
 * 第一行输入一个正整数，0＜N ＜ 100。
 * 接下来N行，每一行包含一个猜测的数字与提示结果。
 *
 * 输出描述
 * 输出最后的答案，答案不确定则输出NA。
 *
 * 示例1
 * 输入
 *
 * 6
 * 4815 1A1B
 * 5716 0A1B
 * 7842 0A1B
 * 4901 0A0B
 * 8585 3A0B
 * 8555 2A1B
 * 输出
 * 3585
 */
public class GuessNumber {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        in.nextLine();
        Map<Integer, HashSet<Character>> notContains = new HashMap<>();
        String[][] questionAnswers = new String[num][2];
        for (int i = 0; i < num; i++) {
            String[] questionAnswer = in.nextLine().split(" ");
            String question = questionAnswer[0];
            String answer = questionAnswer[1];
            if(answer.startsWith("0")) {
                addZeroA(notContains, questionAnswer[0]);
                if(answer.charAt(2) == '0') addZeroAB(notContains, questionAnswer[0]);
            }
            questionAnswers[i] = questionAnswer;
        }

        // 暴力枚举
        int validCount = 0;
        String res = "NA";
        for (int start = 1000; start <= 9999; start++) {
            String judgeNumber = Integer.toString(start);
            if(!judgeNotContains(notContains, judgeNumber)) continue; // 剪制

            boolean isValid = true;
            for (String[] questionAnswer : questionAnswers) {
                String question = questionAnswer[0];
                String answer = questionAnswer[1];
                isValid = judgeQA(question, answer, judgeNumber);
                if(!isValid) break;
            }
            if(isValid) {
                res = judgeNumber;
                validCount++;
            }
            if(validCount > 1) {
                System.out.println("NA");
                return;
            }
        }
        System.out.println(res);
    }

    /**
     * 6
     * 4815 1A1B
     * 5716 0A1B
     * 7842 0A1B
     * 4901 0A0B
     * 8585 3A0B
     * 8555 2A1B
     */
    private static boolean judgeQA(String question, String answer, String number) {
        int countA = 0;
        int[] questionB = new int[10];
        int[] numberB = new int[10];
        for (int i = 0; i < number.length(); i++) {
            char numberChar = number.charAt(i), questionChar = question.charAt(i);
            if(numberChar == questionChar) countA++;
            else {
                questionB[Character.getNumericValue(questionChar)]++;
                numberB[Character.getNumericValue(numberChar)]++;
            }
        }
        int countB = 0;
        for (int i = 0; i < 10; i++) {
            countB += Math.min(questionB[i], numberB[i]);
        }

        return countA == Character.getNumericValue(answer.charAt(0)) && countB == Character.getNumericValue(answer.charAt(2));
    }

    private static boolean judgeNotContains(Map<Integer, HashSet<Character>> notContains, String number) {
        if(notContains.size() == 0) return true;
        for (int i = 0; i < number.length(); i++) {
            if(notContains.get(i).contains(number.charAt(i))) return false;
        }
        return true;
    }

    // 如果A为零，B也为零，可以将字符串的每个数字都加入到不包含的队列内
    private static void addZeroAB(Map<Integer, HashSet<Character>> notContains, String zeroAB) {
        notContains.forEach((index, set) -> {
            for (int i = 0; i < zeroAB.length(); i++) {
                set.add(zeroAB.charAt(i));
            }
        });
    }

    private static void addZeroA(Map<Integer, HashSet<Character>> notContains, String zeroA) {
        for (int i = 0; i < zeroA.length(); i++) {
            HashSet<Character> set= notContains.getOrDefault(i, new HashSet<Character>());
            set.add(zeroA.charAt(i));
            notContains.put(i, set);
        }
    }
}
