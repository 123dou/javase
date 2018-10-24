package leetcode.intermediate.Backtracking;

import java.util.*;

/**
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
 * <p>
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 * <p>
 * <p>
 * <p>
 * 示例:
 * <p>
 * 输入："23"
 * 输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 * 说明:
 * 尽管上面的答案是按字典序排列的，但是你可以任意选择答案输出的顺序。
 */
public class LetterCombinations {
    public static void main(String[] args) {
        String digits = "23";
        List<String> list = letterCombinations(digits);
        System.out.println(list);
    }

    static Map<Character, String[]> map = new HashMap<>();

    static {
        map.put('2', new String[]{"a", "b", "c"});
        map.put('3', new String[]{"d", "e", "f"});
        map.put('4', new String[]{"g", "h", "i"});
        map.put('5', new String[]{"j", "k", "l"});
        map.put('6', new String[]{"m", "n", "o"});
        map.put('7', new String[]{"p", "q", "r", "s"});
        map.put('8', new String[]{"t", "u", "v"});
        map.put('9', new String[]{"w", "x", "y", "z"});
    }

    static List<String> list = new LinkedList<>();
    static StringBuilder sb = new StringBuilder();

    /**
     * @param digits
     * @return
     */
    public static List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) return Collections.emptyList();
        return letterCombinations(digits, sb, 0);
    }

    /**
     * @param digits
     * @param sb
     * @param index
     * @return
     */
    public static List<String> letterCombinations(String digits, StringBuilder sb, int index) {
        if (index == digits.length() - 1) {
            String[] strings = map.get(digits.charAt(index));
            for (int i = 0; i < strings.length; i++) {
                sb.append(strings[i]);
                list.add(sb.toString());
                sb.deleteCharAt(sb.length() - 1); //回溯
            }
            if (sb.length() > 0)
                sb.deleteCharAt(sb.length() - 1); //回溯
            return list;
        }
        String[] str1 = map.get(digits.charAt(index));
        for (int j = 0; j < str1.length; j++) {
            sb.append(str1[j]);
            letterCombinations(digits, sb, index + 1);
        }
        if (sb.length() > 0)
            sb.deleteCharAt(sb.length() - 1); //回溯
        return list;
    }
}
