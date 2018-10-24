package leetcode.primary.linkedList;

import leetcode.TreeAndList.ListNode;

/**
 * 给定一个链表，旋转链表，将链表每个节点向右移动 k 个位置，其中 k 是非负数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 1->2->3->4->5->NULL, k = 2
 * 输出: 4->5->1->2->3->NULL
 * 解释:
 * 向右旋转 1 步: 5->1->2->3->4->NULL
 * 向右旋转 2 步: 4->5->1->2->3->NULL
 * 示例 2:
 * <p>
 * 输入: 0->1->2->NULL, k = 4
 * 输出: 2->0->1->NULL
 * 解释:
 * 向右旋转 1 步: 2->0->1->NULL
 * 向右旋转 2 步: 1->2->0->NULL
 * 向右旋转 3 步: 0->1->2->NULL
 * 向右旋转 4 步: 2->0->1->NULL
 */
public class RotateList {
    public static void main(String[] args) {

    }

    public ListNode rotateRight(ListNode head, int k) {
        if (head == null) return null;
        int len = size(head);
        k = k % len;
        ListNode h = head;
        for (int i = 0; i < k; i++) h = h.next;
        ListNode h2 = head;
        while (h.next != null) {
            h2 = h2.next;
            h = h.next;
        }
        h.next = head;
        head = h2.next;
        h2.next = null;
        return head;
    }


    private int size(ListNode head) {
        int size = 0;
        while (head != null) {
            size++;
            head = head.next;
        }
        return size;
    }
}
