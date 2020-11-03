package cn.lollipop.server;

import cn.lollipop.common.codec.ProtobufDecoder;
import cn.lollipop.common.codec.ProtobufEncoder;
import cn.lollipop.handler.ChatRedirectHandler;
import cn.lollipop.handler.HeartBeatServerHandler;
import cn.lollipop.handler.LoginRequestHandler;
import cn.lollipop.handler.ServerExceptionHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.net.InetSocketAddress;

/**
 * 聊天服务
 *
 * @author zhangyuanhang
 */
@Data
@Slf4j
@Service
public class NettyServer {
    private static final int PORT = 6869;

    /**
     * 通过nio方式来接收连接和处理连接
     */
    private EventLoopGroup bossGroup = new NioEventLoopGroup();
    private EventLoopGroup workerGroup = new NioEventLoopGroup();

    /**
     * 启动引导器
     */
    private final ServerBootstrap serverBootstrap = new ServerBootstrap();

    private final LoginRequestHandler loginRequestHandler;
    private final ServerExceptionHandler serverExceptionHandler;
    private final ChatRedirectHandler chatRedirectHandler;

    public NettyServer(LoginRequestHandler loginRequestHandler, ServerExceptionHandler serverExceptionHandler, ChatRedirectHandler chatRedirectHandler) {
        this.loginRequestHandler = loginRequestHandler;
        this.serverExceptionHandler = serverExceptionHandler;
        this.chatRedirectHandler = chatRedirectHandler;
    }

    public void start() {
        try {
            serverBootstrap
                    // 1 设置reactor 线程
                    .group(bossGroup, workerGroup)
                    // 2 设置nio类型的channel
                    .channel(NioServerSocketChannel.class)
                    // 3 设置监听端口
                    .localAddress(new InetSocketAddress(PORT))
                    // 4 设置通道选项
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
                    // 5 装配流水线
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        //有连接到达时会创建一个channel
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            // 管理pipeline中的Handler
                            ch.pipeline().addLast(new ProtobufDecoder());
                            ch.pipeline().addLast(new ProtobufEncoder());
                            ch.pipeline().addLast(new HeartBeatServerHandler());
                            // 在流水线中添加handler来处理登录,登录后删除
                            ch.pipeline().addLast(loginRequestHandler);
                            ch.pipeline().addLast(chatRedirectHandler);
                            ch.pipeline().addLast(serverExceptionHandler);
                        }
                    });

            // 6 开始绑定 server 通过调用sync同步方法阻塞直到绑定成功
            ChannelFuture channelFuture = serverBootstrap.bind().sync();
            log.info("服务启动, 端口 " + channelFuture.channel().localAddress());
            // 7 监听通道关闭事件 应用程序会一直等待，直到channel关闭
            channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 8 优雅关闭EventLoopGroup，释放掉所有资源包括创建的线程
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }
}