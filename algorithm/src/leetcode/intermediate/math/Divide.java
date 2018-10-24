package leetcode.intermediate.math;

/**
 * 给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。
 * <p>
 * 返回被除数 dividend 除以除数 divisor 得到的商。
 * <p>
 * 示例 1:
 * <p>
 * 输入: dividend = 10, divisor = 3
 * 输出: 3
 * 示例 2:
 * <p>
 * 输入: dividend = 7, divisor = -3
 * 输出: -2
 * 说明:
 * <p>
 * 被除数和除数均为 32 位有符号整数。
 * 除数不为 0。
 * 假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−231,  231 − 1]。本题中，如果除法结果溢出，则返回 231 − 1。
 */
public class Divide {
    public static void main(String[] args) {
        System.out.println(divide2(2147483647, 3));
        System.out.println(2147483647 / 3);
    }

    /**
     * 二进制除法
     *
     * @param dividend
     * @param divisor
     * @return
     */
    public int divide3(int dividend, int divisor) {
        if (dividend == divisor) return 1;
        if (dividend == 0 || (divisor == Integer.MIN_VALUE)) return 0;
        if (dividend == -divisor) return -1;
        if (divisor == 1) return dividend;
        if (divisor == -1) return dividend == Integer.MIN_VALUE ? Integer.MAX_VALUE : -dividend;
        boolean sign = true; //判断正负号
        long L_dividend = dividend;
        //将数字转化为正数再记算
        if (divisor < 0 && L_dividend < 0) {
            divisor = -divisor;
            L_dividend = -L_dividend;
        } else if (divisor < 0 || L_dividend < 0) {
            if (divisor < 0) divisor = -divisor;
            else L_dividend = -L_dividend;
            sign = false;
        }
        if (L_dividend < divisor) return 0;
        int countDivid = 0; //计算二进制被除数最高位的位置
        long temp_dividend = L_dividend;
        while (temp_dividend != 0) {
            countDivid++;
            temp_dividend >>>= 1;
        }
        long c = (1L << (countDivid - 1)); //取被除数的最高位
        long temp = 0; //被除数的高位组成
        int res = 0;
        while (c > 0) {
            if (temp >= divisor) {
                res += 1;
                temp -= divisor;
            } else {
                res <<= 1;
                temp <<= 1;
                if ((c & L_dividend) != 0) temp += 1;
                c >>= 1;
            }
        }
        if (temp > divisor) res += 1;
        if (sign) return res;
        return -res;
    }


    /**
     * 二叉查找
     *
     * @param dividend
     * @param divisor
     * @return
     */
    public static int divide2(int dividend, int divisor) {
        if (divisor == 1) return dividend; //除数为1
        if (dividend == Integer.MIN_VALUE && divisor == -1) return Integer.MAX_VALUE; //处理溢出
        if (dividend == divisor) return 1; //被除数与除数相等
        if (dividend == -divisor) return -1; //被除数与除数互为相反数
        if (dividend == 0 || (dividend != Integer.MIN_VALUE && Math.abs(dividend) < Math.abs(divisor)))
            return 0; //被除数为0,或被除数绝对值小于除数
        int asign = 1; //符号位
        if ((dividend > 0 && divisor < 0) || (dividend < 0 && divisor > 0)) asign = -1;
        long Divided = dividend;
        long Divisor = divisor;
        if (Divided < 0) Divided = -Divided;
        if (Divisor < 0) Divisor = -Divisor;
        long low = 0;
        long high = dividend >>> 1;
        long mid = 0;
        while (low < high) {
            mid = (low + high) >>> 1;
            if (Divided <= Divisor * mid) high = mid;
            else low = mid + 1;
        }
        if (high * Divisor <= Divided) return asign * (int) high;
        return asign * (int) (high - 1);
    }


    /**
     * 蛮力法:时间复杂度高
     *
     * @param dividend
     * @param divisor
     * @return
     */
    public static int divide(int dividend, int divisor) {
        if (divisor == 1) return dividend; //除数为1
        if (dividend == Integer.MIN_VALUE && divisor == -1) return Integer.MAX_VALUE; //处理溢出
        if (dividend == divisor) return 1; //被除数与除数相等
        if (dividend == -divisor) return -1; //被除数与除数互为相反数
        if (dividend == 0 || (dividend != Integer.MIN_VALUE && Math.abs(dividend) < Math.abs(divisor)))
            return 0; //被除数为0,或被除数绝对值小于除数
        int asign = 1; //符号位
        if ((dividend > 0 && divisor < 0) || (dividend < 0 && divisor > 0)) asign = -1;
        long Divided = dividend;
        long Divisor = divisor;
        if (Divided < 0) Divided = -Divided;
        if (Divisor < 0) Divisor = -Divisor;
        int result = 1;
        long mul;
        while ((mul = result * Divisor) < Divided) result++;
        if (result * Divisor == Divided) return asign * result;
        return asign * (result - 1);
    }
}
