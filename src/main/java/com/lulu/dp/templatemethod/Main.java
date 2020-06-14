package com.lulu.dp.templatemethod;

/**
 * @Description:
 * @Author: Milo
 * @Date: 2020-06-14 12:49
 */
public class Main {
    public static void main(String[] args) {
        F f = new C1();
        f.m();
    }
}

abstract class F {
    abstract void p1();

    abstract void p2();

    public void m() {
        p1();
        p2();
    }
}

class C1 extends F {

    @Override
    void p1() {
        System.out.println("p1");
    }

    @Override
    void p2() {
        System.out.println("p2");
    }
}