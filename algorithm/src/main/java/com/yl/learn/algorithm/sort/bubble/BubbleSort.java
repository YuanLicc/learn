package com.yl.learn.algorithm.sort.bubble;

/**
 * 1. 比较相邻的元素。如果第一个比第二个大，就交换它们两个；
 * 2. 对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对，这样在最后的元素应该会是最大的数；
 * 3. 针对所有的元素重复以上的步骤，除了最后一个；
 * 4. 重复步骤1~3，直到排序完成。
 */
public class BubbleSort {

    public static void sort(int[] sorted) {

        for(int i = 0; i < sorted.length - 1; i++) {

            for(int j = 0; j < sorted.length - 1 - i; j++) {
                if(sorted[j] > sorted[j + 1]) {
                    sorted[j] = sorted[j] ^ sorted[j + 1];
                    sorted[j + 1] = sorted[j] ^ sorted[j + 1];
                    sorted[j] = sorted[j] ^ sorted[j + 1];
                }
            }
        }

    }

}
