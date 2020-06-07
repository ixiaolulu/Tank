package com.lulu.tank.decorator;

import com.lulu.tank.GameObject;

import java.awt.*;

/**
 * @Description:
 * @Author: Milo
 * @Date: 2020-06-07 17:55
 */
public abstract class GoDecorator extends GameObject {

    GameObject go;

    public GoDecorator(GameObject go) {
        this.go = go;
    }

    @Override
    public abstract void paint(Graphics g);

}
