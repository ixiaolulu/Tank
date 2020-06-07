package com.lulu.tank;

/**
 * @Description:
 * @Author: Milo
 * @Date: 2020-06-01 20:29
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        TankFrame frame = new TankFrame();
        new Thread(() -> new Audio("audio/war1.wav").play()).start();

        while (true) {
            Thread.sleep(50);
            frame.repaint();
        }
    }
}
