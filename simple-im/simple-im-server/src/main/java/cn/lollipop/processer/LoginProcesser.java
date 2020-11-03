package cn.lollipop.processer;

import cn.lollipop.common.bean.User;
import cn.lollipop.common.bean.msg.ProtoMsg;
import cn.lollipop.common.constant.ProtoConstant;
import cn.lollipop.protobuilder.LoginResponseBuilder;
import cn.lollipop.server.ServerSession;
import cn.lollipop.server.SessionMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LoginProcesser extends AbstractServerProcesser {
    private final LoginResponseBuilder loginResponseBuilder;

    @Autowired
    public LoginProcesser(LoginResponseBuilder loginResponseBuilder) {
        this.loginResponseBuilder = loginResponseBuilder;
    }

    @Override
    public ProtoMsg.HeadType type() {
        return ProtoMsg.HeadType.LOGIN_REQUEST;
    }

    @Override
    public boolean action(ServerSession session, ProtoMsg.Message proto) {
        // 取出token验证
        ProtoMsg.LoginRequest info = proto.getLoginRequest();
        long seqNo = proto.getSequence();

        User user = User.fromMsg(info);

        //检查用户
        if (!checkUser(user)) {
            ProtoConstant.ResultCodeEnum resultCode = ProtoConstant.ResultCodeEnum.NO_TOKEN;
            // 构造登录失败的报文
            ProtoMsg.Message response = loginResponseBuilder.loginResponse(resultCode, seqNo, "-1");
            // 发送登录失败的报文
            session.writeAndFlush(response);
            return false;
        }

        session.setUser(user);
        session.bind();

        //登录成功
        ProtoConstant.ResultCodeEnum resultCode = ProtoConstant.ResultCodeEnum.SUCCESS;
        //构造登录成功的报文
        ProtoMsg.Message response = loginResponseBuilder.loginResponse(resultCode, seqNo, session.getSessionId());
        //发送登录成功的报文
        session.writeAndFlush(response);
        return true;
    }

    private boolean checkUser(User user) {
        return !SessionMap.inst().hasLogin(user);

        //校验用户,比较耗时的操作,需要100 ms以上的时间
        //方法1：调用远程用户restfull 校验服务
        //方法2：调用数据库接口校验
    }
}