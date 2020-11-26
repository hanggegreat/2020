package cn.lollipop.concurrent.pool.thread;

/**
 * @author lollipop
 * @date 2020/11/26 17:27:11
 */
public interface ThreadPool<Job extends Runnable> {
    void execute(Job job);

    void shutdown();

    void addWorkers(int num);

    void removeWorker(int num);

    int getJobSize();
}
