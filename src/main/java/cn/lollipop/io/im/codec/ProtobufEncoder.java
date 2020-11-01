package cn.lollipop.io.im.codec;

import cn.lollipop.io.im.proto.protoData;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import lombok.extern.slf4j.Slf4j;

/**
 * protobuf 编码器
 *
 * @author lollipop
 * @date 2020/11/2
 */
@Slf4j
public class ProtobufEncoder extends MessageToByteEncoder<protoData.Data> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, protoData.Data data, ByteBuf out) throws Exception {
        // 将对象转化为字节
        byte[] bytes = data.toByteArray();

        // 读取消息的长度
        int len = bytes.length;

        // 写入消息头,即消息长度
        out.writeShort(len);

        // 写入消息体
        out.writeBytes(bytes);
    }
}
