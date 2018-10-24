package swordToOffer;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否可能为该栈的弹出顺序。
 * 假设压入栈的所有数字均不相等。
 * 例如序列1,2,3,4,5是某栈的压入顺序，序列4,5,3,2,1是该压栈序列对应的一个弹出序列，
 * 但4,3,5,1,2就不可能是该压栈序列的弹出序列。（注意：这两个序列的长度是相等的）
 */
public class IsPopOrder {

    public static void main(String[] args) {
        int[] pusnA = {5, 4, 2, 1, 3, 7, 9};
        int[] popA = {2, 4, 5, 3, 1, 7, 9};
        IsPopOrder isPopOrder = new IsPopOrder();
        System.out.println(isPopOrder.IsPopOrder(pusnA, popA));
    }

    public boolean IsPopOrder(int[] pushA, int[] popA) {
        int pre = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        for (int n : popA) {
            if (!stack.isEmpty()) {
                if (stack.peek() == n) {
                    stack.pop();
                    continue;
                }
            }
            boolean find = false;
            for (int i = pre; i < pushA.length; i++) {
                if (pushA[i] != n) stack.push(pushA[i]);
                else {
                    pre = i + 1;
                    find = true;
                    break;
                }
            }
            if (!find) return false;
        }
        return true;
    }
}
