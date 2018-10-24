package swordToOffer;

/**
 * 将一个字符串转换成一个整数(实现Integer.valueOf(string)的功能，但是string不符合数字要求时返回0)，
 * 要求不能使用字符串转换整数的库函数。 数值为0或者字符串不是一个合法的数值则返回0。
 * 输入描述:
 * 输入一个字符串,包括数字字母符号,可以为空
 * 输出描述:
 * 如果是合法的数值表达则返回该数字，否则返回0
 * 示例1
 * 输入
 * 复制
 * +2147483647
 * 1a33
 * 输出
 * 复制
 * 2147483647
 * 0
 */
public class StrToInt {
    public static void main(String[] args) {

    }

    private int[] map = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

    public int StrToInt(String str) {
        if (!isIllegal(str)) return 0;
        boolean ispos = true;
        int start = 0;
        char[] chars = str.toCharArray();
        if (chars[0] == '-') {
            ispos = false;
            start++;
        } else if (chars[0] == '+') start++;
        long res = 0;
        for (int i = start; i < chars.length; i++) {
            res += map[chars[i] - '0'];
            res *= 10;
        }
        res /= 10;
        if (ispos) return res > Integer.MAX_VALUE ? 0 : (int) res;
        else return -res < Integer.MIN_VALUE ? 0 : (int) -res;
    }

    /**
     * 主要是考虑各种边界问题
     *
     * @param str
     * @return
     */
    private boolean isIllegal(String str) {
        if (str == null || str.length() == 0 || str.length() > 11) return false;
        char c = str.charAt(0);
        if (c != '-' && c != '+' && (c < '0' || c > '9')) return false;
        for (int i = 1; i < str.length(); i++) {
            c = str.charAt(i);
            if (c < '0' || c > '9') return false;
        }
        return true;
    }
}
