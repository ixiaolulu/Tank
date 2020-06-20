package com.lulu.tank;

import com.lulu.tank.net.MsgDecoder;
import com.lulu.tank.net.MsgEncoder;
import com.lulu.tank.net.MsgType;
import com.lulu.tank.net.TankDieMsg;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.Assert;
import org.junit.Test;

import java.util.UUID;

/**
 * @Description:
 * @Author: Milo
 * @Date: 2020-06-20 22:30
 */
public class TankDieMsgCodecTest {
    @Test
    public void testEncoder() {
        EmbeddedChannel channel = new EmbeddedChannel();
        channel.pipeline().addLast(new MsgEncoder());

        UUID id = UUID.randomUUID();
        UUID bulletId = UUID.randomUUID();
        TankDieMsg tankDieMsg = new TankDieMsg(id, bulletId);

        channel.writeOutbound(tankDieMsg);

        ByteBuf buf = channel.readOutbound();

        MsgType msgType = MsgType.values()[buf.readInt()];
        int length = buf.readInt();

        UUID msgId = new UUID(buf.readLong(), buf.readLong());
        UUID msgBulletId = new UUID(buf.readLong(), buf.readLong());

        Assert.assertEquals(tankDieMsg.getMsgType(), msgType);
        Assert.assertEquals(tankDieMsg.toBytes().length, length);
        Assert.assertEquals(tankDieMsg.id, msgId);
        Assert.assertEquals(tankDieMsg.bulletId, msgBulletId);

    }

    @Test
    public void testDecoder() {
        EmbeddedChannel channel = new EmbeddedChannel();
        channel.pipeline().addLast(new MsgDecoder());

        TankDieMsg tankDieMsg = new TankDieMsg(UUID.randomUUID(), UUID.randomUUID());

        ByteBuf buf = Unpooled.buffer();

        buf.writeInt(tankDieMsg.getMsgType().ordinal());
        byte[] bytes = tankDieMsg.toBytes();
        buf.writeInt(bytes.length);
        buf.writeBytes(bytes);
        channel.writeInbound(buf);

        TankDieMsg msg = channel.readInbound();

        Assert.assertEquals(tankDieMsg.getMsgType(), msg.getMsgType());
        Assert.assertEquals(tankDieMsg.toBytes().length, msg.toBytes().length);
        Assert.assertEquals(tankDieMsg.id, msg.id);
        Assert.assertEquals(tankDieMsg.bulletId, msg.bulletId);

    }
}
