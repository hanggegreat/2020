package cn.lollipop.common.concurrent;

/**
 * 异步任务
 *
 * @param <R> 真实类型
 * @author zhangyuanhang
 */
public interface CallbackTask<R> {
    R execute() throws Exception;

    void onBack(R r);

    void onException(Throwable t);
}