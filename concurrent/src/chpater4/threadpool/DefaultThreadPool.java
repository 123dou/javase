package chpater4.threadpool;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class DefaultThreadPool<Job extends Runnable> implements ThreadPool<Job> {
    //线程池最大数量
    private static final int MAX_WORKER_NUMERS = 10;
    //线程池默认的数量
    private static final int DEFAULT_WORKER_NUMBRES = 10;
    //线程池最小的数量
    private static final int MIN_WORKER_NUMBERS = 1;
    //这是一个工作列表,将会向里面插入工作
    private final LinkedList<Job> jobs = new LinkedList<>();
    //工作者列表
    private final List<Worker> workers = Collections.synchronizedList(new ArrayList<Worker>());
    //工作者线程的数量
    private int workerNum = DEFAULT_WORKER_NUMBRES;
    //线程编号生成
    private AtomicLong threadNum = new AtomicLong();

    public DefaultThreadPool() {
        initialzeWorkers(DEFAULT_WORKER_NUMBRES);
    }

    public DefaultThreadPool(int num) {
        workerNum = num > MAX_WORKER_NUMERS ? MAX_WORKER_NUMERS : num < MIN_WORKER_NUMBERS ? MIN_WORKER_NUMBERS : num;
        initialzeWorkers(workerNum);
    }

    @Override
    public void execute(Job job) {
        if (job != null) {
            //添加一个工作然后通知
            synchronized (jobs) {
                jobs.addLast(job);
                job.notify();
            }
        }
    }

    @Override
    public void shutdown() {
        for (Worker worker : workers) {
            worker.shutdown();
        }
    }

    @Override
    public void addWorkers(int num) {
        synchronized (jobs) {
            //限制新增的worker数量不能超过最大值
            if (num + this.workerNum > MAX_WORKER_NUMERS) {
                num = MAX_WORKER_NUMERS - this.workerNum;
            }
            initialzeWorkers(num);
            this.workerNum += num;
        }
    }

    @Override
    public void removeWorker(int num) {
        synchronized (jobs) {
            if (num >= this.workerNum) {
                throw new IllegalArgumentException("beyond workerNum");
            }
            int count = 0;
            while (count < num) {
                Worker worker = workers.get(count);
                if (workers.remove(worker)) {
                    worker.shutdown();
                    count++;
                }
            }
            this.workerNum -= count;
        }
    }

    @Override
    public int getJobSize() {
        return jobs.size();
    }

    //初始化线程工作者
    private void initialzeWorkers(int num) {
        for (int i = 0; i < num; i++) {
            Worker worker = new Worker();
            workers.add(worker);
            Thread thread = new Thread(worker, "ThreadPool-worker-" + threadNum.incrementAndGet());
            thread.start();
        }
    }

    //工作者,负责消费任务
    class Worker implements Runnable {
        //是否工作
        private volatile boolean running = true;

        @Override
        public void run() {
            while (running) {
                Job job = null;
                synchronized (jobs) {
                    while (jobs.isEmpty()) {
                        try {
                            jobs.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }
                    job = jobs.removeFirst();
                    if (job != null) {
                        try {
                            job.run();
                        } catch (Exception e) {

                        }
                    }
                }
            }
        }

        public void shutdown () {
            running = false;
        }
    }
}
