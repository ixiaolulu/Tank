package com.lulu.tank.net;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class MsgEncoder extends MessageToByteEncoder<Msg> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Msg msg, ByteBuf buf) throws Exception {
        //消息类型 4
        buf.writeInt(msg.getMsgType().ordinal());

        byte[] bytes = msg.toBytes();
        //消息体长度 4
        buf.writeInt(bytes.length);

        //消息体
        buf.writeBytes(bytes);
    }


}
