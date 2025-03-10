package com.yl.learn.algorithm.sort.insert;

import com.yl.learn.util.util.PrintUtil;
import com.yl.learn.algorithm.sort.select.SelectSort;
import com.yl.learn.algorithm.test.TestSuper;
import org.junit.Test;

import java.util.Arrays;

public class InsertSortTest extends TestSuper {

    int[] input = {0, 22, 11, 22, 33, 6, 3, 3};

    @Test
    public void test() {
        PrintUtil.template("Insert sort -> " + Arrays.toString(input), () -> {
            InsertSort.sort(input);
            PrintUtil.println(Arrays.toString(input), "Result -> ", "");
        });
    }

}
