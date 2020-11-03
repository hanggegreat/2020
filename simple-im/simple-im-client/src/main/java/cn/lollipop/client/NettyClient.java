package cn.lollipop.client;

import cn.lollipop.common.bean.User;
import cn.lollipop.common.codec.ProtobufDecoder;
import cn.lollipop.common.codec.ProtobufEncoder;
import cn.lollipop.handler.ChatMsgHandler;
import cn.lollipop.handler.ExceptionHandler;
import cn.lollipop.handler.LoginResponseHandler;
import cn.lollipop.sender.ChatSender;
import cn.lollipop.sender.LoginSender;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.GenericFutureListener;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 客户端
 *
 * @author zhangyuanhang
 */
@Slf4j
@Data
@Service
public class NettyClient {
    /**
     * 服务器端口
     */
    private final String IP = "127.0.0.1";
    private final int PORT = 6869;

    private final ChatMsgHandler chatMsgHandler;
    private final LoginResponseHandler loginResponseHandler;
    private final ExceptionHandler exceptionHandler;


    private Channel channel;
    private ChatSender sender;
    private LoginSender l;

    /**
     * 唯一标记
     */
    private boolean initFlag = true;
    private User user;
    private GenericFutureListener<ChannelFuture> connectedListener;

    private Bootstrap b;
    private EventLoopGroup g;

    public NettyClient(ChatMsgHandler chatMsgHandler, LoginResponseHandler loginResponseHandler, ExceptionHandler exceptionHandler) {
        g = new NioEventLoopGroup();

        this.chatMsgHandler = chatMsgHandler;
        this.loginResponseHandler = loginResponseHandler;
        this.exceptionHandler = exceptionHandler;
    }

    /**
     * 连接
     */
    public void doConnect() {
        try {
            b = new Bootstrap();

            b
                    .group(g)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
                    .remoteAddress(IP, PORT)
                    // 设置通道初始化
                    .handler(
                            new ChannelInitializer<SocketChannel>() {
                                @Override
                                public void initChannel(SocketChannel ch) {
                                    ch.pipeline().addLast("decoder", new ProtobufDecoder());
                                    ch.pipeline().addLast("encoder", new ProtobufEncoder());
                                    ch.pipeline().addLast(loginResponseHandler);
                                    ch.pipeline().addLast(chatMsgHandler);
                                    ch.pipeline().addLast(exceptionHandler);
                                }
                            }
                    );

            log.info("客户端开始连接");

            b
                    .connect()
                    .addListener(connectedListener)
                    .channel()
                    .closeFuture()
                    .sync();
        } catch (Exception e) {
            log.info("客户端连接失败!" + e.getMessage());
        }
    }

    public void close() {
        try {
            g.shutdownGracefully().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}