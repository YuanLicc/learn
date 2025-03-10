package com.yl.learn.algorithm.zuo;

import com.yl.learn.util.util.PrintUtil;
import com.yl.learn.algorithm.test.TestSuper;
import org.junit.Test;

import java.util.Arrays;

public class LongestTest extends TestSuper {

    int[] casea = {1, 3, 23, 32, 12, 3, 5, 90,2, 3};

    @Test
    public void test() {

        PrintUtil.template("Longest increasing subsequence: \n" + Arrays.toString(casea), () -> {

            PrintUtil.println(Arrays.toString(LongestIncreasingSubSequence.longest(casea)));
        });
    }

}
