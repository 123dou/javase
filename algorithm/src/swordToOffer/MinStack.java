package swordToOffer;

import java.util.LinkedList;

/**
 * 定义栈的数据结构，请在该类型中实现一个能够得到栈中所含最小元素的min函数（时间复杂度应为O（1））。
 */
public class MinStack {
    private LinkedList<Integer> minstack = new LinkedList<>();
    private LinkedList<Integer> stack = new LinkedList<>();
    private int min = Integer.MAX_VALUE;

    public void push(int node) {
        stack.push(node);
        if (node < min) min = node;
        minstack.add(min);
    }

    public void pop() {
        int n = stack.pop();
        minstack.pop();
        if (!minstack.isEmpty()) min = minstack.peekFirst();
        else min = Integer.MAX_VALUE;
    }

    public int top() {
        return stack.peekFirst();
    }

    public int min() {
        return minstack.peekFirst();
    }
}
