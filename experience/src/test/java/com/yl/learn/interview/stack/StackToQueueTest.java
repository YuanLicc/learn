package com.yl.learn.interview.stack;

import com.yl.learn.interview.algorithm.stack.StackToQueue;
import com.yl.learn.util.util.PrintUtil;
import junit.framework.TestCase;

public class StackToQueueTest extends TestCase {

    public void testStackToQueue() {
        StackToQueue stackToQueue = new StackToQueue();
        stackToQueue.push(0);
        stackToQueue.push(1);
        PrintUtil.printlnLine();
        PrintUtil.println(stackToQueue.pop());
        PrintUtil.println(stackToQueue.pop());

        StackToQueue stackToQueueEmpty = new StackToQueue();
        stackToQueueEmpty.pop();

        Object o = new Object() {
          public boolean equals(Object o) {
              return true;
          }
        };
        PrintUtil.printlnLine();
        PrintUtil.println(o.equals("c"));
    }

}