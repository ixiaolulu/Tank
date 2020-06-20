package com.lulu.tank;

import com.lulu.tank.net.Client;

/**
 * @Description:
 * @Author: Milo
 * @Date: 2020-06-01 20:29
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        TankFrame frame = TankFrame.INSTANCE;
        frame.setVisible(true);
//        int initTankCount = PropertyMgr.getInt("initTankCount");
//
//        //初始化敌人坦克
//        for (int i = 0; i < initTankCount; i++) {
//            Tank tank = new Tank(50 + i * 100, 200, Dir.DOWN, Group.BAD, frame);
//            frame.tanks.add(tank);
//        }

//        new Thread(() -> new Audio("audio/war1.wav").loop()).start();

        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                frame.repaint();
            }

        }).start();


        Client client = Client.INSTANCE;
        client.connect();

    }
}
