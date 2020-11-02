package cn.lollipop.common.bean;

import cn.lollipop.common.bean.msg.ProtoMsg;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.Data;

/**
 * 聊天消息类
 *
 * @author zhangyuanhang
 */
@Data
public class ChatMsg {
    /**
     * 消息类型
     */
    public enum MSG_TYPE {
        // 纯文本
        TEXT,
        // 音频
        AUDIO,
        // 视频
        VIDEO,
        // 地理位置
        POS,
        // 其他
        OTHER
    }

    public ChatMsg(User user) {
        if (null == user) {
            return;
        }

        this.user = user;
        this.setTime(System.currentTimeMillis());
        this.setFrom(user.getUid());
        this.setFromNick(user.getNickName());
    }

    private User user;

    private long msgId;
    private String from;
    private String to;
    private long time;
    private MSG_TYPE msgType;
    private String content;

    /**
     * 多媒体地址
     */
    private String url;

    /**
     * 附加属性
     */
    private String property;

    /**
     * 发送者昵称
     */
    private String fromNick;

    /**
     * 附加的json串
     */
    private String json;


    public void fillMsg(ProtoMsg.MessageRequest.Builder cb) {
        if (msgId > 0) {
            cb.setMsgId(msgId);
        }

        if (StringUtils.isNotEmpty(from)) {
            cb.setFrom(from);
        }
        if (StringUtils.isNotEmpty(to)) {
            cb.setTo(to);
        }
        if (time > 0) {
            cb.setTime(time);
        }
        if (msgType != null) {
            cb.setMsgType(msgType.ordinal());
        }
        if (StringUtils.isNotEmpty(content)) {
            cb.setContent(content);
        }
        if (StringUtils.isNotEmpty(url)) {
            cb.setUrl(url);
        }
        if (StringUtils.isNotEmpty(property)) {
            cb.setProperty(property);
        }
        if (StringUtils.isNotEmpty(fromNick)) {
            cb.setFromNick(fromNick);
        }
        if (StringUtils.isNotEmpty(json)) {
            cb.setJson(json);
        }
    }

}