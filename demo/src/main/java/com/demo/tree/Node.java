package com.demo.tree;

import lombok.Data;

/**
 * @author brandon
 * Created on 2019-06-09.
 * desc: 树节点类
 */
class Node<E> {

    final E data;

    boolean read = false;

    Node<E> left;

    Node<E> right;

    Node<E> parent;

    Node(E data) {
        this.data = data;
    }
}
