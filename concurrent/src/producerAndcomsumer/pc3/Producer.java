package producerAndcomsumer.pc3;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

public class Producer implements Runnable {
    //共享缓冲区
    private LinkedList<PCData> list;
    //是否还要继续生产
    private volatile boolean isProducer = true;
    private static AtomicInteger count = new AtomicInteger();
    private static volatile int size = 10;

    private final int SLEEP_TIME = 500;

    public Producer(LinkedList<PCData> list) {
        this.list = list;
    }

    @Override
    public void run() {
        PCData data;
        try {
            while (isProducer) {
                synchronized (list) {
                    Thread.sleep((long) (Math.random() * SLEEP_TIME));
                    while (list.size() == size) {
                        System.out.println("list is full--" + Thread.currentThread().getId() + ": waiting");
                        list.wait();
                    }
                    int n = count.incrementAndGet();
                    System.out.println("thread starts producer--id: "
                            + Thread.currentThread().getId() + " produce " + n);
                    data = new PCData(n);
                    list.add(data);
                    list.notifyAll();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }


    public void stop() {
        isProducer = false;
    }
}