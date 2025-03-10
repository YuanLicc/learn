package com.yl.learn.algorithm.sort.quick;


public class QuickSort {

    public static int[] quickSort(int[] input) {
        if(input == null || input.length == 0) {
            return null;
        }
        if(input.length == 1) {
            return input;
        }
        quick(input, 0, input.length - 1);
        return input;
    }

    private static void quick(int[] input, int start, int end) {
        if(start < 0 || end > input.length || start > end) {
            return;
        }

        int pivot = input[start];
        int pivotIndex = start;
        for(int i = start; i <= end; i++) {
            if(input[i] < pivot) {
                int temp = input[i];
                move(input, pivotIndex, i);
                input[pivotIndex] = temp;
                pivotIndex++;
            }
        }
        quick(input, start, pivotIndex - 1);
        quick(input, pivotIndex + 1, end);

    }

    private static void move(int[] input, int start, int end) {
        if(start == end) {
            return;
        }

        for(int i = end - 1; i >= start; i--) {
            input[i + 1] = input[i];
        }
    }

    private static void swap(int[] input, int index, int swap) {
        int temp = input[index];
        input[index] = input[swap];
        input[swap] = temp;
    }

}
