package com.lulu.tank.strategy;

import com.lulu.tank.Audio;
import com.lulu.tank.Bullet;
import com.lulu.tank.Dir;
import com.lulu.tank.Group;
import com.lulu.tank.Tank;

public class FourDirFireStrategy implements FireStrategy {

    @Override
    public void fire(Tank t) {
        int bX = t.getX() + Tank.WIDTH / 2 - Bullet.WIDTH / 2;
        int bY = t.getY() + Tank.HEIGHT / 2 - Bullet.HEIGHT / 2;

        Dir[] dirs = Dir.values();
        for (Dir dir : dirs) {
            new Bullet(bX, bY, dir, t.getGroup());
        }

        if (t.getGroup() == Group.GOOD) new Thread(() -> new Audio("audio/tank_fire.wav").play()).start();
    }

}
