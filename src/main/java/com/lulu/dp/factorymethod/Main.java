package com.lulu.dp.factorymethod;

/**
 * @Description:
 * @Author: Milo
 * @Date: 2020-06-06 11:55
 */
public class Main {

    public static void main(String[] args) {
//        Moveable car = new Car();
//        car.go();

//        SimpleVehicleFactory simpleVehicleFactory = new SimpleVehicleFactory();
//        Car car = simpleVehicleFactory.createCar();
//        car.go();

        Car car = new CarFactory().createCar();
        car.go();
    }
}
