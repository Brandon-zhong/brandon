package com.leetcode.doublepointer;

import java.util.HashSet;
import java.util.Set;

/**
 * @author brandon
 * Created on 2019-10-06.
 * desc: 环形链表
 * 给定一个链表，判断链表中是否有环。
 * <p>
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。
 * <p>
 * 示例 1：
 * <p>
 * 输入：head = [3,2,0,-4], pos = 1
 * 输出：true
 * 解释：链表中有一个环，其尾部连接到第二个节点。
 * <p>
 * 详细描述：https://leetcode-cn.com/problems/linked-list-cycle/
 **/
public class _141_Solution {

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    /**
     * 快慢双指针判断链表回环，如果两指针再次相遇，则表示该链表有环
     */
    public boolean hasCycle_1(ListNode head) {
        ListNode first = head, secend = head;
        while (secend != null && secend.next != null) {
            secend = secend.next.next;
            first = first.next;
            if (first == secend) {
                return true;
            }
        }
        return false;
    }

    /**
     * set集合存储所有经过的节点
     */
    public boolean hasCycle_2(ListNode head) {
        Set<ListNode> set = new HashSet<>();
        while (head != null) {
            if (set.contains(head)) {
                return true;
            }
            set.add(head);
            head = head.next;
        }
        return false;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = head;
        /*head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = head.next;*/

        boolean bool = new _141_Solution().hasCycle_1(head);
        System.out.println("_141_Solution.main --> " + bool);
    }

}
