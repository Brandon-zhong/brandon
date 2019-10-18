package com.leetcode.doublepointer;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author brandon
 * Created on 2019-10-06.
 * desc: 回文链表
 * 请判断一个链表是否为回文链表。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 1->2
 * 输出: false
 * 示例 2:
 * <p>
 * 输入: 1->2->2->1
 * 输出: true
 * 进阶：
 * 你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
 **/
public class _234_Solution {

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }

        @Override
        public String toString() {
            return " " + this.val + " ";
        }
    }

    public boolean isPalindrome_1(ListNode head) {
        ListNode first = head, secend = head;
        //先确定链表的中点
        Stack<Integer> stack = new Stack<>();
        while (secend != null && secend.next != null) {
            stack.push(first.val);
            first = first.next;
            secend = secend.next.next;
        }
        //长度为奇数的话，secend不为空，中点需要向前移动一位
        if (secend != null) {
            first = first.next;
        }
        //开始判断回文
        while (first != null) {
            if (stack.pop() != first.val) {
                return false;
            }
            first = first.next;
        }
        return true;
    }

    public boolean isPalindrome_2(ListNode head) {
        ListNode first = head, secend = head;
        //先确定链表的中点
        ListNode h = null, index;
        while (secend != null && secend.next != null) {
            index = first;
            first = first.next;
            secend = secend.next.next;

            //翻转前面的链表
            index.next = h;
            h = index;
        }
        //长度为奇数的话，secend不为空，中点需要向前移动一位
        if (secend != null) {
            first = first.next;
        }
        //开始判断回文
        index = h;
        while (first != null && index != null) {
            if (first.val != index.val) {
                return false;
            }
            first = first.next;
            index = index.next;
        }
        return true;
    }

    public static void main(String[] args) {
        //[1,3,2,4,3,2,1]
        ListNode head = new ListNode(1);
        ListNode node2 = new ListNode(3);
        ListNode node3 = new ListNode(2);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(3);
        ListNode node6 = new ListNode(3);
        ListNode node7 = new ListNode(1);
        head.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node7;
        boolean bool = new _234_Solution().isPalindrome_2(head);
        System.out.println("_234_Solution.main --> " + bool);

    }


}
