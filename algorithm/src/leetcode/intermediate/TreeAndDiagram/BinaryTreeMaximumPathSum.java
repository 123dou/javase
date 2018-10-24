package leetcode.intermediate.TreeAndDiagram;

import leetcode.TreeAndList.TreeNode;

/**
 * 给定一个非空二叉树，返回其最大路径和。
 * <p>
 * 本题中，路径被定义为一条从树中任意节点出发，达到任意节点的序列。该路径至少包含一个节点，且不一定经过根节点。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,2,3]
 * <p>
 * 1
 * / \
 * 2   3
 * <p>
 * 输出: 6
 * 示例 2:
 * <p>
 * 输入: [-10,9,20,null,null,15,7]
 * <p>
 * -10
 * / \
 * 9  20
 * /  \
 * 15   7
 * <p>
 * 输出: 42
 */
public class BinaryTreeMaximumPathSum {
    public static void main(String[] args) {

    }

    private int max = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        if (root == null) return 0;
        sum(root);
        return max;
    }

    private int sum(TreeNode root) {
        int curr = root.val, l = 0, r = 0;
        if (root.left != null) l = sum(root.left);
        if (root.right != null) r = sum(root.right);

        curr = curr + (l > 0 ? l : 0) + (r > 0 ? r : 0);
        max = Math.max(curr, max);
        return root.val + Math.max((l > 0 ? l : 0), (r > 0 ? r : 0));
    }
}
