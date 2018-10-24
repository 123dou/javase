package swordToOffer;

import leetcode.TreeAndList.ListNode;

import java.util.ArrayList;

/**
 * 输入一个链表，按链表值从尾到头的顺序返回一个ArrayList。
 */
public class PrintListFromTailToHead {
    public static void main(String[] args) {

    }

    /**
     * 递归,时间复杂度只要n,其他的要2n
     *
     * @param listNode
     * @return
     */
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ArrayList<Integer> list = new ArrayList<>();
        printListFromTailToHead(listNode, list);
        return list;
    }

    private void printListFromTailToHead(ListNode listNode, ArrayList<Integer> list) {
        if (listNode == null) return;
        printListFromTailToHead(listNode.next, list);
        list.add(listNode.val);
    }
}
