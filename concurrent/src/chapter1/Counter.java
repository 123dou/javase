package chapter1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 创建多个线程来对一个共享变量进行加1操作
 */
public class Counter {
    private AtomicInteger atomici = new AtomicInteger(0);
    private int i = 0;

    public static void main(String[] args) {
        final Counter cas = new Counter();
        List<Thread> ts = new ArrayList<>(600);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 1000; j++) {
                        cas.count();
                        cas.safeCount();
                    }
                }
            });
            ts.add(thread);
        }
        ts.forEach(t -> t.start());
        ts.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(cas.i);
        System.out.println(cas.atomici.get());
        System.out.println(System.currentTimeMillis() - start);
    }

    private void safeCount() {
        while (true) {
            int i = atomici.get();
            if (atomici.compareAndSet(i, ++i))
                break;
        }
}

    private void count() {
        i++;
    }
}