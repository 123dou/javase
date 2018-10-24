package chapter1.atomic;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomiIntegerTest {
    public final static AtomicInteger ATOMIC_INTEGER = new AtomicInteger(1);
    private static volatile int index = 1;

    public static void main(String[] args) throws InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread() {
                public void run() {
                    try {
                        countDownLatch.await();
                        for (int j = 0; j < 100; j++) {
                            index++;
                            ATOMIC_INTEGER.incrementAndGet();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            threads[i].start();
        }
        Thread.sleep(1000);
        countDownLatch.countDown();
        for (Thread t : threads) {
            t.join();
        }
        System.out.println("atomi的最终运行结果:" + ATOMIC_INTEGER.get());
        System.out.println("正常不加锁变量的运行结果:" + index);
    }
}
