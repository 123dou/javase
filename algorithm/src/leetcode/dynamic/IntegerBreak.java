package leetcode.dynamic;

/**
 * 给定一个正整数 n，将其拆分为至少两个正整数的和，并使这些整数的乘积最大化。 返回你可以获得的最大乘积。
 * <p>
 * 例如，给定 n = 2，返回1（2 = 1 + 1）；给定 n = 10，返回36（10 = 3 + 3 + 4）。
 * <p>
 * 注意：你可以假设 n 不小于2且不大于58。
 * <p>
 * 感谢：
 * 特别感谢 @jianchao.li.fighter 添加此问题并创建所有测试用例。
 */
public class IntegerBreak {
    public static void main(String[] args) {
        System.out.println(integerBreak(59));
    }

    /**
     * f(n) = f(n - 3) * 3
     */
    public static long integerBreak(int n) {
        if (n == 2) return 1;
        if (n == 3) return 2;
        if (n == 4) return 4;
        long[] dp = new long[n + 1];
        dp[2] = 2;
        dp[3] = 3;
        dp[4] = 4;
        for (int i = 5; i <= n; i++) {
            dp[i] = dp[i - 3] * 3;
        }
        return dp[n];
    }
}
