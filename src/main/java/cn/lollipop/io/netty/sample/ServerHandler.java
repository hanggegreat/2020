package cn.lollipop.io.netty.sample;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelPipeline;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * 入站handler
 */
@Slf4j
public class ServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.channel().eventLoop().schedule(() -> {
            ctx.writeAndFlush(Unpooled.copiedBuffer("hello 客户端: ~o( =∩ω∩= )m喵2", CharsetUtil.UTF_8));
        }, 5, TimeUnit.SECONDS);

        log.info("客户端：{}就绪", ctx.channel().remoteAddress());
    }

    /**
     * 可读时调用
     * @param ctx 上下文，包含pipeline channel 等
     * @param msg 客户端发送的数据
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        log.info("当前线程：{}", Thread.currentThread().getName());
        log.info("ctx: {}", ctx);
        Channel channel = ctx.channel();
        ChannelPipeline pipeline = ctx.pipeline();

        ByteBuf byteBuf = (ByteBuf) msg;
        log.info("客户端：{} 发来消息：{}", channel.remoteAddress(), byteBuf.toString(CharsetUtil.UTF_8));
    }

    /**
     * 数据读完时调用
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
            ctx.writeAndFlush(Unpooled.copiedBuffer("hello 客户端: ~o( =∩ω∩= )m喵1", CharsetUtil.UTF_8));
    }

    /**
     * 统一异常处理
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
