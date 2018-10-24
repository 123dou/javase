package chapter1.atomic;

import java.util.concurrent.atomic.AtomicIntegerArray;

public class AtomiIntegerArrayTest {
    private final static AtomicIntegerArray ATOMIC_INTEGER_ARRAY = new AtomicIntegerArray(10);

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[100];
        for (int i = 0; i < 100; i++) {
            final int index = i % 10;
            final int threadName = i;
            threads[i] = new Thread(){
                public void run () {
                    int result = ATOMIC_INTEGER_ARRAY.addAndGet(index, index + 1);
                    System.out.println("线程编号为:" + threadName + ", 对应的原始值为:" + (index + 1) + ", 增加后的结果为:" +
                    result);
                }
            };
            threads[i].start();
        }
        for (Thread t : threads)
            t.join();
        System.out.println("----------------------->执行完成.结果列表为:");
        for (int i = 0; i < ATOMIC_INTEGER_ARRAY.length(); i++) {
            System.out.println(ATOMIC_INTEGER_ARRAY.get(i));
        }

    }
}
