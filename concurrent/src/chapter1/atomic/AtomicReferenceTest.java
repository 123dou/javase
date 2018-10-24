package chapter1.atomic;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceTest {
    public final static AtomicReference<String> ATOMIC_REFERENCE = new AtomicReference<>("abc");
    private final static Random RANDOM = new Random();

    public static void main(String[] args) throws InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        Thread[] threads = new Thread[20];
        for (int i = 0; i < 20; i++) {
            final int num = i;
            threads[i] = new Thread() {
                public void run () {
                    String oldValue = ATOMIC_REFERENCE.get();
                    try {
                        countDownLatch.await();
                        Thread.sleep(RANDOM.nextInt() & 500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (ATOMIC_REFERENCE.compareAndSet(oldValue, oldValue + num))
                        System.out.println("线程: " + num + ", 进行了对像的修改");
                }
            };
            threads[i].start();
        }
        Thread.sleep(200);
        countDownLatch.countDown();
        for (Thread t : threads)
            t.join();
        System.out.println("最终结果为:" + ATOMIC_REFERENCE.get());
    }
}
