package leetcode.primary.other;

/**
 * 两个整数之间的汉明距离指的是这两个数字对应二进制位不同的位置的数目。
 * <p>
 * 给出两个整数 x 和 y，计算它们之间的汉明距离。
 * <p>
 * 注意：
 * 0 ≤ x, y < 231.
 * <p>
 * 示例:
 * <p>
 * 输入: x = 1, y = 4
 * <p>
 * 输出: 2
 * <p>
 * 解释:
 * 1   (0 0 0 1)
 * 4   (0 1 0 0)
 * ↑   ↑
 * <p>
 * 上面的箭头指出了对应二进制位不同的位置。
 */
public class HammingDistance {
    public static void main(String[] args) {
        int x = 1;
        int y = 4;
        int i = hammingDistance(x, y);
        System.out.println(i);
    }

    public static int hammingDistance(int x, int y) {
        int x_y = x ^ y;
        int hammingDistance = 0;
        while (x_y != 0) {
            x_y = x_y & (x_y - 1);
            ++hammingDistance;
        }
        return hammingDistance;
    }

    public int hammingDistance2(int x, int y) {
        return bitCount(x ^ y);
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
