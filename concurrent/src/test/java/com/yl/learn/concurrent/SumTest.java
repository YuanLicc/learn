package com.yl.learn.concurrent;

import junit.framework.TestCase;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class SumTest extends TestCase {

    class AA extends Thread {

        private long a = 0;

        private int start;

        private int end;

        private CyclicBarrier cyclicBarrier;

        public AA(int start, int end, CyclicBarrier cyclicBarrier) {
            this.start = start;
            this.end = end;
            this.cyclicBarrier = cyclicBarrier;
        }

        public long get() {
            return this.a;
        }

        public void run() {
            for(int i = start; i <= end; i++) {
                a += i;
            }
            try {
                cyclicBarrier.await();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public void test() throws BrokenBarrierException, InterruptedException {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(11);
        AA[] aas = new AA[10];
        for(int i = 0; i < 10; i++) {
            AA aa = new AA(i * 100 + 1, (i + 1) * 100, cyclicBarrier);
            aas[i] = aa;
            aa.start();
        }
        cyclicBarrier.await();
        long sum = 0;
        for(int i = 0; i < 10; i++) {
            sum += aas[i].get();
        }

        System.out.println(sum == (1 + 1000) * 500);
    }

}
