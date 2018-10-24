package leetcode.primary.string;

/**
 * 请编写一个函数，其功能是将输入的字符串反转过来。
 * <p>
 * 示例：
 * <p>
 * 输入：s = "hello"
 * 返回："olleh"
 */
public class ReverseString {
    public static void main(String[] args) {
        String s = "1234";
        String s1 = reverseString2(s);
        System.out.println(s1);
    }

    /**
     * 自己写的函数
     *
     * @param s
     * @return
     */
    public static String reverseString2(String s) {
        char[] chars = s.toCharArray();
        int loop_time = chars.length / 2;
        for (int i = 0; i < loop_time; i++) {
            swap(chars, i, chars.length - 1 - i);
        }
        return new String(chars);
    }

    public static void swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }


    /**
     * 直接用StringBuilder中的方法
     *
     * @param s
     * @return
     */
    public static String reverseString(String s) {
        StringBuilder sb = new StringBuilder(s);
        return sb.reverse().toString();
    }
}
