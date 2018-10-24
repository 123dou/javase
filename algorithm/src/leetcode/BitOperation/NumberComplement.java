package leetcode.BitOperation;

/**
 * 476. 数字的补数Copy for Markdown
 * 题目描述提示帮助提交记录社区讨论阅读解答
 * 给定一个正整数，输出它的补数。补数是对该数的二进制表示取反。
 * <p>
 * 注意:
 * <p>
 * 给定的整数保证在32位带符号整数的范围内。
 * 你可以假定二进制数不包含前导零位。
 * 示例 1:
 * <p>
 * 输入: 5
 * 输出: 2
 * 解释: 5的二进制表示为101（没有前导零位），其补数为010。所以你需要输出2。
 * 示例 2:
 * <p>
 * 输入: 1
 * 输出: 0
 * 解释: 1的二进制表示为1（没有前导零位），其补数为0。所以你需要输出0。
 */
public class NumberComplement {
    public static void main(String[] args) {
        int n = 654561;
        System.out.println(findComplement(n));
        System.out.println(findComplement2(n));
    }

    public static int findComplement(int num) {
        int sign = 0;
        int temp = num;
        while (temp != 0) {
            sign = (sign << 1) + 1;
            temp >>>= 1;
        }
        return num ^ sign;
    }

    public static int findComplement2(int num) {
        return ~num & (Integer.highestOneBit(num) - 1);
    }
}
