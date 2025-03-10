package com.yl.learn.algorithm.pm;

public class ArraySearch {

    public static boolean isContains(char[] str1, char[] str2) {

        int[] cell = new int['Z' - 'A' + 1];

        for(int i = 0; i < str1.length; i++) {
            char c = str1[i];

            cell[c - 'A']++;
        }


        for(int i = 0; i < str2.length; i++) {
            char c = str2[i];

            if(cell[c - 'A'] == 0) {
                return false;
            }
        }

        return true;
    }

}
