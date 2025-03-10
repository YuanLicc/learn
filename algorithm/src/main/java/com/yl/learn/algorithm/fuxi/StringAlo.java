package com.yl.learn.algorithm.fuxi;

import com.yl.learn.util.util.PrintUtil;
import junit.framework.TestCase;

public class StringAlo extends TestCase {

    /**
     * 最长回文子串
     * @return
     */
    public String longestPalindrome(String string) {
        if(string == null || string.length() == 0) return "";

        char[] chars = string.toCharArray();

        boolean[][] isPalindrome = new boolean[chars.length][chars.length];

        String max = string.substring(0, 1);
        for(int i = 0; i < isPalindrome.length; i++) {
            isPalindrome[i][i] = true;

            if(i < isPalindrome.length - 1 && chars[i] == chars[i + 1]) {
                isPalindrome[i][i + 1] = true;

                max = string.substring(i, i + 2);
            }
        }

        // is[i][j] = is[i + 1][j - 1]
        for(int i = 2; i < isPalindrome.length - i; i++) {
            for(int j = 0; j < isPalindrome.length - i; j++) {
                isPalindrome[j][j + i] =
                        isPalindrome[j + 1][j + i - 1] && chars[j] == chars[j + i];
                if(isPalindrome[j][j + i]) {
                    String tmp = string.substring(j, j + i + 1);
                    max = tmp.length() > max.length() ? tmp : max;
                }
            }
        }

        return max;
    }

    /**
     * 最长公共子序列
     * @param a
     * @param b
     * @return
     */
    public int longestCommonSubSequence(String a, String b) {
        if(a == null || b == null) return 0;

        int[][] dp = new int[a.length()][b.length()];

        for(int i = 0; i < b.length(); i++) {
            if(a.charAt(0) == b.charAt(i)) dp[0][i] = 1;
            else if(i > 0)  dp[0][i] = dp[0][i - 1];
        }

        for(int i = 0; i < a.length(); i++) {
            if(b.charAt(0) == a.charAt(i)) dp[i][0] = 1;
            else if(i > 0) dp[i][0] = dp[i - 1][0];
        }

        for(int i = 1; i < a.length(); i++) {

            for(int j = 1; j < b.length(); j++) {

                if(a.charAt(i) == b.charAt(j)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }
                else {
                    dp[i][j] = dp[i - 1][j] > dp[i][j - 1] ? dp[i - 1][j] : dp[i][j - 1];
                }
            }
        }

        return dp[a.length() - 1][b.length() - 1];
    }

    public int longestCommonSubString(String a, String b) {
        if(a == null || b == null) return 0;

        int dp[][] = new int[a.length()][b.length()];

        int max = 0;

        for(int i = 0; i < b.length(); i++) {
            if(a.charAt(0) == b.charAt(i)) {
                dp[0][i] = 1;
                max = 1;
            }
        }

        for(int i = 0; i < a.length(); i++) {
            if(a.charAt(i) == b.charAt(0)) dp[i][0] = 1;
        }

        for(int i = 0; i < a.length(); i++) {

            for(int j = 0; j < b.length(); j++) {
                if(a.charAt(i) == b.charAt(j)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    max = max < dp[i][j] ? dp[i][j] : max;
                }
            }
        }

        return max;
    }

    public void testSubStr() {
        PrintUtil.println(longestCommonSubString("abcdcsd", "ebcdcds"));
    }

    public void testSubSeq() {
        PrintUtil.println(longestCommonSubSequence("aba", "ac"));
    }

    public void testPalindrome() {
        PrintUtil.println(longestPalindrome("abccbdsdb"));
    }


}
