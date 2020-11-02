package cn.lollipop.sender;

import cn.lollipop.protobuilder.LoginMsgBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 登录推送类
 *
 * @author zhangyuanhang
 */
@Slf4j
@Service
public class LoginSender extends BaseSender {
    public void sendLoginMsg() {
        if (!isConnected()) {
            log.info("还没有建立连接!");
            return;
        }

        log.info("发送登录消息");
        super.sendMsg(LoginMsgBuilder.buildLoginMsg(getUser(), getSession()));
    }
}