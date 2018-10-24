package leetcode.primary.string;

/**
 * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
 * <p>
 * 说明：本题中，我们将空字符串定义为有效的回文串。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "A man, a plan, a canal: Panama"
 * 输出: true
 * 示例 2:
 * <p>
 * 输入: "race a car"
 * 输出: false
 */
public class IsPalindrome {
    public static void main(String[] args) {
        String s = " ";
        //boolean b = isPalindrome(s);
        //System.out.println(b);
        System.out.println('0' == 'P' - 32);
    }

    /**
     * @param s
     * @return
     */
    public static boolean isPalindrome2(String s) {
        if (s == null) return false;
        char[] chars = s.toCharArray();
        int len = 0;
        for (int i = 0; i < chars.length; i++) {
            char c = Character.toLowerCase(chars[i]);
            int num = c - '0';
            int letter = c - 'a';
            if ((num >= 0 && num <= 9) || (letter >= 0 && letter < 26)) chars[len++] = c;
        }
        int left = 0;
        int right = len - 1;
        while (left <= right) {
            if (chars[left++] != chars[right--]) return false;
        }
        return true;
    }

    /**
     * 用左右两个指针分别逐一对比数字或字母字符
     *
     * @param s
     * @return
     */
    public static boolean isPalindrome(String s) {
        if (s == null)
            return false;
        if ("".equals(s))
            return true;
        s = s.toLowerCase();
        char[] chars = s.toCharArray();
        int left = 0;
        int rigth = s.length() - 1;
        while (left <= rigth) {
            while (left <= rigth && !isNumOrLetter(chars[left]))
                left++;
            while (left <= rigth && !isNumOrLetter(chars[rigth]))
                rigth--;
            if (left <= rigth && chars[left] != chars[rigth]) {
                return false;
            }
            left++;
            rigth--;
        }
        return true;
    }

    public static boolean isNumOrLetter(char c) {
        if ((c >= '0' && c <= '9') || (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z'))
            return true;
        return false;
    }
}
