package com.yl.learn.algorithm.queue;

import java.util.LinkedList;

public class MinArrayWithQueue {

    public static int[] min(int[] array, int windowsLength) {

        LinkedList<Integer> qMax = new LinkedList<>();
        int[] res = new int[array.length - windowsLength + 1];
        int index = 0;

        for (int i = 0; i < array.length; i++) {

            while (!qMax.isEmpty() && array[qMax.peekLast()] > array[i]) {
                qMax.pollLast();
            }

            qMax.addLast(i);
            if (qMax.peekFirst() == i - windowsLength) {
                qMax.pollFirst();
            }
            if (i >= windowsLength - 1) {
                res[index++] = array[qMax.peekFirst()];
            }

        }
        return res;
    }

}
