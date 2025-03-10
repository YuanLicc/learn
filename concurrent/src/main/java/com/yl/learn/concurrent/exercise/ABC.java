package com.yl.learn.concurrent.exercise;

public class ABC {

    public static void main(String[] args) throws InterruptedException{
        Object aLock = new Object();
        Object bLock = new Object();
        Object cLock = new Object();

        Thread thread1 = new Thread(new ABC.Printer(cLock, aLock, "A"));
        Thread thread2 = new Thread(new ABC.Printer(aLock, bLock, "B"));
        Thread thread3 = new Thread(new ABC.Printer(bLock, cLock, "C"));
        thread1.start();
        thread2.start();
        thread3.start();

    }


    static class Printer implements Runnable {

        private Object preLock;

        private Object selfLock;

        private String print;

        public Printer(Object preLock, Object selfLock, String print) {
            this.preLock = preLock;
            this.selfLock = selfLock;
            this.print = print;
        }

        @Override
        public void run() {
            int count = 3;
            while(count > 0) {
                synchronized (preLock) {
                    synchronized (selfLock) {
                        System.out.println(print);
                        count--;

                        selfLock.notifyAll();
                    }

                    try {
                        if(count == 0) {
                            preLock.notifyAll();
                        }
                        else {
                            preLock.wait();
                        }
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
