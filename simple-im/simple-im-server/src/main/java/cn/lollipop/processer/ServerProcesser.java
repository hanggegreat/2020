package cn.lollipop.processer;

import cn.lollipop.common.bean.msg.ProtoMsg;
import cn.lollipop.server.ServerSession;

/**
 * 操作接口
 */
public interface ServerProcesser {
    ProtoMsg.HeadType type();

    boolean action(ServerSession ch, ProtoMsg.Message proto);
}