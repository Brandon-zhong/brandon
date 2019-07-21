package com.demo.tree;

import java.util.Objects;

/**
 * @author brandon
 * create on 2019-05-15
 * desc:
 */
public class TreeSearch extends TreeOrder<Integer> {


    private Node<Integer> getSearchTree() {

        int[] datas = new int[]{33, 16, 50, 13, 18, 34, 58, 15, 17, 25, 51, 66, 19, 27, 55};
        Node<Integer> node = null;
        for (int i : datas) {
            node = add(node, i);
        }
        return node;
    }

    private Node<Integer> add(Node<Integer> root, int data) {

        if (null == root) {
            return new Node<>(data);
        }
        Node<Integer> node = root;
        while (true) {
            if (node.data > data) {
                if (node.left == null) {
                    node.left = new Node<>(data);
                    return root;
                }
                node = node.left;
            } else if (node.data < data) {
                if (node.right == null) {
                    node.right = new Node<>(data);
                    return root;
                }
                node = node.right;
            } else {
                return root;
            }
        }
    }

    private Node<Integer> get(Node<Integer> root, int data) {

        if (null == root) {
            return null;
        }
        Node<Integer> node = root;
        while (node != null) {
            if (node.data == data) {
                return node;
            }
            if (node.data > data) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        return null;
    }

    //TODO 树节点的删除（待完成）
    private boolean delete(Node<Integer> root, int data) {

        /*Node<Integer> node = root, parent = null;
        //查找节点和节点的父节点
        for (; node != null && node.data != data; ) {
            parent = node;
            node = node.data > data ? node.left : node.right;
        }
        //没找到，返回
        if (node == null) {
            return false;
        }

        //如果节点有两个子树,则查找右子树的最小节点替换要删除的节点
        if (node.left != null && node.right != null) {
           Node<Integer> left = node.right;
           Node<Integer> p = null;
            while (left.left != null) {
                p = left;
                left = left.left;
            }
           //将left替换到node
            if (parent.data > node.data) {
                parent.left = left;
            }else {
                parent.right = left;
            }
            left.left = node.left;
            node.left = null;
            left.right = node.right;
            node.right = null;
        }

        //节点只有叶子节点或者只有一个节点
        Node<Integer> child = null;
        if (node.left != null) {
            child = node.left;
        }else if (node.right != null){
            child = node.right;
        }else {
            child = node;
        }

        //删除父节点
        if (parent == null) {

        }*/


        return true;
    }


//    public static void main(String[] args) {
//
//        TreeSearch treeSearch = new TreeSearch();
//        Node<Integer> tree = treeSearch.getSearchTree();
//        treeSearch.preOrder(tree);
//        Node<Integer> node = treeSearch.get(tree, 19);
//        System.out.println("\ndata --> " + (node == null ? 0 : node.data));
//
//    }

}
