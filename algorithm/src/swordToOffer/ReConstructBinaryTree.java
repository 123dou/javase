package swordToOffer;

import leetcode.TreeAndList.TreeNode;

/**
 * 输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。
 * 假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 * 例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，则重建二叉树并返回。
 */
public class ReConstructBinaryTree {
    public TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        if (pre == null || in == null || pre.length != in.length) return null;
        return reConstructBinaryTree(pre, in, 0, 0, in.length - 1);
    }

    private TreeNode reConstructBinaryTree(int[] pre, int[] in, int curr, int inleft, int inright) {
        if (inleft > inright || curr >= pre.length) return null;
        TreeNode node = new TreeNode(pre[curr]);
        int index = inright;
        while (index >= inleft) {
            if (in[index] == pre[curr]) break;
            index--;
        }
        node.left = reConstructBinaryTree(pre, in, curr + 1, inleft, index - 1);
        node.right = reConstructBinaryTree(pre, in, curr + (index - inleft) + 1, index + 1, inright);
        return node;
    }
}
