package leetcode.intermediate.math;

/**
 * 实现 pow(x, n) ，即计算 x 的 n 次幂函数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 2.00000, 10
 * 输出: 1024.00000
 * 示例 2:
 * <p>
 * 输入: 2.10000, 3
 * 输出: 9.26100
 * 示例 3:
 * <p>
 * 输入: 2.00000, -2
 * 输出: 0.25000
 * 解释: 2-2 = 1/22 = 1/4 = 0.25
 * 说明:
 * <p>
 * -100.0 < x < 100.0
 * n 是 32 位有符号整数，其数值范围是 [−231, 231 − 1] 。
 */
public class MyPow {
    public static void main(String[] args) {
        double x = 2;
        int n = 3;
        System.out.println(myPow5(2, 11));
    }

    /**
     * 递归快速幂
     *
     * @param x
     * @param n
     * @return
     */
    public static double myPow5(double x, int n) {
        if ((x - 1.0 == 0.0) || (n == 0)) return 1.0;
        if (n < 0) {
            x = 1 / x;
            n = -n;
        }
        return getPow(x, n);
    }


    public static double getPow(double x, int n) {
        if (n == 2) return x * x;
        double y = 1;
        if ((n & 1) != 0) y = x;
        x *= x;
        double res = getPow(x, n >>> 1);
        return res * y;
    }


    /**
     * 快速幂
     *
     * @param x
     * @param n
     * @return
     */
    public static double myPow4(int x, int n) {
        int t, y;
        t = 1;
        y = x;
        while (n != 0) {
            if ((n & 1) == 1) t *= y;
            y *= y;
            n >>>= 1;
        }
        return t;
    }


    /**
     * @param x
     * @param n
     * @return
     */
    public static double myPow3(double x, int n) {
        if (n == 0) return 1.0;
        if (n == 1) return x;
        if (n < 0) {
            x = 1 / x;
            n = -n;
        }
        long c = n;
        if (n == Integer.MIN_VALUE) c = (long) Integer.MAX_VALUE + 1;
        double m = x;
        long i = 1;
        double result = 1;
        while (i < c) {
            if (i * 2 < c) {
                x *= x;
                i *= 2;
            } else {
                result *= x;
                c -= i;
                x = m;
                i = 1;
            }
        }
        return x * result;
    }


    /**
     * 超出时间限制
     *
     * @param x
     * @param n
     * @return
     */
    public static double myPow2(double x, int n) {
        if (n == 0) return 1.0;
        if (n < 0) {
            x = 1 / x;
            n = -n;
        }
        double m = x;
        for (int i = 1; i < n; i++) x *= m;
        return x;
    }


    /**
     * 直接调用函数
     *
     * @param x
     * @param n
     * @return
     */
    public static double myPow(double x, int n) {
        return Math.pow(x, n);
    }


}
