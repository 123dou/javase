package swordToOffer;

import leetcode.TreeAndList.TreeNode;

/**
 * 输入两个单调递增的链表，输出两个链表合成后的链表，当然我们需要合成后的链表满足单调不减规则。
 */
public class HasSubtree {
    public boolean HasSubtree(TreeNode root1, TreeNode root2) {
        if (root1 != null && root2 != null) {
            if (root1.val == root2.val) {
                if (hasSubtree(root1, root2)) return true;
            }
            return HasSubtree(root1.left, root2) || HasSubtree(root1.right, root2);
        }
        return false;
    }

    private boolean hasSubtree(TreeNode rt1, TreeNode rt2) {
        if (rt2 == null) return true;
        if (rt1 == null || rt1.val != rt2.val) return false;
        return hasSubtree(rt1.left, rt2.left) && hasSubtree(rt1.right, rt2.right);
    }
}
