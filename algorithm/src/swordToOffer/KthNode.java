package swordToOffer;

import leetcode.TreeAndList.TreeNode;

/**
 * 给定一棵二叉搜索树，请找出其中的第k小的结点。例如， （5，3，7，2，4，6，8）    中，
 * 按结点数值大小顺序第三小结点的值为4。
 */
public class KthNode {
    public static void main(String[] args) {

    }

    public TreeNode KthNode(TreeNode pRoot, int k) {
        return KthNode(pRoot, k, new int[]{0});
    }

    private TreeNode KthNode(TreeNode root, int k, int[] index) {
        if (root == null) return null;
        TreeNode node = KthNode(root.left, k, index);
        if (node != null) return node;
        index[0]++;
        if (k == index[0]) {
            return root;
        }
        return KthNode(root.right, k, index);
    }
}
