/**
 * Copyright(C) 2020 Hangzhou Differsoft Co., Ltd. All rights reserved.
 */
package com.lulu.dp.iterator.v5;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * v1:构建一个容器，可以添加对象
 * v2:用链表来实现一个容器
 * v3:添加容器的共同接口，实现容器的替换
 * v4:如何对容器遍历呢？
 * v4:用一种统一的遍历方式，要求每一个容器都要提供Iterator的实现类
 *    作业：实现LinkedList的Iterator
 * v6:JDK的容器的Iterator
 */
public class Main {
    public static void main(String[] args) {
        Collection list = new LinkedList();
        for (int i = 0; i < 15; i++) {
            list.add("ss" + i);
        }
        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
