package com.lulu.tank.abstractfactory;

import com.lulu.tank.Dir;
import com.lulu.tank.Group;
import com.lulu.tank.TankFrame;

/**
 * @Description:
 * @Author: Milo
 * @Date: 2020-06-06 13:43
 */
public class RectFactory extends GameFactory {
    @Override
    public BaseTank createTank(int x, int y, Dir dir, Group group, TankFrame tf) {
        return new RectTank(x, y, dir, group, tf);
    }

    @Override
    public BaseBullet createBullet(int x, int y, Dir dir, Group group, TankFrame tf) {
        return new RectBullet(x, y, dir, group, tf);
    }

    @Override
    public BaseExplode createExplode(int x, int y, TankFrame tf) {
        return new RectExplode(x, y, tf);
    }
}
