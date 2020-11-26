package cn.lollipop.client;

import cn.lollipop.common.bean.User;
import cn.lollipop.common.bean.msg.ProtoMsg;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.AttributeKey;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * 实现客户端 Session会话
 *
 * @author zhangyuanhang
 */
@Slf4j
@Data
public class ClientSession {
    public static final AttributeKey<ClientSession> SESSION_KEY = AttributeKey.valueOf("SESSION_KEY");

    /**
     * 用户实现客户端会话管理的核心
     */
    private Channel channel;
    private User user;

    /**
     * 保存登录后的服务端sessionId
     */
    private String sessionId;

    private boolean isConnected = false;
    private boolean isLogin = false;

    /**
     * session中存储的session 变量属性值
     */
    private Map<String, Object> map = new HashMap<>();

    // 绑定通道
    public ClientSession(Channel channel) {
        this.channel = channel;
        this.sessionId = String.valueOf(-1);
        channel.attr(ClientSession.SESSION_KEY).set(this);
    }

    /**
     * 登录成功之后,设置sessionId
     *
     * @param ctx channelHandler 上下文
     * @param pkg message
     */
    public static void loginSuccess(ChannelHandlerContext ctx, ProtoMsg.Message pkg) {
        ClientSession session = getSession(ctx);
        session.setSessionId(pkg.getSessionId());
        session.setLogin(true);
        log.info("登录成功");
    }

    /**
     * 获取channel
     *
     * @param ctx channelHandler 上下文
     * @return
     */
    public static ClientSession getSession(ChannelHandlerContext ctx) {
        Channel channel = ctx.channel();
        return channel.attr(ClientSession.SESSION_KEY).get();
    }

    public String getRemoteAddress() {
        return channel.remoteAddress().toString();
    }

    /**
     * 写 protobuf 数据帧
     *
     * @param pkg message
     * @return
     */
    public ChannelFuture writeAndFlush(Object pkg) {
        return channel.writeAndFlush(pkg);
    }

    public void writeAndClose(Object pkg) {
        channel.writeAndFlush(pkg).addListener(ChannelFutureListener.CLOSE);
    }

    /**
     * 关闭通道
     */
    public void close() {
        isConnected = false;

        channel.close()
                .addListener((ChannelFutureListener) future1 -> {
                    if (future1.isSuccess()) {
                        log.error("连接顺利断开");
                    }
                });
    }

}