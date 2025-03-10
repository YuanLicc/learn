package com.yl.learn.algorithm.sort.select;

public class SelectSort {

    public static int[] selectSort(int[] input) {

        if(input == null || input.length == 0) {
            return null;
        }

        if(input.length == 1) {
            return input;
        }


        for(int i = 0; i < input.length - 1; i++) {

            int minIndex = i;

            for(int j = i + 1; j < input.length; j++) {
                if(input[minIndex] > input[j]) {
                    minIndex = j;
                }
            }

            if(minIndex != i) {
                input[minIndex] = input[minIndex] ^ input[i];
                input[i] = input[minIndex] ^ input[i];
                input[minIndex] = input[minIndex] ^ input[i];
            }

        }

        return input;
    }


}
