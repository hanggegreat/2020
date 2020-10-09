package cn.lollipop.io.netty.protocol;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

public class ServerChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        pipeline.addLast("messageProtocolDecoder", new MessageProtocolDecoder());
        pipeline.addLast("messageProtocolEncoder", new MessageProtocolEncoder());
        pipeline.addLast("customerServerHandler", new CustomerServerHandler());
    }
}