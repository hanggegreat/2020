package cn.lollipop.io.nio.reactor;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 接受新连接，并为之创建输入输出Handler处理器
 *
 * @author zhangyuanhang
 */
@Slf4j
public class AcceptorHandler {
    private final ThreadPoolExecutor executor;

    public AcceptorHandler() {
        executor = new ThreadPoolExecutor(10, 20, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(20), new ThreadPoolExecutor.CallerRunsPolicy());
    }

    public void accept(ServerSocketChannel serverSocketChannel, Selector selector) {
        executor.submit(() -> {
            try {
                SocketChannel socketChannel = serverSocketChannel.accept();
                log.info("{} 上线", socketChannel.getRemoteAddress());
                new EchoHandler(socketChannel, selector);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}