package leetcode.primary.other;

import java.util.LinkedList;

/**
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 * <p>
 * 有效字符串需满足：
 * <p>
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 注意空字符串可被认为是有效字符串。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "()"
 * 输出: true
 * 示例 2:
 * <p>
 * 输入: "()[]{}"
 * 输出: true
 * 示例 3:
 * <p>
 * 输入: "(]"
 * 输出: false
 * 示例 4:
 * <p>
 * 输入: "([)]"
 * 输出: false
 * 示例 5:
 * <p>
 * 输入: "{[]}"
 * 输出: true
 */
public class BracketsIsValid {
    public static void main(String[] args) {
        String s = "()";
        boolean valid = isValid(s);
        System.out.println(valid);
    }

    public static boolean isValid(String s) {
        if ((s.length() & 1) != 0) return false;
        LinkedList<Character> list = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '[' || c == '{') list.addLast(c);
            else {
                if (list.size() == 0) return false;
                char c1 = list.pollLast();
                if (c1 == '(' && c != ')') return false;
                if (c1 == '[' && c != ']') return false;
                if (c1 == '{' && c != '}') return false;
            }
        }
        if (list.size() != 0) return false;
        return true;
    }
}
