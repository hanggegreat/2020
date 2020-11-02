package cn.lollipop.io.nio;

import lombok.extern.slf4j.Slf4j;

import java.nio.IntBuffer;


@Slf4j
public class BasicBuffer {

    public static void main(String[] args) {
        // 创建一个大小为5的IntBuffer
        IntBuffer intBuffer = IntBuffer.allocate(5);

        for (int i = 0; i < intBuffer.capacity() - 2; i++) {
            intBuffer.put(i * 2);
        }

        // 进行读写切换
        intBuffer.flip();

        while (intBuffer.hasRemaining()) {
            log.info(String.valueOf(intBuffer.get()));
        }

    }

}
