package com.lulu.tank.abstractfactory;

import com.lulu.tank.Dir;
import com.lulu.tank.Explode;
import com.lulu.tank.Group;
import com.lulu.tank.ResourceMgr;
import com.lulu.tank.Tank;
import com.lulu.tank.TankFrame;

import java.awt.*;

/**
 * @Description:
 * @Author: Milo
 * @Date: 2020-06-06 14:16
 */
public class RectBullet extends BaseBullet {
    private static final int SPEED = 10;

    private int x, y;

    public static int WIDTH = ResourceMgr.bulletD.getWidth();
    public static int HEIGHT = ResourceMgr.bulletD.getHeight();

    private Dir dir;

    private boolean moving = false;

    private boolean living = true;

    private TankFrame tf = null;

    private Group group = Group.BAD;

    Rectangle rect = new Rectangle();


    public RectBullet(int x, int y, Dir dir, Group group, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.tf = tf;

        rect.x = this.x;
        rect.y = this.y;
        rect.width = WIDTH;
        rect.height = HEIGHT;

        tf.bullets.add(this);
    }

    @Override
    public void paint(Graphics g) {
        if (!living) {
            tf.bullets.remove(this);
        }
        Color c = g.getColor();
        g.setColor(Color.YELLOW);
        g.fillRect(x, y, 20, 20);
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
        //update rect
        rect.x = this.x;
        rect.y = this.y;
        if (this.x < 0 || this.y < 0 || this.x > TankFrame.GAME_WIDTH || this.y > TankFrame.GAME_HEIGHT) living = false;
    }

    @Override
    public void collideWith(BaseTank tank) {
        if (tank.getGroup() == this.group) return;
        if (this.rect.intersects(tank.rect)) {
            tank.die();
            this.die();
            int eX = tank.getX() + Tank.WIDTH / 2 - Explode.WIDTH / 2;
            int eY = tank.getY() + Tank.HEIGHT / 2 - Explode.HEIGHT / 2;
            tf.explodes.add(tf.gf.createExplode(eX, eY, tf));
        }
    }

    private void die() {
        this.living = false;
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

    public boolean isLiving() {
        return living;
    }

    public void setLiving(boolean living) {
        this.living = living;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
