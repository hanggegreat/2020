package cn.lollipop.processer;

import cn.lollipop.common.bean.msg.ProtoMsg;
import cn.lollipop.server.ServerSession;
import cn.lollipop.server.SessionMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ChatRedirectProcesser extends AbstractServerProcesser {
    @Override
    public ProtoMsg.HeadType type() {
        return ProtoMsg.HeadType.MESSAGE_REQUEST;
    }

    @Override
    public boolean action(ServerSession fromSession, ProtoMsg.Message proto) {
        // 聊天处理
        ProtoMsg.MessageRequest msg = proto.getMessageRequest();
        log.info(
                "chatMsg | from="
                        + msg.getFrom()
                        + " , to=" + msg.getTo()
                        + " , content=" + msg.getContent()
        );

        // 获取接收方的chatID
        String to = msg.getTo();
        List<ServerSession> toSessions = SessionMap.inst().getSessionsBy(to);
        if (toSessions == null) {
            //接收方离线
            log.info("[" + to + "] 不在线，发送失败!");
        } else {
            // 将IM消息发送到接收方
            toSessions.forEach(session -> session.writeAndFlush(proto));
        }
        return true;
    }

}