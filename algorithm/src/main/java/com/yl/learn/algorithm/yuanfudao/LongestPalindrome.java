package com.yl.learn.algorithm.yuanfudao;

import junit.framework.TestCase;

public class LongestPalindrome extends TestCase {

    public String longestPalindrome(String s) {

        if(s == null || s.length() == 1) return s;

        boolean[][] cell = new boolean[s.length()][s.length()];

        int max = 0;
        int maxLeftIndex = 0;
        // 初始化
        for(int i = 0; i < cell.length; i++) {
            cell[i][i] = true;
            if(max < 1) {
                max = 1;
                maxLeftIndex = i;
            }
            if(i < cell.length - 1 && s.charAt(i) == s.charAt(i + 1)) {
                cell[i][i + 1] = true;
                if(max < 2) {
                    max = 2;
                    maxLeftIndex = i;
                }
            }
        }


        // cell[i] == cell[j] ? cell[i + 1][j - 1] ?
        for(int i = 2; i < cell.length; i++) {

            for(int j = 0; j + i < cell[i].length; j++) {
                if(s.charAt(j) == s.charAt(j + i) && cell[j + 1][j + i - 1] == true) {
                    cell[j][j + i] = true;
                    if(max < i + 1) {
                        max = i + 1;
                        maxLeftIndex = j;
                    }
                }

            }

        }


        return s.substring(maxLeftIndex, max + maxLeftIndex);
    }

    public void test() {
        longestPalindrome("abbc");
    }
}
