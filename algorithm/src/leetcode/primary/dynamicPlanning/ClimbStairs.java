package leetcode.primary.dynamicPlanning;

/**
 * 假设你正在爬楼梯。需要 n 步你才能到达楼顶。
 * <p>
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * <p>
 * 注意：给定 n 是一个正整数。
 * <p>
 * 示例 1：
 * <p>
 * 输入： 2
 * 输出： 2
 * 解释： 有两种方法可以爬到楼顶。
 * 1.  1 步 + 1 步
 * 2.  2 步
 * 示例 2：
 * <p>
 * 输入： 3
 * 输出： 3
 * 解释： 有三种方法可以爬到楼顶。
 * 1.  1 步 + 1 步 + 1 步
 * 2.  1 步 + 2 步
 * 3.  2 步 + 1 步
 */
public class ClimbStairs {
    public static void main(String[] args) {
        int n = 25;
        int i = climbStairs(n);
        System.out.println(i);
    }

    /**
     * 设F(n)为第需要n步才能到达楼顶的所有走法,在F(n)= F(n-1) + F(n-2); 因为最后一次走法可以一次走一步也可以一次走两步
     *
     * @param n
     * @return
     */
    public static int climbStairs(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        if (n == 2) return 2;
        int n_1 = 1;
        int n_2 = 2;
        for (int i = 3; i <= n; i++) {
            int temp = n_1;
            n_1 = n_2;
            n_2 = n_2 + temp;
        }
        return n_2;
    }
}
