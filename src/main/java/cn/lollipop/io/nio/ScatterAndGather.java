package cn.lollipop.io.nio;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 * Scattering: 将数据写入到 Buffer 时，可以采用 Buffer 数组，依次写入
 * Gathering: 从 Buffer 中读取数据时，可以采用 Buffer 数组，依次读入
 */
@Slf4j
public class ScatterAndGather {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress inetSocketAddress = new InetSocketAddress(7777);
        serverSocketChannel.bind(inetSocketAddress);

        // 创建 Buffer 数组
        ByteBuffer[] byteBuffers = {ByteBuffer.allocate(5), ByteBuffer.allocate(3)};

        SocketChannel socketChannel = serverSocketChannel.accept();

        while (true) {
            int len = 0;
            while (len < 8) {
                len += socketChannel.read(byteBuffers);
            }

            Arrays.stream(byteBuffers).map(buffer -> "position = " + buffer.position() + ", limit = " + buffer.limit()).forEach(System.out::println);
            Arrays.stream(byteBuffers).forEach(ByteBuffer::flip);

            socketChannel.write(byteBuffers);
            Arrays.stream(byteBuffers).forEach(ByteBuffer::clear);
        }
    }
}
