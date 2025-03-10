package com.yl.learn.algorithm.offer.str;

import com.yl.learn.util.util.PrintUtil;
import com.yl.learn.algorithm.test.TestSuper;
import org.junit.Test;

public class SplitStringTest extends TestSuper {

    StringBuffer stringBuffer = new StringBuffer("We Are Happy ");

    @Test
    public void test() {
        PrintUtil.template("Split space to '%20', the str is:\n\t" + stringBuffer.toString(), () -> {
            SplitString.split(stringBuffer);

            PrintUtil.println(stringBuffer.toString() , "Result:\n\t", "");
        });
    }

}
