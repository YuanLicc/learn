package com.yl.learn.algorithm.dynamic.longest;

public class LongestSubsequence {

    public static int[][] longestSubsequence(String a, String b) {

        if(a == null || b == null) {
            return null;
        }

        int[][] cell = new int[a.length()][b.length()];

        for(int i = 0; i < a.length(); i++) {
            char aChar = a.charAt(i);

            for (int j = 0; j < b.length(); j++) {
                char bChar = b.charAt(j);

                if(aChar == bChar) {
                    cell[i][j] = get(cell, i - 1, j - 1) + 1;
                }
                else {
                    cell[i][j] = Math.max(get(cell, i - 1, j), get(cell, i, j - 1));
                }

            }
        }
        return cell;
    }

    private static int get(int[][] cell, int i, int j) {
        if(i < 0 || j < 0) {
            return 0;
        }
        else {
            return cell[i][j];
        }
    }

}
