package leetcode.primary.other;

/**
 * 颠倒给定的 32 位无符号整数的二进制位。
 * <p>
 * 示例:
 * <p>
 * 输入: 43261596
 * 输出: 964176192
 * 解释: 43261596 的二进制表示形式为 00000010100101000001111010011100 ，
 * 返回 964176192，其二进制表示形式为 00111001011110000010100101000000 。
 * 进阶:
 * 如果多次调用这个函数，你将如何优化你的算法？
 */
public class ReverseBits {
    public static void main(String[] args) {
        int n = 43261596;
        int i = reverseBits(n);
        System.out.println(i);
        System.out.println(Integer.reverse(n));
    }


    //算是分治的思想,先解决2个数的反转01,10,11,再解决4位,8位,16位
    // you need treat n as an unsigned value
    public int reverseBits2(int i) {
        i = (i & 0x55555555) << 1 | (i >>> 1) & 0x55555555;
        i = (i & 0x33333333) << 2 | (i >>> 2) & 0x33333333;
        i = (i & 0x0f0f0f0f) << 4 | (i >>> 4) & 0x0f0f0f0f;
        i = (i << 24) | ((i & 0xff00) << 8) |
                ((i >>> 8) & 0xff00) | (i >>> 24);
        return i;
    }


    public static int reverseBits(int n) {
        if (n == 0) return 0;
        int result;
        int aid = 0x1;
        int count = 1;
        while ((n & aid) == 0) {
            aid <<= 1;
            ++count;
        }
        result = 1;
        while (count < 32) {
            result <<= 1;
            aid <<= 1;
            if ((n & aid) != 0) result += 1;
            ++count;
        }
        return result;
    }
}
