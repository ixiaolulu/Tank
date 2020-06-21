package com.lulu.tank;

import com.lulu.tank.net.Client;
import com.lulu.tank.net.TankDirChangedMsg;
import com.lulu.tank.net.TankStartMovingMsg;
import com.lulu.tank.net.TankStopMsg;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

/**
 * @Description:
 * @Author: Milo
 * @Date: 2020-06-01 20:38
 */
public class TankFrame extends Frame {

    public static final TankFrame INSTANCE = new TankFrame();

    static final int GAME_WIDTH = 800, GAME_HEIGHT = 600;
    Random r = new Random();

    Tank myTank = new Tank(r.nextInt(GAME_WIDTH), r.nextInt(GAME_HEIGHT), Dir.DOWN, Group.GOOD);
    List<Explode> explodes = new ArrayList<>();

    List<Bullet> bullets = new ArrayList<>();

    Map<UUID, Tank> tanks = new HashMap<>();


    public Tank findTankByUUID(UUID id) {
        return tanks.get(id);
    }

    public void addTank(Tank t) {
        tanks.put(t.getId(), t);
    }


    public void addBullet(Bullet b) {
        bullets.add(b);
    }

    public TankFrame() {
        this.setSize(GAME_WIDTH, GAME_HEIGHT);
        this.setTitle("tank war");
        this.setResizable(false);
        this.addKeyListener(new MyKeyListener());
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.exit(0);
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("子弹的数量：" + bullets.size(), 10, 60);
        g.drawString("敌人的数量：" + tanks.size(), 10, 80);
        g.drawString("爆炸的数量：" + explodes.size(), 10, 100);
        g.setColor(c);
        if (myTank != null) {
            myTank.paint(g);
        }

        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).paint(g);
        }

        tanks.values().stream().forEach(e -> e.paint(g));

        for (int i = 0; i < explodes.size(); i++) {
            explodes.get(i).paint(g);
        }

        Collection<Tank> values = tanks.values();
        for (int i = 0; i < bullets.size(); i++) {
            for (Tank t : values) {
                bullets.get(i).collideWith(t);
            }
        }


    }

    Image offScreenImage = null;

    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    public Tank getMainTank() {
        return myTank;
    }

    public Bullet finsBulletByUUID(UUID bulletId) {
        for (int i = 0; i < bullets.size(); i++) {
            if (bullets.get(i).getId().equals(bulletId))
                return bullets.get(i);
        }

        return null;
    }

    class MyKeyListener extends KeyAdapter {

        boolean bL = false;
        boolean bR = false;
        boolean bD = false;
        boolean bU = false;


        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    bL = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = true;
                    break;
                case KeyEvent.VK_UP:
                    bU = true;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = true;
                    break;
                default:
                    break;
            }
            setMainTankDir();
        }

        @Override
        public void keyReleased(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    bL = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = false;
                    break;
                case KeyEvent.VK_UP:
                    bU = false;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = false;
                    break;
                case KeyEvent.VK_F:
                    myTank.fire();
                    break;
                default:
                    break;
            }
            setMainTankDir();
        }

        private void setMainTankDir() {
            Dir oldDir = myTank.getDir();
            if (!bL && !bR && !bU && !bD) {
                myTank.setMoving(false);
                Client.INSTANCE.send(new TankStopMsg(myTank));
            } else {
                if (bL) myTank.setDir(Dir.LEFT);
                if (bU) myTank.setDir(Dir.UP);
                if (bD) myTank.setDir(Dir.DOWN);
                if (bR) myTank.setDir(Dir.RIGHT);
                //发出坦克移动的消息
                if (!myTank.isMoving())
                    Client.INSTANCE.send(new TankStartMovingMsg(myTank));

                myTank.setMoving(true);

                if (oldDir != myTank.getDir()) Client.INSTANCE.send(new TankDirChangedMsg(myTank));
            }
        }
    }


}
