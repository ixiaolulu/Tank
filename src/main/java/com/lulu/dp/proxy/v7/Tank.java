package com.lulu.dp.proxy.v7;

/**
 * 问题：我想记录坦克的移动时间
 * 最简单的办法：修改代码，记录时间
 * 问题2：如果无法改变方法源码呢？
 * 用继承？
 * v05:使用代理
 * v06:代理有各种类型
 * 问题：如何实现代理的各种组合？继承？Decorator?
 * v07:代理的对象改成Movable类型-越来越像decorator了
 * v08:如果有stop方法需要代理...
 * 如果想让LogProxy可以重用，不仅可以代理Tank，还可以代理任何其他可以代理的类型 Object
 * （毕竟日志记录，时间计算是很多方法都需要的东西），这时该怎么做呢？
 * 分离代理行为与被代理对象
 * 使用jdk的动态代理
 */
public class Tank implements Movable {

    public static void main(String[] args) {
        //静态代理
        Tank tank = new Tank();
        TankLogProxy logProxy = new TankLogProxy(tank);
        TankTimeProxy tankTimeProxy = new TankTimeProxy(logProxy);
        tankTimeProxy.move();
    }

    @Override
    public void move() {
        System.out.println("tank running......");
    }
}

interface Movable {
    void move();
}

class TankTimeProxy implements Movable {
    Movable movable;

    @Override
    public void move() {
        System.out.println("tank cost time start");
        Long startTime = System.currentTimeMillis();
        movable.move();
        System.out.println("tank spend " + (System.currentTimeMillis() - startTime) + "ms");

    }

    public TankTimeProxy(Movable movable) {
        this.movable = movable;
    }
}

class TankLogProxy implements Movable {
    Movable movable;

    @Override
    public void move() {
        System.out.println("tank moving......");
        movable.move();
        System.out.println("tank stopped!");
    }

    public TankLogProxy(Movable movable) {
        this.movable = movable;
    }
}