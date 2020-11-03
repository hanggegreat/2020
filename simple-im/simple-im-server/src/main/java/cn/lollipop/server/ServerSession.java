package cn.lollipop.server;

import cn.lollipop.common.bean.User;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.AttributeKey;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 实现服务器Socket Session会话
 *
 * @author zhangyuanhang
 */
@Data
@Slf4j
public class ServerSession {
    public static final AttributeKey<String> KEY_USER_ID = AttributeKey.valueOf("KEY_USER_ID");
    public static final AttributeKey<ServerSession> SESSION_KEY = AttributeKey.valueOf("SESSION_KEY");

    /**
     * 通道
     */
    private Channel channel;

    /**
     * 用户
     */
    private User user;

    /**
     * session唯一标示
     */
    private final String sessionId;

    /**
     * 登录状态
     */
    private boolean isLogin = false;

    /**
     * session中存储的session 变量属性值
     */
    private Map<String, Object> map = new HashMap<>();

    public ServerSession(Channel channel) {
        this.channel = channel;
        this.sessionId = buildNewSessionId();
    }

    /**
     * 反向导航
     *
     * @param ctx
     * @return
     */
    public static ServerSession getSession(ChannelHandlerContext ctx) {
        Channel channel = ctx.channel();
        return channel.attr(ServerSession.SESSION_KEY).get();
    }

    /**
     * 关闭连接
     *
     * @param ctx
     */
    public static void closeSession(ChannelHandlerContext ctx) {
        ServerSession session = ctx.channel().attr(ServerSession.SESSION_KEY).get();

        if (null != session && session.isValid()) {
            session.close();
            SessionMap.inst().removeSession(session.sessionId);
        }
    }

    /**
     * 和 channel 通道实现双向绑定
     */
    public void bind() {
        log.info(" ServerSession 绑定会话 " + channel.remoteAddress());
        channel.attr(ServerSession.SESSION_KEY).set(this);
        SessionMap.inst().addSession(this);
        isLogin = true;
    }

    public void unbind() {
        isLogin = false;
        SessionMap.inst().removeSession(getSessionId());
        this.close();
    }

    public String getSessionId() {
        return sessionId;
    }

    private static String buildNewSessionId() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public synchronized void set(String key, Object value) {
        map.put(key, value);
    }

    public synchronized <T> T get(String key) {
        return (T) map.get(key);
    }


    public boolean isValid() {
        return user != null;
    }

    /**
     * 写 Protobuf数据帧
     *
     * @param pkg
     */
    public synchronized void writeAndFlush(Object pkg) {
        channel.writeAndFlush(pkg);
    }

    /**
     * 关闭连接
     */
    public synchronized void close() {
        ChannelFuture future = channel.close();
        future.addListener(
                (ChannelFutureListener) future1 -> {
                    if (!future1.isSuccess()) {
                        log.error("CHANNEL_CLOSED error ");
                    }
                }
        );
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
        user.setSessionId(sessionId);
    }
}