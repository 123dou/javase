package leetcode.dynamic;

public class OneAndZeroes {
    public static void main(String[] args) {

    }

    /**
     * //多了一维的01背包问题
     *
     * @param strs
     * @param m
     * @param n
     * @return
     */
    public int findMaxForm(String[] strs, int m, int n) {
        if (strs == null || strs.length == 0) return 0;
        int[][] dp = new int[m + 1][n + 1];
        for (int k = 0; k < strs.length; k++) {
            int[] count01 = count01(strs[k]);
            for (int i = m; i >= count01[0]; i--) {
                for (int j = n; j >= count01[1]; j--) {
                    dp[i][j] = Math.max(dp[i - count01[0]][j - count01[1]] + 1, dp[i][j]);
                }
            }
        }
        return dp[m][n];

    }

    private int[] count01(String s) {
        int[] res = new int[2];
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '0') res[0]++;
            else res[1]++;
        }
        return res;
    }
}
