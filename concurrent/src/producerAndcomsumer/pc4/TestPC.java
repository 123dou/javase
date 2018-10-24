package producerAndcomsumer.pc4;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TestPC {
    public static void main(String[] args) throws InterruptedException {
        LinkedList<Integer> list = new LinkedList<>();
        Resource resource = new Resource(list, 10);
        Producer p1 = new Producer(resource);
        Producer p2 = new Producer(resource);
        Producer p3 = new Producer(resource);
        Consumer c1 = new Consumer(resource);
        Consumer c2 = new Consumer(resource);
        Consumer c3 = new Consumer(resource);

        List<Thread> ts = new ArrayList<>();


        Thread t1 = new Thread(p1);
        Thread t2 = new Thread(p2);
        Thread t3 = new Thread(p3);
        Thread t4 = new Thread(c1);
        Thread t5 = new Thread(c2);
        Thread t6 = new Thread(c3);

        ts.add(t1);
        ts.add(t2);
        ts.add(t3);
        ts.add(t4);
        ts.add(t5);
        ts.add(t6);

        ts.forEach(thread -> thread.start());

        Thread.sleep(1000);

//        p1.stop();
//        p2.stop();
//        p3.stop();
//        c1.stop();
//        c2.stop();
//        c3.stop();



    }
}
