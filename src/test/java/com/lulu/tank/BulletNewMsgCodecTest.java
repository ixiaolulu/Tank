package com.lulu.tank;

import com.lulu.tank.net.BulletNewMsg;
import com.lulu.tank.net.MsgDecoder;
import com.lulu.tank.net.MsgEncoder;
import com.lulu.tank.net.MsgType;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.Assert;
import org.junit.Test;

import java.util.UUID;

/**
 * @Description:
 * @Author: Milo
 * @Date: 2020-06-20 22:04
 */
public class BulletNewMsgCodecTest {
    @Test
    public void testEncoder() {
        EmbeddedChannel channel = new EmbeddedChannel();
        channel.pipeline().addLast(new MsgEncoder());

        BulletNewMsg newMsg = new BulletNewMsg(2, 4, Dir.LEFT, Group.GOOD, UUID.randomUUID(), UUID.randomUUID());

        channel.writeOutbound(newMsg);

        ByteBuf buf = channel.readOutbound();

        MsgType msgType = MsgType.values()[buf.readInt()];
        int length = buf.readInt();
        int x = buf.readInt();
        int y = buf.readInt();
        Dir dir = Dir.values()[buf.readInt()];
        Group group = Group.values()[buf.readInt()];
        UUID playerId = new UUID(buf.readLong(), buf.readLong());
        UUID id = new UUID(buf.readLong(), buf.readLong());

        Assert.assertEquals(newMsg.getMsgType(), msgType);
        Assert.assertEquals(newMsg.toBytes().length, length);
        Assert.assertEquals(newMsg.x, x);
        Assert.assertEquals(newMsg.y, y);
        Assert.assertEquals(newMsg.dir, dir);
        Assert.assertEquals(newMsg.group, group);
        Assert.assertEquals(newMsg.playerId, playerId);
        Assert.assertEquals(newMsg.id, id);

    }

    @Test
    public void testDecoder() {
        EmbeddedChannel channel = new EmbeddedChannel();
        channel.pipeline().addLast(new MsgDecoder());

        BulletNewMsg newMsg = new BulletNewMsg(2, 4, Dir.LEFT, Group.GOOD, UUID.randomUUID(), UUID.randomUUID());

        ByteBuf buf = Unpooled.buffer();

        buf.writeInt(newMsg.getMsgType().ordinal());
        byte[] bytes = newMsg.toBytes();
        buf.writeInt(bytes.length);
        buf.writeBytes(bytes);
        channel.writeInbound(buf);

        BulletNewMsg msg = channel.readInbound();

        Assert.assertEquals(newMsg.getMsgType(), msg.getMsgType());
        Assert.assertEquals(newMsg.toBytes().length, msg.toBytes().length);
        Assert.assertEquals(newMsg.x, msg.x);
        Assert.assertEquals(newMsg.y, msg.y);
        Assert.assertEquals(newMsg.dir, msg.dir);
        Assert.assertEquals(newMsg.group, msg.group);
        Assert.assertEquals(newMsg.playerId, msg.playerId);
        Assert.assertEquals(newMsg.id, msg.id);

    }
}
