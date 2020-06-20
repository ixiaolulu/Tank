package com.lulu.tank;

import com.lulu.tank.net.MsgDecoder;
import com.lulu.tank.net.MsgEncoder;
import com.lulu.tank.net.MsgType;
import com.lulu.tank.net.TankDirChangedMsg;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.Assert;
import org.junit.Test;

import java.util.UUID;

/**
 * @Description:
 * @Author: Milo
 * @Date: 2020-06-20 21:31
 */
public class TankDirChangedMsgCodecTest {
    @Test
    public void testEncoder() {
        EmbeddedChannel channel = new EmbeddedChannel();
        channel.pipeline().addLast(new MsgEncoder());

        TankDirChangedMsg dirChangedMsg = new TankDirChangedMsg(1, 5, Dir.DOWN, UUID.randomUUID());

        channel.writeOutbound(dirChangedMsg);

        ByteBuf buf = channel.readOutbound();

        MsgType msgType = MsgType.values()[buf.readInt()];
        int length = buf.readInt();
        int x = buf.readInt();
        int y = buf.readInt();
        Dir dir = Dir.values()[buf.readInt()];
        UUID id = new UUID(buf.readLong(), buf.readLong());

        Assert.assertEquals(dirChangedMsg.getMsgType(), msgType);
        Assert.assertEquals(dirChangedMsg.toBytes().length, length);
        Assert.assertEquals(dirChangedMsg.x, x);
        Assert.assertEquals(dirChangedMsg.y, y);
        Assert.assertEquals(dirChangedMsg.dir, dir);
        Assert.assertEquals(dirChangedMsg.id, id);

    }

    @Test
    public void testDecoder() {
        EmbeddedChannel channel = new EmbeddedChannel();
        channel.pipeline().addLast(new MsgDecoder());

        TankDirChangedMsg dirChangedMsg = new TankDirChangedMsg(1, 5, Dir.DOWN, UUID.randomUUID());

        ByteBuf buf = Unpooled.buffer();

        buf.writeInt(dirChangedMsg.getMsgType().ordinal());
        byte[] bytes = dirChangedMsg.toBytes();
        buf.writeInt(bytes.length);
        buf.writeBytes(bytes);
        channel.writeInbound(buf);

        TankDirChangedMsg msg = channel.readInbound();

        Assert.assertEquals(dirChangedMsg.getMsgType(), msg.getMsgType());
        Assert.assertEquals(dirChangedMsg.toBytes().length, msg.toBytes().length);
        Assert.assertEquals(dirChangedMsg.x, msg.x);
        Assert.assertEquals(dirChangedMsg.y, msg.y);
        Assert.assertEquals(dirChangedMsg.dir, msg.dir);
        Assert.assertEquals(dirChangedMsg.id, msg.id);

    }
}
