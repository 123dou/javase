package leetcode.primary.array;

import java.util.Arrays;

/**
 * 给定一个非负整数组成的非空数组，在该数的基础上加一，返回一个新的数组。
 * <p>
 * 最高位数字存放在数组的首位， 数组中每个元素只存储一个数字。
 * <p>
 * 你可以假设除了整数 0 之外，这个整数不会以零开头。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,2,3]
 * 输出: [1,2,4]
 * 解释: 输入数组表示数字 123。
 * 示例 2:
 * <p>
 * 输入: [4,3,2,1]
 * 输出: [4,3,2,2]
 * 解释: 输入数组表示数字 4321。
 */
public class PlusOne {
    public static void main(String[] args) {
        int[] digits = {9};
        int[] ints = plusOne2(digits);
        System.out.println(Arrays.toString(ints));
    }

    public int[] plusOne4(int[] digits) {
        if (digits == null || digits.length == 0) return digits;
        int sign = 1;
        for (int i = digits.length - 1; i >= 0; i--) {
            int addOne = digits[i] + sign;
            if (addOne > 9) {
                digits[i] = 0;
            } else {
                digits[i] = addOne;
                sign = 0;
                break;
            }
        }
        if (sign == 0) return digits;
        int[] res = new int[digits.length + 1];
        res[0] = 1;
        return res;

    }


    public static int[] plusOne2(int[] digits) {
        boolean flag = false;
        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] + 1 > 9) {
                digits[i] = 0;
                if (i == 0)
                    flag = true;
            } else {
                digits[i] = digits[i] + 1;
                break;
            }
        }
        if (flag) {
            int[] result = new int[digits.length + 1];
            result[0] = 1;
            System.arraycopy(digits, 0, result, 1, digits.length);
            return result;
        }

        return digits;
    }


    /**
     * 解析的范围是int类型的范围只能是int类型
     *
     * @param digits
     * @return
     */
    public static int[] plusOne(int[] digits) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < digits.length; i++) {
            sb.append(digits[i]);
        }
        int value = Integer.parseInt(sb.toString()) + 1; //parseInt()方法可以解析的范围是int类型的范围
        String s = "" + value;
        int[] result = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            result[i] = Integer.valueOf(s.substring(i, i + 1));
        }
        return result;
    }
}
