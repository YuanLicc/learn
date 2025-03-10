package com.yl.learn.algorithm.sort.merge;

import com.yl.learn.util.util.PrintUtil;
import com.yl.learn.algorithm.test.TestSuper;
import org.junit.Test;

import java.util.Arrays;

public class MergeSortTest extends TestSuper {

    int[] a = {1,34 ,44, 44, 2, 43, 5, 19, 42};

    @Test
    public void test() {
        PrintUtil.template("Merge sort -> " + Arrays.toString(a), () -> {
        MergeSort.sort(a);
            PrintUtil.println(Arrays.toString(a), "Result -> ", "");
        });
    }

}
