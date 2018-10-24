package producerAndcomsumer.pc5;


import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Resource {
    //缓冲区大小
    private final int SIZE;
    //缓冲区
    private LinkedList<Integer> list;
    //生产的产品
    private static AtomicInteger res = new AtomicInteger();

    private final int SLEEP_TIME = 500;

    private ReentrantLock lock = new ReentrantLock();

    private Condition notFull = lock.newCondition();
    private Condition notEmpty = lock.newCondition();

    public Resource(LinkedList<Integer> list, int size) {
        this.list = list;
        this.SIZE = size;
    }

    public void add() throws InterruptedException {
        final Lock lock = this.lock;
        lock.lock();
        try {
            while (list.size() == SIZE) {
                System.out.println("full ,thread-" + Thread.currentThread().getId() + " is waiting:");
                notFull.await();
            }
            int p = res.incrementAndGet();
            list.add(p);
            System.out.println("thread-" + Thread.currentThread().getId() + ": produce-" + p);
            notEmpty.signalAll();
        } finally {
            lock.unlock();
        }

    }

    public void remove() throws InterruptedException {
        final Lock lock = this.lock;
        lock.lock();
        try {
                while (list.size() == 0) {
                    System.out.println("Empty:thread-" + Thread.currentThread().getId() + ":waiting");
                    notEmpty.await();
                }
                int n = list.poll();
                System.out.println("thread-" + Thread.currentThread().getId()
                        + ": consumer-" + n + ",rest: " + list.size());
                notFull.signalAll();
            } finally {
            lock.unlock();
        }
    }

    public void stopRemove() {
        while (list.size() != 0) ;
        Thread.currentThread().interrupt();
    }

}
