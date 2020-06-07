package com.lulu.tank.abstractfactory;

import java.awt.*;

/**
 * @Description:
 * @Author: Milo
 * @Date: 2020-06-06 13:22
 */
public abstract class BaseBullet {

    public abstract void paint(Graphics g);

    public abstract void collideWith(BaseTank tank);

}
