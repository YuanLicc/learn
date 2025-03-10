package com.yl.learn.algorithm.queue;

import com.yl.learn.util.util.PrintUtil;
import com.yl.learn.algorithm.test.TestSuper;
import org.junit.Test;

import java.util.Arrays;

public class MinArrayWithQueueTest extends TestSuper {

    int[] caseA = {1, 5, 3, 4, 8, 7, 3};

    @Test
    public void test() {
        PrintUtil.template("Use queue find min eles in every windows of array:\n"
                + Arrays.toString(caseA), () -> {
            PrintUtil.println(Arrays.toString(MinArrayWithQueue.min(caseA, 3)));
        });
    }

}
