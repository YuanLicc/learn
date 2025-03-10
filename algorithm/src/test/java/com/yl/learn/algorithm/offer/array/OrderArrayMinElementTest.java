package com.yl.learn.algorithm.offer.array;

import com.yl.learn.algorithm.test.TestSuper;
import com.yl.learn.util.util.PrintUtil;
import org.junit.Test;

import java.util.Arrays;

public class OrderArrayMinElementTest extends TestSuper {

    @Test
    public void test() {

        int[] arr = {6, 7, 1, 2, 3, 4, 5};

        PrintUtil.template("Find the min element, The array is reverse：\n" + Arrays.toString(arr), () -> {

            PrintUtil.println(OrderArrayMinElement.min(arr));

        });
    }

}
