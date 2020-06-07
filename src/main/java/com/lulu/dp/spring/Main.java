package com.lulu.dp.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Description:
 * @Author: Milo
 * @Date: 2020-06-07 18:18
 */
public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("app.xml");

//        Driver d = (Driver)context.getBean("driver");
        Car car = (Car) context.getBean("car");

    }
}
