package cn.lollipop.io.nio.reactor;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 负责socket的数据输入、业务处理、结果输出
 *
 * @author zhangyuanhang
 */
@Slf4j
public class EchoHandler {
    private final ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 1, 0, TimeUnit.SECONDS, new ArrayBlockingQueue<>(0), new ThreadPoolExecutor.AbortPolicy());

    private SocketChannel socketChannel;
    private SelectionKey selectionKey;

    private final ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

    private final int RECEIVING = 0;
    private final int SENDING = 1;

    private int state = RECEIVING;

    public EchoHandler(SocketChannel socketChannel, Selector selector) {
        try {
            this.socketChannel = socketChannel;
            socketChannel.configureBlocking(false);
            this.selectionKey = socketChannel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE, this);
            selector.wakeup();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        executor.submit(() -> {
            try {
                if (state == SENDING) {
                    socketChannel.write(byteBuffer);
                    byteBuffer.clear();
                    selectionKey.interestOps(SelectionKey.OP_READ);
                    state = RECEIVING;
                } else {
                    int length;
                    while ((length = socketChannel.read(byteBuffer)) > 0) {
                        log.info(new String(byteBuffer.array(), 0, length));
                    }

                    byteBuffer.flip();
                    selectionKey.interestOps(SelectionKey.OP_WRITE);
                    state = SENDING;
                }
            } catch (IOException e) {
                log.error(e.getMessage());
            }
        });
    }
}
