package leetcode.dynamic;

/**
 * 一条包含字母 A-Z 的消息通过以下方式进行了编码：
 * <p>
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * 给定一个只包含数字的非空字符串，请计算解码方法的总数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "12"
 * 输出: 2
 * 解释: 它可以解码为 "AB"（1 2）或者 "L"（12）。
 * 示例 2:
 * <p>
 * 输入: "226"
 * 输出: 3
 * 解释: 它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
 */
public class NumDecodings {
    public static void main(String[] args) {
        String s = "1030";
        int i = numDecoding(s);
        System.out.println(i);
    }

    /**
     * 设f(i)为编译到第i个字符时的所拥有的最大译码数,则到达第i个字符有两种方法,
     * 1.编译f(i-1)之后到达f(i)
     * 2.f(i-1),f(i-2)合起来编译,即从f(i-2)编译完f(i-2)之后到达f(i)
     * 即f(i) = f(i-1) + f(i-2)
     * 但是会有取f(i-2)的时候有条件:只有当s.substring(i-2,i)的结果在[11,26]之内才可以走
     * 边界:当第一个为字符0或者字符串中出现"00""30""40""50""60""70""80""90"时,整个字符串都不能编译
     *
     * @param s
     * @return
     */
    public static int numDecoding(String s) {
        if (s.length() == 0 || s.charAt(0) == '0') return 0;
        int[] dp = new int[s.length() + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 1; i < s.length(); i++) {
            int tmp = Integer.parseInt(s.substring(i - 1, i + 1));
            if (tmp <= 26 && tmp >= 10)
                dp[i + 1] += dp[i - 1];
            if (tmp % 10 != 0)//s[i]==0
                dp[i + 1] += dp[i];
        }
        return dp[s.length()];
    }
}