package algs4.graph;


import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class IndexMinPQ<Key extends Comparable<Key>> implements Iterable<Integer> {
    private int n; //队列中元素的最后位置
    private int[] pq; //优先队列,实际比较的是keys[pq[i]]
    private int[] qp; //key 到优先队列索引的映射
    private Key[] keys; //实际存储的对像
    private final int CAPACITY; //优先队列的容量

    public IndexMinPQ(int capacity) {
        if (capacity < 0) throw new IllegalArgumentException();
        pq = new int[capacity + 1];
        qp = new int[capacity + 1];
        keys = (Key[]) new Comparable[capacity + 1];
        Arrays.fill(qp, -1);
        CAPACITY = capacity;
    }

    public void insert(int k, Key key) {
        if (contains(k))
            throw new IllegalArgumentException("index " + k + "already exist!");
        if (k <= 0 || k > CAPACITY)
            throw new IllegalArgumentException("index : " + k + "out of capacity!");
        pq[++n] = k;
        keys[k] = key;
        qp[k] = n;
        swim(k);
    }

    /**
     * 删除最小key并返回其关联的索引
     *
     * @return
     */
    public int deleteMin() {
        int min = pq[1];
        exch(1, n--);
        sink(1);
        qp[min] = -1;
        keys[min] = null;
        qp[n + 1] = -1;
        return min;
    }

    /**
     * 删示索引为k的key
     *
     * @param k
     */
    public void delete(int k) {
        if (!contains(k))
            throw new IllegalArgumentException("no this index:" + k);
        if (k <= 0 || k > CAPACITY)
            throw new IllegalArgumentException("index : " + k + "out of capacity!");
        int index = qp[k];
        exch(index, n--);
        swim(index);
        sink(index);
        qp[k] = -1;
        keys[k] = null; //help gc
        qp[n + 1] = -1;
    }

    /**
     * 返回最小的key
     *
     * @return
     */
    public Key min() {
        if (n == 0) throw new NoSuchElementException("Priority queue underflow");
        return keys[pq[0]];
    }

    /**
     * 返回最小key的索引
     *
     * @return
     */
    public int MinIndex() {
        if (n == 0) throw new NoSuchElementException("Priority queue underflow");
        return pq[1];
    }

    /**
     * @return 队列中元素的数量
     */
    public int size() {
        return n;
    }

    /**
     * 改变该索引关联的key
     *
     * @param k
     * @param key
     */
    public void change(int k, Key key) {
        chanegeKey(k, key);
    }

    private void chanegeKey(int k, Key key) {
        if (!contains(k))
            throw new IllegalArgumentException("no this index:" + k);
        if (k <= 0 || k > CAPACITY)
            throw new IllegalArgumentException("index : " + k + "out of capacity!");
        keys[k] = key;
        swim(qp[k]);
        sink(qp[k]);
    }


    /**
     * 向上调整队列
     *
     * @param k
     */
    private void swim(int k) {
        while (k > 1) {
            int parent = k / 2;
            if (less(k, parent)) exch(k, parent);
            k = parent;
        }
    }

    /**
     * 向下调整队列
     *
     * @param k
     */
    private void sink(int k) {
        while (2 * k <= n) {
            int t = 2 * k;
            if (t + 1 <= n && less(t + 1, t)) t++;
            if (!less(k, t)) break;
            exch(k, t);
            k = t;
        }
    }

    /**
     * 交换两个pq,qp,keys中对应的i, j
     *
     * @param i
     * @param j
     */
    private void exch(int i, int j) {
        int t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
        qp[pq[i]] = i;
        qp[pq[j]] = j;
    }

    /**
     * 比较两个索引对应的key的大小
     * 若i对应的key小于j对应的key则返回true
     *
     * @param i
     * @param j
     * @return
     */
    private boolean less(int i, int j) {
        return keys[pq[i]].compareTo(keys[pq[j]]) < 0;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public boolean contains(int k) {
        if (k > CAPACITY || k <= 0)
            throw new IllegalArgumentException("out of priority capacity: " + k);
        return qp[k] != -1;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new HeapIterator();
    }

    private class HeapIterator implements Iterator<Integer> {
        private IndexMinPQ<Key> copy;

        public HeapIterator() {
            copy = new IndexMinPQ<>(pq.length - 1);
            for (int i = 1; i < pq.length; i++) copy.insert(pq[i], keys[pq[i]]);
        }

        @Override
        public boolean hasNext() {
            return !copy.isEmpty();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public Integer next() {
            if (copy.isEmpty()) throw new NoSuchElementException();
            return copy.deleteMin();
        }
    }
}
