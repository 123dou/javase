package algs4.string.wordSearchTree;


/**
 * 基于三向单词查找树的符号表
 *
 * @param <V>
 */
public class TST<V> {
    private Node root;

    /**
     * 获取特定的key
     *
     * @param key
     * @return
     */
    public V get(String key) {
        Node x = get(root, key, 0);
        if (x == null)
            return null;
        return (V) x.v;
    }

    /**
     * @param x
     * @param key
     * @param d
     * @return
     */
    private Node get(Node x, String key, int d) {
        if (x == null)
            return null;
        char c = key.charAt(d);
        if (c < x.c)
            return get(x.left, key, d);
        else if (c > x.c)
            return get(x.right, key, d);
        else if (d < key.length() - 1)
            return get(x.mid, key, d + 1);
        else
            return x;
    }

    /**
     * 添加字符串
     *
     * @param key
     * @param v
     */
    public void put(String key, V v) {
        root = put(root, key, v, 0);
    }

    private Node put(Node x, String key, V v, int d) {
        char c = key.charAt(d);
        if (x == null) {
            x = new Node();
            x.c = c;
        }
        if (c < x.c)
            x.left = put(x.left, key, v, d);
        else if (c > x.c)
            x.right = put(x.right, key, v, d);
        else if (d < key.length() - 1)
            x.mid = put(x.mid, key, v, d + 1);
        else
            x.v = v;
        return x;
    }

    /**
     * 结点内部类
     */
    private class Node {
        char c; //字符
        Node left, mid, right; //左中右三向单词查找树
        V v; //和字符串相关联的值
    }
}
