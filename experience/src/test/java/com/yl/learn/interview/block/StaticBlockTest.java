package com.yl.learn.interview.block;

import com.yl.learn.util.util.PrintUtil;
import junit.framework.TestCase;

public class StaticBlockTest extends TestCase {

    public void testStaticBlock() {

        PrintUtil.printlnLine();
        PrintUtil.println("Init first object:");
        StaticBlock staticBlock = new StaticBlock();
        PrintUtil.println("Init second object:");
        StaticBlock staticBlock1 = new StaticBlock();

        String a = "111";
        String b = "111";
        String c = new String("111");

        PrintUtil.println(a == b);
        PrintUtil.println(a == c);
        PrintUtil.println(b == c);
        PrintUtil.println(a == "111");
    }

}
