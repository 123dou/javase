package chapter1.atomic;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

public class AtomiReferenceABATest {
    public final static AtomicReference<String> ATOMIC_REFERENCE = new AtomicReference<>("abc");
    private static Random random = new Random();

    public static void main(String[] args) throws InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        Thread[] threads = new Thread[20];
        for (int i = 0; i < 20; i++) {
            final int num = i;
            final String oleVale = ATOMIC_REFERENCE.get();
            threads[i] = new Thread(){
                public void run() {
                    try {
                        countDownLatch.await();
                        Thread.sleep(random.nextInt() & 500);
                        if (ATOMIC_REFERENCE.compareAndSet(oleVale, oleVale + num)) {
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
                    while (!ATOMIC_REFERENCE.compareAndSet(ATOMIC_REFERENCE.get(), "abc"));
                    System.out.println("我修改为原值了");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
