package cn.lollipop.netty.chat;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.nio.file.Files;
import java.nio.file.Path;

public class CustomHandler extends SimpleChannelInboundHandler<HttpObject> {
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
        if (!(msg instanceof HttpRequest)) {
            return;
        }

        Channel channel = ctx.channel();

        System.out.println(channel.remoteAddress());

        ByteBuf content = Unpooled.copiedBuffer(Files.readString(Path.of(getClass().getResource("/template/index.html").toURI())), CharsetUtil.UTF_8);

        FullHttpResponse response = new DefaultFullHttpResponse(
                HttpVersion.HTTP_1_1,
                HttpResponseStatus.OK,
                content
        );

        response.headers().set(HttpHeaderNames.CONTENT_TYPE, "html");
        response.headers().set(HttpHeaderNames.CONTENT_LENGTH, content.readableBytes());

        ctx.writeAndFlush(response);
    }
}
