package com.lulu.dp.abstractfactory;

/**
 * @Description:
 * @Author: Milo
 * @Date: 2020-06-06 12:36
 */
public abstract class AbstarctFactory {

    abstract Food createFood();

    abstract Weapon createWeapon();

    abstract Vehicle createVehicle();
}
