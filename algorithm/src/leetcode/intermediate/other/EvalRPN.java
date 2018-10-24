package leetcode.intermediate.other;

import java.util.LinkedList;

/**
 * 根据逆波兰表示法，求表达式的值。
 * <p>
 * 有效的运算符包括 +, -, *, / 。每个运算对象可以是整数，也可以是另一个逆波兰表达式。
 * <p>
 * 说明：
 * <p>
 * 整数除法只保留整数部分。
 * 给定逆波兰表达式总是有效的。换句话说，表达式总会得出有效数值且不存在除数为 0 的情况。
 * 示例 1：
 * <p>
 * 输入: ["2", "1", "+", "3", "*"]
 * 输出: 9
 * 解释: ((2 + 1) * 3) = 9
 * 示例 2：
 * <p>
 * 输入: ["4", "13", "5", "/", "+"]
 * 输出: 6
 * 解释: (4 + (13 / 5)) = 6
 * 示例 3：
 * <p>
 * 输入: ["10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"]
 * 输出: 22
 * 解释:
 * ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
 * = ((10 * (6 / (12 * -11))) + 17) + 5
 * = ((10 * (6 / -132)) + 17) + 5
 * = ((10 * 0) + 17) + 5
 * = (0 + 17) + 5
 * = 17 + 5
 * = 22
 */
public class EvalRPN {
    public static void main(String[] args) {
        String[] tokens = {"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"};
        System.out.println(evalRPN3(tokens));
    }

    /**
     * 用递归代替stack
     *
     * @param tokens
     * @return
     */
    private static int curr = -1;

    public static int evalRPN3(String[] tokens) {
        if (curr == -1)
            curr = tokens.length - 1;
        String token = tokens[curr--];
        char c = token.charAt(0);
        if (token.length() == 1 && "+-*/".indexOf(c) != -1) {
            int b = evalRPN(tokens);
            int a = evalRPN(tokens);
            switch (c) {
                case '+':
                    return a + b;
                case '-':
                    return a - b;
                case '*':
                    return a * b;
                case '/':
                    return a / b;
                default:
                    break;
            }
        } else {
            return Integer.parseInt(token);
        }
        return curr;
    }


    /**
     * 用case简洁一点
     *
     * @param tokens
     * @return
     */
    public static int evalRPN2(String[] tokens) {
        if (tokens == null || tokens.length == 0) return 0;
        LinkedList<Integer> stack = new LinkedList<>();
        int a, b;
        for (int i = 0; i < tokens.length; i++) {
            switch (tokens[i]) {
                case "+":
                    stack.addLast(stack.pollLast() + stack.pollLast());
                    break;
                case "-":
                    stack.addLast(-(stack.pollLast() - stack.pollLast()));
                    break;
                case "*":
                    stack.addLast(stack.pollLast() * stack.pollLast());
                    break;
                case "/":
                    a = stack.pollLast();
                    b = stack.pollLast();
                    stack.addLast(b / a);
                    break;
                default:
                    stack.addLast(Integer.parseInt(tokens[i]));
                    break;
            }
        }
        return stack.pollLast();
    }

    /**
     * @param tokens
     * @return
     */
    public static int evalRPN(String[] tokens) {
        if (tokens == null || tokens.length == 0) return 0;
        LinkedList<Integer> stack = new LinkedList<>();
        int a, b;
        for (int i = 0; i < tokens.length; i++) {
            if (!tokens[i].equals("+") && !tokens[i].equals("-") && !tokens[i].equals("*") && !tokens[i].equals("/"))
                stack.addLast(Integer.parseInt(tokens[i]));
            else {
                if (tokens[i].equals("+")) {
                    a = stack.pollLast();
                    b = stack.pollLast();
                    stack.addLast(a + b);
                } else if (tokens[i].equals("-")) {
                    a = stack.pollLast();
                    b = stack.pollLast();
                    stack.addLast(b - a);
                } else if (tokens[i].equals("*")) {
                    a = stack.pollLast();
                    b = stack.pollLast();
                    stack.addLast(a * b);
                } else if (tokens[i].equals("/")) {
                    a = stack.pollLast();
                    b = stack.pollLast();
                    stack.addLast(b / a);
                }
            }
        }
        return stack.pollLast();
    }
}
