package com.lulu.tank;

import com.lulu.tank.net.MsgDecoder;
import com.lulu.tank.net.MsgEncoder;
import com.lulu.tank.net.MsgType;
import com.lulu.tank.net.TankJoinMsg;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.Assert;
import org.junit.Test;

import java.util.UUID;

/**
 * @Description:
 * @Author: Milo
 * @Date: 2020-06-20 14:52
 */
public class TankJoinMsgCodecTest {


    @Test
    public void testEncoder() {
        EmbeddedChannel channel = new EmbeddedChannel();
        channel.pipeline().addLast(new MsgEncoder());

        TankJoinMsg msg = new TankJoinMsg(5, 8, Dir.RIGHT, false, Group.BAD, UUID.randomUUID());
        channel.writeOutbound(msg);

        ByteBuf buf = channel.readOutbound();


        MsgType msgType = MsgType.values()[buf.readInt()];
        Assert.assertEquals(msg.getMsgType(), msgType);

        int length = buf.readInt();
        Assert.assertEquals(msg.toBytes().length, length);

        int x = buf.readInt();
        int y = buf.readInt();
        Dir dir = Dir.values()[buf.readInt()];
        boolean moving = buf.readBoolean();
        Group group = Group.values()[buf.readInt()];
        UUID id = new UUID(buf.readLong(), buf.readLong());

        Assert.assertEquals(msg.x, x);
        Assert.assertEquals(msg.y, y);
        Assert.assertEquals(msg.dir, dir);
        Assert.assertEquals(msg.moving, moving);
        Assert.assertEquals(msg.group, group);
        Assert.assertEquals(msg.id, id);

    }


    @Test
    public void testDecoder() {

        EmbeddedChannel channel = new EmbeddedChannel();
        channel.pipeline().addLast(new MsgDecoder());

        UUID id = UUID.randomUUID();
        TankJoinMsg msg = new TankJoinMsg(5, 8, Dir.RIGHT, false, Group.BAD, id);

        byte[] bytes = msg.toBytes();
        ByteBuf buf = Unpooled.buffer();


        buf.writeInt(msg.getMsgType().ordinal());
        buf.writeInt(bytes.length);
        buf.writeBytes(bytes);

        channel.writeInbound(buf.duplicate());

        TankJoinMsg newMsg = channel.readInbound();

        Assert.assertEquals(msg.getMsgType(), newMsg.getMsgType());
        Assert.assertEquals(bytes.length, newMsg.toBytes().length);
        Assert.assertEquals(msg.x, newMsg.x);
        Assert.assertEquals(msg.y, newMsg.y);
        Assert.assertEquals(msg.dir, newMsg.dir);
        Assert.assertEquals(msg.moving, newMsg.moving);
        Assert.assertEquals(msg.group, newMsg.group);
        Assert.assertEquals(msg.id, newMsg.id);


    }

}
