/**
 * Copyright(C) 2020 Hangzhou Differsoft Co., Ltd. All rights reserved.
 */
package com.lulu.dp.iterator.v3;

/**
 *
 * @since 2020/6/12 14:26
 * @author DingXianLu
 *
 */
public class LinkedList_ implements Collection_ {
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

    private class Node {
        private Object o;
        Node next;

        public Node(Object o) {
            this.o = o;
        }
    }

    public int size() {
        return size;
    }

    @Override
    public Object get(int i) {
        if (head == null || (size-1) < i )
            return null;
        Node node = head;
        for (int j = 0; j < i; j++) {
            node = node.next;
        }
        return node.o;
    }
}
