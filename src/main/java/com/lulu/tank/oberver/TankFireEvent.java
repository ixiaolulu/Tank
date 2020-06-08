package com.lulu.tank.oberver;

import com.lulu.tank.Tank;

/**
 * @Description:
 * @Author: Milo
 * @Date: 2020-06-08 21:53
 */
public class TankFireEvent {

    private Tank source;

    public Tank getSource() {
        return source;
    }

    public TankFireEvent(Tank source) {
        this.source = source;
    }
}
