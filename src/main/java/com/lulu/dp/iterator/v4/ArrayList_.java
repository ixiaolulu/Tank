/**
 * Copyright(C) 2020 Hangzhou Differsoft Co., Ltd. All rights reserved.
 */
package com.lulu.dp.iterator.v4;

/**
 *
 * @since 2020/6/12 14:29
 * @author DingXianLu
 *
 */
public class ArrayList_ implements Collection_ {
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

    @Override
    public Object get(int i) {
        if (i < 0 || i > index - 1)
            return null;
        return objects[i];
    }

    @Override
    public Iterator_ iterator() {
        return new ArrayListIterator();
    }

    private class ArrayListIterator implements Iterator_ {

        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < index;
        }

        @Override
        public Object next() {
            Object o = objects[currentIndex];
            currentIndex++;
            return o;

        }
    }

}
