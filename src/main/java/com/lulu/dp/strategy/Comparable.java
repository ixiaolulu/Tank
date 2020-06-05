package com.lulu.dp.strategy;

/**
 * @Description:
 * @Author: Milo
 * @Date: 2020-06-05 21:46
 */
@FunctionalInterface
public interface Comparable<T> {
    int compareTo(T o);

    default void a(String m) {
        System.out.println(m);
    }
}
