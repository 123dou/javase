package leetcode.intermediate.math;

/**
 * 给定一个 Excel 表格中的列名称，返回其相应的列序号。
 * <p>
 * 例如，
 * <p>
 * A -> 1
 * B -> 2
 * C -> 3
 * ...
 * Z -> 26
 * AA -> 27
 * AB -> 28
 * ...
 * 示例 1:
 * <p>
 * 输入: "A"
 * 输出: 1
 * 示例 2:
 * <p>
 * 输入: "AB"
 * 输出: 28
 * 示例 3:
 * <p>
 * 输入: "ZY"
 * 输出: 701
 */
public class TitleToNumber {
    public static void main(String[] args) {
        String s = "ZYA";
        int i = titleToNumber(s);
        System.out.println(i);
    }

    /**
     * 就是一个字母表,然后进制转换
     *
     * @param s
     * @return
     */
    public static int titleToNumber(String s) {
        char[] chars = s.toCharArray();
        int sum = 0;
        int count = 1;
        for (int i = chars.length - 1; i >= 0; i--) {
            sum += (chars[i] - 'A' + 1) * count;
            count *= 26;
        }
        return sum;
    }
}
