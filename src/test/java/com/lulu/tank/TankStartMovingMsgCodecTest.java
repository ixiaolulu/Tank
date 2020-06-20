package com.lulu.tank;

import com.lulu.tank.net.MsgDecoder;
import com.lulu.tank.net.MsgEncoder;
import com.lulu.tank.net.MsgType;
import com.lulu.tank.net.TankStartMovingMsg;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.Assert;
import org.junit.Test;

import java.util.UUID;

/**
 * @Description:
 * @Author: Milo
 * @Date: 2020-06-20 19:19
 */
public class TankStartMovingMsgCodecTest {

    @Test
    public void testEncoder() {
        EmbeddedChannel channel = new EmbeddedChannel();
        channel.pipeline().addLast(new MsgEncoder());

        TankStartMovingMsg movingMsg = new TankStartMovingMsg(2, 4, Dir.RIGHT, UUID.randomUUID());

        channel.writeOutbound(movingMsg);

        ByteBuf buf = channel.readOutbound();

        MsgType msgType = MsgType.values()[buf.readInt()];
        int length = buf.readInt();
        int x = buf.readInt();
        int y = buf.readInt();
        Dir dir = Dir.values()[buf.readInt()];
        UUID id = new UUID(buf.readLong(), buf.readLong());

        Assert.assertEquals(movingMsg.getMsgType(), msgType);
        Assert.assertEquals(movingMsg.toBytes().length, length);
        Assert.assertEquals(movingMsg.x, x);
        Assert.assertEquals(movingMsg.y, y);
        Assert.assertEquals(movingMsg.dir, dir);
        Assert.assertEquals(movingMsg.id, id);

    }

    @Test
    public void testDecoder() {
        EmbeddedChannel channel = new EmbeddedChannel();
        channel.pipeline().addLast(new MsgDecoder());

        TankStartMovingMsg movingMsg = new TankStartMovingMsg(2, 4, Dir.RIGHT, UUID.randomUUID());

        ByteBuf buf = Unpooled.buffer();

        buf.writeInt(movingMsg.getMsgType().ordinal());

        byte[] bytes = movingMsg.toBytes();
        buf.writeInt(bytes.length);
        buf.writeBytes(bytes);

        channel.writeInbound(buf);

        TankStartMovingMsg msg = channel.readInbound();

        Assert.assertEquals(movingMsg.getMsgType(), msg.getMsgType());
        Assert.assertEquals(movingMsg.toBytes().length, msg.toBytes().length);
        Assert.assertEquals(movingMsg.x, msg.x);
        Assert.assertEquals(movingMsg.y, msg.y);
        Assert.assertEquals(movingMsg.dir, msg.dir);
        Assert.assertEquals(movingMsg.id, msg.id);

    }
}
