package swordToOffer;

import leetcode.TreeAndList.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * 从上到下按层打印二叉树，同一层结点从左至右输出。每一层输出一行。
 */
public class Print {
    public static void main(String[] args) {

    }

    public ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> lists = new ArrayList<>();
        if (pRoot == null) return lists;
        LinkedList<TreeNode> q = new LinkedList<>();
        q.add(pRoot);
        q.add(null);
        ArrayList<Integer> l = new ArrayList<>();
        while (!q.isEmpty()) {
            pRoot = q.poll();
            if (pRoot != null) {
                l.add(pRoot.val);
                if (pRoot.left != null) q.add(pRoot.left);
                if (pRoot.right != null) q.add(pRoot.right);
            } else {
                lists.add(l);
                if (q.isEmpty()) break;
                l = new ArrayList<>();
                q.add(null);
            }
        }
        return lists;
    }
}
