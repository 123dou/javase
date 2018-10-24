package chapter1;

import java.util.concurrent.TimeUnit;

/**
 * 在控制台输入:jps
 * 输出如下:
 * 611 935 Jps
 * 929 ThreadState
 * 270
 * 可以看到进程对应的进程id为929,接着输入jstack 929
 * 就可时看到线程的该进程对应的线程信息
 */
public class ThreadState {
    public static void main(String[] args) {
        new Thread(new TimeWaiting (), "TimeWaitingThread").start();
        new Thread(new Waiting (), "WaitingThread").start();
        new Thread(new Blocked (), "BlockedThread-1").start();
        new Thread(new Blocked (), "BlockThread-2").start();
    }

    static class TimeWaiting implements Runnable{
        @Override
        public void run() {
            while (true) {
                SleepUtils.second(100);
            }
        }
    }

    static class Waiting implements Runnable {
        @Override
        public void run() {
            while (true) {
                synchronized (Waiting.class) {
                    try {
                        Waiting.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    static class Blocked implements Runnable {

        @Override
        public void run() {
            synchronized (Blocked.class) {
                while (true) {
                    SleepUtils.second(100);
                }
            }
        }
    }
}

class SleepUtils {
    public static final void second(long seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}