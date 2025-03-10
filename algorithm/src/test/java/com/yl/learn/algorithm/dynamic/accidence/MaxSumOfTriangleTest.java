package com.yl.learn.algorithm.dynamic.accidence;

import com.yl.learn.util.util.PrintUtil;
import com.yl.learn.algorithm.test.TestSuper;
import org.junit.Test;

import java.util.Arrays;

public class MaxSumOfTriangleTest extends TestSuper {

    private int[][] caseA = {
            {7},
            {3, 8},
            {8 ,1, 0},
            {2 ,7 ,4 ,4},
            {4 ,5 ,2 ,6 ,5}
    };

    @Test
    public void test() {
        PrintUtil.template("Dynamic plain, Max sum of triangle :", () -> {
            Arrays.asList(caseA).stream().forEach((item) -> {
                PrintUtil.println(Arrays.toString(item));
            });

            PrintUtil.println(MaxSumOfTriangle.maxSum(caseA), "Result:\n", "");
        });
    }

}
