package cn.lollipop.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * 实现客户端发送一个请求，服务端会返回 hello netty
 */
public class HelloServer {
    public static void main(String[] args) {

        // 定义一对线程组

        // 主线程组，用于接收客户端的连接，但是不做任何处理
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        // 从线程组，用于处理主线程组给与的任务
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new HelloServerInitializer());

        try {
            // 启动server，同步方式
            ChannelFuture channelFuture = serverBootstrap.bind("0.0.0.0", 8088).sync();
            // 监听关闭的channel，同步方式
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
