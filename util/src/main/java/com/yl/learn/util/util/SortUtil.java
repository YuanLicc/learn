package com.yl.learn.util.util;

import junit.framework.TestCase;

import java.util.ArrayList;

public class SortUtil extends TestCase {
    
    /**
     * O(nlogn) - O(n^2)
     * @param array 给定待排序数组
     * @param <T> 数组元素类型
     */
    public static <T extends Comparable<T>> void quickSort(T[] array) {
        quickSort(array, 0, array.length - 1);
    }
    
    private static <T extends Comparable<T>> void quickSort(T[] array, int start, int end) {
        if(start < end) {
            int startIndex = start;
            int endIndex = end;
            T tmp = array[start];
            // 12
            // 12 1 21 4 8
            while(startIndex != endIndex) {
                
                while(startIndex < endIndex) {
                    if(array[endIndex].compareTo(tmp) <= 0) {
                        array[startIndex++] = array[endIndex];
                        break;
                    }
                    endIndex--;
                }
                
                while(startIndex < endIndex) {
                    if(array[startIndex].compareTo(tmp) > 0) {
                        array[endIndex--] = array[startIndex];
                    }
                    startIndex++;
                }
            }
            
            array[startIndex] = tmp;
            
            quickSort(array, start, startIndex);
            quickSort(array, startIndex + 1, end);
        }
    }
    
    /**
     * O(n^2)
     * @param array
     * @param <T>
     */
    public static <T extends Comparable<T>> void selectSort(T[] array) {
        if(array == null || array.length <= 1) return;
        
        int index = 0;
        while(index < array.length) {
            T min = array[index];
            int minIndex = index;
            for(int i = index + 1; i < array.length; i++) {
                if(array[i].compareTo(min) < 0) {
                    minIndex = i;
                    min = array[i];
                }
            }
            array[minIndex] = array[index];
            array[index] = min;
            index++;
        }
    }
    
    /**
     * 插入排序 O(n^2)
     * @param array
     * @param <T>
     */
    public static <T extends Comparable<T>> void insertSort(T[] array) {
        if(array == null || array.length <= 1) return;

        for(int i = 1; i < array.length; i++) {
            for(int j = i - 1; j >= 0; j--) {
                T cur = array[j];
                if(array[j + 1].compareTo(cur) < 0) {
                    T tmp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = tmp;
                }
            }
        }
    }
    
    /**
     * 冒泡排序 O(n^2)
     * @param array
     * @param <T>
     */
    public static <T extends Comparable<T>> void bubbleSort(T[] array) {
        if(array == null || array.length <= 1) return;

        for(int i = 0; i < array.length; i++) {
            for(int j = i + 1; j < array.length; j++) {
                // (n - 1) + (n - 2) + ... + 1 = (n - 1) * n / 2
                if(array[j].compareTo(array[j - 1]) < 0) {
                    T tmp = array[j];
                    array[j] = array[j - 1];
                    array[j - 1] = tmp;
                }
            }
        }
    }
    
    /**
     * 归并排序 O(nlogn)
     * @param array
     * @param <T>
     */
    public static <T extends Comparable<T>> void mergeSort(T[] array) {
        if(array == null || array.length <= 1) return;
        mergeSort(array, 0, array.length - 1);
    }

    private static <T extends Comparable<T>> void mergeSort(T[] array, int start, int end) {
        int minus = end - start;
        if(minus > 1) {
            int middle = (start + end) >> 1;
            mergeSort(array, start, middle);
            mergeSort(array, middle + 1, end);

            merge(array, start, middle, end);
        }

        if(minus == 1 && array[start].compareTo(array[end]) > 0) {
            T tmp = array[start];
            array[start] = array[end];
            array[end] = tmp;
        }

    }

    private static <T extends Comparable<T>> void merge(T[] array, int leftStart, int leftEnd, int rightEnd) {
        int leftIndex = leftStart;
        int rightIndex = leftEnd + 1;
    
        ArrayList<T> tmpList = new ArrayList<>(rightEnd - leftStart + 1);
        
        while (leftIndex <= leftEnd && rightIndex <= rightEnd) {
            if(array[leftIndex].compareTo(array[rightIndex]) < 0) {
                tmpList.add(array[leftIndex++]);
            }
            else {
                tmpList.add(array[rightIndex++]);
            }
        }
        
        while(leftIndex <= leftEnd) {
            tmpList.add(array[leftIndex++]);
        }
        
        while (rightIndex <= rightEnd) {
            tmpList.add(array[rightIndex++]);
        }
        
        for(int i = leftStart; i <= rightEnd; i++) {
            array[i] = tmpList.get(i - leftStart);
        }
    }
    
    public void testQuickSort() {
        Integer[] arr = new Integer[]{1, -1, -2, 10, 11, 33};
        PrintUtil.printlnArray(arr);
        quickSort(arr);
        PrintUtil.printlnArray(arr);
    }
    
    public void testMergeSort() {
        Integer[] arr = new Integer[]{1, -1, -2, 10, 11, 33};
        PrintUtil.printlnArray(arr);
        mergeSort(arr);
        PrintUtil.printlnArray(arr);
    }

    public void testBubbleSort() {
        Integer[] arr = new Integer[]{1, -1, 20, 10, 11, 33};
        bubbleSort(arr);
        PrintUtil.printlnArray(arr);
    }

    public void testInsertSort() {
        Integer[] arr = new Integer[]{1, -1, -2, 10, 11, 33};
        insertSort(arr);
        PrintUtil.printlnArray(arr);
    }
    
    public void testSelectSort() {
        Integer[] arr = new Integer[]{1, -1, 20, 10, 11, 33};
        selectSort(arr);
        PrintUtil.printlnArray(arr);
    }
    
}
