package com.yl.learn.interview.algorithm.array;

import com.yl.learn.common.util.PrintUtil;
import com.yl.learn.common.util.Timer;
import junit.framework.TestCase;

public class AlgorithmArrayTest extends TestCase {

    String delimiter = ", 使用时间：";
    String prefix = "结果：";
    String suffix = " 纳秒";

    public void testOne() {
        int[][] oneCaseOne = {{1,2,8,9},{2,4,9,12}, {4,7,10,13},{6,8,11,15}};
        int[][] oneCaseTwo = {{1, 2}, {2, 4}, {4, 7}};
        int[][] oneCaseThree = {{1}};

        Timer oneTimer = Timer.start();
        PrintUtil.printlnWithNaoTime(One.find(7, oneCaseOne)
                , oneTimer, prefix, delimiter, suffix);

        oneTimer.reset();
        PrintUtil.printlnWithNaoTime(One.find(15, oneCaseOne)
                , oneTimer, prefix, delimiter, suffix);

        // 解决
        oneTimer.reset();
        PrintUtil.printlnWithNaoTime(One.solution(7, oneCaseOne)
                , oneTimer, prefix, delimiter, suffix);

        oneTimer.reset();
        PrintUtil.printlnWithNaoTime(One.solution(4, oneCaseOne)
                , oneTimer, prefix, delimiter, suffix);

        oneTimer.reset();
        PrintUtil.printlnWithNaoTime(One.solution(7, null)
                , oneTimer, prefix, delimiter, suffix);

        oneTimer.reset();
        PrintUtil.printlnWithNaoTime(One.solution(15, oneCaseOne)
                , oneTimer, prefix, delimiter, suffix);

        PrintUtil.printlnLine();
        PrintUtil.println(One.find(7, oneCaseOne));

        PrintUtil.printlnLine();
        PrintUtil.println(One.find(7, null));

        PrintUtil.printlnLine();
        PrintUtil.println(One.find(6, oneCaseOne));

        PrintUtil.printlnLine();
        PrintUtil.println(One.find(6, oneCaseTwo));

        PrintUtil.printlnLine();
        PrintUtil.println(One.find(6, oneCaseThree));

        PrintUtil.printlnLine();
        PrintUtil.println(One.find(0, oneCaseTwo));

    }

    public void testTwo() {
        Timer timer = Timer.start();

        PrintUtil.printlnWithNaoTime(Two.replaceStr(new StringBuffer("We Are "), "%20")
                , timer, prefix, delimiter, suffix);

        timer.reset();
        PrintUtil.printlnWithNaoTime(Two.replaceStr(null, "%20")
                , timer, prefix, delimiter, suffix);

        timer.reset();
        PrintUtil.printlnWithNaoTime(Two.replaceSpace(new StringBuffer("We Are "), "%20")
                , timer, prefix, delimiter, suffix);

        timer.reset();
        PrintUtil.printlnWithNaoTime(Two.replaceSpace(null, "%20")
                , timer, prefix, delimiter, suffix);

        timer.reset();
        PrintUtil.printlnWithNaoTime(Two.solution(new StringBuffer("We Are "))
                , timer, prefix, delimiter, suffix);

        timer.reset();
        PrintUtil.printlnWithNaoTime(Two.solution(new StringBuffer("We Are "))
                , null, prefix, delimiter, suffix);

    }

    public void testPlusPlus() {
        int count = 0;
        int total = 0;
        for(int i = 0; i < 10; i++) {
            total = count++;
            PrintUtil.print(count);
        }
        PrintUtil.print(total);
    }
}
