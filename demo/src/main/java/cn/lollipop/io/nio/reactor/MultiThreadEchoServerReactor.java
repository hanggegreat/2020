package cn.lollipop.io.nio.reactor;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * reactor
 *
 * @author zhangyuanhang
 */
@Slf4j
public class MultiThreadEchoServerReactor {
    private final ServerSocketChannel serverSocketChannel;
    private AtomicInteger next = new AtomicInteger();
    private Selector[] selectors = new Selector[2];
    private final AcceptorHandler acceptorHandler;
    private final ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 2, 0, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10), new ThreadPoolExecutor.AbortPolicy());

    private CountDownLatch latch = new CountDownLatch(2);

    public MultiThreadEchoServerReactor(int port) throws IOException {
        selectors[0] = Selector.open();
        selectors[1] = Selector.open();
        serverSocketChannel = ServerSocketChannel.open();
        acceptorHandler = new AcceptorHandler();
        // 绑定端口
        serverSocketChannel.bind(new InetSocketAddress(port));
        // 设置非阻塞
        serverSocketChannel.configureBlocking(false);
        // 将新连接处理器作为附件，绑定到sk选择键
        serverSocketChannel.register(selectors[0], SelectionKey.OP_ACCEPT, acceptorHandler);
    }

    public void start() {
        for (Selector selector : selectors) {
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
                } finally {
                    latch.countDown();
                }
            });
        }
    }

    private void dispatch(SelectionKey sk) {
        AcceptorHandler handler = (AcceptorHandler) sk.attachment();
        handler.accept(serverSocketChannel, selectors[next.getAndSet(next.get() + 1 % selectors.length)]);
    }

    public static void main(String[] args) throws Exception {
        MultiThreadEchoServerReactor multiThreadEchoServerReactor = new MultiThreadEchoServerReactor(6869);
        multiThreadEchoServerReactor.start();
        multiThreadEchoServerReactor.latch.await();
    }
}
