package com.lolkt.netty;


import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class NettyServerHandler extends ChannelInboundHandlerAdapter {


    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println(String.format("客户端信息： %s", channel.remoteAddress()));
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Channel channel = ctx.channel();
        String request = (String) msg;
        System.out.println(String.format("客户端发送的消息 %s : %s", channel.remoteAddress(), request));

    /*    ByteBuf result = (ByteBuf) msg;
        byte[] result1 = new byte[result.readableBytes()];
        result.readBytes(result1);*/

//        String response = requestHandler.handle(request);
//        ctx.write(response);
        ctx.flush();


    }


}