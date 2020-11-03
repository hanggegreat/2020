package cn.lollipop.handler;

import cn.lollipop.common.bean.msg.ProtoMsg;
import cn.lollipop.common.concurrent.FutureTaskScheduler;
import cn.lollipop.processer.ChatRedirectProcesser;
import cn.lollipop.server.ServerSession;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@ChannelHandler.Sharable
public class ChatRedirectHandler extends ChannelInboundHandlerAdapter {
    private final ChatRedirectProcesser chatRedirectProcesser;

    public ChatRedirectHandler(ChatRedirectProcesser chatRedirectProcesser) {
        this.chatRedirectProcesser = chatRedirectProcesser;
    }

    /**
     * 收到消息
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //判断消息实例
        if (!(msg instanceof ProtoMsg.Message)) {
            super.channelRead(ctx, msg);
            return;
        }

        //判断消息类型
        ProtoMsg.Message pkg = (ProtoMsg.Message) msg;
        ProtoMsg.HeadType headType = ((ProtoMsg.Message) msg).getType();
        if (!headType.equals(chatRedirectProcesser.type())) {
            super.channelRead(ctx, msg);
            return;
        }

        //判断是否登录
        ServerSession session = ServerSession.getSession(ctx);
        if (null == session || !session.isLogin()) {
            log.error("用户尚未登录，不能发送消息");
            return;
        }

        //异步处理IM消息转发的逻辑
        FutureTaskScheduler.add(() -> chatRedirectProcesser.action(session, pkg));
    }
}