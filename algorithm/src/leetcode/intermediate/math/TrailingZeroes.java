package leetcode.intermediate.math;

import java.math.BigInteger;

/**
 * 给定一个整数 n，返回 n! 结果尾数中零的数量。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 3
 * 输出: 0
 * 解释: 3! = 6, 尾数中没有零。
 * 示例 2:
 * <p>
 * 输入: 5
 * 输出: 1
 * 解释: 5! = 120, 尾数中有 1 个零.
 * 说明: 你算法的时间复杂度应为 O(log n) 。
 */
public class TrailingZeroes {
    public static void main(String[] args) {
        int n = 10000;
        int i = trailingZeroes2(n);
        System.out.println(i);
    }

    /**
     * 当乘数中有2*5,时,有一个零,且2出现的频率比5高,所以只要出所有乘数里面因子里5,5^2,5^3.....的总数
     *
     * @param n
     * @return
     */
    public static int trailingZeroes2(int n) {
        int sum = 0;
        while (n > 0) {
            sum += n / 5;
            n /= 5;
        }
        return sum;
    }


    /**
     * 暴力法:时间复杂度过大
     *
     * @param n
     * @return
     */
    public static int trailingZeroes(int n) {
        if (n == 0) return 0;
        BigInteger bigInteger = new BigInteger("1");
        for (int i = 1; i <= n; i++) {
            bigInteger = bigInteger.multiply(new BigInteger("" + i));
        }
        char[] chars = bigInteger.toString().toCharArray();
        int count_0 = 0;
        for (int i = chars.length - 1; i >= 0; i--) {
            if (chars[i] == '0') count_0++;
            else break;
        }
        return count_0;
    }

}
