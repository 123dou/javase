package leetcode.BitOperation;

/**
 * 给定一个正整数，检查他是否为交替位二进制数：换句话说，就是他的二进制数相邻的两个位数永不相等。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 5
 * 输出: True
 * 解释:
 * 5的二进制数是: 101
 * 示例 2:
 * <p>
 * 输入: 7
 * 输出: False
 * 解释:
 * 7的二进制数是: 111
 * 示例 3:
 * <p>
 * 输入: 11
 * 输出: False
 * 解释:
 * 11的二进制数是: 1011
 * 示例 4:
 * <p>
 * 输入: 10
 * 输出: True
 * 解释:
 * 10的二进制数是: 1010
 */
public class BinaryNumberWithAlternatingBits {
    public static void main(String[] args) {

    }

    /**
     * @param n
     * @return
     */
    public boolean hasAlternatingBits(int n) {
        int pre = -1;
        while (n != 0) {
            if ((n & 1) == 1) {
                if (pre == 1) return false;
                else pre = 1;
            } else {
                if (pre == 0) return false;
                else pre = 0;
            }
            n >>>= 1;
        }
        return true;
    }
}
