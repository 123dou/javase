package swordToOffer;

import leetcode.TreeAndList.ListNode;

/**
 * 给定一个double类型的浮点数base和int类型的整数exponent。求base的exponent次方。
 */

public class FindKthToTail {
    public ListNode FindKthToTail(ListNode head, int k) {
        if (head == null || head.next == null) return head;
        int len = size(head);
        if (k > len) return null;
        ListNode front = head;
        while (k-- > 0) { //先走k步
            front = front.next;
        }
        while (front != null) {
            front = front.next;
            head = head.next;
        }
        return head;
    }

    //获取链表的长度
    private int size(ListNode head) {
        int len = 0;
        while (head != null) {
            head = head.next;
            len++;
        }
        return len;
    }
}