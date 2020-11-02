package cn.lollipop.io.nio;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

@Slf4j
public class NIOClient {
    // 定义属性
    private static final String HOST = "127.0.0.1";
    private static final int PORT = 8888;
    private Selector selector;
    private SocketChannel socketChannel;
    private String username;

    public NIOClient() throws IOException {
        selector = Selector.open();
        // 连接到服务器
        socketChannel = SocketChannel.open(new InetSocketAddress(HOST, PORT));
        socketChannel.configureBlocking(false);
        // 注册
        socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));

        username = socketChannel.getLocalAddress().toString().substring(1);
        log.info("{} is ok", username);
    }

    public void sendInfo() throws IOException {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            socketChannel.write(ByteBuffer.wrap((username + " 说：" + sc.nextLine()).getBytes()));
        }
    }

    public void readInfo() throws IOException {
        while (true) {
            if (selector.select() == 0) {
                continue;
            }

            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();

            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                if (key.isReadable()) {
                    SocketChannel socketChannel = (SocketChannel) key.channel();
                    ByteBuffer buffer = (ByteBuffer) key.attachment();
                    socketChannel.read(buffer);
                    log.info(new String(buffer.array()).trim());
                }

                iterator.remove();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        NIOClient nioClient = new NIOClient();

        new Thread(() -> {
            try {
                nioClient.readInfo();
                TimeUnit.SECONDS.sleep(2);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        nioClient.sendInfo();
    }
}
