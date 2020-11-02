package cn.lollipop.io.nio;

import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

@Slf4j
public class BasicFileChannel {
    private static final String srcFileName = "01.txt";
    private static final String destFileName = "02.txt";
    private static final String content = "中华人民共和国万岁！2333中华人民共和国万岁！2333中华人民共和国万岁！2333中华人民共和国万岁！2333中华人民共和国万岁！2333中华人民共和国万岁！2333中华人民共和国万岁！2333中华人民共和国万岁！2333中华人民共和国万岁！2333中华人民共和国万岁！2333中华人民共和国万岁！2333中华人民共和国万岁！2333中华人民共和国万岁！2333中华人民共和国万岁！2333中华人民共和国万岁！2333中华人民共和国万岁！2333中华人民共和国万岁！2333中华人民共和国万岁！2333中华人民共和国万岁！2333中华人民共和国万岁！2333中华人民共和国万岁！2333中华人民共和国万岁！2333中华人民共和国万岁！2333中华人民共和国万岁！2333中华人民共和国万岁！2333";

    public static void main(String[] args) throws IOException {
        write();
//        copy();
        copy1();
        read();
    }

    public static void write() throws IOException {
        // 创建 ByteBuffer 缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        FileOutputStream outputStream = new FileOutputStream(srcFileName);
        // 通过 FileOutputStream 获取 FileChannel
        FileChannel channel = outputStream.getChannel();
        // 向缓冲区中写入数据
        buffer.put(content.getBytes());
        // 进行读写切换
        buffer.flip();
        // 将 buffer 中的数据写入 fileChannel
        channel.write(buffer);
        channel.close();
        outputStream.close();
    }

    public static void read() throws IOException {
        // 创建 ByteBuffer 缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        FileInputStream inputStream = new FileInputStream(destFileName);
        // 通过 FileInputStream 获取 FileChannel
        FileChannel channel = inputStream.getChannel();
        // 从 channel 中读取数据
        channel.read(buffer);
        log.info(new String(buffer.array()));
        channel.close();
        inputStream.close();
    }

    public static void copy() throws IOException {
        // 创建 ByteBuffer 缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(512);

        FileInputStream inputStream = new FileInputStream(srcFileName);
        // 通过 FileInputStream 获取 FileChannel
        FileChannel inputChannel = inputStream.getChannel();

        FileOutputStream outputStream = new FileOutputStream(destFileName);
        // 通过 FileOutputStream 获取 FileChannel
        FileChannel outputChannel = outputStream.getChannel();

        while (inputChannel.read(buffer) != -1) {
            buffer.flip();
            outputChannel.write(buffer);
            buffer.clear();
        }

        inputChannel.close();
        inputStream.close();
        outputChannel.close();
        outputStream.close();
    }

    public static void copy1() throws IOException {
        FileInputStream inputStream = new FileInputStream(srcFileName);
        // 通过 FileInputStream 获取 FileChannel
        FileChannel inputChannel = inputStream.getChannel();

        FileOutputStream outputStream = new FileOutputStream(destFileName);
        // 通过 FileOutputStream 获取 FileChannel
        FileChannel outputChannel = outputStream.getChannel();

        outputChannel.transferFrom(inputChannel, 0, inputChannel.size());

        inputChannel.close();
        inputStream.close();
        outputChannel.close();
        outputStream.close();
    }
}
