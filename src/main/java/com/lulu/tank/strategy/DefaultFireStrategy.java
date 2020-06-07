package com.lulu.tank.strategy;

import com.lulu.tank.Audio;
import com.lulu.tank.Bullet;
import com.lulu.tank.Group;
import com.lulu.tank.Tank;

public class DefaultFireStrategy implements FireStrategy {

    @Override
    public void fire(Tank t) {
        int bX = t.getX() + Tank.WIDTH / 2 - Bullet.WIDTH / 2;
        int bY = t.getY() + Tank.HEIGHT / 2 - Bullet.HEIGHT / 2;

        //Bug? new Bullet把自己加了一遍
//		GameModel.getInstance().add(
//				new RectDecorator(
//						new TailDecorator(
//						new Bullet(bX, bY, t.dir, t.group))));
        new Bullet(bX, bY, t.getDir(), t.getGroup());

        if (t.getGroup() == Group.GOOD) new Thread(() -> new Audio("audio/tank_fire.wav").play()).start();
    }

}
