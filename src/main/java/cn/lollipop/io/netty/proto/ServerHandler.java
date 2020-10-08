package cn.lollipop.io.netty.proto;

import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;


/**
 * 入站handler
 */
@Slf4j
public class ServerHandler extends SimpleChannelInboundHandler<DataInfo.Data> {

    /**
     * 可读时调用
     *
     * @param ctx  上下文，包含pipeline channel 等
     * @param data 客户端发送的数据
     * @throws Exception
     */
    @Override
    public void channelRead0(ChannelHandlerContext ctx, DataInfo.Data data) throws Exception {
        log.info("客户端：{} 发来消息：{}", ctx.channel().remoteAddress(), data.getDatatype() == DataInfo.Data.DataType.StudentType ? data.getStudent() : data.getTeacher());
    }

    /**
     * 数据读完时调用
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello 客户端: ~o( =∩ω∩= )m喵1", CharsetUtil.UTF_8));
    }

    /**
     * 统一异常处理
     *
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
