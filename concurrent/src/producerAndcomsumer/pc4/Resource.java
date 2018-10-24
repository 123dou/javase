package producerAndcomsumer.pc4;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

public class Resource {
    //缓冲区大小
    private final int SIZE;
    //缓冲区
    private LinkedList<Integer> list;
    //生产的产品
    private static AtomicInteger res = new AtomicInteger();

    private final int SLEEP_TIME = 500;

    public Resource(LinkedList<Integer> list, int size) {
        this.list = list;
        this.SIZE = size;
    }

    public void add() {
        try {
            synchronized (this) {
                while (list.size() == SIZE) {
                    System.out.println("full,thread-" + Thread.currentThread().getId() + "+ waiting");
                    wait();
                }
                int p = res.incrementAndGet();
                list.add(p);
                System.out.println("thread-" + Thread.currentThread().getId() + ": produce-" + p);
                notifyAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

    public void remove() throws InterruptedException {
        try {
            synchronized (this) {
                while (list.size() == 0) {
                    System.out.println("Empty:thread-" + Thread.currentThread().getId() + ":waiting");
                    wait();
                }
                int n = list.poll();
                System.out.println("thread-" + Thread.currentThread().getId()
                        + ": consumer-" + n + ",rest: " + (SIZE - list.size()));
                notifyAll();
            }
        } catch (InterruptedException e) {
            throw new InterruptedException();
        }
    }

    public void stopRemove() {
        while (list.size() != 0);
        Thread.currentThread().interrupt();
    }

}
