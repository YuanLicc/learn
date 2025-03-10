package com.yl.learn.concurrent.exercise;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class HowToThread {

    // 方法1
    static class ThreadOne implements Runnable {
        @Override
        public void run() {
            System.out.println(1);
        }
    }

    // 方法2
    static class ThreadTwo extends Thread {
        public void run() {
            System.out.println(2);
        }
    }

    static class CallableThread implements Callable<Integer> {

        @Override
        public Integer call() throws Exception {
            return (int)Math.round(Math.random() * 100);
        }
    }

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        executorService.submit(new ThreadOne());
        executorService.submit(new ThreadTwo());
        Future<Integer> fur = executorService.submit(new CallableThread());
        System.out.println(fur.get());
        executorService.shutdown();
    }
}
