package swordToOffer;

import leetcode.TreeAndList.TreeNode;

/**
 * 输入一棵二叉树，判断该二叉树是否是平衡二叉树。
 */
public class IsBalanced_Solution {
    public static void main(String[] args) {

    }

    public boolean IsBalanced_Solution(TreeNode root) {
        return isBalanced(root);
    }

    private boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        if (!isBalanced(root.left)) return false;
        int left = treeDepth(root.left);
        int right = treeDepth(root.right);
        if (Math.abs(left - right) > 1) return false;
        return isBalanced(root.right);
    }

    private int treeDepth(TreeNode root) {
        if (root == null) return 0;
        return Math.max(treeDepth(root.left) + 1, treeDepth(root.right) + 1);
    }
}
