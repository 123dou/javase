package swordToOffer;

import leetcode.TreeAndList.TreeNode;

import java.util.ArrayList;

/**
 * 输入一颗二叉树的跟节点和一个整数，打印出二叉树中结点值的和为输入整数的所有路径。
 * 路径定义为从树的根结点开始往下一直到叶结点所经过的结点形成一条路径。
 * (注意: 在返回值的list中，数组长度大的数组靠前)
 */
public class FindPath {
    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(10);
        root1.left = new TreeNode(5);
        root1.left.left = new TreeNode(4);
        root1.left.right = new TreeNode(7);
        root1.right = new TreeNode(12);
        FindPath findPath = new FindPath();
        ArrayList<ArrayList<Integer>> lists = findPath.FindPath(root1, 22);
        System.out.println(lists);
    }

    private ArrayList<ArrayList<Integer>> lists;

    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int target) {
        lists = new ArrayList<>();
        if (root == null) return lists;
        ArrayList<Integer> list = new ArrayList<>();
        findPath(root, target, list);
        return lists;
    }

    /**
     * 回溯
     * 想计算出递归结结束的条件
     *
     * @param node
     * @param target
     * @param list
     */
    private void findPath(TreeNode node, int target, ArrayList<Integer> list) {
        if (node == null) return;
        if (node.left == null && node.right == null && target - node.val == 0) {
            ArrayList<Integer> l = new ArrayList<>();
            for (int i : list) l.add(i);
            l.add(node.val);
            lists.add(l);
            return;
        }
        list.add(node.val);
        findPath(node.left, target - node.val, list);
        findPath(node.right, target - node.val, list);
        if (!list.isEmpty()) list.remove(new Integer(node.val));
    }
}
