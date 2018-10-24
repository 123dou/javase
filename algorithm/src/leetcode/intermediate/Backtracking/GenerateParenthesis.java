package leetcode.intermediate.Backtracking;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 给出 n 代表生成括号的对数，请你写出一个函数，使其能够生成所有可能的并且 有效的括号组合。
 * <p>
 * 例如，给出 n = 3，生成结果为：
 * <p>
 * [
 * "((()))",
 * "(()())",
 * "(())()",
 * "()(())",
 * "()()()"
 * ]
 */
public class GenerateParenthesis {
    public static void main(String[] args) {
        int m = 4;
        List<String> list = generateParenthesis(m);
        System.out.println(list);
    }

    public static List<String> generateParenthesis(int n) {
        if (n <= 0) return Collections.emptyList();
        List<String> list = new LinkedList<>();
        generateParenthesis(n, n, "", list);
        return list;
    }

    /**
     * 根据条件得:
     * 1.只能选"(" 或")"
     * 2.只有当")"的数量少于"("时才能选"("
     * 3.当")","("都选完时,找到一个满足条件的组合
     *
     * @param left
     * @param right
     * @param str
     * @param list
     */
    public static void generateParenthesis(int left, int right, String str, List<String> list) {
        if (left == 0 && right == 0) {
            list.add(str);
            return;
        }
        if (left > 0) {
            generateParenthesis(left - 1, right, str + "(", list);
        }
        if (right > left) {
            generateParenthesis(left, right - 1, str + ")", list);
        }
    }
}
