package cn.lollipop.sender;

import cn.lollipop.common.bean.ChatMsg;
import cn.lollipop.common.bean.msg.ProtoMsg;
import cn.lollipop.protobuilder.ChatMsgBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 聊天推送类
 *
 * @author zhangyuanhang
 */
@Slf4j
@Service
public class ChatSender extends BaseSender {

    public void sendChatMsg(String toUid, String content) {
        log.info("发送消息 startConnectServer");
        ChatMsg chatMsg = new ChatMsg(getUser());
        chatMsg.setContent(content);
        chatMsg.setMsgType(ChatMsg.MSG_TYPE.TEXT);
        chatMsg.setTo(toUid);
        chatMsg.setMsgId(System.currentTimeMillis());
        super.sendMsg(ChatMsgBuilder.buildChatMsg(chatMsg, getUser(), getSession()));
    }

    @Override
    protected void sendSucceed(ProtoMsg.Message message) {
        log.info("发送成功:" + message.getMessageRequest().getContent());
    }

    @Override
    protected void sendFailed(ProtoMsg.Message message) {
        log.info("发送失败:" + message.getMessageRequest().getContent());
    }
}