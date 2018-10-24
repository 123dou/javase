package leetcode.primary.string;

/**
 * 实现 atoi，将字符串转为整数。
 * <p>
 * 在找到第一个非空字符之前，需要移除掉字符串中的空格字符。如果第一个非空字符是正号或负号，选取该符号，并将其与后面尽可能多的连续的数字组合起来，这部分字符即为整数的值。如果第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成整数。
 * <p>
 * 字符串可以在形成整数的字符后面包括多余的字符，这些字符可以被忽略，它们对于函数没有影响。
 * <p>
 * 当字符串中的第一个非空字符序列不是个有效的整数；或字符串为空；或字符串仅包含空白字符时，则不进行转换。
 * <p>
 * 若函数不能执行有效的转换，返回 0。
 * <p>
 * 说明：
 * <p>
 * 假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−231,  231 − 1]。如果数值超过可表示的范围，则返回  INT_MAX (231 − 1) 或 INT_MIN (−231) 。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "42"
 * 输出: 42
 * 示例 2:
 * <p>
 * 输入: "   -42"
 * 输出: -42
 * 解释: 第一个非空白字符为 '-', 它是一个负号。
 * 我们尽可能将负号与后面所有连续出现的数字组合起来，最后得到 -42 。
 * 示例 3:
 * <p>
 * 输入: "4193 with words"
 * 输出: 4193
 * 解释: 转换截止于数字 '3' ，因为它的下一个字符不为数字。
 * 示例 4:
 * <p>
 * 输入: "words and 987"
 * 输出: 0
 * 解释: 第一个非空字符是 'w', 但它不是数字或正、负号。
 * 因此无法执行有效的转换。
 * 示例 5:
 * <p>
 * 输入: "-91283472332"
 * 输出: -2147483648
 * 解释: 数字 "-91283472332" 超过 32 位有符号整数范围。
 * 因此返回 INT_MIN (−231) 。
 */
public class MyAtoi {
    public static void main(String[] args) {
        String str = "  +0 42";
        int i = myAtoi(str);
        System.out.println(i);
    }

    public int myAtoi2(String str) {
        if (str == null) return 0;
        str = str.trim();
        if (str.length() == 0) return 0;
        char first = str.charAt(0);
        if ('-' != first && first != '+' && (first - '0' < 0 || first - 0 > '9')) return 0;
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (i == 0) {
                sb.append(c);
            } else {
                if (isValid(c)) sb.append(c);
                else break;
            }
        }
        try {
            return Integer.parseInt(sb.toString());
        } catch (NumberFormatException e) {
            if (sb.length() == 1 && (sb.charAt(0) == '+' || '-' == sb.charAt(0))) return 0;
            else if (sb.charAt(0) == '-') return Integer.MIN_VALUE;
            return Integer.MAX_VALUE;
        }
    }

    private boolean isValid(char c) {
        if (c - '0' >= 0 && c - '0' <= 9) return true;
        return false;
    }


    /**
     * 就是拼接字符串
     *
     * @param str
     * @return
     */
    public static int myAtoi(String str) {
        if (str == null || "".equals(str.trim()))
            return 0;
        str = str.trim();
        StringBuilder sb = new StringBuilder();
        char[] chars = str.toCharArray();
        if (chars[0] != '-' && chars[0] != '+' && (chars[0] < '0' || chars[0] > '9')) {
            return 0;
        } else {
            sb.append(chars[0]);
            for (int i = 1; i < chars.length; i++) {
                if (chars[i] >= '0' && chars[i] <= '9') {
                    sb.append(chars[i]);
                } else {
                    break;
                }
            }
        }
        if (sb.length() == 1 && (chars[0] == '-' || chars[0] == '+'))
            return 0;
        else {
            String s = sb.toString();
            if (s.charAt(0) == '-') {
                try {
                    return Integer.parseInt(s);
                } catch (NumberFormatException e) {
                    return Integer.MIN_VALUE;
                }
            } else {
                try {
                    return Integer.parseInt(s);
                } catch (NumberFormatException e) {
                    return Integer.MAX_VALUE;
                }
            }
        }
    }
}
