package leetcode.dynamic;

/**
 * 最长公共子序列问题
 */
public class LCS {
    public static void main(String[] args) {
        String a = "ABKLMNABCDI";
        String b = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        System.out.println(lcs(a, b));
    }

    /**
     * f(i, j) 表示a的前i,b的前j个字符的最长公共子序列
     * f(i, j) = {-- f(i - 1, j - 1) + 1 ; ai = bj
     * -- max{f(i - 1, j), f(i, j - 1)};
     * }
     *
     * @param a
     * @param b
     * @return
     */
    public static int lcs(String a, String b) {
        if (a == null || b == null || a.length() == 0 || b.length() == 0) return 0;
        int[][] dp = new int[a.length() + 1][b.length() + 1];
        for (int i = 1; i <= a.length(); i++) {
            for (int j = 1; j <= b.length(); j++) {
                if (a.charAt(i - 1) == b.charAt(j - 1)) dp[i][j] = dp[i - 1][j - 1] + 1;
                else dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        return dp[a.length()][b.length()];
    }
}
