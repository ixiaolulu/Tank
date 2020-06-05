package com.lulu.dp.strategy;

/**
 * @Description:
 * @Author: Milo
 * @Date: 2020-06-05 22:06
 */
@FunctionalInterface
public interface Comparator<T> {

    int compare(T o1, T o2);
}
