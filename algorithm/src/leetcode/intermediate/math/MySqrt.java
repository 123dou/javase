package leetcode.intermediate.math;

/**
 * 实现 int sqrt(int x) 函数。
 * <p>
 * 计算并返回 x 的平方根，其中 x 是非负整数。
 * <p>
 * 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 4
 * 输出: 2
 * 示例 2:
 * <p>
 * 输入: 8
 * 输出: 2
 * 说明: 8 的平方根是 2.82842...,
 * 由于返回类型是整数，小数部分将被舍去。
 */
public class MySqrt {
    public static void main(String[] args) {
        System.out.println(mySqrt(Integer.MAX_VALUE));
        System.out.println(mySqrt3(8));
        System.out.println(mySqrt2(4));
    }

    /**
     * 二叉查找
     *
     * @param x
     * @return
     */
    public static int mySqrt3(int x) {
        if (x == 0 || x == 1) return x;
        long low = 0;
        long high = x / 2;
        long mid;
        while (low < high) {
            mid = (low + high) / 2;
            if (x <= mid * mid) high = mid;
            else low = mid + 1;
        }
        if (high * high <= x) return (int) high;
        return (int) high - 1;

    }


    /**
     * 直接暴力遍历
     *
     * @param x
     * @return
     */
    public static int mySqrt2(int x) {
        if (x == 0) return 0;
        if (x == 1) return 1;
        long n = 2;
        long square = n * n;
        while (square <= x) {
            n++;
            square = n * n;
        }
        return (int) n - 1;
    }


    /**
     * 直接调用函数
     *
     * @param x
     * @return
     */
    public static int mySqrt(int x) {
        return (int) Math.sqrt(x);
    }
}
