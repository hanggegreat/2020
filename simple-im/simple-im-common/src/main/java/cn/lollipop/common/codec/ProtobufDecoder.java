package cn.lollipop.common.codec;

import cn.lollipop.common.bean.msg.ProtoMsg;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * protobuf 解码器
 *
 * @author lollipop
 * @date 2020/11/2
 */
@Slf4j
public class ProtobufDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf in, List<Object> out) throws Exception {
        // 标记一下当前的读指针 readIndex 的位置
        in.markReaderIndex();

        // 判断包头的长度
        if (in.readableBytes() < Short.BYTES) {
            // 长度不够
            return;
        }

        // 读取传送过来的的消息体的长度
        int len = in.readShort();

        // 判断消息体的长度
        if (in.readableBytes() < len) {
            // 长度不够
            in.resetReaderIndex();
            return;
        }

        byte[] content;
        if (in.hasArray()) {
            // 堆缓冲区
            ByteBuf slice = in.slice();
            content = slice.array();
        } else {
            // 直接缓冲区
            content = new byte[len];
            in.readBytes(content, 0, len);
        }

        // 将字节数组转化为 Protobuf 对应的 POJO 类对象
        ProtoMsg.Message msg = ProtoMsg.Message.parseFrom(content);
        if (msg == null) {
            return;
        }

        // 传送给下一个 handler
        out.add(msg);
    }
}