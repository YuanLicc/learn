package com.yl.learn.interview.string;

import com.yl.learn.common.util.ObjectUtil;
import com.yl.learn.common.util.PrintUtil;
import com.yl.learn.common.util.StringUtil;
import com.yl.learn.interview.algorithm.string.AllPermutation;
import com.yl.learn.interview.algorithm.string.Reverse;
import com.yl.learn.interview.algorithm.string.ReverseOrder;
import junit.framework.TestCase;

public class StringTest extends TestCase {

    public void testCodePoint() {
        String s = "哈";

        PrintUtil.println();
        PrintUtil.printlnLine("Code Point", "");

        s.codePoints().boxed().forEach(codePoint -> {
            PrintUtil.println(codePoint, "", " ");
        });
    }

    public void testReverse() {
        String reversed = "abcde";

        PrintUtil.template("String Reverse: ", () -> {
            PrintUtil.println(Reverse.reverse(reversed)
                    , reversed + " ===> ", "");
        });

        PrintUtil.template("String Reverse: ", () -> {
            PrintUtil.println(Reverse.reverse(null)
                    , ObjectUtil.NULL + " ===> ", "");
        });

        PrintUtil.template("String Reverse: ", () -> {
            PrintUtil.println(Reverse.reverse("")
                    , StringUtil.EMPTY + " ===> ", "");
        });
    }

    public void testReverseOrder() {
        String isReverse = "abcba";
        String notReverse = "abcde";

        PrintUtil.template("String Is Reverse Order: ", () -> {
            PrintUtil.println(ReverseOrder.isReverseOrder(isReverse)
                    , isReverse + " ===> ", "");
        });

        PrintUtil.template("String Is Not Reverse Order:", () -> {
            PrintUtil.println(ReverseOrder.isReverseOrder(notReverse)
                    , notReverse + " ===> ", "");
        });

        PrintUtil.template("String Is Reverse Order (Use buffer):", () -> {
            PrintUtil.println(ReverseOrder.isReverseOrder_buffer(isReverse)
                    , isReverse + " ===> ", "");
        });

        PrintUtil.template("String Is Not Reverse Order (Use buffer):", () -> {
            PrintUtil.println(ReverseOrder.isReverseOrder_buffer(notReverse)
                    , notReverse + " ===> ", "");
        });

        PrintUtil.template("String Is Reverse Order: ", () -> {
            PrintUtil.println(ReverseOrder.isReverseOrder(null)
                    , ObjectUtil.NULL + " ===> ", "");
        });

        PrintUtil.template("String Is Not Reverse Order:", () -> {
            PrintUtil.println(ReverseOrder.isReverseOrder("")
                    , "" + " ===> ", "");
        });

        PrintUtil.template("String Is Reverse Order (Use buffer):", () -> {
            PrintUtil.println(ReverseOrder.isReverseOrder_buffer(null)
                    , ObjectUtil.NULL + " ===> ", "");
        });

        PrintUtil.template("String Is Not Reverse Order (Use buffer):", () -> {
            PrintUtil.println(ReverseOrder.isReverseOrder_buffer("")
                    , "" + " ===> ", "");
        });

    }

    public void testAllPermutation() {
        String source = "abc";

        PrintUtil.template("All permutations:", () -> {
            PrintUtil.println(AllPermutation.allPermutations(source)
                    , source + " ===> ", "");
        });

        PrintUtil.template("All permutations:", () -> {
            PrintUtil.println(AllPermutation.allPermutations(null)
                    , ObjectUtil.NULL + " ===> ", "");
        });

    }

    private interface aa{
        abstract void aa();
    }

}
