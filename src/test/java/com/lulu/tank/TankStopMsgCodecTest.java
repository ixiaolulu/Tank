package com.lulu.tank;

import com.lulu.tank.net.MsgDecoder;
import com.lulu.tank.net.MsgEncoder;
import com.lulu.tank.net.MsgType;
import com.lulu.tank.net.TankStopMsg;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.Assert;
import org.junit.Test;

import java.util.UUID;

/**
 * @Description:
 * @Author: Milo
 * @Date: 2020-06-20 20:49
 */
public class TankStopMsgCodecTest {
    @Test
    public void testEncoder() {
        EmbeddedChannel channel = new EmbeddedChannel();
        channel.pipeline().addLast(new MsgEncoder());

        TankStopMsg stopMsg = new TankStopMsg(2, 4, UUID.randomUUID());

        channel.writeOutbound(stopMsg);

        ByteBuf buf = channel.readOutbound();

        MsgType msgType = MsgType.values()[buf.readInt()];
        int length = buf.readInt();
        int x = buf.readInt();
        int y = buf.readInt();
        UUID id = new UUID(buf.readLong(), buf.readLong());

        Assert.assertEquals(stopMsg.getMsgType(), msgType);
        Assert.assertEquals(stopMsg.toBytes().length, length);
        Assert.assertEquals(stopMsg.x, x);
        Assert.assertEquals(stopMsg.y, y);
        Assert.assertEquals(stopMsg.id, id);

    }

    @Test
    public void testDecoder() {
        EmbeddedChannel channel = new EmbeddedChannel();
        channel.pipeline().addLast(new MsgDecoder());

        TankStopMsg stopMsg = new TankStopMsg(2, 4, UUID.randomUUID());

        ByteBuf buf = Unpooled.buffer();

        buf.writeInt(stopMsg.getMsgType().ordinal());
        byte[] bytes = stopMsg.toBytes();
        buf.writeInt(bytes.length);
        buf.writeBytes(bytes);
        channel.writeInbound(buf);

        TankStopMsg msg = channel.readInbound();

        Assert.assertEquals(stopMsg.getMsgType(), msg.getMsgType());
        Assert.assertEquals(stopMsg.toBytes().length, msg.toBytes().length);
        Assert.assertEquals(stopMsg.x, msg.x);
        Assert.assertEquals(stopMsg.y, msg.y);
        Assert.assertEquals(stopMsg.id, msg.id);

    }
}
