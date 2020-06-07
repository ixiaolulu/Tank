package com.lulu.tank.cor;

import com.lulu.tank.GameObject;
import com.lulu.tank.Tank;

/**
 * @Description:
 * @Author: Milo
 * @Date: 2020-06-07 12:54
 */
public class TankTankCollider implements Collider {
    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if (o1 instanceof Tank && o2 instanceof Tank) {
            Tank t1 = (Tank) o1;
            Tank t2 = (Tank) o2;
            if (t1.getRect().intersects(t2.getRect())) {
                t1.back();
                t2.back();
            }
        }
        return false;
    }
}
