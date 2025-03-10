package com.yl.learn.algorithm.zuo;

import java.util.Arrays;

/**
 * 给定数组，找出最长递增子序列（LIS）
 */
public class LongestIncreasingSubSequence {

    public static int[] longest(int[] arr) {
        if(arr == null || arr.length == 0) {
            return null;
        }

        int cell[] = new int[arr.length];

        cell[0] = 1;

        for(int i = 1; i < cell.length; i++) {

            int max = 0;

            for(int j = i - 1; j >= 0; j--) {
                if(arr[i] > arr[j]) {
                    max = max > cell[j] ? max : cell[j];
                }
            }
            cell[i] = max + 1;
        }

        return lis(arr, cell);
    }

    public static int[] lis(int[] arr, int[] cell) {

        int index = 0;
        int max = 0;

        for(int i = 0; i < cell.length; i++) {

            if(cell[i] > max) {
                max = cell[i];
                index = i;
            }
        }

        int[] lis = new int[max];
        lis[--max] = arr[index];

        for(int i = index; i >= 0; i--) {

            if(arr[i] < arr[index] && cell[i] == cell[index] - 1) {
                lis[--max] = arr[i];
                index = i;
            }
        }

        return lis;

    }

}
