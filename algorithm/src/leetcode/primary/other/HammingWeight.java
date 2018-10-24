package leetcode.primary.other;

/**
 * 编写一个函数，输入是一个无符号整数，返回其二进制表达式中数字位数为 ‘1’ 的个数（也被称为汉明重量）。
 * <p>
 * 示例 :
 * <p>
 * 输入: 11
 * 输出: 3
 * 解释: 整数 11 的二进制表示为 00000000000000000000000000001011
 * <p>
 * <p>
 * 示例 2:
 * <p>
 * 输入: 128
 * 输出: 1
 * 解释: 整数 128 的二进制表示为 00000000000000000000000010000000
 */
public class HammingWeight {
    public static void main(String[] args) {
        int n = 15;
        int i = hammingWeight(n);
        System.out.println(i);
    }

    public static int hammingWeight(int n) {
        int re = 0;

        while (0 != n) {
            n = n & (n - 1);
            ++re;
        }

        return re;
    }

    private int bitCount(int n) {
        n = n - ((n >>> 1) & 0x55555555);
        n = (n & 0x33333333) + ((n >>> 2) & 0x33333333);
        n = (n + (n >>> 4)) & 0x0f0f0f0f;
        n = (n + (n >>> 8));
        n = (n + (n >>> 16));
        return n & 0x3f;
    }
}
