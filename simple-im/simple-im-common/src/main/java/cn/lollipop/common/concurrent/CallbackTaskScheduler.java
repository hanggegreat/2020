package cn.lollipop.common.concurrent;

import com.google.common.util.concurrent.*;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;


/**
 * 异步任务调度类
 *
 * @author zhangyuanhang
 */
@Slf4j
public class CallbackTaskScheduler extends Thread {
    /**
     * 任务队列
     */
    private final ConcurrentLinkedQueue<CallbackTask<?>> executeTaskQueue = new ConcurrentLinkedQueue<>();

    /**
     * 线程休眠时间
     */
    private final long sleepTime = 200;

    private final ExecutorService executor =
            new ThreadPoolExecutor(
                    10,
                    100,
                    10,
                    TimeUnit.SECONDS,
                    new ArrayBlockingQueue<>(100),
                    new ThreadPoolExecutor.AbortPolicy()
            );

    private final ListeningExecutorService gExecutor = MoreExecutors.listeningDecorator(executor);

    private static final CallbackTaskScheduler inst = new CallbackTaskScheduler();

    private CallbackTaskScheduler() {
        this.start();
    }

    /**
     * 添加任务
     *
     * @param executeTask 异步任务
     */
    public static <R> void add(CallbackTask<R> executeTask) {
        inst.executeTaskQueue.add(executeTask);
    }

    @Override
    public void run() {
        while (true) {
            // 处理任务
            handleTask();
            threadSleep();
        }
    }

    private void threadSleep() {
        try {
            sleep(sleepTime);
        } catch (InterruptedException e) {
            log.error("exception: {0}", e);
        }
    }

    /**
     * 处理任务队列，检查其中是否有任务
     */
    private void handleTask() {
        try {
            while (executeTaskQueue.peek() != null) {
                handleTask(executeTaskQueue.poll());
            }
        } catch (Exception e) {
            log.error("exception: {0}", e);
        }
    }

    /**
     * 执行任务操作
     *
     * @param executeTask 异步任务
     */
    private <R> void handleTask(CallbackTask<R> executeTask) {
        ListenableFuture<R> future = gExecutor.submit(executeTask::execute);

        Futures.addCallback(future, new FutureCallback<>() {
            @Override
            public void onSuccess(R r) {
                executeTask.onBack(r);
            }

            @Override
            public void onFailure(Throwable t) {
                executeTask.onException(t);
            }
        }, gExecutor);
    }
}