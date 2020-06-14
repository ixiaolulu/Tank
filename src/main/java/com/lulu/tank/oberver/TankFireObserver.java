package com.lulu.tank.oberver;

import java.io.Serializable;

/**
 * @Description:
 * @Author: Milo
 * @Date: 2020-06-08 21:54
 */
public interface TankFireObserver extends Serializable {

    void actionOnFire(TankFireEvent event);
}
