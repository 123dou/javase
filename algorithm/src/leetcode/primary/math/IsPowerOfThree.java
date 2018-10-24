package leetcode.primary.math;

/**
 * 给定一个整数，写一个函数来判断它是否是 3 的幂次方。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 27
 * 输出: true
 * 示例 2:
 * <p>
 * 输入: 0
 * 输出: false
 * 示例 3:
 * <p>
 * 输入: 9
 * 输出: true
 * 示例 4:
 * <p>
 * 输入: 45
 * 输出: false
 * 进阶：
 * 你能不使用循环或者递归来完成本题吗？
 */
public class IsPowerOfThree {
    public static void main(String[] args) {
        int n = 45;
        boolean powerOfThree = isPowerOfThree2(n);
        System.out.println(powerOfThree);
    }

    public static boolean isPowerOfThree2(int n) {
        return (n > 0 && 1162261467 % n == 0);
    }


    /**
     * 循环的方法
     *
     * @param n
     * @return
     */
    public static boolean isPowerOfThree(int n) {
        if (n == 0) return false;
        if (n == 1) return true;
        while (n % 3 == 0) {
            n /= 3;
        }
        if (n == 1) return true;
        else return false;
    }
}
