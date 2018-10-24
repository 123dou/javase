package leetcode.intermediate.TreeAndDiagram;


import leetcode.TreeAndList.TreeNode;

import java.util.*;

public class TreeQuestion {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.left.left = new TreeNode(2);
        root.left.left.left = new TreeNode(1);
        root.left.right = new TreeNode(4);
        root.right = new TreeNode(6);
        int i = kthSmallest(root, 3);
        System.out.println(i);
    }

    /**
     * 中序遍历,非递归
     *
     * @param root
     * @return
     */
    public static List<Integer> instack2Traversal(TreeNode root) {
        if (root == null) return Collections.emptyList();
        List<Integer> result = new LinkedList<>();
        TreeNode node = root;
        LinkedList<TreeNode> stack = new LinkedList<>();
        stack.addLast(node);
        node = node.left;
        while (true) {
            while (node != null) {
                stack.addLast(node);
                node = node.left;
            }
            if (stack.isEmpty()) break;
            node = stack.pollLast();
            result.add(node.val);
            node = node.right;
        }
        return result;
    }

    /**
     * 给定一个二叉树，返回其节点值的锯齿形层次遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
     * <p>
     * 例如：
     * 给定二叉树 [3,9,20,null,null,15,7],
     * <p>
     * 3
     * / \
     * 9  20
     * /  \
     * 15   7
     * 返回锯齿形层次遍历如下：
     * <p>
     * [
     * [3],
     * [20,9],
     * [15,7]
     * ]
     * 画幅简单的树来演示一遍就差不多有思路了
     *
     * @param root
     * @return
     */
    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null) return Collections.emptyList();
        LinkedList<TreeNode> stack2 = new LinkedList<>();
        LinkedList<TreeNode> stack1 = new LinkedList<>();
        List<List<Integer>> listList = new LinkedList<>();
        TreeNode node = root;
        stack1.addLast(node);
        stack1.addFirst(null); //一层结束的标志
        List<Integer> list = new LinkedList<>();
        boolean isOrder = true; //顺逆序输出的标志位
        while (!stack2.isEmpty() || !stack1.isEmpty()) {
            if (isOrder) node = stack1.pollLast(); //顺序输出
            else node = stack2.pollLast(); //逆序输出
            if (node != null) {
                list.add(node.val);
                if (isOrder) { //stack1输出,stack2加入
                    if (node.left != null) stack2.addLast(node.left);
                    if (node.right != null) stack2.addLast(node.right);
                } else { //stack2输出,stack1加入
                    if (node.right != null) stack1.addLast(node.right);
                    if (node.left != null) stack1.addLast(node.left);
                }
            } else { //node == null,一层结束
                listList.add(list);
                list = new LinkedList<>();
                if (!stack2.isEmpty() || !stack1.isEmpty())
                    if (isOrder) stack2.addFirst(null);
                    else stack1.addFirst(null);
                isOrder = !isOrder;
            }
        }
        return listList;
    }

    /**
     * 这个思路清晰点,只需要用一个链表
     *
     * @param root
     * @return
     */
    public static List<List<Integer>> zigzagLevelOrder2(TreeNode root) {
        List<List<Integer>> lists = new LinkedList<>();
        if (root == null) return lists;
        TreeNode temp = root;
        LinkedList<TreeNode> ll = new LinkedList<>();
        ll.add(temp);
        ll.add(null);
        ll.add(null);
        List<Integer> l = new LinkedList<>();
        boolean isOrder = true; //是否顺序输出
        while (!ll.isEmpty()) {
            if (isOrder) {
                temp = ll.pollFirst();
                if (temp != null) {
                    l.add(temp.val);
                    //顺序输出,从左到右添加到队尾
                    if (temp.left != null) ll.addLast(temp.left);
                    if (temp.right != null) ll.addLast(temp.right);
                } else { //一层遍历结束,交换顺序再继续遍历
                    lists.add(l);
                    l = new LinkedList<>();
                    isOrder = false;
                }
            } else {
                temp = ll.pollLast();
                if (temp != null) {
                    l.add(temp.val);
                    //逆序输出,从右到左添加元素到队头
                    if (temp.right != null) ll.addFirst(temp.right);
                    if (temp.left != null) ll.addFirst(temp.left);
                } else { //一层遍历结束,交换顺序再继续遍历
                    if (l.size() != 0) lists.add(l);
                    l = new LinkedList<>();
                    if (!ll.isEmpty()) {
                        ll.addLast(null);
                        ll.addLast(null);
                    } else break;
                    isOrder = true; //交换顺序
                }
            }
        }
        return lists;
    }


    /**
     * 根据一棵树的前序遍历与中序遍历构造二叉树。
     * <p>
     * 注意:
     * 你可以假设树中没有重复的元素。
     * <p>
     * 例如，给出
     * <p>
     * 前序遍历 inorder = [3,9,20,15,7]
     * 中序遍历 preorder = [9,3,15,20,7]
     * 返回如下的二叉树：
     * <p>
     * 3
     * / \
     * 9  20
     * /  \
     * 15   7
     * 算法思路: 性能:遍历一次前序序列
     * 1.选前序序列的第一个数做根结点
     * 2.从第二个数开始遍历前序序列
     * 3.获得当前遍历的值在中序中的位置设为:x   获得当前遍历的前一个值在中序中的位置为:y
     * 4.如果x<y,则将x为y的左子结点,将x结点放入栈中
     * 5.如果x>y,则遍历栈,直到找到栈中的node结点的值在中序遍历中的位置y,使得x<y,则x为y的前一个结点的右子结点
     *
     * @param inorder
     * @param preorder
     * @return
     */
    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        if (inorder == null || preorder == null || inorder.length != preorder.length
                || inorder.length == 0 || preorder.length == 0) return null;
        TreeNode root = new TreeNode(preorder[0]); //前序遍历的第一个结点为头结点
        TreeNode node = root; //返回根结点
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) { //中序遍历的序列散列,值做为键,索引做为值
            map.put(inorder[i], i);
        }
        LinkedList<TreeNode> stack = new LinkedList<>(); //将新增的结点入栈
        stack.add(node);
        int pre_curr; //前序遍历序列的当前索引
        int pre_pre; //前序遍历序列的当前索引的前一个索引
        int in_curr; //中序遍历序列的当前索引
        int in_pre;  //中序遍历序列的当前索引的前一个索引
        for (int i = 1; i < preorder.length; i++) {
            pre_curr = i;
            pre_pre = pre_curr - 1;
            in_curr = map.get(preorder[pre_curr]);
            in_pre = map.get(preorder[pre_pre]);
            if (in_curr < in_pre) { //当前结点在中序遍历的位置比前一个结点左,说明当前结点是前一个结点的左结点
                node.left = new TreeNode(preorder[pre_curr]);
                node = node.left;
                stack.add(node);
            } else {
                TreeNode pre;
                TreeNode node1 = stack.pollLast(); //前一个结点出栈
                if (stack.isEmpty()) { //根结点没有左子树的情况
                    node1.right = new TreeNode(preorder[pre_curr]);
                    node = node1.right;
                    stack.add(node);
                    continue;
                }
                while (!stack.isEmpty()) {
                    pre = node1;
                    if (!stack.isEmpty()) {
                        node1 = stack.peekLast();
                        int pre_node1 = map.get(node1.val);
                        if (in_curr < pre_node1) {
                            pre.right = new TreeNode(preorder[pre_curr]);
                            node = pre.right;
                            stack.add(node);
                            break;
                        } else {
                            if (stack.size() != 1)
                                stack.pollLast();
                            else { //从根结点的左子树到根结点的右子树
                                pre = stack.pollLast();
                                pre.right = new TreeNode(preorder[pre_curr]);
                                node = pre.right;
                                stack.add(node);
                                break;
                            }
                        }
                    }
                }
            }
        }
        return root;
    }

    /**
     * 最优解:
     *
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree2(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length <= 0) return null;
        return buildTree(preorder, inorder, 0, preorder.length - 1, 0);
    }

    private TreeNode buildTree(int[] preorder, int[] inorder, int istart, int iend, int root) {
        int value = preorder[root];
        TreeNode treeNode = new TreeNode(value);
        int i = iend;
        while (i > istart) {
            if (value == inorder[i]) break;
            i--;
        }

        if (i != istart) {
            treeNode.left = buildTree(preorder, inorder, istart, i - 1, root + 1);
        }

        if (i != iend) {
            //此为揭示在中序遍历中找出此节点左子树一共有多少个节点，则此节点的右节点，必定是前序遍历中向右偏移offset个的那个，
            // 因为在先序遍历中左子树的所有节点一定出现在右节点前。则为preorder[i+1+offset];
            int offset = root - istart;
            treeNode.right = buildTree(preorder, inorder, i + 1, iend, i + 1 + offset);
        }
        return treeNode;
    }

    /**
     * 给定一个二叉搜索树，编写一个函数 kthSmallest 来查找其中第 k 个最小的元素。
     * <p>
     * 说明：
     * 你可以假设 k 总是有效的，1 ≤ k ≤ 二叉搜索树元素个数。
     * <p>
     * 示例 1:
     * <p>
     * 输入: root = [3,1,4,null,2], k = 1
     * 3
     * / \
     * 1   4
     * \
     * 2
     * 输出: 1
     * 示例 2:
     * <p>
     * 输入: root = [5,3,6,2,4,null,null,1], k = 3
     * 5
     * / \
     * 3   6
     * / \
     * 2   4
     * /
     * 1
     * 输出: 3
     * 进阶：
     * 如果二叉搜索树经常被修改（插入 / 删除操作）并且你需要频繁地查找第 k 小的值，你将如何优化 kthSmallest 函数？
     *
     * @param root
     * @param k
     * @return
     */
    static int n_k = 0;

    public static int kthSmallest(TreeNode root, int k) {
        if (root != null) {
            int result = kthSmallest(root.left, k);
            if (n_k == k) return result;
            ++n_k;
            if (n_k == k) return root.val;
            return kthSmallest(root.right, k);
        }
        return 0;
    }

}
