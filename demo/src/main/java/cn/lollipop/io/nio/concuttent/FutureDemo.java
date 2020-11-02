package cn.lollipop.io.nio.concuttent;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * FutureDemo
 *
 * @author lollipop
 */
@Slf4j
public class FutureDemo {
    public static ExecutorService executor =
            new ThreadPoolExecutor(
                    2,
                    2,
                    0,
                    TimeUnit.SECONDS,
                    new ArrayBlockingQueue<>(10),
                    new ThreadPoolExecutor.AbortPolicy()
            );

    public static final int SLEEP_GAP = 500;

    public static void main(String[] args) {

        FutureTask<Boolean> hotWaterTask = new FutureTask<>(() -> {
            try {
                log.info("洗好水壶");
                log.info("灌上凉水");
                log.info("放在火上");
                TimeUnit.MILLISECONDS.sleep(SLEEP_GAP);
                log.info("水烧开了");
                log.info("烧结果: {}", true);
            } catch (InterruptedException e) {
                log.info("烧发生异常被中断");
                log.info("烧结果: {}", false);
            }

            return true;
        });

        FutureTask<Boolean> washTask = new FutureTask<>(() -> {
            try {
                log.info("洗茶壶");
                log.info("洗茶杯");
                log.info("找茶叶");
                TimeUnit.MILLISECONDS.sleep(SLEEP_GAP);
                log.info("洗完了");
                log.info("洗结果: {}", true);
            } catch (InterruptedException e) {
                log.info("洗发生异常被中断");
                log.info("洗结果: {}", false);
            }

            return true;
        });

        executor.submit(hotWaterTask);
        executor.submit(washTask);
        try {
            boolean hResult = hotWaterTask.get();
            boolean wResult = washTask.get();
            log.info("hResult: {}", hResult);
            log.info("wResult: {}", wResult);
        } catch (InterruptedException e) {
            log.info("主线程gg");
        } catch (Exception e) {
            e.printStackTrace();
        }

        log.info("主线程运行结束");
    }
}
