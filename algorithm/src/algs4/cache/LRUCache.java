package algs4.cache;

import java.util.HashMap;
import java.util.Map;

/**
 * 运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制。它应该支持以下操作： 获取数据 get 和 写入数据 put 。
 * <p>
 * 获取数据 get(key) - 如果密钥 (key) 存在于缓存中，则获取密钥的值（总是正数），否则返回 -1。
 * 写入数据 put(key, value) - 如果密钥不存在，则写入其数据值。当缓存容量达到上限时，它应该在写入新数据之前删除最近最少使用的数据值，从而为新的数据值留出空间。
 * <p>
 * 进阶:
 * <p>
 * 你是否可以在 O(1) 时间复杂度内完成这两种操作？
 * <p>
 * 示例:
 * <p>
 * //LRUCache cache = new LRUCache(2)// 缓存容量;
 * cache.put(1,1);
 * cache.put(2,2);
 * cache.get(1);       // 返回  1
 * cache.put(3,3);    // 该操作会使得密钥 2 作废
 * cache.get(2);       // 返回 -1 (未找到)
 * cache.put(4,4);    // 该操作会使得密钥 1 作废
 * cache.get(1);       // 返回 -1 (未找到)
 * cache.get(3);       // 返回  3
 * cache.get(4);       // 返回  4
 */

public class LRUCache {
    private final int CAPACITY;
    private Map<Integer, ListNode> map;
    private int size;
    private ListNode head;
    private ListNode tail;

    public LRUCache(int capacity) {
        this.CAPACITY = capacity;
        this.map = new HashMap<>();
    }

    public int get(int key) {
        if (size == 0 || !map.containsKey(key)) return -1;
        ListNode node = map.get(key);
        deleteNode(node);
        setHead(node);
        return node.val;
    }

    /**
     * 删除一个结点
     *
     * @param node
     */
    private void deleteNode(ListNode node) {
        if (node == head) return;
        ListNode pre = node.prve;
        ListNode next = node.next;
        pre.next = next;
        if (next != null) next.prve = pre;
        if (node == tail) tail = pre;
    }

    /**
     * 设置头结点
     *
     * @param node
     */
    private void setHead(ListNode node) {
        if (node == head) return;
        node.next = head;
        head.prve = node;
        head = node;
        head.prve = node;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            ListNode node = map.get(key);
            node.val = value;
            deleteNode(node);
            setHead(node);
        } else {
            ListNode node;
            if (size < CAPACITY) {
                node = new ListNode(key, value);
                if (head == null) {
                    head = node;
                    tail = head;
                }
                size++;
            } else {
                map.remove(tail.key);
                node = tail; //复用结点
                node.key = key;
                node.val = value;
                deleteNode(tail);
            }
            setHead(node);
            map.put(key, node);
        }
    }

    private class ListNode {
        ListNode next, prve;
        int key, val;

        public ListNode(int key, int val) {
            this.val = val;
            this.key = key;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) return true;
            if (!(obj instanceof ListNode)) return false;
            if (((ListNode) obj).key != key) return false;
            if (((ListNode) obj).val != val) return false;
            return true;
        }

        @Override
        public int hashCode() {
            int hash = super.hashCode();
            hash = hash * 31 + key;
            hash = hash * 31 + val;
            return hash;
        }
    }

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(2);
        lruCache.put(2, 1);
        lruCache.put(1, 1);
        //System.out.println(lruCache.get(1));
        lruCache.put(2, 3);
        lruCache.put(4, 1);
        System.out.println(lruCache.get(1));
        System.out.println(lruCache.get(2));
    }
}
