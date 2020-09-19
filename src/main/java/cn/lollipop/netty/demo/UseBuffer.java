package cn.lollipop.netty.demo;

import lombok.extern.slf4j.Slf4j;

import java.nio.IntBuffer;

@Slf4j
public class UseBuffer {

    public static void putTest() {
        IntBuffer intBuffer = IntBuffer.allocate(20);

        log.info("position = " + intBuffer.position());
        log.info("limit = " + intBuffer.limit());
        log.info("capacity = " + intBuffer.capacity());

        for (int i = 0; i < 5; i++) {
            intBuffer.put(i);
        }

        log.info("=========== After put ============");

        log.info("position = " + intBuffer.position());
        log.info("limit = " + intBuffer.limit());
        log.info("capacity = " + intBuffer.capacity());

        intBuffer.flip();

        for (int i = 0; i < 2; i++) {
            int j = intBuffer.get();
            log.info("j = " + j);
        }

        log.info("=========== After get 2 int ============");

        log.info("position = " + intBuffer.position());
        log.info("limit = " + intBuffer.limit());
        log.info("capacity = " + intBuffer.capacity());

        for (int i = 0; i < 3; i++) {
            int j = intBuffer.get();
            log.info("j = " + j);
        }

        log.info("=========== After get 3 int ============");

        log.info("position = " + intBuffer.position());
        log.info("limit = " + intBuffer.limit());
        log.info("capacity = " + intBuffer.capacity());

    }

    public static void main(String[] args) {
        putTest();
    }
}
