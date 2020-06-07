package com.lulu.dp.abstractfactory;

/**
 * @Description:
 * @Author: Milo
 * @Date: 2020-06-06 12:53
 */
public class MagicFactory extends AbstarctFactory {
    @Override
    Food createFood() {
        return new MushRoom();
    }

    @Override
    Weapon createWeapon() {
        return new MagicStick();
    }

    @Override
    Vehicle createVehicle() {
        return new Broom();
    }
}
