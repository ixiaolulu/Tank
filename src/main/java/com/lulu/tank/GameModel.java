package com.lulu.tank;

import com.lulu.tank.cor.ColliderChain;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: Milo
 * @Date: 2020-06-07 12:13
 */
public class GameModel {

    private static final GameModel INSTANCE = new GameModel();

    static {
        INSTANCE.init();
    }


    Tank myTank;

    private List<GameObject> objects = new ArrayList<>();

    ColliderChain colliderChain = new ColliderChain();


    public static GameModel getInstance() {
        return INSTANCE;
    }

    private GameModel() {
    }

    private void init() {
        myTank = new Tank(200, 400, Dir.DOWN, Group.GOOD);

        //初始化敌人坦克
        int initTankCount = PropertyMgr.getInt("initTankCount");
        for (int i = 0; i < initTankCount; i++) {
            new Tank(50 + i * 80, 200, Dir.DOWN, Group.BAD);
        }

        // 初始化墙
        add(new Wall(150, 150, 200, 50));
        add(new Wall(550, 150, 200, 50));
        add(new Wall(300, 300, 50, 200));
        add(new Wall(550, 300, 50, 200));
    }

    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.WHITE);
//        g.drawString("子弹的数量：" + bullets.size(), 10, 60);
//        g.drawString("敌人的数量：" + tanks.size(), 10, 80);
//        g.drawString("爆炸的数量：" + explodes.size(), 10, 100);
        g.setColor(c);
        myTank.paint(g);
        for (int i = 0; i < this.objects.size(); i++) {
            this.objects.get(i).paint(g);
        }

        //碰撞检测
        for (int i = 0; i < objects.size(); i++) {
            for (int j = i + 1; j < objects.size(); j++) {
                GameObject o1 = objects.get(i);
                GameObject o2 = objects.get(j);
                colliderChain.collide(o1, o2);
            }
        }

    }

    public Tank getMainTank() {
        return this.myTank;
    }

    public void add(GameObject go) {
        this.objects.add(go);
    }

    public void remove(GameObject go) {
        this.objects.remove(go);
    }

}
