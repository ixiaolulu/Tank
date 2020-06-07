package com.lulu.tank.decorator;

import com.lulu.tank.GameObject;

import java.awt.*;

/**
 * @Description:
 * @Author: Milo
 * @Date: 2020-06-07 17:56
 */
public class TailDecorator extends GoDecorator {

    public TailDecorator(GameObject go) {
        super(go);
    }

    @Override
    public void paint(Graphics g) {
        this.x = go.x;
        this.y = go.y;
        go.paint(g);

        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.drawLine(go.x, go.y, go.x + getWidth(), go.y + getHeight());
        g.setColor(c);
    }


    @Override
    public int getWidth() {
        return super.go.getWidth();
    }

    @Override
    public int getHeight() {
        return super.go.getHeight();
    }
}
