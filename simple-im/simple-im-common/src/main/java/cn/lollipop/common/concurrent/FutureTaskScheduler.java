package cn.lollipop.common.concurrent;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

@Slf4j
public class FutureTaskScheduler extends Thread {
    // 任务队列
    private ConcurrentLinkedQueue<ExecuteTask> executeTaskQueue = new ConcurrentLinkedQueue<>();
    // 线程休眠时间
    private long sleepTime = 200;
    private ExecutorService pool = new ThreadPoolExecutor(10, 10, 0, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10), new ThreadPoolExecutor.AbortPolicy());

    private static final FutureTaskScheduler inst = new FutureTaskScheduler();

    private FutureTaskScheduler() {
        this.start();
    }

    /**
     * 添加任务
     *
     * @param executeTask
     */


    public static void add(ExecuteTask executeTask) {
        inst.executeTaskQueue.add(executeTask);
    }

    @Override
    public void run() {
        while (true) {
            handleTask();// 处理任务
            threadSleep(sleepTime);
        }
    }

    private void threadSleep(long time) {
        try {
            sleep(time);
        } catch (InterruptedException e) {
            log.error("exception: {0}", e);
        }
    }

    /**
     * 处理任务队列，检查其中是否有任务
     */
    private void handleTask() {
        try {
            ExecuteTask executeTask;
            while (executeTaskQueue.peek() != null) {
                executeTask = executeTaskQueue.poll();
                handleTask(executeTask);
            }
        } catch (Exception e) {
            log.error("exception: {0}", e);
        }
    }

    /**
     * 执行任务操作
     *
     * @param executeTask
     */
    private void handleTask(ExecuteTask executeTask) {
        pool.execute(executeTask::execute);
    }
}