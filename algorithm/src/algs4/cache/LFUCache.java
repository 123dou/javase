package algs4.cache;

import java.util.HashMap;
import java.util.Map;

/**
 * 设计并实现最不经常使用（LFU）缓存的数据结构。它应该支持以下操作：get 和 put。
 * <p>
 * get(key) - 如果键存在于缓存中，则获取键的值（总是正数），否则返回 -1。
 * put(key, value) - 如果键不存在，请设置或插入值。当缓存达到其容量时，它应该在插入新项目之前，使最不经常使用的项目无效。在此问题中，当存在平局（即两个或更多个键具有相同使用频率）时，最近最少使用的键将被去除。
 * <p>
 * 进阶：
 * 你是否可以在 O(1) 时间复杂度内执行两项操作？
 * <p>
 * 示例：
 * <p>
 * LFUCache cache = new LFUCache( 2  );
 * <p>
 * cache.put(1, 1);
 * cache.put(2, 2);
 * cache.get(1);       // 返回 1
 * cache.put(3, 3);    // 去除 key 2
 * cache.get(2);       // 返回 -1 (未找到key 2)
 * cache.get(3);       // 返回 3
 * cache.put(4, 4);    // 去除 key 1
 * cache.get(1);       // 返回 -1 (未找到 key 1)
 * cache.get(3);       // 返回 3
 * cache.get(4);       // 返回 4
 */
public class LFUCache {
    private Map<Integer, ListNode> cache;
    private final int CAPACITY;
    private int size;
    private ListNode head;
    private ListNode tail;

    public LFUCache(int capacity) {
        this.CAPACITY = capacity;
        cache = new HashMap<>();
    }

    public int get(int key) {
        if (size == 0 || !cache.containsKey(key)) return -1;
        ListNode node = cache.get(key);
        node.freq++;
        adjustNode(node);
        return node.val;
    }

    public void put(int key, int value) {
        if (CAPACITY == 0) return;
        if (cache.containsKey(key)) {
            ListNode node = cache.get(key);
            node.val = value;
            node.freq++;
            adjustNode(node);
            return;
        }
        if (size < CAPACITY) {
            ListNode node = new ListNode(key, value, 1);
            if (head == null) {
                head = node;
                tail = head;
            } else {
                tail.next = node;
                node.prve = tail;
                tail = node;
            }
            size++;
            cache.put(key, node);
        } else {
            cache.remove(tail.key);
            tail.key = key;
            tail.val = value;
            tail.freq = 1;
            cache.put(key, tail);
        }
        adjustNode(tail);
    }


    private void adjustNode(ListNode node) {
        if (head == node) return;
        ListNode curr = node.prve;
        if (curr.freq > node.freq) return;
        curr.next = node.next;
        if (node == tail) tail = node.prve;
        else node.next.prve = curr;
        while (curr != null && curr.freq <= node.freq) {
            curr = curr.prve;
        }
        if (curr == null) {
            node.next = head;
            head.prve = node;
            head = node;
            head.prve = null;
        } else {
            node.next = curr.next;
            node.next.prve = node;
            curr.next = node;
            node.prve = curr;
        }
    }

    private class ListNode {
        ListNode next, prve;
        int key, val, freq;

        public ListNode(int key, int val, int freq) {
            this.key = key;
            this.val = val;
            this.freq = freq;
        }
    }


    public static void main(String[] args) {
        LFUCache t = new LFUCache(10);
        t.put(7, 28);
        t.put(7, 1);
        t.put(8, 15);
        System.out.println(t.get(6));
        t.put(10, 27);
        t.put(8, 10);
        System.out.println(t.get(8));
        t.put(6, 29);
        t.put(1, 9);
        System.out.println(t.get(6));
        t.put(10, 7);
        System.out.println(t.get(1));
        System.out.println(t.get(2));
        System.out.println(t.get(13));
        t.put(8, 30);
        t.put(1, 5);
        System.out.println(t.get(1));
        t.put(13, 2);
        System.out.println(t.get(12));
    }
}
