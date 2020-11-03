package cn.lollipop.protobuilder;

import cn.lollipop.common.bean.msg.ProtoMsg;
import cn.lollipop.common.constant.ProtoConstant;
import org.springframework.stereotype.Service;

@Service
public class LoginResponseBuilder {
    /**
     * 登录应答 应答消息protobuf
     */
    public ProtoMsg.Message loginResponse(ProtoConstant.ResultCodeEnum en, long seqId, String sessionId) {
        ProtoMsg.Message.Builder mb =
                ProtoMsg.Message.newBuilder()
                        // 设置消息类型
                        .setType(ProtoMsg.HeadType.LOGIN_RESPONSE)
                        .setSequence(seqId)
                        // 设置应答流水，与请求对应
                        .setSessionId(sessionId);

        ProtoMsg.LoginResponse.Builder b =
                ProtoMsg.LoginResponse.newBuilder()
                        .setCode(en.getCode())
                        .setInfo(en.getDesc())
                        .setExpose(1);

        return mb.setLoginResponse(b.build()).build();
    }
}