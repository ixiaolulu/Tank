package com.lulu.tank;

import com.lulu.tank.net.TankJoinMsg;
import org.junit.Assert;
import org.junit.Test;

import java.util.UUID;

/**
 * @Description:
 * @Author: Milo
 * @Date: 2020-06-20 12:45
 */
public class TankJoinMsgTest {


    @Test
    public void testToBytesAndParse() {
        TankJoinMsg msg = new TankJoinMsg(2, 5, Dir.DOWN, true, Group.BAD, UUID.randomUUID());
        byte[] bytes = msg.toBytes();

        System.out.println(msg);
        TankJoinMsg newMsg = new TankJoinMsg();
        newMsg.parse(bytes);
        Assert.assertTrue(msg.x == newMsg.x);
        Assert.assertTrue(msg.y == newMsg.y);
        Assert.assertTrue(msg.dir == newMsg.dir);
        Assert.assertTrue(msg.moving == newMsg.moving);
        Assert.assertTrue(msg.group == newMsg.group);
        Assert.assertTrue(msg.id.equals(newMsg.id));

        System.out.println(newMsg);
    }
}
