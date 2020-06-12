/**
 * Copyright(C) 2020 Hangzhou Differsoft Co., Ltd. All rights reserved.
 */
package com.lulu.dp.iterator.v2;

/**
 *
 * @since 2020/6/12 14:02
 * @author DingXianLu
 *
 */
public class Main {

    public static void main(String[] args) {
        LinkedList_ list = new LinkedList_();
        for (int i = 0; i < 15; i++) {
            list.add("s" + i);
        }
        System.out.println(list.size());
    }
}

class LinkedList_ {
    Node head = null;
    Node tail = null;

    // 目前容器中的节点数，多少个元素
    private int size = 0;

    public void add(Object o) {
        Node n = new Node(o);
        n.next = null;

        if (head == null) {
            head = n;
            tail = n;
        }

        tail.next = n;
        tail = n;
        size++;
    }

    public int size() {
        return size;
    }
}

class Node {
    private Object o;
    Node next;

    public Node(Object o) {
        this.o = o;
    }
}