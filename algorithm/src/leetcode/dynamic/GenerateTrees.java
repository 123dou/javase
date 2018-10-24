package leetcode.dynamic;


import leetcode.TreeAndList.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个整数 n，生成所有由 1 ... n 为节点所组成的二叉搜索树。
 * <p>
 * 示例:
 * <p>
 * 输入: 3
 * 输出:
 * [
 * [1,null,3,2],
 * [3,2,null,1],
 * [3,1,null,null,2],
 * [2,1,3],
 * [1,null,2,null,3]
 * ]
 * 解释:
 * 以上的输出对应以下 5 种不同结构的二叉搜索树：
 * <p>
 * 1         3     3      2      1
 * \       /     /      / \      \
 * 3     2     1      1   3      2
 * /     /       \                 \
 * 2     1         2                 3
 */
public class GenerateTrees {
    public static void main(String[] args) {
        List<TreeNode> nodeList = generateTrees2(3);
        System.out.println(nodeList);
    }

    public static List<TreeNode> generateTrees2(int n) {
        if (n == 0) return Collections.emptyList();
        return generate(1, n);
    }

    /**
     * @param start 构造树的所有元素的开始
     * @param end   构造树的所有元素的结尾
     * @return
     */
    private static List<TreeNode> generate(int start, int end) {
        List<TreeNode> res = new ArrayList<>();
        if (start > end) res.add(null);
        for (int i = start; i <= end; i++) {
            List<TreeNode> lefts = generate(start, i - 1);
            List<TreeNode> rights = generate(i + 1, end);
            for (TreeNode l : lefts) {
                for (TreeNode r : rights) {
                    TreeNode root = new TreeNode(i);
                    root.left = l;
                    root.right = r;
                    res.add(root);
                }
            }
        }
        return res;
    }


    /**
     * 优化了空间
     *
     * @param n
     * @return
     */
    public static List<TreeNode> generateTrees(int n) {
        if (n < 1) return new ArrayList<>();
        //利用一个辅助数组保存中间的值，避免重复求取，但是也会导致有些子树的结点是公共的
        List[][] dp = new List[n + 2][n + 2];
        return generateTrees(1, n, dp);
    }

    private static List<TreeNode> generateTrees(int start, int end, List[][] dp) {
        List<TreeNode> res = new ArrayList<>();
        if (end < start) {
            res.add(null);
            return res;
        }
        for (int i = start; i <= end; i++) {
            List<TreeNode> list1 = dp[start][i - 1];
            if (list1 == null) {
                //递归，并将结果保存下来
                list1 = generateTrees(start, i - 1, dp);
                dp[start][i - 1] = list1;
            }
            //（start,i-1）为左子树，遍历不同的左子树组合
            for (TreeNode left : list1) {
                List<TreeNode> list2 = dp[i + 1][end];//当 i = n 时，就需要求 dp[n+1][end] 的值
                if (list2 == null) {
                    //递归，并将结果保存下来
                    list2 = generateTrees(i + 1, end, dp);
                    dp[i + 1][end] = list2;
                }
                //（i+1,end）为右子树，遍历不同的右子树组合
                for (TreeNode right : list2) {
                    TreeNode root = new TreeNode(i);
                    root.left = left;
                    root.right = right;
                    res.add(root);
                }
            }
        }
        return res;
    }
}
