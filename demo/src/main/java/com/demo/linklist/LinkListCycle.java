package com.demo.linklist;

/**
 * @author brandon
 * Created on 2019-06-09.
 * desc: 单向链表环路判断以及求入环点
 */
public class LinkListCycle {

//    public static void main(String[] args) {
//
//        LinkListCycle linkListCycle = new LinkListCycle();
//        Node<Integer> cycleLinkList = linkListCycle.getCycleLinkList();
//        System.out.println("head --> " + cycleLinkList.data);
//        Node<Integer> meetPoint = linkListCycle.getMeetPoint(cycleLinkList);
//        System.out.println("meet point --> " + (meetPoint == null ? "null" : meetPoint.data));
//        Node<Integer> cyclePoint = linkListCycle.getCyclePoint(cycleLinkList);
//        System.out.println("cycle point --> " + cyclePoint.data);
//    }

    private Node<Integer> getCycleLinkList() {
        int len = 15;
        int cycleNum = 5;
        Node<Integer> head = null, index = null, cyclePoint = null;
        for (int i = 0; i < len; i++) {
            Node<Integer> node = new Node<>(i);
            if (head == null) {
                head = node;
                index = head;
                continue;
            }
            index.next = node;
            index = node;
            //设置入环点
            if (i == cycleNum) {
                cyclePoint = index;
            }
        }

        Node.printLinkList(head);

        //打印之后手动做环
        index.next = cyclePoint;
//        System.out.println("cycle point --> " + cyclePoint.data);
        return head;
    }

    /**
     * 获取传入链表的相遇点，此方法是为了给判断链表是否有环调用
     *
     * @param node 要判断的链表头结点
     * @return 返回相遇点，如果没有则返回null
     */
    private Node<Integer> getMeetPoint(Node<Integer> node) {

        Node<Integer> fast = node, slow = node;
        while (fast != null && fast.next != null) {
//            System.out.println("fast --> " + fast.data + "  slow --> " + slow.data);
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                return fast;
            }
        }
        return null;
    }

    /**
     * 获取循环链表的入环点
     *
     * @param node 要检查的链表
     * @return 入环点
     */
    public Node<Integer> getCyclePoint(Node<Integer> node) {

        Node<Integer> meetPoint = getMeetPoint(node);
        if (meetPoint == null) {
            return null;
        }
        Node<Integer> i = node, j = meetPoint, cyclePoint = null;
        while (i != j && i != null) {
            i = i.next;
            cyclePoint = j;
            j = j.next;
        }
        return cyclePoint;
    }


}
