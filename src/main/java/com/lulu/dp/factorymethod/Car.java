package com.lulu.dp.factorymethod;

/**
 * @Description:
 * @Author: Milo
 * @Date: 2020-06-06 11:55
 */
public class Car implements Moveable {
    @Override
    public void go() {
        System.out.println("car run......");
    }
}
