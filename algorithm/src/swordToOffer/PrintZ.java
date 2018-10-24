package swordToOffer;

import leetcode.TreeAndList.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * 请实现一个函数按照之字形打印二叉树，即第一行按照从左到右的顺序打印，第二层按照从右至左的顺序打印，
 * 第三行按照从左到右的顺序打印，其他行以此类推。
 */
public class PrintZ {
    public static void main(String[] args) {

    }

    public ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> lists = new ArrayList<>();
        if (pRoot == null) return lists;
        LinkedList<TreeNode> q = new LinkedList<>();
        q.addLast(pRoot);
        q.addLast(null);
        q.addLast(null);
        boolean isOrderOut = true;
        ArrayList<Integer> l = new ArrayList<>();
        while (!q.isEmpty()) {
            if (isOrderOut) {
                pRoot = q.pollFirst();
                if (pRoot != null) {
                    l.add(pRoot.val);
                    if (pRoot.left != null) q.addLast(pRoot.left);
                    if (pRoot.right != null) q.addLast(pRoot.right);
                } else {
                    lists.add(l);
                    l = new ArrayList<>();
                    isOrderOut = !isOrderOut;
                }
            } else {
                pRoot = q.pollLast();
                if (pRoot != null) {
                    l.add(pRoot.val);
                    if (pRoot.right != null) q.addFirst(pRoot.right);
                    if (pRoot.left != null) q.addFirst(pRoot.left);
                } else {
                    if (l.size() != 0) lists.add(l);
                    if (q.isEmpty()) break;
                    l = new ArrayList<>();
                    q.addLast(null);
                    q.addLast(null);
                    isOrderOut = !isOrderOut;
                }
            }
        }
        return lists;
    }
}
