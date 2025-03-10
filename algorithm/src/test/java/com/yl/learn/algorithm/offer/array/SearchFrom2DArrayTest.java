package com.yl.learn.algorithm.offer.array;

import com.yl.learn.util.util.PrintUtil;
import com.yl.learn.algorithm.test.TestSuper;
import org.junit.Test;

import java.util.Arrays;

public class SearchFrom2DArrayTest extends TestSuper {

    private static int[][] cell = {
            {1, 2, 8, 9},
            {2, 4, 9, 12},
            {4, 7, 10, 13},
            {6, 8, 11, 15}
    };

    private static int aim = 9;

    @Test
    public void test() {
        PrintUtil.template("Search " + aim + " from the array:", () -> {
            Arrays.asList(cell).stream().forEach((item) -> {
                PrintUtil.println(Arrays.toString(item));
            });

            PrintUtil.println(SearchFrom2DArray.search(cell, aim), "Result: ", "");
        });
    }

}
