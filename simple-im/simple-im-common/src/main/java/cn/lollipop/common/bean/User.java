package cn.lollipop.common.bean;

import cn.lollipop.common.bean.msg.ProtoMsg;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class User {

    String uid;
    String devId;
    String token;
    String nickName = "nickName";
    PLATTYPE platform = PLATTYPE.WINDOWS;

    // windows,mac,android, ios, web , other
    public enum PLATTYPE {
        WINDOWS, MAC, ANDROID, IOS, WEB, OTHER;
    }

    private String sessionId;


    public void setPlatform(int platform) {
        PLATTYPE[] values = PLATTYPE.values();
        for (int i = 0; i < values.length; i++) {
            if (values[i].ordinal() == platform) {
                this.platform = values[i];
            }
        }

    }

    public static User fromMsg(ProtoMsg.LoginRequest info) {
        User user = new User();
        user.uid = info.getUid();
        user.devId = info.getDeviceId();
        user.token = info.getToken();
        user.setPlatform(info.getPlatform());
        log.info("登录中: {}", user.toString());
        return user;

    }

}