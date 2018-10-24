package leetcode.intermediate.designProblem;

/**
 * 实现一个 Trie (前缀树)，包含 insert, search, 和 startsWith 这三个操作。
 * <p>
 * 示例:
 * <p>
 * Trie trie = new Trie();
 * <p>
 * trie.insert("apple");
 * trie.search("apple");   // 返回 true
 * trie.search("app");     // 返回 false
 * trie.startsWith("app"); // 返回 true
 * trie.insert("app");
 * trie.search("app");     // 返回 true
 * 说明:
 * <p>
 * 你可以假设所有的输入都是由小写字母 a-z 构成的。
 * 保证所有输入均为非空字符串。
 */
public class ImplementTriePrefixTree {
    public static void main(String[] args) {

    }

    private Node root;


    /**
     * Inserts a word into the trie.
     */
    public void insert(String word) {
        if (word.length() == 0) return;
        root = insert(word, root, 0);
    }

    private Node insert(String word, Node x, int length) {
        char c = word.charAt(length);
        if (x == null) {
            x = new Node(c);
        }
        if (c < x.c)
            x.left = insert(word, x.left, length);
        else if (c > x.c)
            x.right = insert(word, x.right, length);
        else if (length < word.length() - 1)
            x.mid = insert(word, x.mid, length + 1);
        else
            x.key = word;
        return x;
    }


    /**
     * Returns if the word is in the trie.
     */
    public boolean search(String word) {
        return search(word, root, 0);
    }

    private boolean search(String word, Node x, int length) {
        if (x == null)
            return false;
        char c = word.charAt(length);
        if (c < x.c)
            return search(word, x.left, length);
        else if (c > x.c)
            return search(word, x.right, length);
        else if (length < word.length() - 1)
            return search(word, x.mid, length + 1);
        else
            return word.equals(x.key);
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        return startsWith(prefix, root, 0);
    }


    private boolean startsWith(String prefix, Node x, int length) {
        if (x == null || length == prefix.length()) return false;
        char c = prefix.charAt(length);
        if (length == prefix.length() - 1 && c == x.c) return true;
        if (c < x.c) return startsWith(prefix, x.left, length);
        else if (c > x.c) return startsWith(prefix, x.right, length);
        else return startsWith(prefix, x.mid, length + 1);
    }


    static class Node {
        String key; //从根结点到该结点构成的单词是否存在
        char c;
        Node left, mid, right;

        Node(char val) {
            this(val, null);
        }

        Node(char c, String key) {
            this.c = c;
            this.key = key;
        }
    }


    //查找树符号表
    private Node2 root2;

    /**
     * Inserts a word into the trie.
     */
    public void insert2(String word) {
        if (root2 == null) root2 = new Node2();
        insert(word, root2, 0);
    }

    private void insert(String word, Node2 root, int length) {
        Node2 node = root.next[word.charAt(length) - 'a'];
        if (length == word.length() - 1) {
            if (node == null)
                root.next[word.charAt(length) - 'a'] = new Node2(true);
            else node.exist = true;
            return;
        }
        if (node == null)
            root.next[word.charAt(length) - 'a'] = new Node2();
        insert(word, root.next[word.charAt(length) - 'a'], length + 1);
    }


    /**
     * Returns if the word is in the trie.
     */
    public boolean search2(String word) {
        if (root2 == null) return false;
        return search(word, root2.next[word.charAt(0) - 'a'], 0);
    }

    private boolean search(String word, Node2 root, int length) {
        if (root == null) return false;
        if (length == word.length() - 1) return root.exist;
        return search(word, root.next[word.charAt(length + 1) - 'a'], length + 1);
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith2(String prefix) {
        if (root2 == null) return false;
        return startsWith(prefix, root2.next[prefix.charAt(0) - 'a'], 0);
    }


    private boolean startsWith(String word, Node2 root, int length) {
        if (root == null) return false;
        if (length == word.length() - 1) return true;
        return startsWith(word, root.next[word.charAt(length + 1) - 'a'], length + 1);
    }


    static class Node2 {
        boolean exist; //从根结点到该结点构成的单词是否存在
        Node2[] next = new Node2[26];

        Node2() {
        }

        ;

        //都为小写字母,字母表大小为26
        Node2(boolean exist) {
            this.exist = exist;
        }
    }
}
