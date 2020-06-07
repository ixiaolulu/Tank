package com.lulu.dp.abstractfactory;

/**
 * @Description:
 * @Author: Milo
 * @Date: 2020-06-06 12:27
 */
public class Main {

    public static void main(String[] args) {
        AbstarctFactory f = new ModernFactory();
        Vehicle c = f.createVehicle();
        c.go();
        Weapon weapon = f.createWeapon();
        weapon.shoot();
        Food food = f.createFood();
        food.printName();
    }
}
