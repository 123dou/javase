package algs4.string.wordSearchTree;


import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 基于查找树的符号表
 */
public class TrieST<V> {
    private static int R = 256;
    private Node root;

    /**
     * 从单词查找树中删除一个键和它所关联的值
     *
     * @param key
     */
    public void delete(String key) {
        root = delete(root, key, 0);
    }

    /**
     * 从一棵单词查找树中删区一个键值对的第一步是,找到键所对应的结点并将它的值设为空(null).如果该结点含有一个费空的链接指向某个子结点,
     * 那么就不需要再进行其它操作了.如果它的所有链接均为空,那就需要从数据结构中删去这个结点.如果删去它使得它的父结点的所有链接也均为空,
     * 就需要继续删除它的父结点,依次类推.
     *
     * @param x
     * @param key
     * @param d
     * @return
     */
    private Node delete(Node x, String key, int d) {
        if (x == null)
            return null;
        if (d == key.length())
            x.val = null;
        else {
            char c = key.charAt(d);
            x.next[c] = delete(x.next[c], key, d + 1);
        }
        if (x.val != null)
            return x;
        for (int c = 0; c < R; c++) {
            if (x.next[c] != null) return x;
        }
        return null;
    }

    /**
     * 找到给定字符串的最长键前缀,这是一个类似get()的递归方法,它会记录查找路径上所找到的最长键的长度(将它作为递归方法的参数在遇到值非空的结
     * 点时更新它).查找会在被查找的字符串结束或是遇到空链接时终止.
     *
     * @param s
     * @return
     */
    public String longestPrefixOf(String s) {
        int length = search(root, s, 0, 0);
        return s.substring(0, length);
    }

    /**
     * 找到给定字符串的最长键前缀,这是一个类似get()的递归方法,它会记录查找路径上所找到的最长键的长度(将它作为递归方法的参数在遇到值非空的结
     * 点时更新它).查找会在被查找的字符串结束或是遇到空链接时终止.
     *
     * @param x
     * @param s
     * @param d
     * @param length
     * @return
     */
    private int search(Node x, String s, int d, int length) {
        if (x == null)
            return length;
        if (x.val != null)
            length = d;
        if (d == s.length())
            return length;
        char c = s.charAt(d);
        return search(x.next[c], s, d + 1, length);
    }

    /**
     * 返回含有pat字符串的所有key的集合,"."可以匹配任意字符
     *
     * @param pat
     * @return
     */
    public Iterable<String> keysThatMatch(String pat) {
        Queue<String> queue = new ArrayDeque<>();
        collect(root, "", pat, queue);
        return queue;
    }

    /**
     * 递归添加所有含有pat的key入queue里面
     *
     * @param x
     * @param pre
     * @param pat
     * @param queue
     */
    private void collect(Node x, String pre, String pat, Queue<String> queue) {
        if (x == null)
            return;
        int d = pre.length();
        if (d == pat.length() && x.val != null)
            queue.add(pre);
        if (d == pat.length())
            return;
        char next = pat.charAt(d);
        for (char c = 0; c < R; c++) {
            if (next == '.' || next == c)
                collect(x, pre + c, pat, queue);
        }
    }

    /**
     * * 返回所有key的一个迭器
     *
     * @return
     */
    public Iterable<String> keys() {
        return keyWithPrefix("");
    }

    /**
     * 返回含有前缀pre的所有key的迭器
     *
     * @param pre
     * @return
     */
    private Iterable<String> keyWithPrefix(String pre) {
        Queue<String> queue = new ArrayDeque<>();
        collect(get(root, pre, 0), pre, queue);
        return queue;
    }

    /**
     * 它维护了一个字符串用来保存从根结点出发的路径上的一系列字符.每当我们在collect()中调用访问一个结点时,方法的第一个参数就是该结点,
     * 第二个参数就是和该结点相关联的字符串(从根结点到该结点的路径上的所有字符).
     * 在访问一个结点时,如果它的值非空,我们就和它相关联的字符串加入队列之中,然后(递归地)访问它的链接数组所指向的所有可能的字符结点.
     *
     * @param x
     * @param pre
     * @param queue
     */
    private void collect(Node x, String pre, Queue<String> queue) {
        if (x == null)
            return;
        if (x.val != null)
            queue.add(pre);

        for (char c = 0; c < R; c++) {
            collect(x.next[c], pre + c, queue);
        }
    }

    /**
     * 查找key对应的字符串: 返回null表示没有找到,否则反回对应的值
     *
     * @param key
     * @return
     */
    public V get(String key) {
        Node x = get(root, key, 0);
        if (x == null) return null;
        return (V) x.val;
    }

    /**
     * 递归的在单词树上查找
     *
     * @param x
     * @param key
     * @param d
     * @return
     */
    private Node get(Node x, String key, int d) {
        if (root == null)
            return null;
        if (d == key.length()) //递归结速的条件: 可能找到了对应的字符,也可能没有找到
            return x; //x可能为null,对应查找无果
        char c = key.charAt(d);
        return get(x.next[c], key, d + 1); //递归调用: x.next[c]key中第d个子符对应的映射 char ->int 可以自动类型转换
    }

    /**
     * 添加单词
     *
     * @param key
     */
    public void put(String key, V v) {
        root = put(root, key, v, 0);
    }

    /**
     * 递归添加单词树分支
     *
     * @param x   单词树的结点
     * @param key 要添加的单词
     * @param d   要添加的单词的key的第d个字符
     * @return
     */
    private Node put(Node x, String key, V v, int d) {
        if (x == null) x = new Node();
        if (d == key.length()) { //递归结束的条件
            x.val = v;
            return x;
        }
        char c = key.charAt(d);
        x.next[c] = put(x.next[c], key, v, d + 1); //将key的第d个字符插入到结点
        return x;
    }

    /**
     * 结点内部类:
     * R向单词查找树
     */
    private static class Node {
        private Object val; //结点维持的数据
        private Node[] next = new Node[R]; //数组的每个元素对应着一个字符串中的字符
    }
}
