package cn.lollipop.io.netty.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class MessageProtocolDecoder extends ReplayingDecoder<Void> {

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        log.info("MessageProtocolDecoder.decode被调用");
        int len = byteBuf.readInt();
        byte[] data = new byte[len];
        byteBuf.readBytes(data);

        MessageProtocol messageProtocol = MessageProtocol.builder().len(len).data(data).build();
        list.add(messageProtocol);
    }
}
