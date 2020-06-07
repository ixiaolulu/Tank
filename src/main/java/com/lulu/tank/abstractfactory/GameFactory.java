package com.lulu.tank.abstractfactory;

import com.lulu.tank.Dir;
import com.lulu.tank.Group;
import com.lulu.tank.TankFrame;

/**
 * @Description:
 * @Author: Milo
 * @Date: 2020-06-06 13:23
 */
public abstract class GameFactory {

    public abstract BaseTank createTank(int x, int y, Dir dir, Group group, TankFrame tf);

    public abstract BaseBullet createBullet(int x, int y, Dir dir, Group group, TankFrame tf);

    public abstract BaseExplode createExplode(int x, int y, TankFrame tf);
}
