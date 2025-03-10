package com.yl.learn.algorithm.search.binary;

import com.yl.learn.util.util.PrintUtil;
import com.yl.learn.algorithm.test.TestSuper;
import org.junit.Test;

import java.util.Arrays;

public class BinarySearchTest extends TestSuper {

    int input[] = {0, 1, 4, 12, 18, 88, 199, 700, 1000};

    @Test
    public void testBinarySearch() {
        int aim = 18;
        test(input, aim);
    }

    @Test
    public void testError() {
        int aim = 100000;

        test(input, aim);
    }

    @Test
    public void testNon() {
        int aim = -10;

        test(input, aim);
    }

    @Test
    public void testNull() {
        int aim = -10;

        test(null, aim);
    }

    private void test(int[] input, int aim) {
        PrintUtil.template(Arrays.toString(input) + "\nBinary Search (Recursion) " + aim + ": ", () -> {
            PrintUtil.println(BinarySearch.binarySearch(input, aim),"Result : ", "");
        });
    }

    @Test
    public void testBinarySearchRecursion() {
        int aim = 18;
        testRecursion(input, aim);
    }

    @Test
    public void testErrorRecursion() {
        int aim = 100000;

        testRecursion(input, aim);
    }

    @Test
    public void testNonRecursion() {
        int aim = -10;

        testRecursion(input, aim);
    }

    @Test
    public void testNullRecursion() {
        int aim = -10;

        testRecursion(null, aim);
    }

    private void testRecursion(int[] input, int aim) {
        PrintUtil.template(Arrays.toString(input) + "\nBinary Search(non-recursion) " + aim + ": ", () -> {
            PrintUtil.println(BinarySearch.binary(input, aim),"Result : ", "");
        });
    }
}
