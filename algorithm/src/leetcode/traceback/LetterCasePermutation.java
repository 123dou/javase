package leetcode.traceback;

import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个字符串S，通过将字符串S中的每个字母转变大小写，我们可以获得一个新的字符串。返回所有可能得到的字符串集合。
 * <p>
 * 示例:
 * 输入: S = "a1b2"
 * 输出: ["a1b2", "a1B2", "A1b2", "A1B2"]
 * <p>
 * 输入: S = "3z4"
 * 输出: ["3z4", "3Z4"]
 * <p>
 * 输入: S = "12345"
 * 输出: ["12345"]
 * 注意：
 * <p>
 * S 的长度不超过12。
 * S 仅由数字和字母组成。
 */
public class LetterCasePermutation {
    public static void main(String[] args) {

    }

    /**
     * 其实就是子集问题,只不过需要对每个字母进行大小写转换
     *
     * @param S
     * @return
     */
    public List<String> letterCasePermutation(String S) {
        List<String> list = new LinkedList<>();
        if (S == null) return list;
        int count = 0;
        for (int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);
            if (c > '9') count++;
        }
        list.add(S);
        for (int i = 1; i <= count; i++) {
            letterCasePermutation(S.toCharArray(), 0, list, i, 0);
        }
        return list;
    }

    public void letterCasePermutation(char[] chars, int index,
                                      List<String> list, int changsNum, int count) {
        if (count == changsNum) {
            list.add(new String(chars));
            return;
        }
        for (int i = index; i < chars.length; i++) {
            char c = chars[i];
            if (c > '9') {
                if (c >= 'a' && c <= 'z') chars[i] = Character.toUpperCase(c);
                else chars[i] = Character.toLowerCase(c);
                letterCasePermutation(chars, i + 1, list, changsNum, count + 1);
                chars[i] = c;
            }
        }
    }
}
