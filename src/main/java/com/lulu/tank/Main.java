package com.lulu.tank;

/**
 * @Description:
 * @Author: Milo
 * @Date: 2020-06-01 20:29
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        TankFrame frame = new TankFrame();

        int initTankCount = PropertyMgr.getInt("initTankCount");

        //初始化敌人坦克
        for (int i = 0; i < initTankCount; i++) {
            Tank tank = new Tank(50 + i * 100, 200, Dir.DOWN, Group.BAD, frame);
            frame.tanks.add(tank);
        }

        while (true) {
            Thread.sleep(50);
            frame.repaint();
        }
    }
}
