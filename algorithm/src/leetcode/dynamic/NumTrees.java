package leetcode.dynamic;

/**
 * 给定一个整数 n，求以 1 ... n 为节点组成的二叉搜索树有多少种？
 * <p>
 * 示例:
 * <p>
 * 输入: 3
 * 输出: 5
 * 解释:
 * 给定 n = 3, 一共有 5 种不同结构的二叉搜索树:
 * <p>
 * 1         3     3      2      1
 * \       /     /      / \      \
 * 3     2     1      1   3      2
 * /     /       \                 \
 * 2     1         2                 3
 */
public class NumTrees {
    public static void main(String[] args) {
        System.out.println(numTrees(5));
    }

    /**
     * 设f(i)表示元素个数为1~i个时,组成的所有不同的二叉搜索树的个数
     * 则f(1) = 1;
     * f(2) = 2; 有1当根结点和2当根结点两种情况
     * 则当f(3)时, 当1当根结点时, 1              当2当根结点时: 2          当3当根结点时:   3
     * \                        /                          /
     * {2,3} = f(2)            {1}=f(1)                  {1,2}=f(2)
     * 同理:求f(4),当1当根结点时:  1             当2当根结点时:   2                当3当根结点时:  3                   当4当根结点时:   4
     * \                       /     \                        /       \                           /
     * {2,3,4}=f(3)          {1}=f(1) {3,4}=f(2)            {1,2}=f(2) {4}=f(1)                {1,2,3}=f(3)
     * 总结可得状态转移方程:f(i) = 1~i分别做根结点时的所有不同结构的数目和
     *
     * @param n
     * @return
     */
    public static int numTrees(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        if (n == 2) return 2;
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;
        int l; //左子树的元素个数
        int r; //右子树的元素个数
        int temp;
        for (int i = 3; i <= n; i++) {
            temp = 1;
            while (temp <= i) {
                l = temp - 1; //左子树的元素个数
                r = i - temp; //右子树的元素个数
                dp[i] += dp[l] * dp[r];
                temp++;
            }
        }
        return dp[n];
    }
}
