package swordToOffer;

import leetcode.TreeAndList.TreeNode;

/**
 * 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表。要求不能创建任何新的结点，
 * 只能调整树中结点指针的指向。
 */
public class Convert {
    private TreeNode head;
    private boolean isMostLeft = true;
    private TreeNode pre;

    public TreeNode Convert(TreeNode pRootOfTree) {
        if (pRootOfTree == null) return null;
        convert(pRootOfTree);
        return head;
    }

    private void convert(TreeNode root) {
        if (root != null) {
            convert(root.left);
            if (isMostLeft) {
                head = root;
                isMostLeft = false;
            }
            if (pre == null) pre = root;
            else {
                pre.right = root;
                root.left = pre;
                pre = root;
            }
            convert(root.right);
        }
    }
}
