package com.yl.learn.algorithm.offer.array;

/**
 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
 * 输入一个非减排序的数组的一个旋转，输出旋转数组的最小元素。
 * 例如数组 {3,4,5,1,2} 为 {1,2,3,4,5} 的一个旋转，该数组的最小值为 1。
 * NOTE：给出的所有元素都大于 0，若数组大小为 0，请返回 0。
 */
public class OrderArrayMinElement {

    public static int min(int[] array) {
        if(array.length == 0) {
            return 0;
        }

        int leftIndex = 0;
        int rightIndex = array.length - 1;
        int middleIndex = leftIndex;

        while (array[leftIndex] >= array[rightIndex]) {

            if(rightIndex - leftIndex == 1) {
                middleIndex = rightIndex;
                break;
            }

            middleIndex = (leftIndex + rightIndex) >> 1;

            if(array[middleIndex] >= array[leftIndex]) {
                leftIndex = middleIndex;
            }
            else {
                rightIndex = middleIndex;
            }
        }
        return array[middleIndex];
    }

}
