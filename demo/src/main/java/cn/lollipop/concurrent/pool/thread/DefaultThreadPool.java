package cn.lollipop.concurrent.pool.thread;

import java.util.*;

/**
 * @author lollipop
 * @date 2020/11/26 17:28:48
 */
public class DefaultThreadPool<Job extends Runnable> implements ThreadPool<Job> {
    private static final int MAX_WORKER_NUM = 10;
    private static final int CORE_WORKER_NUM = 5;

    private final Queue<Job> jobs = new LinkedList<>();
    private final List<Worker> workers = new ArrayList<>();
    private int workerNum;

    public DefaultThreadPool() {
        initWorkers(CORE_WORKER_NUM);
    }

    public DefaultThreadPool(int size) {
        initWorkers(Math.min(Math.max(CORE_WORKER_NUM, size), MAX_WORKER_NUM));
    }

    private void initWorkers(int num) {
        workerNum = num;
        for (int i = 0; i < num; i++) {
            workers.add(new Worker());
        }
    }


    @Override
    public void execute(Job job) {
        if (job != null) {
            synchronized (jobs) {
                jobs.offer(job);
                jobs.notify();
            }
        }
    }

    @Override
    public void shutdown() {
        workers.forEach(Worker::shutdown);
    }

    @Override
    public void addWorkers(int num) {
        synchronized (workers) {
            num = Math.min(MAX_WORKER_NUM - CORE_WORKER_NUM, Math.max(0, num));
            for (int i = 0; i < num; i++) {
                workers.add(new Worker());
            }
        }
    }

    @Override
    public void removeWorker(int num) {
        synchronized (workers) {
            num = Math.min(MAX_WORKER_NUM - CORE_WORKER_NUM, Math.max(0, num));
            for (int i = 0; i < num; i++) {
                Worker worker = workers.get(0);
                worker.shutdown();
                workers.remove(worker);
            }
            workerNum -= num;
        }
    }

    @Override
    public int getJobSize() {
        return jobs.size();
    }

    class Worker implements Runnable {
        private boolean isRunning = true;

        @Override
        public void run() {
            while (isRunning) {
                synchronized (jobs) {
                    while (jobs.isEmpty()) {
                        try {
                            jobs.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }

                    jobs.poll().run();
                }
            }
        }

        public void shutdown() {
            isRunning = false;
        }
    }
}
