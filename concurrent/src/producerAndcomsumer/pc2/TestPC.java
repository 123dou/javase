package producerAndcomsumer.pc2;

import java.util.concurrent.*;

public class TestPC {
    public static void main(String[] args) throws InterruptedException {
        //建立缓冲区
//        BlockingQueue<PCData> blockingQueue = new ArrayBlockingQueue<>(1);
        BlockingQueue<PCData> blockingQueue = new LinkedBlockingQueue<>(1);
        //建立生产者
        Producer p1 = new Producer(blockingQueue);
        Producer p2 = new Producer(blockingQueue);
        Producer p3 = new Producer(blockingQueue);
        //建立消费者
        Consumer c1 = new Consumer(blockingQueue);
        Consumer c2 = new Consumer(blockingQueue);
        Consumer c3 = new Consumer(blockingQueue);
        //建立线程池
        ExecutorService service = Executors.newCachedThreadPool();

        //运行生产者
        service.execute(p1);
        service.execute(p2);
        service.execute(p2);

        //运行消费者
        service.execute(c1);
        service.execute(c2);
        service.execute(c3);

        Thread.sleep(10 * 100);

        p1.stop();
        p2.stop();
        p3.stop();

        c1.stop();
        c2.stop();
        c3.stop();

        Thread.sleep(3000);
        service.shutdown();
    }
}
