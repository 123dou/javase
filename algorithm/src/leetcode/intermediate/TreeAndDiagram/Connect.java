package leetcode.intermediate.TreeAndDiagram;

public class Connect {
    public static void main(String[] args) {

    }

    /**
     * 给定一个二叉树
     * <p>
     * struct TreeLinkNode {
     * TreeLinkNode *left;
     * TreeLinkNode *right;
     * TreeLinkNode *next;
     * }
     * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
     * <p>
     * 初始状态下，所有 next 指针都被设置为 NULL。
     * <p>
     * 说明:
     * <p>
     * 你只能使用额外常数空间。
     * 使用递归解题也符合要求，本题中递归程序占用的栈空间不算做额外的空间复杂度。
     * 你可以假设它是一个完美二叉树（即所有叶子节点都在同一层，每个父节点都有两个子节点）。
     * 示例:
     * <p>
     * 给定完美二叉树，
     * <p>
     * 1
     * /  \
     * 2    3
     * / \  / \
     * 4  5  6  7
     * 调用你的函数后，该完美二叉树变为：
     * <p>
     * 1 -> NULL
     * /  \
     * 2 -> 3 -> NULL
     * / \  / \
     * 4->5->6->7 -> NULL
     * 算法思想:递归:画图了解一下
     *
     * @param root
     */
    public static void connect(TreeLinkNode root) {
        if (root == null) return;
        TreeLinkNode node = root;
        if (node.left != null) {
            node.left.next = node.right;
            connect(node.left, node.right);
        }

    }

    public static void connect(TreeLinkNode node, TreeLinkNode broNode) {
        if (node.left == null || broNode.left == null) return;
        if (node.left != null) node.left.next = node.right;
        if (broNode.left != null) {
            node.right.next = broNode.left;
            broNode.left.next = broNode.right;
        }
        connect(node.left, node.right);
        connect(node.right, broNode.left);
        connect(broNode.left, broNode.right);
    }

    /**
     * 利用next
     *
     * @param root
     */
    public static void connect2(TreeLinkNode root) {
        if (root == null) return;
        if (root.left != null) {
            root.left.next = root.right;
            if (root.next != null) {
                root.right.next = root.next.left;
            }
            connect2(root.left);
            connect2(root.right);
        }
    }
}

class TreeLinkNode {
    int val;
    TreeLinkNode left, right, next;

    TreeLinkNode(int x) {
        val = x;
    }
}
