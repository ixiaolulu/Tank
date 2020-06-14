package com.lulu.dp.proxy.cglib;


import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Description:
 * @Author: Milo
 * @Date: 2020-06-11 20:21
 */
public class Main {

    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Tank.class);
        enhancer.setCallback((MethodInterceptor) (o, method, objects, methodProxy) -> {
            System.out.println("tank moving....");
            Object result = methodProxy.invokeSuper(o, objects);
            System.out.println("tank stopped!");
            return result;
        });
        Tank tank = (Tank) enhancer.create();
        tank.move();

    }


}

class MyMethodInterceptor implements MethodInterceptor {

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("tank moving....");
        Object result = methodProxy.invoke(o, objects);
        System.out.println("tank stopped!");
        return result;
    }
}

class Tank {

    public void move() {
        System.out.println("tank running......");
    }
}



