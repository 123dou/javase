package swordToOffer;

import leetcode.TreeAndList.ListNode;

/**
 * 在一个排序的链表中，存在重复的结点，请删除该链表中重复的结点，重复的结点不保留，返回链表头指针。
 * 例如，链表1->2->3->3->4->4->5 处理后为 1->2->5
 */
public class DeleteDuplication {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(1);
        head.next.next = new ListNode(2);
        head.next.next.next = new ListNode(3);
        head.next.next.next.next = new ListNode(3);
        head.next.next.next.next.next = new ListNode(4);
        head.next.next.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next.next.next = new ListNode(5);
        DeleteDuplication t = new DeleteDuplication();
        t.deleteDuplication(head);
    }


    public ListNode deleteDuplication(ListNode pHead) {
        if (pHead == null || pHead.next == null) return pHead;
        ListNode h = pHead;
        ListNode pre = pHead;
        ListNode curr = pHead;
        ListNode next = pHead.next;
        boolean flag = false;
        while (curr != null && next != null) {
            if (curr.val == next.val) {
                while (next != null && curr.val == next.val) {
                    curr = curr.next;
                    next = next.next;
                }
                if (flag) pre.next = next;
                else {
                    pre = next;
                    pHead = next;
                }
                curr.next = null;
            } else { //执行过这里一次就说明头结点跟下一个结点不一样
                pre = curr;
                flag = true;
            }
            if (next == null) break;
            curr = next;
            next = next.next;
        }
        return pHead;
    }
}
