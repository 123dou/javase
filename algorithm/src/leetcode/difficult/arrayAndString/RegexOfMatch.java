package leetcode.difficult.arrayAndString;

/**
 * 给定一个字符串 (s) 和一个字符模式 (p)。实现支持 '.' 和 '*' 的正则表达式匹配。
 * <p>
 * '.' 匹配任意单个字符。
 * '*' 匹配零个或多个前面的元素。
 * 匹配应该覆盖整个字符串 (s) ，而不是部分字符串。
 * <p>
 * 说明:
 * <p>
 * s 可能为空，且只包含从 a-z 的小写字母。
 * p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。
 * 示例 1:
 * <p>
 * 输入:
 * s = "aa"
 * p = "a"
 * 输出: false
 * 解释: "a" 无法匹配 "aa" 整个字符串。
 * 示例 2:
 * <p>
 * 输入:
 * s = "aa"
 * p = "a*"
 * 输出: true
 * 解释: '*' 代表可匹配零个或多个前面的元素, 即可以匹配 'a' 。因此, 重复 'a' 一次, 字符串可变为 "aa"。
 * 示例 3:
 * <p>
 * 输入:
 * s = "ab"
 * p = ".*"
 * 输出: true
 * 解释: ".*" 表示可匹配零个或多个('*')任意字符('.')。
 * 示例 4:
 * <p>
 * 输入:
 * s = "aab"
 * p = "c*a*b"
 * 输出: true
 * 解释: 'c' 可以不被重复, 'a' 可以被重复一次。因此可以匹配字符串 "aab"。
 * 示例 5:
 * <p>
 * 输入:
 * s = "mississippi"
 * p = "mis*is*p*."
 * 输出: false
 */
public class RegexOfMatch {
    public static void main(String[] args) {

    }

    public boolean isMatch2(String s, String p) {
        boolean[] match = new boolean[s.length() + 1];
        match[s.length()] = true;
        for (int i = p.length() - 1; i >= 0; i--) {
            if (p.charAt(i) == '*') {
                //如果是星号，从后往前匹配
                for (int j = s.length() - 1; j >= 0; j--) {
                    match[j] = match[j] ||
                            (match[j + 1] &&
                                    (p.charAt(i - 1) == '.' || (p.charAt(i - 1) == s.charAt(j))));
                }
                //记得把i多减1，因为星号是和其前面的字符匹配使用
                i--;

            } else {
                //如果不是星号，从前往后匹配
                for (int j = 0; j < s.length(); j++) {
                    match[j] = match[j + 1]
                            &&
                            (p.charAt(i) == '.' || (p.charAt(i) == s.charAt(j)));
                }
                //只要试过了pattern中最后一个字符，就要把match【s。length（）】置为false
                match[s.length()] = false;
            }
        }
        return match[0];
    }


    /**
     * 递归容易理解,但是时间复杂度高
     *
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch(String s, String p) {
        if (s == null || p == null) return false;
        return match(s.toCharArray(), p.toCharArray(), 0, 0);
    }

    /**
     * 递归调用简洁点,但是时间复杂度高
     *
     * @param str
     * @param pattern
     * @param i
     * @param j
     * @return
     */
    private boolean match(char[] str, char[] pattern, int i, int j) {
        if (i == str.length && j == pattern.length) return true;
        if (j == pattern.length) return false;
        if (j + 1 < pattern.length) {
            if (pattern[j + 1] == '*') {
                if (i < str.length && (pattern[j] == '.' || str[i] == pattern[j])) {
                    return match(str, pattern, i + 1, j + 2)
                            || match(str, pattern, i + 1, j)
                            || match(str, pattern, i, j + 2);
                }
                return match(str, pattern, i, j + 2);
            }
        }
        if (i < str.length && str[i] == pattern[j] || pattern[j] == '.')
            return match(str, pattern, i + 1, j + 1);
        return false;
    }

}
