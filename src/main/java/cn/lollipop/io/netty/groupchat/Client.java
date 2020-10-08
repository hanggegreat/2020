package cn.lollipop.io.netty.groupchat;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.util.Scanner;

@Slf4j
public class Client {

    private final String host;
    private final int port;

    public Client(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void start() {
        EventLoopGroup group = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .handler(new GroupChatClientInitializer());

        log.info("客户端准备就绪...");

        try {
            Channel channel = bootstrap.connect(new InetSocketAddress(host, port)).addListener(
                    future -> {
                        if (future.isSuccess()) {
                            log.info("端口绑定成功！");
                        } else {
                            log.info("端口绑定失败！");
                        }
                    }
            ).sync().channel();

            log.info("--------- {} ---------", channel.localAddress());
            Scanner sc = new Scanner(System.in);
            while (sc.hasNextLine()) {
                channel.writeAndFlush(sc.nextLine() + "\n");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        new Client("127.0.0.1", 8888).start();
    }
}
