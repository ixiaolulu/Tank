package com.lulu.tank.cor;

import com.lulu.tank.Bullet;
import com.lulu.tank.Explode;
import com.lulu.tank.GameObject;
import com.lulu.tank.Tank;

/**
 * @Description:
 * @Author: Milo
 * @Date: 2020-06-07 12:54
 */
public class BulletTankCollider implements Collider {
    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if (o1 instanceof Bullet && o2 instanceof Tank) {
            Bullet b = (Bullet) o1;
            Tank t = (Tank) o2;

            if (t.getGroup() == b.getGroup()) return false;
            if (b.getRect().intersects(t.getRect())) {
                t.die();
                b.die();
                int eX = t.getX() + Tank.WIDTH / 2 - Explode.WIDTH / 2;
                int eY = t.getY() + Tank.HEIGHT / 2 - Explode.HEIGHT / 2;
                new Explode(eX, eY);
                return true;
            }
        } else if (o1 instanceof Tank && o2 instanceof Bullet) {
            collide(o2, o1);
        }

        return false;
    }
}
