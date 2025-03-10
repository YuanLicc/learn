package com.yl.learn.algorithm.offer.array;

import com.yl.learn.algorithm.test.TestSuper;
import com.yl.learn.util.util.PrintUtil;
import org.junit.Test;

import java.util.Arrays;

public class ComposeArrayTest extends TestSuper {

    int[] a1 = {1,4,6,8};
    int[] a2 = {2,4,6,9, 10};

    @Test
    public void test() {
        PrintUtil.template("Compose these array which sorted: ", () -> {

            PrintUtil.println(Arrays.toString(a1), "Array one:\n\t", "");
            PrintUtil.println(Arrays.toString(a2), "Array two:\n\t", "");

            PrintUtil.println(Arrays.toString(ComposeArray.compose(a1, a2)), "Result:\n\t", "");
        });
    }

}
