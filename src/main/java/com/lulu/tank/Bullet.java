package com.lulu.tank;

import java.awt.*;

/**
 * @Description:
 * @Author: Milo
 * @Date: 2020-06-01 22:07
 */
public class Bullet {

    private static final int SPEED = 5;

    private static final int WIDTH = 30, HEIGHT = 30;

    private int x, y;

    private Dir dir;

    private boolean moving = false;

    private boolean live = true;

    private TankFrame tf = null;

    public Bullet(int x, int y, Dir dir,TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
    }

    public void paint(Graphics g) {
        if(!live){
           tf.bullets.remove(this) ;
        }
        Color c = g.getColor();
        g.setColor(Color.RED);
        g.fillOval(x, y, WIDTH, HEIGHT);
        g.setColor(c);
        move();

    }

    private void move() {
        switch (dir) {
            case UP:
                y -= SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
            case LEFT:
                x -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
            default:
                break;
        }
        if(this.x < 0 || this.y<0 || this.x>TankFrame.GAME_WIDTH || this.y>TankFrame.GAME_HEIGHT) live = false;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    public TankFrame getTf() {
        return tf;
    }

    public void setTf(TankFrame tf) {
        this.tf = tf;
    }
}
