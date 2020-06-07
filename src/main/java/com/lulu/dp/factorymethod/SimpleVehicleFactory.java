package com.lulu.dp.factorymethod;

/**
 * @Description:
 * @Author: Milo
 * @Date: 2020-06-06 12:18
 */
public class SimpleVehicleFactory {


    public Car createCar() {
        //before for processing
        return new Car();
    }

    public Plane createPlane() {
        return new Plane();
    }

}
