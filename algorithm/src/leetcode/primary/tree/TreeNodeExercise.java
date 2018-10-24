package leetcode.primary.tree;

import leetcode.TreeAndList.TreeNode;

import java.util.*;

public class TreeNodeExercise {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6, 7, 9};
        TreeNode node = sortedArrayToBST(nums);

        System.out.println();
    }

    /**
     * 给定一个二叉树，找出其最大深度。
     * <p>
     * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
     * <p>
     * 说明: 叶子节点是指没有子节点的节点。
     * <p>
     * 示例：
     * 3
     * / \
     * 9  20
     * /  \
     * 15   7
     * 返回它的最大深度 3
     * 给定二叉树 [3,9,20,null,null,15,7]，
     * 递归求左右子树的最大深度,返回左右子树中的最大值
     *
     * @param root
     * @return
     */
    public static int maxDepth(TreeNode root) {
        if (root == null) return 0;
        int left = maxDepth(root.left) + 1;
        int right = maxDepth(root.right) + 1;
        return left > right ? left : right;
    }

    /**
     * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
     * <p>
     * 一个二叉搜索树具有如下特征：
     * <p>
     * 节点的左子树只包含小于当前节点的数。
     * 节点的右子树只包含大于当前节点的数。
     * 所有左子树和右子树自身必须也是二叉搜索树。
     * 示例 1:
     * <p>
     * 输入:
     * 2
     * / \
     * 1   3
     * 输出: true
     * 示例 2:
     * <p>
     * 输入:
     * 5
     * / \
     * 1   4
     * / \
     * 3   6
     * 输出: false
     * 解释: 输入为: [5,1,4,null,null,3,6]。
     * 根节点的值为 5 ，但是其右子节点值为 4 。
     *
     * @param root
     * @return
     */
    public static boolean isValidBST(TreeNode root) {
        if (root == null) return true;
        List<Integer> list = new ArrayList<>();
        list = isValidBST(root, list);
        for (int i = 0; i < list.size() - 1; i++) { //根据中序遍历来判断是否有序
            if (list.get(i) >= list.get(i + 1))
                return false;
        }
        return true;
    }

    /**
     * 获取中序遍历的结果
     *
     * @param root
     * @param list
     * @return
     */
    private static List<Integer> isValidBST(TreeNode root, List<Integer> list) {
        if (root != null) {
            isValidBST(root.left, list);
            list.add(root.val);
            isValidBST(root.right, list);
        }
        return list;
    }

    /**
     * 好方法
     *
     * @param root
     * @return
     */
    static TreeNode pre; //用来保存中序遍历的前一个结点的值

    public static boolean isValidBST2(TreeNode root) {
        if (root == null) return true;
        if (!isValidBST(root.left)) return false;
        if (pre == null) pre = root;
        else {
            if (pre.val >= root.val) return false;
            pre = root;
        }
        return isValidBST(root.right);
    }

    /**
     * 给定一个二叉树，检查它是否是镜像对称的。
     * <p>
     * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
     * <p>
     * 1
     * / \
     * 2   2
     * / \ / \
     * 3  4 4  3
     * 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
     * <p>
     * 1
     * / \
     * 2   2
     * \   \
     * 3    3
     * 说明:
     * <p>
     * 如果你可以运用递归和迭代两种方法解决这个问题，会很加分。
     *
     * @param root
     * @return
     */
    public static boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        TreeNode left = root.left;
        TreeNode right = root.right;
        return isSymmetric(left, right);
    }

    /**
     * 递归方法调用
     *
     * @param root1
     * @param root2
     * @return
     */
    private static boolean isSymmetric(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) return true;
        if (root1 == null || root2 == null) return false;
        if (!isSymmetric(root1.left, root2.right)) return false;
        if (root1.val != root2.val) return false;
        return isSymmetric(root1.right, root2.left);
    }

    /**
     * 用层次遍历:跌代法
     *
     * @param root
     * @return
     */
    public static boolean isSymmetric2(TreeNode root) {
        if (root == null) return true;
        TreeNode left = root;
        TreeNode right = root;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        queue.add(root);
        while (!queue.isEmpty()) {
            left = queue.poll();
            right = queue.poll();
            if (left == null && right == null) continue;
            if (left == null || right == null) return false;
            if (left.val != right.val) return false;
            //注意入队列的顺序
            queue.add(left.left);
            queue.add(right.right);
            queue.add(left.right);
            queue.add(right.left);
        }
        return true;
    }

    /**
     * 给定一个二叉树，返回其按层次遍历的节点值。 （即逐层地，从左到右访问所有节点）。
     * <p>
     * 例如:
     * 给定二叉树: [3,9,20,null,null,15,7],
     * <p>
     * 3
     * / \
     * 9  20
     * /  \
     * 15   7
     * 返回其层次遍历结果：
     * <p>
     * [
     * [3],
     * [9,20],
     * [15,7]
     * ]l
     * 层次遍历用队列作为存储结构,在每一层的末尾,放一个null作为哨兵
     *
     * @param root
     * @return
     */
    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> lists = new LinkedList<>();
        if (root == null) return lists;
        LinkedList<TreeNode> q = new LinkedList<>();
        q.add(root);
        q.add(null);
        LinkedList<Integer> l = new LinkedList<>();
        while (!q.isEmpty()) {
            root = q.poll();
            if (root != null) {
                l.add(root.val);
                if (root.left != null) q.add(root.left);
                if (root.right != null) q.add(root.right);
            } else {
                lists.add(l);
                if (q.isEmpty()) break;
                l = new LinkedList<>();
                q.add(null);
            }
        }
        return lists;
    }

    /**
     * 将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。
     * <p>
     * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
     * <p>
     * 示例:
     * <p>
     * 给定有序数组: [-10,-3,0,5,9],
     * <p>
     * 一个可能的答案是：[0,-3,9,-10,null,5]，它可以表示下面这个高度平衡二叉搜索树：
     * <p>
     * 0
     * / \
     * -3   9
     * /   /
     * -10  5
     *
     * @param nums
     * @return
     */
    public static TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) return null;
        return sortedArrayToBST(nums, 0, nums.length - 1);
    }

    /**
     * 递归的方法
     *
     * @param nums
     * @param low
     * @param hi
     * @return
     */
    public static TreeNode sortedArrayToBST(int[] nums, int low, int hi) {
        if (low > hi) return null;
        int mid = low + (hi - low) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = sortedArrayToBST(nums, low, mid - 1);
        root.right = sortedArrayToBST(nums, mid + 1, hi);
        return root;
    }

    /**
     * 跌代的方法
     *
     * @param nums
     * @return
     */
    public static TreeNode sortedArrayToBST2(int[] nums) {
        if (nums == null || nums.length == 0) return null;
        LinkedList<Integer> list = new LinkedList<>();
        TreeNode root = new TreeNode(nums[nums.length >> 1]);
        int low = 0;
        int hi = nums.length - 1;
        int mid = nums.length >> 1;
        list.addLast(hi);
        list.addLast(mid + 1);
        list.addLast(mid - 1);
        list.addLast(low);
        while (!list.isEmpty()) {
            low = list.pollLast();
            hi = list.pollLast();
            if (low <= hi) {
                mid = low + (hi - low) >> 1;
                insert(root, nums[mid]);
                if (hi >= mid + 1) {
                    list.addLast(hi);
                    list.addLast(mid + 1);
                }
                if (low <= mid + 1) {
                    list.addLast(mid - 1);
                    list.addLast(low);
                }
            }

        }
        return root;
    }

    /**
     * 二叉搜索树的插入
     *
     * @param root
     * @param n
     */
    public static void insert(TreeNode root, int n) {
        TreeNode node = new TreeNode(n);
        while (root != null) {
            if (n < root.val && root.left == null) {
                root.left = node;
                break;
            } else if (n < root.val) root = root.left;
            else if (root.right == null) {
                root.right = node;
                break;
            } else root = root.right;
        }
    }

}
