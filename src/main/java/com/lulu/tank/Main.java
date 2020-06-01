package com.lulu.tank;

/**
 * @Description:
 * @Author: Milo
 * @Date: 2020-06-01 20:29
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
      TankFrame frame = new TankFrame();

      while(true){
          Thread.sleep(50);
          frame.repaint();
      }
    }
}
