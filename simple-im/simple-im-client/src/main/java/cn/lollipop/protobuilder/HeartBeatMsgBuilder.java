package cn.lollipop.protobuilder;

import cn.lollipop.client.ClientSession;
import cn.lollipop.common.bean.User;
import cn.lollipop.common.bean.msg.ProtoMsg;

/**
 * 心跳消息Builder
 *
 * @author zhangyuanhang
 */
public class HeartBeatMsgBuilder extends BaseBuilder {
    private final User user;

    public HeartBeatMsgBuilder(User user, ClientSession session) {
        super(ProtoMsg.HeadType.KEEPALIVE_REQUEST, session);
        this.user = user;
    }

    public ProtoMsg.Message buildMsg() {
        ProtoMsg.Message message = buildCommon(-1);
        ProtoMsg.HeartBeatRequest.Builder lb =
                ProtoMsg.HeartBeatRequest.newBuilder()
                        .setSeq(0)
                        .setJson("{\"from\":\"client\"}")
                        .setUid(user.getUid());
        return message.toBuilder().setHeartBeatRequest(lb).build();
    }
}