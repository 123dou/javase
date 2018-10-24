package leetcode;

/**
 * 给定一个字符串 s，你可以通过在字符串前面添加字符将其转换为回文串。找到并返回可以用这种方式转换的最短回文串。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "aacecaaa"
 * 输出: "aaacecaaa"
 * 示例 2:
 * <p>
 * 输入: "abcd"
 * 输出: "dcbabcd"
 */
public class ShortestPalindrome {
    public static void main(String[] args) {
        String s = "aacecaaa";
        String s1 = shortestPalindrome2(s);
        System.out.println(s1);
    }


    public static String shortestPalindrome2(String s) {
        if (s == null)
            return s;
        char[] chars = s.toCharArray();
        int longest = 1; //最长的回文子串的长度
        int start = 0; //最长的回文子串的起始索引
        for (int i = 0; i <= chars.length - 2; i++) { //以长度为2为基串找出的
            int left = i - 1;
            int right = i + 2;
            boolean flag = true;
            int length;
            if (chars[i] == chars[i + 1]) {
                length = 2;
                while (left >= 0 || right < chars.length) {
                    if (left >= 0 && right < chars.length && chars[left] == chars[right]) {
                        left--;
                        right++;
                        length += 2;
                        flag = false;
                    } else if (flag && left >= 0 && chars[left] == chars[i]) {
                        left--;
                        length++;
                    } else if (flag && right < chars.length && chars[right] == chars[i]) {
                        right++;
                        length++;
                    } else {
                        break;
                    }
                }
                if (length > longest) {
                    start = ++left;
                    if (start == 0) {
                        longest = length;
                    }
                }
            }
        }
        for (int j = 0; j <= chars.length - 3; j++) {
            int left = j - 1;
            int right = j + 3;
            int length;
            if (chars[j] == chars[j + 2]) {
                length = 3;
                while (left >= 0 && right < chars.length && chars[left] == chars[right]) {
                    left--;
                    right++;
                    length += 2;
                }
                if (length > longest) {
                    start = ++left;
                    if (start == 0)
                        longest = length;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        if (longest == 1) {
            for (int i = chars.length - 1; i > 0; i--) {
                sb.append(chars[i]);
            }
            return sb.append(s).toString();
        } else {
            for (int i = chars.length - 1; i > longest - 1; i--) {
                sb.append(chars[i]);
            }
            return sb.append(s).toString();
        }
    }


    /**
     * 暴力法超时:就是找出以第一个字符开始的最长回文字符串
     *
     * @param s
     * @return
     */
    public static String shortestPalindrome(String s) {
        StringBuilder sb = new StringBuilder();
        char[] chars = s.toCharArray();
        int longest = 1;
        for (int i = 0; i < chars.length; i++) {
            sb.append(chars[i]);
            if (isPalindrome(sb.toString())) {
                if (sb.toString().length() > longest)
                    longest = sb.toString().length();
            }
        }
        sb.delete(0, sb.length());
        if (longest == 1) {
            for (int i = chars.length - 1; i > 0; i--) {
                sb.append(chars[i]);
            }
            return sb.append(s).toString();
        } else {
            for (int i = chars.length - 1; i > longest - 1; i--) {
                sb.append(chars[i]);
            }
            return sb.append(s).toString();
        }

    }

    /**
     * 判断是不是回文字符串
     *
     * @param s
     * @return
     */
    public static boolean isPalindrome(String s) {
        if (s == null)
            return false;
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length / 2; i++) {
            if (chars[i] != chars[chars.length - 1 - i])
                return false;
        }
        return true;
    }
}
