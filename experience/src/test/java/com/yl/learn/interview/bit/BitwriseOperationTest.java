package com.yl.learn.interview.bit;

import com.yl.learn.common.util.PrintUtil;
import junit.framework.TestCase;

public class BitwriseOperationTest extends TestCase {

    public void testBitwiseOperation() {

        int num = 10;
        int moveCount = 2;

        PrintUtil.printlnLine();

        PrintUtil.println(BitwiseOperation.leftMove(num, moveCount), "左移位操作：num : " + num + " ", "");

        PrintUtil.printlnLine();

        PrintUtil.println(BitwiseOperation.rightMove(num, moveCount), "右移位操作：num : " + num + " ", "");

    }

}
