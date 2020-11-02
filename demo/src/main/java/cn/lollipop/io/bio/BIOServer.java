package cn.lollipop.io.bio;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class BIOServer {
    public static void main(String[] args) {
        start();
    }

    public static void start() {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(6666);
            log.info("服务器启动");

            while (true) {
                Socket socket = serverSocket.accept();
                log.info("客户端连接到达");

                cachedThreadPool.submit(() -> handler(socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert serverSocket != null;
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void handler(Socket socket) {
        byte[] bytes = new byte[1024];

        try {
            InputStream inputStream = socket.getInputStream();
            int len;
            while ((len = inputStream.read(bytes)) != -1) {
                log.info(new String(bytes, 0, len));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
