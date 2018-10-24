package leetcode.intermediate.designProblem;

import leetcode.TreeAndList.TreeNode;

import java.util.LinkedList;

public class SerializeAndDeserialize {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(5);
        String s = serialize(root);
        System.out.println(s);
        deserialize(s);
    }

    /**
     * 层次遍历
     * 先计算出总的结点数
     *
     * @param root
     * @return
     */
    public static String serialize(TreeNode root) {
        if (root == null) return "";
        int count = getCount(root);
        TreeNode temp = root;
        LinkedList<TreeNode> q = new LinkedList<>();
        q.add(temp);
        StringBuilder sb = new StringBuilder();
        sb.append(temp.val).append(",");
        while (count > 0) {
            temp = q.pollFirst();
            if (temp.left != null) {
                sb.append(temp.left.val).append(",");
                q.add(temp.left);
            } else sb.append("n").append(",");
            if (temp.right != null) {
                q.add(temp.right);
                sb.append(temp.right.val).append(",");
            } else sb.append("n").append(",");
            --count;
        }
        sb.delete(sb.length() - 1, sb.length());
        return sb.toString();
    }

    // Decodes your encoded data to algs4.tree.
    public static TreeNode deserialize(String data) {
        if (data.length() == 0) return null;
        String[] strs = data.split(",");
        TreeNode root = new TreeNode(Integer.parseInt(strs[0]));
        TreeNode temp = root;
        LinkedList<TreeNode> q = new LinkedList<>();
        q.add(temp);
        for (int i = 1; i < strs.length - 1; i++) {
            temp = q.pollFirst();
            if (!"n".equals(strs[i])) {
                temp.left = new TreeNode(Integer.parseInt(strs[i]));
                q.add(temp.left);
            }
            if (!"n".equals(strs[++i])) {
                temp.right = new TreeNode(Integer.parseInt(strs[i]));
                q.add(temp.right);
            }
        }
        return root;
    }

    public static int getCount(TreeNode root) {
        if (root == null) return 0;
        return getCount(root.left) + getCount(root.right) + 1;
    }
}
