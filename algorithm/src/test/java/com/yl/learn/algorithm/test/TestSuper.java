package com.yl.learn.algorithm.test;

import com.yl.learn.util.util.PrintUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

public class TestSuper {

    private long time;

    private long mTime;

    @Before
    public void testStart() {
        time = System.nanoTime();
        mTime = System.currentTimeMillis();
    }

    @After
    public void testEnd() {
        PrintUtil.println(System.nanoTime() - time, "Elapsed Time: ", " ns");
        PrintUtil.println(System.currentTimeMillis() - mTime, "Elapsed Time: ", " ms");
    }

}
