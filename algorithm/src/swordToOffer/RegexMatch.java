package swordToOffer;

/**
 * 请实现一个函数用来匹配包括'.'和'*'的正则表达式。
 * 模式中的字符'.'表示任意一个字符，而'*'表示它前面的字符可以出现任意次（包含0次）。
 * 在本题中，匹配是指字符串的所有字符匹配整个模式。
 * 例如，字符串"aaa"与模式"a.a"和"ab*ac*a"匹配，但是与"aa.a"和"ab*a"均不匹配
 */
public class RegexMatch {
    public static void main(String[] args) {
        String str = "bbbba";
        String pattern = ".*a*a";
        RegexMatch t = new RegexMatch();
        boolean match = t.match(str.toCharArray(), pattern.toCharArray());
        System.out.println(match);
    }

    public boolean match(char[] str, char[] pattern) {
        if (str == null || pattern == null) return false;

        return match(str, pattern, 0, 0);
    }

    /**
     * 递归调用简洁点
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
                //if (i == str.length) return match(str, pattern,  i, j + 2);
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
