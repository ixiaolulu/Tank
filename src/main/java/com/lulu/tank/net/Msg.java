package com.lulu.tank.net;

/**
 * @Description:
 * @Author: Milo
 * @Date: 2020-06-20 16:25
 */
public abstract class Msg {

    public abstract void handle();

    public abstract byte[] toBytes();

    public abstract void parse(byte[] bytes);

    public abstract MsgType getMsgType();
}
