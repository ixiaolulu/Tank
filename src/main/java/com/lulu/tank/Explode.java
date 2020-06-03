package com.lulu.tank;

import java.awt.*;

/**
 * @Description:
 * @Author: Milo
 * @Date: 2020-06-03 22:16
 */
public class Explode {

    public static int WIDTH = ResourceMgr.explodes[0].getWidth();
    public static int HEIGHT = ResourceMgr.explodes[0].getHeight();

    private int x, y;

    //private boolean living = true;

    private int step = 0;
    TankFrame tf = null;

    public Explode(int x, int y,TankFrame tf) {
        this.x = x;
        this.y = y;
        this.tf = tf;

        new Thread(()->new Audio("audio/explode.wav").play()).start();
    }



    public void paint(Graphics g) {

        g.drawImage(ResourceMgr.explodes[step++], x, y, null);

        if(step >= ResourceMgr.explodes.length)
            step = 0;
//            TankFrame.INSTANCE.explodes.remove(this);


    }

}
