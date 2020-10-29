package cn.lollipop.io.nio.reactor;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * reactor
 * @author zhangyuanhang
 */
@Slf4j
public class MultiThreadEchoServerReactor {

    private final Selector selector;
    private final ServerSocketChannel serverSocketChannel;
    private final AcceptorHandler acceptorHandler;
    private final ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 1, 0, TimeUnit.SECONDS, new ArrayBlockingQueue<>(0), new ThreadPoolExecutor.AbortPolicy());

    public MultiThreadEchoServerReactor(int port) throws IOException {
        selector = Selector.open();
        serverSocketChannel = ServerSocketChannel.open();
        acceptorHandler = new AcceptorHandler();
        // 绑定端口
        serverSocketChannel.bind(new InetSocketAddress(port));
        // 设置非阻塞
        serverSocketChannel.configureBlocking(false);
        // 将新连接处理器作为附件，绑定到sk选择键
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT, acceptorHandler);
    }

    public void start() throws IOException {
        executor.submit(() -> {
            try {
                while (!Thread.interrupted()) {
                    int count = selector.select();
                    if (count == 0) {
                        continue;
                    }

                    Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                    // 遍历所有事件
                    while (iterator.hasNext()) {
                        SelectionKey key = iterator.next();

                        // 有新的连接到达
                        if (key.isAcceptable()) {
                            dispatch(key);
                        }

                        // 移除key，防止重复处理
                        iterator.remove();
                    }
                }
            } catch (IOException e) {
                log.error(e.getMessage());
            }
        });
    }

    private void dispatch(SelectionKey sk) throws IOException {
        AcceptorHandler handler = (AcceptorHandler) sk.attachment();
        handler.accept(serverSocketChannel, selector);
    }
}


