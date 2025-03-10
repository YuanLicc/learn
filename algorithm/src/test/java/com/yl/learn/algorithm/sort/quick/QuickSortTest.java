package com.yl.learn.algorithm.sort.quick;

import com.yl.learn.util.util.PrintUtil;
import com.yl.learn.algorithm.test.TestSuper;
import org.junit.Test;

import java.util.Arrays;

public class QuickSortTest extends TestSuper {

    int[] input = {0, 22, 11, 22, 33, 6, 3, 19, 33, 8, 6};

    @Test
    public void test() {
        PrintUtil.template("Quick sort -> " + Arrays.toString(input), () -> {
            PrintUtil.println(Arrays.toString(QuickSort.quickSort(input)), "Result -> ", "");
        });
    }

}
