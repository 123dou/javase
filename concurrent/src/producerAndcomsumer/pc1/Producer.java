package producerAndcomsumer.pc1;

import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {
    private final BlockingQueue blockingQueue;

    private final int QUEUE_SIZE = 10;

    public Producer(BlockingQueue blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    int task = 1;

    @Override
    public void run() {
        while (true) {
            try {
                System.out.println("正在生产:" + task);
                blockingQueue.put(task);
                ++task;
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
