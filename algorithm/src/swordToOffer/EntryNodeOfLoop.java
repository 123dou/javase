package swordToOffer;

import leetcode.TreeAndList.ListNode;

/**
 * 给一个链表，若其中包含环，请找出该链表的环的入口结点，否则，输出null。
 */
public class EntryNodeOfLoop {
    public static void main(String[] args) {

    }


    public ListNode EntryNodeOfLoop(ListNode pHead) {
        if (pHead == null) return pHead;
        ListNode fast = pHead;
        ListNode slow = pHead;
        boolean hasLoop = false;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                hasLoop = true;
                break;
            }
        }
        if (!hasLoop) return null;
        fast = pHead;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return fast;
    }
}
