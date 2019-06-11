package com.demo.tree;

/**
 * @author brandon
 * create on 2019-05-13
 * desc: tree 的前、中、后序遍历
 */
public class TreeOrder<E> {


    private Node<String> getTree() {

        Node<String> root = new Node<>("A");
        Node<String> BNode = new Node<>("B");
        Node<String> CNode = new Node<>("C");
        Node<String> DNode = new Node<>("D");
        Node<String> ENode = new Node<>("E");
        Node<String> FNode = new Node<>("F");
        Node<String> GNode = new Node<>("G");
        Node<String> HNode = new Node<>("H");
        Node<String> INode = new Node<>("I");
        Node<String> JNode = new Node<>("J");

        root.parent = null;

        root.left = BNode;
        BNode.parent = root;

        BNode.left = DNode;
        DNode.parent = BNode;

        BNode.right = ENode;
        ENode.parent = BNode;

        DNode.left = HNode;
        HNode.parent = DNode;

        DNode.right = INode;
        INode.parent = DNode;

        ENode.left = JNode;
        JNode.parent = ENode;

        root.right = CNode;
        CNode.parent = root;

        CNode.left = FNode;
        FNode.parent = CNode;

        CNode.right = GNode;
        GNode.parent = CNode;
        return root;
    }

    /**
     * 前序遍历
     * 依次根节点，左节点，右节点
     *
     * @param root 根节点
     */
    public void preOrder(Node<E> root) {

        if (root == null) {
            return;
        }
        //打印根节点
        System.out.print(root.data + " ");

        //打印左节点
        preOrder(root.left);

        //打印右节点
        preOrder(root.right);
    }

    /**
     * 中序遍历
     * 依次打印左节点，根节点，右节点
     *
     * @param root 根节点
     */
    public void inOrder(Node<E> root) {
        if (root == null) {
            return;
        }
        //打印左节点
        inOrder(root.left);
        //打印根节点
        System.out.print(root.data + " ");

        inOrder(root.right);
    }

    /**
     * 后序遍历
     * 依次打印左节点，右节点，根节点
     *
     * @param root 根节点
     */
    public void postOrder(Node<E> root) {
        if (root == null) {
            return;
        }
        //打印左节点
        postOrder(root.left);
        //打印右节点
        postOrder(root.right);
        //打印根节点
        System.out.print(root.data + " ");
    }

    public void preOrderFor(Node<E> root) {
        if (root == null) {
            return;
        }
        Node<E> node = root;
        for (; node != null; ) {
            if (!node.read) {
                node.read = true;
                System.out.print(node.data + " ");
            }
            if (node.left != null && !node.left.read) {
                node = node.left;
                continue;
            }
            if (node.right != null && !node.right.read) {
                node = node.right;
                continue;
            }
            node = node.parent;
        }
    }

}
