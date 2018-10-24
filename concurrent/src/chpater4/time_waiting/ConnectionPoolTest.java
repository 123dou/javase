package chpater4.time_waiting;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class ConnectionPoolTest {
    static ConnectionPool pool = new ConnectionPool(10);
    //保证所有Connection能够同时开始
    static CountDownLatch start = new CountDownLatch(1);
    //main线程会等待所有Connection结束才能继续执行
    static CountDownLatch end;

    public static void main(String[] args) throws InterruptedException {
        //线程数量,可以修改数量进行观察
        int threadCount = 20;
        end = new CountDownLatch(threadCount);
        int count = 20;
        AtomicInteger got = new AtomicInteger();
        AtomicInteger notGot = new AtomicInteger();
        for (int i = 0; i < threadCount; i++) {
            Thread thread = new Thread(new ConnectionRunner(count, got, notGot), "ConnectionRunnerThread");
            thread.start();
        }
        start.countDown();
        end.await();
        System.out.println("total invoke: " + (threadCount * count));
        System.out.println("got connection: " + got);
        System.out.println("not got connection " + notGot);
    }

    static class ConnectionRunner implements Runnable {
        int count;
        AtomicInteger got;
        AtomicInteger notGot;

        public ConnectionRunner(int count, AtomicInteger got, AtomicInteger notGot) {
            this.count = count;
            this.got = got;
            this.notGot = notGot;
        }

        public void run() {
            try {
                start.await();
            } catch (Exception e) {

            }
            while (count > 0) {
                try {
                    Connection connection = pool.fetchConnection(100);
                    //从连接池中获取连接,如果1000ms内无法获得,将会返回null
                    //分别统计连接获取的数量got和为获取到的数量notGot
                    if (connection != null) {
                        try {
                            connection.createStatement();
                            connection.commit();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        } finally {
                            pool.releaseConnection(connection);
                            got.incrementAndGet();
                        }
                    } else {
                        notGot.incrementAndGet();
                    }
                } catch (Exception e) {

                } finally {
                    count--;
                }
            }
            end.countDown();
        }
    }
}
