package com.demo.linklist;

import lombok.Data;

/**
 * @author brandon
 * Created on 2019-06-09.
 * desc: 链表节点
 */
class Node<T> {

    protected T data;

    protected Node<T> previous;

    protected Node<T> next;

    protected Node(T data) {
        this.data = data;
    }

    protected static void printLinkList(Node node) {
        Node index = node;
        while (index != null) {
            System.out.print(index.data + " ");
            index = index.next;
        }
        System.out.println();
    }
}
