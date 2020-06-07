package com.lulu.tank.abstractfactory;

import com.lulu.tank.Bullet;
import com.lulu.tank.Dir;
import com.lulu.tank.Explode;
import com.lulu.tank.Group;
import com.lulu.tank.Tank;
import com.lulu.tank.TankFrame;

/**
 * @Description:
 * @Author: Milo
 * @Date: 2020-06-06 13:23
 */
public class DefaultFactory extends GameFactory {
    @Override
    public BaseTank createTank(int x, int y, Dir dir, Group group, TankFrame tf) {
        return new Tank(x, y, dir, group, tf);
    }

    @Override
    public BaseBullet createBullet(int x, int y, Dir dir, Group group, TankFrame tf) {
        return new Bullet(x, y, dir, group, tf);
    }

    @Override
    public BaseExplode createExplode(int x, int y, TankFrame tf) {
        return new Explode(x, y, tf);
    }
}
