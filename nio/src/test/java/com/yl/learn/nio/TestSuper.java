package com.yl.learn.nio;

import com.yl.learn.util.util.PrintUtil;
import org.junit.After;
import org.junit.Before;

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
