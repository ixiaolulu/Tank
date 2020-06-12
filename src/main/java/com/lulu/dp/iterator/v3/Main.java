/**
 * Copyright(C) 2020 Hangzhou Differsoft Co., Ltd. All rights reserved.
 */
package com.lulu.dp.iterator.v3;

import java.util.LinkedList;

/**
 * v1:构建一个容器，可以添加对象 v2:用链表来实现一个容器 v3:添加容器的共同接口，实现容器的替换
 */
public class Main {

    public static void main(String[] args) {
        Collection_ c = new ArrayList_();
        for (int i = 0; i < 15; i++) {
            c.add("s" + i);
        }
        System.out.println(c.get(15));
    }
}
