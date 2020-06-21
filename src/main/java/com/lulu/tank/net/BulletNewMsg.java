package com.lulu.tank.net;

import com.lulu.tank.Bullet;
import com.lulu.tank.Dir;
import com.lulu.tank.Group;
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
 * @Date: 2020-06-20 21:48
 */
public class BulletNewMsg extends Msg {

    public int x, y;
    public Dir dir;
    public Group group;
    public UUID playerId;
    public UUID id;


    public BulletNewMsg(int x, int y, Dir dir, Group group, UUID playerId, UUID id) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.playerId = playerId;
        this.id = id;
    }

    public BulletNewMsg() {
    }

    public BulletNewMsg(Bullet b) {
        this.x = b.getX();
        this.y = b.getY();
        this.dir = b.getDir();
        this.group = b.getGroup();
        this.playerId = b.getPlayerId();
        this.id = b.getId();
    }

    @Override
    public void handle() {
        if (this.playerId.equals(TankFrame.INSTANCE.getMainTank().getId())) return;

        Bullet bullet = new Bullet(this.playerId, x, y, dir, group);
        bullet.setId(this.id);
        TankFrame.INSTANCE.addBullet(bullet);

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
            dos.writeInt(this.group.ordinal());
            dos.writeLong(this.playerId.getMostSignificantBits());//8
            dos.writeLong(this.playerId.getLeastSignificantBits());//8
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
            this.group = Group.values()[dis.readInt()];
            this.playerId = new UUID(dis.readLong(), dis.readLong());
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
        return MsgType.BulletNew;
    }

    @Override
    public String toString() {
        return "BulletNewMsg{" +
                "x=" + x +
                ", y=" + y +
                ", dir=" + dir +
                ", group=" + group +
                ", playerId=" + playerId +
                ", id=" + id +
                '}';
    }
}
