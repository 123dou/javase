package leetcode.primary.linkedList;

import leetcode.TreeAndList.ListNode;

import java.util.LinkedList;

/**
 * 请编写一个函数，使其可以删除某个链表中给定的（非末尾的）节点，您将只被给予要求被删除的节点。
 * <p>
 * 比如：假设该链表为 1 -> 2 -> 3 -> 4  ，给定您的为该链表中值为 3 的第三个节点，那么在调用了您的函数之后，该链表则应变成 1 -> 2 -> 4 。l
 */
public class Linkedlist {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(-21);
        l1.next = new ListNode(10);
        l1.next = l1;
        boolean b = hasCycle(l1);
        System.out.println(b);
    }

    /**
     * 将所有结点的值往前挪,然后删除最后一个结点
     *
     * @param node
     */
    public static void deleteNode(ListNode node) {
        if (node.next != null) {
            node.val = node.next.val;
            node.next = node.next.next;
        }
    }

    /**
     * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
     * <p>
     * 示例：
     * <p>
     * 给定一个链表: 1->2->3->4->5, 和 n = 2.
     * <p>
     * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
     * 说明：
     * <p>
     * 给定的 n 保证是有效的。
     * <p>
     * 进阶：
     * <p>
     * 你能尝试使用一趟扫描实现吗？
     * 用双指针来做只需要一次遍历
     *
     * @param head
     * @param n
     * @return
     */
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null || head.next == null)
            return null;
        ListNode ahead = head;
        ListNode behind = head;
        while (n-- > 0)
            ahead = ahead.next;
        if (ahead == null) {
            ListNode newHead = head.next;
            head.next = null;
            return newHead;
        }
        while (ahead.next != null) {
            ahead = ahead.next;
            behind = behind.next;
        }
        behind.next = behind.next.next;
        return head;
    }

    /**
     * 反转一个单链表。
     * <p>
     * 示例:
     * <p>
     * 输入: 1->2->3->4->5->NULL
     * 输出: 5->4->3->2->1->NULL
     * 进阶:
     * 你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
     *
     * @param head
     * @return
     */
    public static ListNode reverseList(ListNode head) { //依次找到尾结点,赋给新点结点后删除,时间复杂度太高
        if (head == null || head.next == null)
            return head;
        ListNode newHead;
        ListNode next;
        ListNode aid = head;
        while (aid.next.next != null)
            aid = aid.next;
        newHead = aid.next; //找到頭節點
        aid.next = null; //逐一删除尾结点
        next = newHead;
        while (head.next != null) {
            aid = head;
            while (aid.next.next != null) {
                aid = aid.next;
            }
            next.next = aid.next;
            next = next.next;
            aid.next = null;
        }
        next.next = head;
        return newHead;
    }

    /**
     * 用LinkedList来模拟栈的跌代: 返转两个结点的, node.next.next = node;
     *
     * @param head
     * @return
     */
    public static ListNode reverseList2(ListNode head) {
        if (head == null)
            return null;
        if (head.next == null)
            return head;
        LinkedList<ListNode> list = new LinkedList<>();
        ListNode aid = head;
        while (aid.next != null) {
            list.addLast(aid);
            aid = aid.next;
        }
        ListNode node = list.pollLast();
        node.next.next = node;
        ListNode newHead = node.next;
        while (!list.isEmpty()) {
            node = list.pollLast();
            node.next.next = node;
        }
        node.next = null;
        return newHead;
    }

    /**
     * 递归:返转两个结点的, node.next.next = node;
     *
     * @param head
     * @return
     */
    public static ListNode reverseList3(ListNode head) {
        if (head == null) return head;
        if (head.next == null) return head;
        ListNode pre = head.next;
        ListNode node = reverseList3(pre);
        pre.next = head;
        head.next = null;
        return node;
    }

    /**
     * 简单明了
     *
     * @param head
     * @return
     */
    public static ListNode reverseList4(ListNode head) {
        if (head == null) return head;
        ListNode aid, pre;
        pre = null;
        while (head != null) {
            aid = head.next;
            head.next = pre;
            pre = head;
            head = aid;
        }
        return pre;
    }


    /**
     * 将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
     * <p>
     * 示例：
     * <p>
     * 输入：1->2->4, 1->3->4
     * 输出：1->1->2->3->4->4
     * 通过比较将l2的结点插入到l1的相应位置
     *
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) return null;
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        if (l1.val > l2.val) { //确保l1的头结点是两个列表里面最小的
            ListNode temp = l2.next;
            l2.next = l1;
            l1 = l2;
            l2 = temp;
        }
        ListNode aid_l1 = l1; //l1的辅助结点
        ListNode temp_l2; //l2的辅助结点
        ListNode temp_l1;
        while (aid_l1.next != null && l2 != null) {
            if (aid_l1.next.val <= l2.val) {
                aid_l1 = aid_l1.next;
            } else {
                temp_l1 = aid_l1.next;
                temp_l2 = l2.next;
                aid_l1.next = l2;
                l2.next = temp_l1;
                l2 = temp_l2;
                aid_l1 = aid_l1.next;
            }
        }
        if (aid_l1.next == null && aid_l1.val <= l2.val) {
            aid_l1.next = l2;
        }
        return l1;
    }

    /**
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        ListNode head = null;
        if (l1.val < l2.val) {
            head = l1;
            l1 = l1.next;
        } else {
            head = l2;
            l2 = l2.next;
        }
        ListNode node = head;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                node.next = l1;
                l1 = l1.next;
                node = node.next;
            } else {
                node.next = l2;
                l2 = l2.next;
                node = node.next;
            }
        }
        if (l1 != null) node.next = l1;
        if (l2 != null) node.next = l2;
        return head;
    }


    /**
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
     * 你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
     *
     * @param head
     * @return
     */
    //什么垃圾代码
    public static boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) return true;
        int length = 0;
        for (ListNode node = head; node != null; node = node.next) length++;
        ListNode aid = head;
        ListNode beh;
        if (length % 2 != 0) {
            for (int i = 0; i < length / 2 - 1; i++) aid = aid.next;
            aid.next = aid.next.next;
        } else
            for (int i = 0; i < length / 2 - 1; i++) aid = aid.next;
        beh = aid.next;
        beh = reverseList3(beh);
        for (int i = 0; i < length / 2; i++) {
            if (beh.val != head.val)
                return false;
            beh = beh.next;
            head = head.next;
        }
        return true;
    }

    /**
     * 给定一个链表，判断链表中是否有环。
     * <p>
     * 进阶：
     * 你能否不使用额外空间解决此题？
     * 用双指针来解决,一个走一步,一个走两步
     *
     * @param head
     * @return
     */
    public static boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (slow == fast)
                return true;
        }
        return false;
    }

}
