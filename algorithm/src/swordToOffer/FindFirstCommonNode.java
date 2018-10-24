package swordToOffer;

import leetcode.TreeAndList.ListNode;

/**
 * 输入两个链表，找出它们的第一个公共结点。
 */
public class FindFirstCommonNode {
    public static void main(String[] args) {

    }


    public ListNode FindFirstCommonNode2(ListNode h1, ListNode h2) {
        int size1 = 0;
        int size2 = 0;
        ListNode node = h1;
        while (node != null) {
            size1++;
            node = node.next;
        }
        node = h2;
        while (node != null) {
            size2++;
            node = node.next;
        }
        int gap;
        if (size1 > size2) {
            gap = size1 - size2;
            for (int i = 0; i < gap; i++) h1 = h1.next;
        } else if (size2 > size1) {
            gap = size2 - size1;
            for (int i = 0; i < gap; i++) h2 = h2.next;
        }
        while (h1 != h2) {
            h1 = h1.next;
            h2 = h2.next;
        }
        return h1;
    }


    /**
     * 先打个还,转换为寻找环的第一个结点
     *
     * @param pHead1
     * @param pHead2
     * @return
     */
    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        if (pHead1 == null || pHead2 == null) return null;
        if (pHead1 == pHead2) return pHead1;
        ListNode node = pHead1;
        while (node.next != null) node = node.next;
        node.next = pHead2;
        ListNode slow = pHead1;
        ListNode fast = pHead1;
        boolean hasLoop = false;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                hasLoop = true;
                break;
            }
        }
        if (!hasLoop) {
            node.next = null;
            return null;
        }
        fast = pHead1;
        while (fast != slow) {
            slow = slow.next;
            fast = fast.next;
        }
        node.next = null;
        return fast;
    }
}
