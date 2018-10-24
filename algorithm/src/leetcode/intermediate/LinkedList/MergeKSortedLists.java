package leetcode.intermediate.LinkedList;

import leetcode.TreeAndList.ListNode;

/**
 * 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
 * <p>
 * 示例:
 * <p>
 * 输入:
 * [
 * 1->4->5,
 * 1->3->4,
 * 2->6
 * ]
 * 输出: 1->1->2->3->4->4->5->6
 */
public class MergeKSortedLists {
    public static void main(String[] args) {

    }

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        return mKList(lists, 0, lists.length - 1);
    }

    private ListNode mKList(ListNode[] lists, int low, int hi) {
        if (low >= hi) return lists[low];
        int mid = (low + hi) >>> 1;
        ListNode l = mKList(lists, low, mid);
        ListNode r = mKList(lists, mid + 1, hi);
        return merge(l, r);
    }


    private ListNode merge(ListNode h1, ListNode h2) {
        if (h1 == null) return h2;
        if (h2 == null) return h1;
        ListNode h, curr;
        if (h1.val <= h2.val) {
            curr = h = h1;
            h1 = h1.next;
        } else {
            curr = h = h2;
            h2 = h2.next;
        }
        while (h1 != null && h2 != null) {
            if (h1.val <= h2.val) {
                curr.next = h1;
                h1 = h1.next;
            } else {
                curr.next = h2;
                h2 = h2.next;
            }
            curr = curr.next;
        }
        if (h1 != null) curr.next = h1;
        if (h2 != null) curr.next = h2;
        return h;
    }
}
