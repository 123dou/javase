package producerAndcomsumer.pc2;

import java.text.MessageFormat;
import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {
    private BlockingQueue<PCData> blockingQueue;
    private static final int SLEEP_TIME = 500;
    private volatile boolean isRunning = true;

    public Consumer(BlockingQueue<PCData> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        System.out.println("start Consumer id = " + Thread.currentThread().getId());

        Random random = new Random();

        try {
            while (isRunning) {
                PCData data = blockingQueue.take();
                if (null != data) {
                    int res = data.getDATA() * data.getDATA();
                    System.out.println(MessageFormat.
                            format("{0}*{1}={2}", data.getDATA(), data.getDATA(), res));
                }
                Thread.sleep(random.nextInt(SLEEP_TIME));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

    public void stop() {
        while (!blockingQueue.isEmpty()) ;
        isRunning = false;
    }
}
