package com.lulu.tank.cor;

import com.lulu.tank.Bullet;
import com.lulu.tank.GameObject;
import com.lulu.tank.Tank;
import com.lulu.tank.Wall;

public class TankWallCollider implements Collider {

    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if (o1 instanceof Tank && o2 instanceof Wall) {
            Tank t = (Tank) o1;
            Wall w = (Wall) o2;

            if (t.getRect().intersects(w.getRect())) {
                t.back();
            }

        } else if (o1 instanceof Wall && o2 instanceof Bullet) {
            return collide(o2, o1);
        }

        return false;

    }

}
