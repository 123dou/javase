package leetcode.primary.string;

/**
 * 给定一个 32 位有符号整数，将整数中的数字进行反转。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 123
 * 输出: 321
 * 示例 2:
 * <p>
 * 输入: -123
 * 输出: -321
 * 示例 3:
 * <p>
 * 输入: 120
 * 输出: 21
 * 注意:
 * <p>
 * 假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−231,  231 − 1]。根据这个假设，如果反转后的整数溢出，则返回 0。
 */
public class ReverseInteger {
    public static void main(String[] args) {
        int x = 1534236469;
        int reverse = reverse2(x);
        System.out.println(reverse);
    }

    /**
     * 用数学的方法来解决:
     *
     * @param x
     * @return
     */
    public static int reverse2(int x) {
        int sign = 1;
        if (x < 0) {
            sign = -1;
            x *= -1;
        }

        long reverseNumber = 0;
        while (x > 0) {
            reverseNumber = reverseNumber * 10 + x % 10;
            x /= 10;
        }
        reverseNumber *= sign;
        if (reverseNumber > Integer.MAX_VALUE || reverseNumber < Integer.MIN_VALUE)
            return 0;
        return (int) reverseNumber;
    }


    /**
     * 转化为字符串来解决
     *
     * @param x
     * @return
     */
    public static int reverse(int x) {
        String s = "" + x;
        if (x >= 0) {
            String string = new StringBuilder(s).reverse().toString();
            try {
                x = Integer.parseInt(string);
                return x;
            } catch (NumberFormatException e) {
                return 0;
            }
        } else {
            String string = new StringBuilder(s.substring(1, s.length())).reverse().toString();
            try {
                x = Integer.parseInt(string);
                return 0 - x;
            } catch (NumberFormatException e) {
                return 0;
            }
        }
    }
}
