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

    public boolean isPalindrome(ListNode head) {
       return false;
    }

    public static void main(String[] args) {
        //[1,3,2,4,3,2,1]
        ListNode head = new ListNode(1);
        ListNode node2 = new ListNode(3);
        ListNode node3 = new ListNode(2);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(2);
        ListNode node6 = new ListNode(3);
        ListNode node7 = new ListNode(1);
        head.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node7;
        boolean bool = new _234_Solution().isPalindrome(head);
        System.out.println("_234_Solution.main --> " + bool);

    }


}
