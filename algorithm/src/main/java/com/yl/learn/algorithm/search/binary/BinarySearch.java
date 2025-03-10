package com.yl.learn.algorithm.search.binary;

/**
 * 二分查找
 * @author YuanLi
 */
public class BinarySearch {

    public static int binarySearch(int[] input, int aim) {
        if(input == null || input.length == 0) {
            return -1;
        }

        return binary(input, aim, 0, input.length - 1);
    }

    /**
     * 递归算法
     */
    public static int binary(int[] input, int aim, int start, int end) {
        if(start >= input.length || end < 0 || start > end) {
            return -1;
        }

        if(start == end) {
            return input[start] == aim ? start : -1;
        }

        int middle = (start + end) >> 1;

        if(aim == input[middle]) {
            return middle;
        }
        else if(aim > input[middle]) {
            return binary(input, aim, middle + 1, end);
        }
        else {
            return binary(input, aim, start, middle - 1);
        }
    }

    /**
     * 非递归算法
     */
    public static int binary(int[] input, int aim) {
        if(input == null || input.length == 0) return -1;

        int maxIndex = input.length - 1;

        int leftIndex = 0;
        int rightIndex = input.length;

        while(leftIndex <= rightIndex) {
            int middle = (leftIndex + rightIndex) >> 1;
            int middleValue = input[middle];

            if(middleValue == aim) {
                return middle;
            }
            else if(middleValue > aim) {
                if(leftIndex == maxIndex) return -1;
                leftIndex = middle + 1;
            }
            else {
                if(rightIndex == 0) return -1;
                rightIndex = middle - 1;
            }
        }

        return -1;
    }

}
