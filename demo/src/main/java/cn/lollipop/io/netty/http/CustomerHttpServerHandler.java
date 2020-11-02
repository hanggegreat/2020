package cn.lollipop.io.netty.http;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomerHttpServerHandler extends SimpleChannelInboundHandler<HttpObject> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, HttpObject msg) throws Exception {
        if (!(msg instanceof HttpRequest)) {
            return;
        }

        HttpRequest httpRequest = (HttpRequest) msg;
        if (httpRequest.uri().contains("/favicon.ico")) {
            log.info("忽略本次请求");
        }

        log.info("客户端地址：{}", channelHandlerContext.channel().remoteAddress());
        log.info("msg 类型：{}", msg.getClass());

        ByteBuf content = Unpooled.copiedBuffer("hello, 我是服务器", CharsetUtil.UTF_8);

        DefaultFullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, content);
        response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain; charset=utf-8");
        response.headers().set(HttpHeaderNames.CONTENT_LENGTH, content.readableBytes());

        channelHandlerContext.writeAndFlush(response);
    }
}
