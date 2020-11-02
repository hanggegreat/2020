package cn.lollipop.io.nio;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

@Slf4j
public class MappedBuffer {
    public static void main(String[] args) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile("01.txt", "rw");
        // 获取 FileChannel
        FileChannel channel = randomAccessFile.getChannel();
        // 直接缓冲区
        MappedByteBuffer mappedByteBuffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, 5);

        mappedByteBuffer.putChar(0, '哈');
        randomAccessFile.close();
    }
}
