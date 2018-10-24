package leetcode.intermediate.TreeAndDiagram;

import leetcode.TreeAndList.TreeNode;

/**
 * 根据一棵树的中序遍历与后序遍历构造二叉树。
 * <p>
 * 注意:
 * 你可以假设树中没有重复的元素。
 * <p>
 * 例如，给出
 * <p>
 * 中序遍历 inorder = [9,3,15,20,7]
 * 后序遍历 postorder = [9,15,7,20,3]
 * 返回如下的二叉树：
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 */
public class BuildTreeWithInorderAndPostorder {
    public static void main(String[] args) {

    }

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder == null || postorder == null || inorder.length == 0 || postorder.length == 0) return null;
        return build(postorder, inorder, 0, inorder.length - 1, postorder.length - 1);
    }


    private TreeNode build(int[] post, int[] in, int inStart, int inEnd, int root) {
        if (inStart > inEnd) return null;
        TreeNode node = new TreeNode(post[root]);
        int i;
        for (i = inStart; i <= inEnd; i++) {
            if (in[i] == post[root]) break;
        }
        int offset = inEnd - i + 1;
        node.right = build(post, in, i + 1, inEnd, root - 1);
        node.left = build(post, in, inStart, i - 1, root - offset);
        return node;
    }
}
