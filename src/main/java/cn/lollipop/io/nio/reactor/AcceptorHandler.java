package cn.lollipop.io.nio.reactor;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * 接受新连接，并为之创建输入输出Handler处理器
 *
 * @author zhangyuanhang
 */
@Slf4j
public class AcceptorHandler {

    public void accept(ServerSocketChannel serverSocketChannel, Selector selector) {
        try {
            SocketChannel socketChannel = serverSocketChannel.accept();
            log.info("{} 上线", socketChannel.getRemoteAddress());
            new MultiThreadEchoHandler(socketChannel, selector).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}