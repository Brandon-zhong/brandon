package com.leetcode.link;

/**
 * @author brandon
 * Created on 2019-08-04.
 * desc: 两数相加
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 * <p>
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * <p>
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * <p>
 * 示例：
 * <p>
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 */
public class _2_Solution {

    /**
     * 题目很简单，思路看代码，要注意的提到是最后一个数满10的时候要进1
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        //同步控制两个位数相加，如果满10进1，则flag置为true
        boolean flag = false;
        ListNode head = new ListNode(-1);
        ListNode end = head;
        while (l1 != null || l2 != null) {
            int sum = 0;
            if (l1 != null && l2 != null) {
                //两个数字相加，满10取个位并flag等于true
                sum = l1.val + l2.val;
                l1 = l1.next;
                l2 = l2.next;
            } else if (l1 != null && l2 == null) {
                sum = l1.val;
                l1 = l1.next;
            } else if (l1 == null && l2 != null) {
                sum = l2.val;
                l2 = l2.next;
            }
            sum = sum + (flag ? 1 : 0);
            //每次用完flag要还原
            flag = false;
            int num = 0;
            if (sum > 9) {
                num = sum % 10;
                flag = true;
            } else {
                num = sum;
            }
            end.next = new ListNode(num);
            end = end.next;
        }
        //如果flag为true，说明最后一个数字满10，要新加一个节点
        if (flag) {
            end.next = new ListNode(1);
            end = end.next;
        }
        return head.next;
    }

    public void printListNode(ListNode node) {
        while (node != null) {
            System.out.print(node.val + " ");
            node = node.next;
        }
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(6);
//        node1.next = new ListNode(4);
//        node1.next.next = new ListNode(3);
        ListNode node2 = new ListNode(5);
//        node2.next = new ListNode(6);
//        node2.next.next = new ListNode(4);
        _2_Solution solution = new _2_Solution();
        System.out.print("node1 --> ");
        solution.printListNode(node1);
        System.out.println();
        System.out.print("node2 --> ");
        solution.printListNode(node2);
        System.out.println();
        ListNode addNode = solution.addTwoNumbers(node1, node2);
        System.out.print("node3 --> ");
        solution.printListNode(addNode);

    }

    static class ListNode {
        int val;

        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

}
