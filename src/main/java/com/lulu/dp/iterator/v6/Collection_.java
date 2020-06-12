/**
 * Copyright(C) 2020 Hangzhou Differsoft Co., Ltd. All rights reserved.
 */
package com.lulu.dp.iterator.v6;

public interface Collection_<E> {
    void add(E o);
    int size();
    E get(int i);
    Iterator_<E> iterator();
}
