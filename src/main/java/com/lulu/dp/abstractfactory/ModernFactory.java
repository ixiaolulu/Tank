package com.lulu.dp.abstractfactory;

/**
 * @Description:
 * @Author: Milo
 * @Date: 2020-06-06 12:52
 */
public class ModernFactory extends AbstarctFactory {
    @Override
    Food createFood() {
        return new Bread();
    }

    @Override
    Weapon createWeapon() {
        return new AK47();
    }

    @Override
    Vehicle createVehicle() {
        return new Car();
    }
}
