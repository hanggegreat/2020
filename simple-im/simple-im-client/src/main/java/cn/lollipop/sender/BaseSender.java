package cn.lollipop.sender;

import cn.lollipop.client.ClientSession;
import cn.lollipop.common.bean.User;
import cn.lollipop.common.bean.msg.ProtoMsg;
import io.netty.channel.Channel;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 基础推送类
 *
 * @author zhangyuanhang
 */
@Data
@Slf4j
public abstract class BaseSender {
    private User user;
    private ClientSession session;

    public boolean isConnected() {
        if (null == session) {
            log.info("session is null");
            return false;
        }

        return session.isConnected();
    }

    public boolean isLogin() {
        if (null == session) {
            log.info("session is null");
            return false;
        }

        return session.isLogin();
    }

    public void sendMsg(ProtoMsg.Message message) {
        if (null == getSession() || !isConnected()) {
            log.info("连接还没成功");
            return;
        }

        Channel channel = getSession().getChannel();
        channel
                .writeAndFlush(message)
                .addListener(future -> {
                    if (future.isSuccess()) {
                        sendSucceed(message);
                    } else {
                        sendFailed(message);
                    }
                });

    }

    protected void sendSucceed(ProtoMsg.Message message) {
        log.info("发送成功");
    }

    protected void sendFailed(ProtoMsg.Message message) {
        log.info("发送失败");
    }
}