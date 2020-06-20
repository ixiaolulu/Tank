package com.lulu.tank.net;

import com.lulu.tank.Bullet;
import com.lulu.tank.Tank;
import com.lulu.tank.TankFrame;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.UUID;

/**
 * @Description:
 * @Author: Milo
 * @Date: 2020-06-20 22:24
 */
public class TankDieMsg extends Msg {

    // which tank die
    public UUID id;

    //who kill me
    public UUID bulletId;

    public TankDieMsg(UUID id, UUID bulletId) {
        this.id = id;
        this.bulletId = bulletId;
    }

    public TankDieMsg() {
    }


    @Override
    public void handle() {
        System.out.println("we got a tank die:" + id);
        System.out.println("and my tank is:" + TankFrame.INSTANCE.getMainTank().getId());
        Tank tt = TankFrame.INSTANCE.findTankByUUID(this.id);
        System.out.println("i found a tank with this id:" + tt);

        Bullet bullet = TankFrame.INSTANCE.finsBulletByUUID(this.bulletId);
        if (bullet != null) {
            bullet.die();
        }

        if (this.id.equals(TankFrame.INSTANCE.getMainTank().getId())) {
            TankFrame.INSTANCE.getMainTank().die();
        } else {
            Tank t = TankFrame.INSTANCE.findTankByUUID(this.id);
            if (t != null) {
                t.die();
            }
        }

    }

    @Override
    public byte[] toBytes() {
        ByteArrayOutputStream baos = null;
        DataOutputStream dos = null;
        byte[] bytes = null;
        try {

            baos = new ByteArrayOutputStream();
            dos = new DataOutputStream(baos);

            dos.writeLong(this.id.getMostSignificantBits());//8
            dos.writeLong(this.id.getLeastSignificantBits());//8
            dos.writeLong(this.bulletId.getMostSignificantBits());//8
            dos.writeLong(this.bulletId.getLeastSignificantBits());//8

            //32
            dos.flush();
            bytes = baos.toByteArray();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (baos != null) {
                try {
                    baos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (dos != null) {
                try {
                    dos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return bytes;
    }

    @Override
    public void parse(byte[] bytes) {
        DataInputStream dis = new DataInputStream(new ByteArrayInputStream(bytes));
        try {
            this.id = new UUID(dis.readLong(), dis.readLong());
            this.bulletId = new UUID(dis.readLong(), dis.readLong());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (dis != null) {
                try {
                    dis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public MsgType getMsgType() {
        return MsgType.TankDie;
    }

    @Override
    public String toString() {
        return "TankDieMsg{" +
                "id=" + id +
                ", bulletId=" + bulletId +
                '}';
    }
}
