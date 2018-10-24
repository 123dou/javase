package leetcode.difficult.dynamic;

/**
 * 给定三个字符串 s1, s2, s3, 验证 s3 是否是由 s1 和 s2 交错组成的。
 * <p>
 * 示例 1:
 * <p>
 * 输入: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
 * 输出: true
 * 示例 2:
 * <p>
 * 输入: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
 * 输出: false
 */
public class IsInterleave {
    public static void main(String[] args) {
        String s1 = "ab";
        String s2 = "bc";
        String s3 = "bcab";
        boolean interleave = isInterleave(s1, s2, s3);
        System.out.println(interleave);
    }

    /**
     * 二维动态规划:
     * f(i,j)设为有s1的前i个和s2的前j个组成,即有个数为i个子序列是s1的前i个,有个数为j个的子序列是s2的前j个
     * f(i,j) = f(i,j) || f(i - 1,j) && s1(i - 1) == s3(i - 1 + j),数组从零开始算起
     * (i,j) = f(i,j) || f(i,j - 1) && s2(j-1) == s3(i + j - 1)
     *
     * @param s1
     * @param s2
     * @param s3
     * @return
     */
    public static boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) return false;
        boolean[][] marked = new boolean[s1.length() + 1][s2.length() + 1];
        marked[0][0] = true;
        for (int i = 0; i < marked.length; i++)
            for (int j = 0; j < marked[0].length; j++) {
                if (j > 0) marked[i][j] = marked[i][j - 1] && (s3.charAt(i + j - 1) == s2.charAt(j - 1));
                if (i > 0)
                    marked[i][j] = marked[i][j] || (marked[i - 1][j] && s3.charAt(i + j - 1) == s1.charAt(i - 1));
            }
        return marked[s1.length()][s2.length()];
    }
}