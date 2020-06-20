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
 * @Date: 2020-06-20 21:23
 */
public class TankDirChangedMsg extends Msg {

    public int x, y;
    public Dir dir;
    public UUID id;

    public TankDirChangedMsg(int x, int y, Dir dir, UUID id) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.id = id;
    }

    public TankDirChangedMsg() {

    }

    public TankDirChangedMsg(Tank t) {
        this.x = t.getX();
        this.y = t.getY();
        this.dir = t.getDir();
        this.id = t.getId();
    }

    @Override
    public void handle() {

        if (this.id.equals(TankFrame.INSTANCE.getMainTank().getId())) return;

        Tank t = TankFrame.INSTANCE.findTankByUUID(this.id);
        if (t != null) {
            t.setX(this.x);
            t.setY(this.y);
            t.setDir(this.dir);
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

            dos.writeInt(this.x);
            dos.writeInt(this.y);
            dos.writeInt(this.dir.ordinal());//4
            dos.writeLong(this.id.getMostSignificantBits());//8
            dos.writeLong(this.id.getLeastSignificantBits());//8

            //4+16=20字节
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
        return MsgType.TankDirChanged;
    }

    @Override
    public String toString() {
        return "TankDirChangedMsg{" +
                "x=" + x +
                ", y=" + y +
                ", dir=" + dir +
                ", id=" + id +
                '}';
    }
}
