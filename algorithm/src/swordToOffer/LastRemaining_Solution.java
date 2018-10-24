package swordToOffer;

import leetcode.TreeAndList.ListNode;

/**
 * 每年六一儿童节,牛客都会准备一些小礼物去看望孤儿院的小朋友,今年亦是如此。HF作为牛客的资深元老,
 * 自然也准备了一些小游戏。其中,有个游戏是这样的:首先,让小朋友们围成一个大圈。
 * 然后,他随机指定一个数m,让编号为0的小朋友开始报数。
 * 每次喊到m-1的那个小朋友要出列唱首歌,然后可以在礼品箱中任意的挑选礼物,并且不再回到圈中,
 * 从他的下一个小朋友开始,继续0...m-1报数....这样下去....直到剩下最后一个小朋友,可以不用表演,
 * 并且拿到牛客名贵的“名侦探柯南”典藏版(名额有限哦!!^_^)。请你试着想下,哪个小朋友会得到这份礼品呢？
 * (注：小朋友的编号是从0到n-1)
 */
public class LastRemaining_Solution {
    public static void main(String[] args) {
        int n = 5, m = 3;
        LastRemaining_Solution ls = new LastRemaining_Solution();
        int i = ls.LastRemaining_Solution(n, m);
        System.out.println(i);
    }

    public int LastRemaining_Solution2(int n, int m) {
        if (n < 1 || m < 1) return -1;
        int last = 0;
        for (int i = 2; i <= n; i++) last = (last + m) % i;
        return last;
    }


    /**
     * 思路,先把各位数字连成一个环
     * 注意边界
     *
     * @param n
     * @param m
     * @return
     */
    public int LastRemaining_Solution(int n, int m) {
        m--;
        if (n < 0 || m < 0) return -1;
        if (n == 0) return n;
        ListNode head = new ListNode(0);
        ListNode node = head;
        for (int i = 1; i < n; i++) {
            node.next = new ListNode(i);
            node = node.next;
        }
        node.next = head;
        while (head != head.next.next) {
            int c = m;
            while (c > 1) {
                head = head.next;
                c--;
            }
            head.next = head.next.next;
            head = head.next;
        }
        if ((m & 1) == 1) return head.val;
        return head.next.val;
    }
}

