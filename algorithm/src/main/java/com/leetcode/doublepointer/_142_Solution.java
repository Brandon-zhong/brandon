package com.leetcode.doublepointer;

import java.util.HashSet;
import java.util.Set;

/**
 * @author brandon
 * Created on 2019-10-06.
 * desc: 环形链表 II
 * 给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
 * <p>
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。
 * <p>
 * 说明：不允许修改给定的链表。
 * 详细描述：https://leetcode-cn.com/problems/linked-list-cycle-ii/
 **/
public class _142_Solution {

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }

        @Override
        public String toString() {
            return "ListNode{" +
                    "val=" + val +
                    '}';
        }
    }

    /**
     * 快慢双指针判断是否回环，如果回环的话再进行入环点判断
     */
    public ListNode detectCycle_1(ListNode head) {

        ListNode first = head, secend = head;
        //先判断是否有回环
        while (secend != null && secend.next != null) {
            secend = secend.next.next;
            first = first.next;
            //有回环
            if (first == secend) {
                first = head;
                while (first != secend) {
                    first = first.next;
                    secend = secend.next;
                }
                return first;
            }
        }
        return null;
    }


    /**
     * 集合判断，将经过的节点放到集合中，
     * 每次经过新节点的时候判断，如果集合中存在则有环，且当前节点为入环点
     */
    public ListNode detectCycle_2(ListNode head) {
        Set<ListNode> set = new HashSet<>();
        //先判断是否有回环
        while (head != null && head.next != null) {
            if (set.contains(head)) {
                return head;
            }
            set.add(head);
            head = head.next;
        }
        return null;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = head.next;

        ListNode listNode = new _142_Solution().detectCycle_2(head);
        System.out.println("_141_Solution.main --> " + listNode);
    }


}
