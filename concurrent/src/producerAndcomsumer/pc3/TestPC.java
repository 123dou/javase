package producerAndcomsumer.pc3;

import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestPC {
    public static void main(String[] args) throws InterruptedException {
        LinkedList<PCData> list = new LinkedList<>();

        Producer p1 = new Producer(list);
        Producer p2 = new Producer(list);
        Producer p3 = new Producer(list);
        Consumer c1 = new Consumer(list);
        Consumer c2 = new Consumer(list);
        Consumer c3 = new Consumer(list);

        ExecutorService service = Executors.newCachedThreadPool();

        service.execute(p1);
        service.execute(p2);
        service.execute(p3);
        service.execute(c1);
        service.execute(c2);
        service.execute(c3);

        Thread.sleep(3000);

        p1.stop();
        p2.stop();
        p3.stop();
        Thread.sleep(3000);
        c1.stop();
        c2.stop();
        c3.stop();

        Thread.sleep(1000);
        service.shutdown();
    }
}
