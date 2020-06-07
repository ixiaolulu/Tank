package com.lulu.tank;

/**
 * @Description:
 * @Author: Milo
 * @Date: 2020-06-01 20:29
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        TankFrame tf = new TankFrame();

        int initTankCount = PropertyMgr.getInt("initTankCount");

        //初始化敌人坦克
        for (int i = 0; i < initTankCount; i++) {
            tf.tanks.add(tf.gf.createTank(50 + i * 100, 200, Dir.DOWN, Group.BAD, tf));
        }

        while (true) {
            Thread.sleep(50);
            tf.repaint();
        }
    }
}
