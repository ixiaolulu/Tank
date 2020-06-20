package com.lulu.tank.net;

import com.lulu.tank.Dir;
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
 * @Date: 2020-06-20 19:09
 */
public class TankStartMovingMsg extends Msg {

    public int x, y;
    public Dir dir;
    public UUID id;


    public TankStartMovingMsg(int x, int y, Dir dir, UUID id) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.id = id;
    }


    public TankStartMovingMsg(Tank t) {
        this.x = t.getX();
        this.y = t.getY();
        this.dir = t.getDir();
        this.id = t.getId();
    }

    public TankStartMovingMsg() {
    }

    @Override
    public void handle() {
        if (this.id.equals(TankFrame.INSTANCE.getMainTank().getId()))
            return;
        Tank t = TankFrame.INSTANCE.findTankByUUID(this.id);
        if (t != null) {
            t.setMoving(true);
            t.setX(this.x);
            t.setY(this.y);
            t.setDir(dir);
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

            dos.writeInt(this.x);//4
            dos.writeInt(this.y);//4
            dos.writeInt(dir.ordinal());//4
            dos.writeLong(this.id.getMostSignificantBits());//8
            dos.writeLong(this.id.getLeastSignificantBits());//8

            //12+16=28字节
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
            this.x = dis.readInt();
            this.y = dis.readInt();
            this.dir = Dir.values()[dis.readInt()];
            this.id = new UUID(dis.readLong(), dis.readLong());

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
        return MsgType.TankStartMoving;
    }

    @Override
    public String toString() {
        return "TankStartMovingMsg{" +
                "x=" + x +
                ", y=" + y +
                ", dir=" + dir +
                ", id=" + id +
                '}';
    }
}
