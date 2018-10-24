package producerAndcomsumer.pc2;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Producer implements Runnable {
    //内存缓冲区
    private BlockingQueue<PCData> blockingQueue;
    private volatile boolean isRunning = true;

    private static AtomicInteger count = new AtomicInteger();

    private final int SLEEP_TIME = 500;

    public Producer(BlockingQueue<PCData> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        PCData data;
        Random random = new Random();
        System.out.println("start producer id = " + Thread.currentThread().getId());
        try {
            while (isRunning) {
                Thread.sleep(random.nextInt(SLEEP_TIME));
                data = new PCData(count.incrementAndGet());
                System.out.println("data is put into queue");
                if (!blockingQueue.offer(data, 2, TimeUnit.SECONDS)){
                    System.out.println("fail to put data" + data);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

    public void stop() {
        isRunning = false;
    }
}
