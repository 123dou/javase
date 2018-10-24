package leetcode.intermediate.LinkedList;

import leetcode.TreeAndList.ListNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 对链表进行排序,要求时间复杂度(nlogn),空间复杂度常数
 */
public class SortList {
    public static void main(String[] args) {
        SortList t = new SortList();
        int n = 5000;
        ListNode head = t.generateList(n);
        long sum = 0;
        List<Long> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            long start = System.currentTimeMillis();
            ListNode node = t.sortList(head);
            long end = System.currentTimeMillis();
            long time = end - start;
            sum += time;
        }
        // System.out.println(list);
        System.out.println(sum / 100);
    }

    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode slow = head, fast = head, pre = head;
        while (fast != null && fast.next != null) {
            pre = slow;
            slow = slow.next;
            //注意这里:fast = fast.next.next 的写法比 fast = slow.next;的写法快很多倍
            //猜测是这里如果这个循换被多次调用的话,会进行运行期的优化
            fast = slow.next;
        }
        pre.next = null;
        return merge(sortList(head), sortList(slow));
        //return null;
    }

    private ListNode merge(ListNode h1, ListNode h2) {
        if (h1 == null) return h1;
        if (h2 == null) return h2;
        ListNode h;
        if (h1.val < h2.val) {
            h = h1;
            h1 = h1.next;
        } else {
            h = h2;
            h2 = h2.next;
        }
        ListNode head = h;
        while (h1 != null && h2 != null) {
            if (h1.val < h2.val) {
                h.next = h1;
                h1 = h1.next;
            } else {
                h.next = h2;
                h2 = h2.next;
            }
            h = h.next;
        }

        if (h1 != null) {
            h.next = h1;
        }
        if (h2 != null) {
            h.next = h2;
        }
        return head;
    }

    private Random random = new Random();

    public ListNode generateList(int n) {
        ListNode head = new ListNode(random.nextInt(n));
        ListNode curr = head;
        for (int i = 1; i < n; i++) {
            curr.next = new ListNode(random.nextInt());
            curr = curr.next;
        }
        return head;
    }
}
