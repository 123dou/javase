package clhAndMsc;

import org.junit.Test;

public class TestCLHLock {
    private int i = 0;
    private int j = 0;

    public static void main(String[] args) throws InterruptedException {
        TestCLHLock testCLHLock = new TestCLHLock();
        testCLHLock.testMultiplyThread();
        Thread.sleep(2000);
        System.out.println(testCLHLock.i + " " + testCLHLock.j);
    }


    @Test
    public void testMultiplyThread() {
        for (int i = 0; i < 10; i++) {
            new Thread() {
                @Override
                public void run() {
                    for (int j = 0; j < 100; j++) {
                        inc();
                        incLock();
                    }
                }
            }.start();
        }
    }

    private void inc() {
        i++;
    }
    private CLHLock clhLock = new CLHLock();
    private void incLock() {
        clhLock.lock();
       try {
           j++;
       } finally {
           clhLock.unlock();
       }
    }
}
