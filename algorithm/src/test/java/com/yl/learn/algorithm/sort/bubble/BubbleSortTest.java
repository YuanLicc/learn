package com.yl.learn.algorithm.sort.bubble;

import com.yl.learn.algorithm.test.TestSuper;
import com.yl.learn.util.util.PrintUtil;
import org.junit.Test;

import java.util.Arrays;

public class BubbleSortTest extends TestSuper {

    int[] ca = {1, 22, 33, 2, 5, 6, 1};

    @Test
    public void test() {
        PrintUtil.template("Bubble sort: ", () -> {
            PrintUtil.println(Arrays.toString(ca), "Sorted array is: \n\t", "");

            BubbleSort.sort(ca);
            PrintUtil.println(Arrays.toString(ca), "Result: \n\t", "");
        });
    }
}
