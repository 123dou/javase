package leetcode.dfs;

import leetcode.TreeAndList.TreeNode;

import java.util.LinkedList;

/**
 * 给定一个二叉树，判断它是否是高度平衡的二叉树。
 * <p>
 * 本题中，一棵高度平衡二叉树定义为：
 * <p>
 * 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过1。
 * <p>
 * 示例 1:
 * <p>
 * 给定二叉树 [3,9,20,null,null,15,7]
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * 返回 true 。
 * <p>
 * 示例 2:
 * <p>
 * 给定二叉树 [1,2,2,3,3,null,null,4,4]
 * <p>
 * 1
 * / \
 * 2   2
 * / \
 * 3   3
 * / \
 * 4   4
 * 返回 false 。
 */
public class BanlanceBinaryTree {
    public static void main(String[] args) {

    }

    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        int left = getDepth(root.left);
        int right = getDepth(root.right);
        if (Math.abs(left - right) > 1) return false;
        if (!isBalanced(root.left)) return false;
        if (!isBalanced(root.right)) return false;
        ;
        return true;
    }

    public int getLayers(TreeNode root) {
        if (root == null) return 0;
        TreeNode temp = root;
        LinkedList<TreeNode> q = new LinkedList<>();
        q.add(temp);
        q.add(null);
        int res = 0;
        while (!q.isEmpty()) {
            temp = q.poll();
            if (temp != null) {
                if (temp.left != null) q.add(temp.left);
                if (temp.right != null) q.add(temp.right);
            } else {
                res++;
                if (q.isEmpty()) return res;
                q.add(null);
            }
        }
        return res;

    }

    public int getDepth(TreeNode root) {
        if (root == null) return 0;
        int leftDepth = getDepth(root.left);
        int rightDepth = getDepth(root.right);
        return leftDepth > rightDepth ? leftDepth + 1 : rightDepth + 1;
    }

}
