package swordToOffer;

import leetcode.TreeAndList.TreeNode;

/**
 * 输入两个单调递增的链表，输出两个链表合成后的链表，当然我们需要合成后的链表满足单调不减规则。
 */
public class Mirror {

    public void Mirror(TreeNode root) {
        if (root == null) return;
        TreeNode root2 = new TreeNode(root.val);
        mirror(root, root2);
        root.left = root2.left;
        root.right = root2.right;
    }

    private void mirror2(TreeNode rt) {
        if (rt == null || (rt.left == null && rt.right == null)) return;
        TreeNode temp = rt.left;
        rt.left = rt.right;
        rt.right = temp;
        mirror2(rt.left);
        mirror2(rt.right);
    }

    private void mirror(TreeNode rt1, TreeNode rt2) {
        if (rt1 == null) return;
        if (rt1.left != null)
            rt2.right = new TreeNode(rt1.left.val);
        mirror(rt1.left, rt2.right);
        if (rt1.right != null)
            rt2.left = new TreeNode(rt1.right.val);
        mirror(rt1.right, rt2.left);
    }
}
