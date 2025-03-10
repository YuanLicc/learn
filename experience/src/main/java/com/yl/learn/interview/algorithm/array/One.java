package com.yl.learn.interview.algorithm.array;

/**
 * 在一个二维数组中（每个一维数组的长度相同），每一行都按照从左到右递增的顺序排序，
 * 每一列都按照从上到下递增的顺序排序。请完成一个函数，输入这样的一个二维数组和一个整数，
 * 判断数组中是否含有该整数。
 */
public class One {

    public static boolean find(int target, int [][] array) {
        if(array == null || array.length == 0) {
            return false;
        }

        for(int i = 0; i < array.length; i++) {
            int[] row = array[i];
            int rowLength = row.length;

            if(rowLength > 2) {
                int rowStart = row[0];

                if(rowStart == target) {
                    return true;
                }
                else if(rowStart > target) {
                    return false;
                }
                else {
                    int rowEnd = row[row.length - 1];

                    if(rowEnd < target) {
                        //
                    }
                    else if(rowEnd == target) {
                        return true;
                    }
                    else {
                        if(binary(target, 0, rowLength - 1, row)) {
                            return true;
                        }
                    }
                }
            }
            else if(rowLength == 2) {
                if(row[0] == target || row[1] == target) {
                    return true;
                }
            }
            else if(rowLength == 1){
                if(row[0] == target) {
                    return true;
                }
            }
            else {
                //
            }
        }
        return false;
    }

    public static boolean binary(int target, int start, int end, int[] sortedArray) {
        if(sortedArray == null || start < 0 || end < 0 || start > end) {
            return false;
        }
        int length = sortedArray.length;

        if(length == 0 || start >= length || end >= length) {
            return false;
        }

        int minus = end - start;
        if(start == end) {
            return target == sortedArray[start];
        }
        else if(minus == 1) {
            return target == sortedArray[start] || target == sortedArray[end];
        }
        else if(minus == 2) {
            return target == sortedArray[start + 1];
        }
        else {
            int middle = (start + end) / 2;
            int middleValue = sortedArray[middle];

            if(middleValue == target) {
                return true;
            }
            else if(middleValue > target) {
                return binary(target, start, middle, sortedArray);
            }
            else {
                return binary(target, middle, end, sortedArray);
            }
        }
    }

    /**
     * 矩阵是有序的，从左下角来看，向上数字递减，向右数字递增，
     * 因此从左下角开始查找，当要查找数字比左下角数字大时。右移
     * 要查找数字比左下角数字小时，上移。
     */
    public static boolean solution(int target, int [][] array) {
        if(array == null || array.length == 0) {
            return false;
        }

        int col = 0;
        int row = array.length - 1;

        while(row <= array.length - 1 && col >= 0) {
            if(target == array[row][col]) {
                return true;
            }
            else if(target > array[row][col]) {
                col ++;
            }
            else{
                row --;
            }
        }
        return false;
    }
}
