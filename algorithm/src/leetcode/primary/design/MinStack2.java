package leetcode.primary.design;

import java.util.LinkedList;

public class MinStack2 {
    private LinkedList<Integer> minStack;
    private LinkedList<Integer> stack;
    private int min = Integer.MAX_VALUE;

    /**
     * initialize your data structure here.
     */
    public MinStack2() {
        stack = new LinkedList<>();
        minStack = new LinkedList<>();
        min = Integer.MAX_VALUE;
    }

    public void push(int x) {
        stack.push(x);
        if (x < min) {
            min = x;
        }
        minStack.push(min);
    }

    public void pop() {
        stack.pop();
        minStack.pop();
        if (!minStack.isEmpty()) min = minStack.peekFirst();
        else min = Integer.MAX_VALUE;
    }

    public int top() {
        return stack.peekFirst();
    }

    public int getMin() {
        return minStack.peekFirst();
    }
}
