package chapter1.atomic;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

public class AtomiStampReferenceTest {
    public final static AtomicStampedReference<String> ATOMIC_STAMPED_REFERENCE = new AtomicStampedReference<>("abc", 0);
    private static Random random = new Random();

    public static void main(String[] args) throws InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        Thread[] threads = new Thread[20];
        for (int i = 0; i < 20; i++) {
            final int num = i;
            final String oleVale = ATOMIC_STAMPED_REFERENCE.getReference();
            final int stamp = ATOMIC_STAMPED_REFERENCE.getStamp();
            threads[i] = new Thread(){
                public void run() {
                    try {
                        countDownLatch.await();
                        Thread.sleep(random.nextInt() & 500);
                        if (ATOMIC_STAMPED_REFERENCE.compareAndSet(oleVale, oleVale + num, stamp, stamp + 1)) {
                            System.out.println("我是线程-" + num + ", 进行了修改");
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            threads[i].start();
        }
        Thread.sleep(200);
        countDownLatch.countDown();
        new Thread(){
            public void run () {
                try {
                    Thread.sleep(random.nextInt() & 500);
                    int stamp = ATOMIC_STAMPED_REFERENCE.getStamp();
                    while (!ATOMIC_STAMPED_REFERENCE.compareAndSet(ATOMIC_STAMPED_REFERENCE.getReference(), "abc", stamp, stamp + 1));
                    System.out.println("我修改为原值了");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
