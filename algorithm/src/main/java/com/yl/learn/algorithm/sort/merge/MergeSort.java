package com.yl.learn.algorithm.sort.merge;

/*
n*logn
 */
public class MergeSort {

    public static void sort(int[] input) {
        divide(input, 0, input.length - 1);
    }

    public static void divide(int[] input, int start, int end) {
        if(start == end) {
            return;
        }

        int middle = (start + end) / 2;
        divide(input, start, middle);
        divide(input, middle + 1, end);

        merge(input, start, middle, middle + 1, end);
    }

    public static void merge(int[] input, int leftStartIndex, int leftEndIndex, int rightStartIndex, int rightEndIndex) {

        int temp[] = new int[rightEndIndex - leftStartIndex + 1];

        int leftStartTemp = leftStartIndex;

        int count = 0;
        while(true) {
            if(leftStartIndex > leftEndIndex) {
                if(rightStartIndex <= rightEndIndex) {
                    while (rightStartIndex <= rightEndIndex) {
                        temp[count++] = input[rightStartIndex];
                        rightStartIndex++;
                    }
                }
                else {
                    break;
                }
            }
            else {
                if(rightStartIndex > rightEndIndex) {
                    while (leftStartIndex <= leftEndIndex) {
                        temp[count++] = input[leftStartIndex];
                        leftStartIndex++;
                    }
                    break;
                }
                else {
                    if(input[leftStartIndex] >= input[rightStartIndex]) {
                        temp[count++] = input[rightStartIndex];
                        rightStartIndex++;
                    }
                    else {
                        temp[count++] = input[leftStartIndex];
                        leftStartIndex++;
                    }
                }
            }
        }

        for(int i = 0; i < temp.length; i++) {
            input[leftStartTemp + i] = temp[i];
        }
    }

}
