package cn.lollipop.io.netty.protocol;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;

@Slf4j
public class Client {
    public static void main(String[] args) {
        EventLoopGroup group = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .handler(new ClientChannelInitializer());

        log.info("客户端准备就绪...");

        try {
            bootstrap.connect(new InetSocketAddress("127.0.0.1", 8888)).addListener(
                    future -> {
                        if (future.isSuccess()) {
                            log.info("端口绑定成功！");
                        } else {
                            log.info("端口绑定失败！");
                        }
                    }
            ).sync().channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }

    }
}
