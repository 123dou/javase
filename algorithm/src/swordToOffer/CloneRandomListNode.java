package swordToOffer;

import java.util.HashMap;
import java.util.Map;

/**
 * 输入一个复杂链表（每个节点中有节点值，以及两个指针，一个指向下一个节点，另一个特殊指针指向任意一个节点），
 * 返回结果为复制后复杂链表的head。（注意，输出结果中请不要返回参数中的节点引用，否则判题程序会直接返回空）
 */
public class CloneRandomListNode {
    //用map集合就行了
    public RandomListNode Clone(RandomListNode pHead) {
        if (pHead == null) return null;
        RandomListNode head = new RandomListNode(pHead.label);
        RandomListNode h = head;
        RandomListNode ph = pHead.next;
        Map<RandomListNode, RandomListNode> map = new HashMap<>();
        map.put(pHead, h);
        while (ph != null) {
            h.next = new RandomListNode(ph.label);
            h = h.next;
            map.put(ph, h);
            ph = ph.next;
        }
        h = head;
        ph = pHead;
        while (ph != null) {
            h.random = map.get(ph.random);
            h = h.next;
            ph = ph.next;
        }
        return head;
    }
}

class RandomListNode {
    int label;
    RandomListNode next = null;
    RandomListNode random = null;

    RandomListNode(int label) {
        this.label = label;
    }
}
