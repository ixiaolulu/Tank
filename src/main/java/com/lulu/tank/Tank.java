package com.lulu.tank;

import com.lulu.tank.oberver.TankFireEvent;
import com.lulu.tank.oberver.TankFireHandler;
import com.lulu.tank.oberver.TankFireObserver;
import com.lulu.tank.strategy.DefaultFireStrategy;
import com.lulu.tank.strategy.FireStrategy;

import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @Description:
 * @Author: Milo
 * @Date: 2020-06-01 21:40
 */
public class Tank extends GameObject {

    private int preX, preY;

    public static int WIDTH = ResourceMgr.goodTankU.getWidth();
    public static int HEIGHT = ResourceMgr.goodTankU.getHeight();

    private Dir dir;

    private static final int SPEED = 5;

    private boolean moving = true;

    private boolean living = true;

    private Group group = Group.BAD;

    FireStrategy fs;

    private Random random = new Random();

    Rectangle rect = new Rectangle();

    public Tank(int x, int y, Dir dir, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;

        rect.x = this.x;
        rect.y = this.y;
        rect.width = WIDTH;
        rect.height = HEIGHT;

        GameModel.getInstance().add(this);

        if (group == Group.GOOD) {
            String goodFSName = PropertyMgr.getString("goodFS");

            try {
                fs = (FireStrategy) Class.forName(goodFSName).getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            fs = new DefaultFireStrategy();
        }
    }


    public void paint(Graphics g) {
        if (!living) GameModel.getInstance().remove(this);
        switch (dir) {
            case LEFT:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankL : ResourceMgr.badTankL, x, y, null);
                break;
            case UP:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankU : ResourceMgr.badTankU, x, y, null);
                break;
            case RIGHT:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankR : ResourceMgr.badTankR, x, y, null);
                break;
            case DOWN:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankD : ResourceMgr.badTankD, x, y, null);
                break;
            default:
                break;
        }
        move();

    }

    @Override
    public int getWidth() {
        return WIDTH;
    }

    @Override
    public int getHeight() {
        return HEIGHT;
    }

    private void move() {
        preX = this.x;
        preY = this.y;
        if (!moving) return;
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

        if (this.group == Group.BAD && random.nextInt(100) > 95) this.fire();

        if (this.group == Group.BAD && random.nextInt(100) > 95) randomDir();

        boundsCheck();

        rect.x = this.x;
        rect.y = this.y;
    }

    private void boundsCheck() {
        if (this.x < 0) x = 2;
        else if (this.y < 28) y = 28;
        else if (this.x > TankFrame.GAME_WIDTH - Tank.WIDTH - 2) x = TankFrame.GAME_WIDTH - Tank.WIDTH - 2;
        else if (this.y > TankFrame.GAME_HEIGHT - Tank.HEIGHT - 2) y = TankFrame.GAME_HEIGHT - Tank.HEIGHT - 2;
    }

    private void randomDir() {

        this.dir = Dir.values()[random.nextInt(4)];
    }

    public void fire() {
        fs.fire(this);
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

    public static int getSPEED() {
        return SPEED;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public void die() {
        this.living = false;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Rectangle getRect() {
        return rect;
    }

    public void back() {
        x = this.preX;
        y = this.preY;
    }

    private List<TankFireObserver> observerList = Arrays.asList(new TankFireHandler());

    public void handleFireKey() {
        TankFireEvent event = new TankFireEvent(this);
        for (TankFireObserver observer : observerList) {
            observer.actionOnFire(event);
        }

    }
}
