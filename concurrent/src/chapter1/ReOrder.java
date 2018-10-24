package chapter1;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class ReOrder {
    private int i = 0;
    private boolean flag = false;
    private static volatile int a = 2;

    public static CyclicBarrier cyclicBarrier = new CyclicBarrier(3, new Runnable() {
        @Override
        public void run() {
            System.out.println("最后:" + a); //重排序可能会使a = 0
            System.out.println("-----------------------------------------------");
        }
    });

    public void init() {
        i = 2;
        flag = true;
        System.out.println("初始化完毕");
    }

    public void read() {
        if (flag) {
            a = i * i;
            System.out.println("读取完毕:" + a + ", i =" + i);
        } else
            System.out.println("读取完毕:" + a + ", i =" + i + ", flag = " + flag);
    }

    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        ReOrder reOrder = new ReOrder();

        //for (int i = 0; i < 100; i++) {
        reOrder.i = 0;
        reOrder.flag = false;
        final CountDownLatch countDownLatch = new CountDownLatch(2);
        new Thread() {
            @Override
            public void run() {
                try {
                    countDownLatch.await();
                    reOrder.init();
                    cyclicBarrier.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }.start();
        new Thread() {
            @Override
            public void run() {
                try {
                    countDownLatch.await();
                    reOrder.read();
                    cyclicBarrier.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
        countDownLatch.countDown();
        countDownLatch.countDown();
        cyclicBarrier.await();
        //   }
    }
}
