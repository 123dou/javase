package leetcode.intermediate.LinkedList;

import leetcode.TreeAndList.ListNode;

import java.util.HashSet;
import java.util.Set;

public class LinkListQuestion {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(3);
        l1.next.next.next = new ListNode(4);
        l1.next.next.next.next = new ListNode(5);
        l1.next.next.next.next.next = new ListNode(6);
        FindFirstCommonNode(l1, l1.next);

    }

    /**
     * 给定两个非空链表来表示两个非负整数。位数按照逆序方式存储，它们的每个节点只存储单个数字。将两数相加返回一个新的链表。
     * <p>
     * 你可以假设除了数字 0 之外，这两个数字都不会以零开头。
     * <p>
     * 示例：
     * <p>
     * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
     * 输出：7 -> 0 -> 8
     * 原因：342 + 465 = 807
     *
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) return null;
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        boolean count = false;
        int sum;
        ListNode node = null;
        ListNode head = null;
        boolean isHead = true;
        while (l1 != null && l2 != null) {
            if (count) {
                sum = l1.val + l2.val + 1;
                count = false;
            } else sum = l1.val + l2.val;
            if (sum > 9) {
                count = true;
                sum = sum % 10;
            }
            if (isHead) {
                head = new ListNode(sum);
                node = head;
                isHead = false;
            } else {
                node.next = new ListNode(sum);
                node = node.next;
            }
            l1 = l1.next;
            l2 = l2.next;
        }
        while (l1 != null) {
            if (count) {
                sum = l1.val + 1;
                count = false;
            } else sum = l1.val;
            if (sum > 9) {
                count = true;
                sum = 0;
            }
            node.next = new ListNode(sum);
            node = node.next;
            l1 = l1.next;
        }
        while (l2 != null) {
            if (count) {
                sum = l2.val + 1;
                count = false;
            } else sum = l2.val;
            if (sum > 9) {
                count = true;
                sum = 0;
            }
            node.next = new ListNode(sum);
            node = node.next;
            l2 = l2.next;
        }
        if (count) {
            node.next = new ListNode(1);
        }
        return head;
    }

    /**
     * 给定一个单链表，把所有的奇数节点和偶数节点分别排在一起。请注意，这里的奇数节点和偶数节点指的是节点编号的奇偶性，而不是节点的值的奇偶性。
     * <p>
     * 请尝试使用原地算法完成。你的算法的空间复杂度应为 O(1)，时间复杂度应为 O(nodes)，nodes 为节点总数。
     * <p>
     * 示例 1:
     * <p>
     * 输入: 1->2->3->4->5->NULL
     * 输出: 1->3->5->2->4->NULL
     * 示例 2:
     * <p>
     * 输入: 2->1->3->5->6->4->7->NULL
     * 输出: 2->3->6->7->1->5->4->NULL
     * 说明:
     * <p>
     * 应当保持奇数节点和偶数节点的相对顺序。
     * 链表的第一个节点视为奇数节点，第二个节点视为偶数节点，以此类推。
     *
     * @param head
     * @return
     */
    public static ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) return head;
        ListNode even = head.next;
        ListNode nodeEven = even;
        ListNode nodeOdd = head;
        while (nodeOdd != null && nodeOdd.next != null && nodeEven.next != null) {
            nodeOdd.next = nodeOdd.next.next;
            nodeOdd = nodeOdd.next;
            nodeEven.next = nodeEven.next.next;
            nodeEven = nodeOdd.next;
        }
        nodeOdd.next = even;
        return head;
    }

    /**
     * 编写一个程序，找到两个单链表相交的起始节点。
     * <p>
     * <p>
     * <p>
     * 例如，下面的两个链表：
     * <p>
     * A:          a1 → a2
     * ↘
     * c1 → c2 → c3
     * ↗
     * B:     b1 → b2 → b3
     * 在节点 c1 开始相交。
     * <p>
     * <p>
     * <p>
     * 注意：
     * <p>
     * 如果两个链表没有交点，返回 null.
     * 在返回结果后，两个链表仍须保持原有的结构。
     * 可假定整个链表结构中没有循环。
     * 程序尽量满足 O(n) 时间复杂度，且仅用 O(1) 内存。
     *
     * @param headA
     * @param headB
     * @return
     */
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        if (headA == headB) return headA;
        Set<ListNode> set = new HashSet<>();
        while (headA != null) {
            set.add(headA);
            headA = headA.next;
        }
        while (headB != null) {
            if (set.contains(headB)) return headB;
            headB = headB.next;
        }
        return null;
    }

    /**
     * 不能改变表结构:将连接两个链表将问题变为寻找循环链表的头结点,然后再恢复表结构
     *
     * @param headA
     * @param headB
     * @return
     */
    public static ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        if (headA == headB) return headA;
        ListNode node = headA;
        while (node.next != null) {
            node = node.next;
        }
        node.next = headB; //改变了表结构,noPass
        ListNode fast = headA;
        ListNode slow = headA;
        boolean isIntersection = false;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (fast == slow) {
                isIntersection = true;
                break;
            }
        }
        if (isIntersection) {
            fast = headA;
            while (fast != slow) {
                fast = fast.next;
                slow = slow.next;
            }
            node.next = null;
            return fast;
        }
        node.next = null; //还原表结构
        return null;
    }

    public static ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        if (pHead1 == null || pHead2 == null) return null;
        if (pHead1 == pHead2) return pHead1;
        ListNode temp = pHead1;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = pHead2;
        boolean hasLoop = false;
        ListNode slow = pHead1;
        ListNode fast = pHead1;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                hasLoop = true;
                break;
            }
        }
        if (!hasLoop) {
            temp = null;
            return null;
        }
        fast = pHead1;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        temp = null;
        return fast;
    }


}
