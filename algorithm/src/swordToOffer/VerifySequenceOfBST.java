package swordToOffer;

import leetcode.TreeAndList.TreeNode;

import java.util.Arrays;

/**
 * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。
 * 如果是则输出Yes,否则输出No。假设输入的数组的任意两个数字都互不相同。
 */
public class VerifySequenceOfBST {
    public static void main(String[] args) {
        int[] sequence = {7, 4, 6, 5};
        VerifySequenceOfBST verifySequenceOfBST = new VerifySequenceOfBST();
        boolean b = verifySequenceOfBST.VerifySquenceOfBST(sequence);
        System.out.println(b);
    }

    public boolean VerifySquenceOfBST(int[] sequence) {
        if (sequence == null || sequence.length == 0) return false;
        int[] in = new int[sequence.length];
        System.arraycopy(sequence, 0, in, 0, sequence.length);
        Arrays.sort(in);
        TreeNode root = rebuild(in, sequence, sequence.length - 1, 0, in.length - 1);
        int size = size(root);
        return sequence.length == size;
    }

    /**
     * 简结明了
     *
     * @param sequence
     * @param left
     * @param right
     * @return
     */
    private boolean verifySequenceOfBST(int[] sequence, int left, int right) {
        if (left > right) return true;
        int offset = 0;
        //从0开始第一个比根结点大的[0, offset)为以sequence[right]为根结点的左子树,
        // [offset, right)属于根结点的右子树,主要是判断[offset, right)范围内的结点是否全都大与根结点
        for (; offset < right; ++offset) {
            if (sequence[offset] > sequence[right]) break;
        }
        int j = offset;
        for (; j < right - 1; j++) {
            if (sequence[j] < sequence[right]) return false;
        }
        //递归检查左右子树
        return verifySequenceOfBST(sequence, 0, offset - 1)
                && verifySequenceOfBST(sequence, offset, right - 1);
    }


    /**
     * 先重建二叉查找树
     *
     * @param in
     * @param post
     * @param curr
     * @param inleft
     * @param inright
     * @return
     */
    private TreeNode rebuild(int[] in, int[] post, int curr, int inleft, int inright) {
        if (inleft > inright || curr < 0) return null;
        TreeNode node = new TreeNode(post[curr]);
        int t = inright;
        int offset = 0;
        while (t >= inleft) {
            if (in[t] == post[curr]) break;
            offset++;
            t--;
        }
        node.left = rebuild(in, post, curr - offset - 1, inleft, t - 1);
        node.right = rebuild(in, post, curr - 1, t + 1, inright);
        return node;
    }

    private int size(TreeNode root) {
        if (root == null) return 0;
        return size(root.left) + size(root.right) + 1;
    }
}
