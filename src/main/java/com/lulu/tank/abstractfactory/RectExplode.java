package com.lulu.tank.abstractfactory;

import com.lulu.tank.Audio;
import com.lulu.tank.ResourceMgr;
import com.lulu.tank.TankFrame;

import java.awt.*;

/**
 * @Description:
 * @Author: Milo
 * @Date: 2020-06-06 13:44
 */
public class RectExplode extends BaseExplode {

    public static int WIDTH = ResourceMgr.explodes[0].getWidth();
    public static int HEIGHT = ResourceMgr.explodes[0].getHeight();

    private int x, y;

    //private boolean living = true;

    private int step = 0;
    TankFrame tf = null;

    public RectExplode(int x, int y, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.tf = tf;

        new Thread(() -> new Audio("audio/explode.wav").play()).start();
    }


    @Override
    public void paint(Graphics g) {

//        g.drawImage(ResourceMgr.explodes[step++], x, y, null);
        Color c = g.getColor();
        g.setColor(Color.RED);
        g.fillRect(x, y, 10 * step, 10 * step);
        step++;

        if (step >= 15)
            tf.explodes.remove(this);

        g.setColor(c);

    }
}
