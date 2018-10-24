package producerAndcomsumer.pc3;

import java.text.MessageFormat;
import java.util.LinkedList;

public class Consumer implements Runnable {

    private LinkedList<PCData> list;
    private volatile boolean isConsumer = true;

    private final int SLEEP_TIME = 500;

    public Consumer(LinkedList<PCData> list) {
        this.list = list;
    }

    @Override
    public void run() {
        try {
            while (isConsumer) {
                synchronized (list) {
                    while (list.size() == 0 && !Thread.interrupted()) {
                        System.out.println("consumer waiting:" + Thread.currentThread().getId());
                        list.wait();
                    }
                    System.out.println("consumer " + Thread.currentThread().getId() + " is consuming");
                    PCData data = list.poll();
                    int res = data.getDATA() * data.getDATA();
                    System.out.println(MessageFormat.
                            format("{0}*{1}={2}", data.getDATA(), data.getDATA(), res));
                    Thread.sleep((long) (Math.random() * SLEEP_TIME));
                    list.notifyAll();
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }


    public void stop() {
        while (list.size() != 0) {
        }
        isConsumer = false;
        //Thread.currentThread().interrupt();
    }
}
