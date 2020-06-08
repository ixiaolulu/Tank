package com.lulu.tank.oberver;

import com.lulu.tank.Tank;

/**
 * @Description:
 * @Author: Milo
 * @Date: 2020-06-08 21:54
 */
public class TankFireHandler implements TankFireObserver {

    @Override
    public void actionOnFire(TankFireEvent event) {
        Tank tank = event.getSource();
        tank.fire();
    }
}
