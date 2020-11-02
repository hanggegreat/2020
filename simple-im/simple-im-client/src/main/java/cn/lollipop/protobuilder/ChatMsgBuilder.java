package cn.lollipop.protobuilder;

import cn.lollipop.client.ClientSession;
import cn.lollipop.common.bean.ChatMsg;
import cn.lollipop.common.bean.User;
import cn.lollipop.common.bean.msg.ProtoMsg;

/**
 * 聊天消息Builder
 *
 * @author zhangyuanhang
 */

public class ChatMsgBuilder extends BaseBuilder {
    private ChatMsg chatMsg;
    private User user;

    public ChatMsgBuilder(ChatMsg chatMsg, User user, ClientSession session) {
        super(ProtoMsg.HeadType.MESSAGE_REQUEST, session);
        this.chatMsg = chatMsg;
        this.user = user;
    }

    public ProtoMsg.Message build() {
        ProtoMsg.Message message = buildCommon(-1);
        ProtoMsg.MessageRequest.Builder cb
                = ProtoMsg.MessageRequest.newBuilder();

        chatMsg.fillMsg(cb);
        return message
                .toBuilder()
                .setMessageRequest(cb)
                .build();
    }

    public static ProtoMsg.Message buildChatMsg(ChatMsg chatMsg, User user, ClientSession session) {
        return new ChatMsgBuilder(chatMsg, user, session).build();
    }
}