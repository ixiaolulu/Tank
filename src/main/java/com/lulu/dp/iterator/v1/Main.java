/**
 * Copyright(C) 2020 Hangzhou Differsoft Co., Ltd. All rights reserved.
 */
package com.lulu.dp.iterator.v1;

import java.util.ArrayList;

/**
 *
 * @since 2020/6/12 13:33
 * @author DingXianLu
 *
 */
public class Main {

    public static void main(String[] args) {

        ArrayList_ list = new ArrayList_();
        for (int i = 0; i < 15; i++) {
            list.add(i + "a");
        }
        System.out.println(list.size());
    }

}

class ArrayList_ {
    Object[] objects = new Object[10];
    private int index = 0;

    public void add(Object o) {
        if (index == objects.length) {
            Object[] newObjects = new Object[objects.length + (objects.length >> 1)];
            System.arraycopy(objects, 0, newObjects, 0, objects.length);
            objects = newObjects;
        }

        objects[index] = o;
        index++;
    }

    public int size() {
        return index;
    }
}