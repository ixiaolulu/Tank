package com.lulu.tank;

import java.awt.*;
import java.io.Serializable;

/**
 * @Description:
 * @Author: Milo
 * @Date: 2020-06-07 12:34
 */
public abstract class GameObject implements Serializable {

    public int x, y;

    public abstract void paint(Graphics g);

    public abstract int getWidth();

    public abstract int getHeight();
}
