package cn.lollipop.protobuilder;

import cn.lollipop.common.bean.msg.ProtoMsg;
import cn.lollipop.common.constant.ProtoConstant;

/**
 * 响应消息构造类
 *
 * @author zhangyuanhang
 */
public class ChatMsgBuilder {

    public static ProtoMsg.Message chatResponse(long seqId, ProtoConstant.ResultCodeEnum en) {
        ProtoMsg.Message.Builder mb =
                ProtoMsg.Message.newBuilder()
                        // 设置消息类型
                        .setType(ProtoMsg.HeadType.MESSAGE_RESPONSE)
                        // 设置应答流水，与请求对应
                        .setSequence(seqId);

        ProtoMsg.MessageResponse.Builder rb =
                ProtoMsg.MessageResponse.newBuilder()
                        .setCode(en.getCode())
                        .setInfo(en.getDesc())
                        .setExpose(1);

        return mb.setMessageResponse(rb.build()).build();
    }
}