package com.yl.learn.interview.algorithm.util;

import com.yl.learn.util.util.PrintUtil;
import com.yl.learn.util.util.StringUtil;
import com.yl.learn.util.util.Timer;
import junit.framework.TestCase;

public class PrintUtilTest extends TestCase {

    public void testPrintLine() {

        String prefix = "start", suffix = "end";
        int column = 100, row = 2;

        Timer timer = Timer.start();

        PrintUtil.printLine(prefix, suffix);
        PrintUtil.println();

        PrintUtil.printLine(column, row);
        PrintUtil.println();

        PrintUtil.printLine(column, prefix, suffix);
        PrintUtil.println();

        PrintUtil.printLine();
        PrintUtil.println();

        PrintUtil.printLine(column);
        PrintUtil.println();

        PrintUtil.printlnLine(prefix, suffix);
        PrintUtil.println();

        PrintUtil.printlnLine(column, row);
        PrintUtil.println();

        PrintUtil.printlnLine(column, prefix, suffix);
        PrintUtil.println();

        PrintUtil.printlnLine();
        PrintUtil.println();

        PrintUtil.printlnLine(column);
        PrintUtil.println();

        PrintUtil.printLine(column, row, prefix, suffix);
        PrintUtil.println();

        PrintUtil.printLine(-1, -1, prefix, suffix);
        PrintUtil.println();

        PrintUtil.printLine(column, -1, prefix, suffix);
        PrintUtil.println();

        PrintUtil.printLine(-1, row, prefix, suffix);
        PrintUtil.println();

        PrintUtil.printLine(column, row, null, null);
        PrintUtil.println();

        PrintUtil.printlnLine(column);
        PrintUtil.println();

        PrintUtil.printlnLine(column, 2, prefix, suffix);
        PrintUtil.println();

        PrintUtil.print(prefix, prefix, suffix);
        PrintUtil.println();

        PrintUtil.print(null, prefix, suffix);
        PrintUtil.println();

        PrintUtil.printWithNaoTime(prefix, timer);
        PrintUtil.println();

        PrintUtil.printWithNaoTime(prefix, null);
        PrintUtil.println();

        PrintUtil.printWithNaoTime(prefix, timer, prefix);
        PrintUtil.println();

        PrintUtil.printWithNaoTime(prefix, timer, prefix, prefix, prefix);
        PrintUtil.println();

        PrintUtil.printlnWithNaoTime(prefix, timer);
        PrintUtil.println();

        PrintUtil.println(prefix, prefix, suffix);
        PrintUtil.println();

        PrintUtil.printlnWithNaoTime(prefix, suffix, timer);
        PrintUtil.println();

        PrintUtil.println(StringUtil.newString('a', -1), prefix, suffix);
        PrintUtil.println();

    }
}
