package com.lulu.tank.net;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class MsgDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if (in.readableBytes() < 8) return; //消息类型+消息体长度=8

        in.markReaderIndex();

        MsgType msgType = MsgType.values()[in.readInt()];
        int len = in.readInt();

        if (in.readableBytes() < len) {
            in.resetReaderIndex();
            return;
        }

        //读完了，创建一个消息体长度的字节数组，把消息体内容读取到bytes里面去
        byte[] bytes = new byte[len];
        in.readBytes(bytes);

        switch (msgType) {
            case TankJoin:
                TankJoinMsg msg = new TankJoinMsg();
                msg.parse(bytes);
                out.add(msg);
                break;
            case TankStartMoving:
                TankStartMovingMsg movingMsg = new TankStartMovingMsg();
                movingMsg.parse(bytes);
                out.add(movingMsg);
                break;
            case TankStop:
                TankStopMsg stopMsg = new TankStopMsg();
                stopMsg.parse(bytes);
                out.add(stopMsg);
                break;
            case TankDirChanged:
                TankDirChangedMsg dirChangedMsg = new TankDirChangedMsg();
                dirChangedMsg.parse(bytes);
                out.add(dirChangedMsg);
                break;
            case BulletNew:
                BulletNewMsg bulletNewMsg = new BulletNewMsg();
                bulletNewMsg.parse(bytes);
                out.add(bulletNewMsg);
                break;
            case TankDie:
                TankDieMsg dieMsg = new TankDieMsg();
                dieMsg.parse(bytes);
                out.add(dieMsg);
                break;
            default:
                break;

        }

    }

}
