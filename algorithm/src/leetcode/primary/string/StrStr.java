package leetcode.primary.string;

/**
 * 实现 strStr() 函数。
 * <p>
 * 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从 0 开始)。如果不存在，则返回  -1。
 * <p>
 * 示例 1:
 * <p>
 * 输入: haystack = "hello", needle = "ll"
 * 输出: 2
 * 示例 2:
 * <p>
 * 输入: haystack = "aaaaa", needle = "bba"
 * 输出: -1
 * 说明:
 * <p>
 * 当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
 * <p>
 * 对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与 C 语言的 strstr() 以及 Java 的 indexOf() 定义相符。
 */
public class StrStr {
    public static void main(String[] args) {
        String haystack = "aaaaa";
        String needle = "bba";
        int i = strStr(haystack, needle);
        System.out.println(i);
    }

    public static int strStr(String haystack, String needle) {
        if ("".equals(needle)) {
            return 0;
        }
        char[] haystack_chars = haystack.toCharArray();
        char[] needle_chars = needle.toCharArray();
        if (haystack_chars.length < needle_chars.length)
            return -1;
        int index = -1;
        //直接暴力搜索
        for (int i = 0; i <= haystack_chars.length - needle_chars.length; i++) {
            boolean flag = true;
            for (int j = 0; j < needle_chars.length; j++) {
                if (haystack_chars[j + i] != needle_chars[j]) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                index = i;
                break;
            }
        }
        return index;
    }
}
