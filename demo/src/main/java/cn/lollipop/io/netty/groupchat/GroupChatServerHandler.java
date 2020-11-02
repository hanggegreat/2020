package cn.lollipop.io.netty.groupchat;


import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.concurrent.GlobalEventExecutor;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
public class GroupChatServerHandler extends SimpleChannelInboundHandler<String> {

    private static final ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();

        channelGroup.writeAndFlush("【客户端】" + channel.remoteAddress() + " 加入群聊" + sdf.format(new Date()) + "\n");
        channelGroup.add(channel);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
        Channel channel = channelHandlerContext.channel();

        channelGroup.forEach(ch -> {
            if (channel == ch) {
                channelGroup.writeAndFlush("【自己】发送消息：" + s + ", " + sdf.format(new Date()) + "\n");
            } else {
                channelGroup.writeAndFlush("【客户端】" + channel.remoteAddress() + " 发送消息：" + s + ", " + sdf.format(new Date()) + "\n");
            }
        });
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.writeAndFlush("【客户端】" + channel.remoteAddress() + "离开群聊" + sdf.format(new Date()) + "\n");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        log.info("【客户端】：{} 上线, {}", channel.remoteAddress(), sdf.format(new Date()));
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        log.info("【客户端】：{} 下线, {}", channel.remoteAddress(), sdf.format(new Date()));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error(cause.getLocalizedMessage());
        ctx.close();
    }

    /**
     * @param ctx 上下文
     * @param evt 事件
     * @throws Exception
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (!(evt instanceof IdleStateEvent)) {
            return;
        }

        IdleStateEvent event = (IdleStateEvent) evt;
        String eventType;
        if (event.state() == IdleState.READER_IDLE) {
            eventType = "读空闲";
        } else if (event.state() == IdleState.WRITER_IDLE) {
            eventType = "写空闲";
        } else {
            eventType = "读写空闲";
        }

        log.info("client: {}, {}", ctx.channel().remoteAddress(), eventType);
    }
}
