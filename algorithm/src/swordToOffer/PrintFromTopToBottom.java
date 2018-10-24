package swordToOffer;

import leetcode.TreeAndList.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * 从上往下打印出二叉树的每个节点，同层节点从左至右打印。
 */
public class PrintFromTopToBottom {
    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        if (root == null) return list;
        LinkedList<TreeNode> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            root = q.poll();
            list.add(root.val);
            if (root.left != null) q.add(root.left);
            if (root.right != null) q.add(root.right);
        }
        return list;
    }
}
