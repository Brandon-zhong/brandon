package com.demo.tree;

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
            } else if (node.data < data) {
                node = node.right;
            }
        }
        return null;
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
