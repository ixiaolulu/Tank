package com.lulu.tank.abstractfactory;

import com.lulu.tank.Group;

import java.awt.*;

/**
 * @Description:
 * @Author: Milo
 * @Date: 2020-06-06 13:22
 */
public abstract class BaseTank {
    public Group group = Group.BAD;
    public Rectangle rect = new Rectangle();

    public abstract void paint(Graphics g);

    public Group getGroup() {
        return this.group;
    }

    public abstract void die();

    public abstract int getX();

    public abstract int getY();
}
