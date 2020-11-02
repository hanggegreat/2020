package cn.lollipop.io.netty.protocol;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomerClientHandler extends SimpleChannelInboundHandler<MessageProtocol> {
    private int count = 0;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for (int i = 0; i < 5; i++) {
            MessageProtocol messageProtocol = new MessageProtocol();
            messageProtocol.setData("今天天气冷，吃火锅去啊！".getBytes(CharsetUtil.UTF_8));
            messageProtocol.setLen(messageProtocol.getData().length);

            ctx.writeAndFlush(messageProtocol);
        }
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MessageProtocol msg) throws Exception {
        log.info("客户端接收到以下消息: ");
        log.info("len = {}", msg.getLen());
        log.info("data = {}", new String(msg.getData(), CharsetUtil.UTF_8));
        log.info("count = {}", ++count);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error(cause.getLocalizedMessage());
        ctx.close();
    }
}
