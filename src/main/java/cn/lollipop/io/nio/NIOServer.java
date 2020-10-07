package cn.lollipop.io.nio;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

@Slf4j
public class NIOServer {

    // 定义属性
    private Selector selector;
    private ServerSocketChannel serverSocketChannel;
    private final static int PORT = 8888;


    public NIOServer() throws IOException {
        selector = Selector.open();
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(PORT)); // 绑定端口
        serverSocketChannel.configureBlocking(false); // 设置非阻塞
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT); // 注册
    }

    public void start() throws IOException {
        while (true) {
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
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    // 注册读取事件
                    socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));

                    log.info("{} 上线", socketChannel.getRemoteAddress());
                }

                // 有新的数据到达
                if (key.isReadable()) {
                    read(key);
                }

                // 移除key，防止重复处理
                iterator.remove();
            }
        }
    }

    private void read(SelectionKey key) {
        SocketChannel channel = null;
        try {
            channel = (SocketChannel) key.channel();
            ByteBuffer buffer = (ByteBuffer) key.attachment();
            if (channel.read(buffer) > 0) {
                String msg = new String(buffer.array());
                log.info("from 客户端: {}, msg: {}", channel.getRemoteAddress(), msg);
                sendToOther(channel, msg);
            }
        } catch (IOException e) {
            try {
                log.info("{} 离线了...", channel.getRemoteAddress());
                key.cancel(); // 取消注册
                channel.close();
            } catch (IOException e2) {
                log.error(e2.getMessage());
            }
        }
    }

    private void sendToOther(SocketChannel self, String msg) throws IOException {
        for (SelectionKey key : selector.keys()) {
            Channel channel = key.channel();
            if (!(channel instanceof SocketChannel) || channel == self) {
                continue;
            }

            SocketChannel socketChannel = (SocketChannel) channel;
            socketChannel.write(ByteBuffer.wrap(msg.getBytes()));
        }
    }

    public static void main(String[] args) throws IOException {
        NIOServer nioServer = new NIOServer();
        nioServer.start();
    }

}
