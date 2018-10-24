package swordToOffer;

import leetcode.TreeAndList.TreeNode;

/**
 * 请实现两个函数，分别用来序列化和反序列化二叉树
 */
public class SerializeAndDeserialize {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(8);
        root.left = new TreeNode(7);
        root.left.left = new TreeNode(5);
        root.left.right = new TreeNode(6);
        root.right = new TreeNode(9);
        root.right.right = new TreeNode(11);
        root.right.left = new TreeNode(10);
        SerializeAndDeserialize t = new SerializeAndDeserialize();
        String s = t.Serialize(root);
        System.out.println(s);
        TreeNode node = t.Deserialize(s);
        System.out.println(node);
    }

    /**
     * 中序遍历中将null节点序列化为"n"
     *
     * @param root
     * @return
     */
    public String Serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serialize(root, sb);
        return sb.toString();
    }

    private void serialize(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append("n,");
            return;
        }
        sb.append(root.val + ",");
        serialize(root.left, sb);
        serialize(root.right, sb);
    }

    /**
     * 返序列化中用长度为1的数组来模拟指针
     *
     * @param str
     * @return
     */
    public TreeNode Deserialize(String str) {
        if (str == null || str.length() == 0) return null;
        String[] strs = str.split(",");
        return deserialize(strs, new int[]{0});
    }


    private TreeNode deserialize(String[] strs, int[] index) {
        if (index[0] >= strs.length || "n".equals(strs[index[0]])) {
            index[0]++;
            return null;
        }
        TreeNode node = new TreeNode(Integer.parseInt(strs[index[0]]));
        index[0]++;
        node.left = deserialize(strs, index);
        node.right = deserialize(strs, index);
        return node;
    }
}
