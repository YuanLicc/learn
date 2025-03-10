package com.yl.learn.algorithm.offer.array;

import com.yl.learn.algorithm.test.TestSuper;
import com.yl.learn.util.util.PrintUtil;
import org.junit.Test;

import java.util.Arrays;

public class ReverseWordsTest extends TestSuper {

    @Test
    public void test() {

        char[] arr = "I love studying.".toCharArray();

        PrintUtil.template("Reverse words:\n" + Arrays.toString(arr), () -> {

            ReverseWords.reverseWords(arr);

            PrintUtil.println(Arrays.toString(arr));

        });

    }

}
