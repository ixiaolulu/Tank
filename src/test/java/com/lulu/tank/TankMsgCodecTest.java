package com.lulu.tank;

import com.lulu.netty.study.v3.TankMsg;
import com.lulu.netty.study.v3.TankMsgDecoder;
import com.lulu.netty.study.v3.TankMsgEncoder;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.Assert;
import org.junit.Test;

/**
 * @Description:
 * @Author: Milo
 * @Date: 2020-06-20 10:55
 */
public class TankMsgCodecTest {

    @Test
    public void testTankMsgEncoder() {
        TankMsg msg = new TankMsg(3, 6);
        EmbeddedChannel channel = new EmbeddedChannel(new TankMsgEncoder());
        channel.writeOutbound(msg);
        ByteBuf buf = channel.readOutbound();
        int x = buf.readInt();
        int y = buf.readInt();
        Assert.assertTrue(x == 3 && y == 6);
        buf.release();
    }


    @Test
    public void testTankMsgDecoder() {
        ByteBuf buf = Unpooled.buffer();
        TankMsg tankMsg = new TankMsg(1, 2);
        buf.writeInt(tankMsg.x);
        buf.writeInt(tankMsg.y);
        EmbeddedChannel channel = new EmbeddedChannel(new TankMsgEncoder(), new TankMsgDecoder());
        channel.writeInbound(buf.duplicate());

        TankMsg msg = channel.readInbound();

        Assert.assertTrue(msg.x == 1 && msg.y == 2);
    }


}
