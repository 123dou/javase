package leetcode.primary.design;

import java.util.LinkedList;

/**
 * 设计一个支持 push，pop，top 操作，并能在常数时间内检索到最小元素的栈。
 * <p>
 * push(x) -- 将元素 x 推入栈中。
 * pop() -- 删除栈顶的元素。
 * top() -- 获取栈顶元素。
 * getMin() -- 检索栈中的最小元素。
 * 示例:
 * <p>
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin();   --> 返回 -3.
 * minStack.pop();
 * minStack.top();      --> 返回 0.
 * minStack.getMin();   --> 返回 -2.
 */
public class MinStack {
    /**
     * initialize your data structure here.
     */
    int min;
    LinkedList<Integer> stack;

    public MinStack() {
        stack = new LinkedList<>();
        min = Integer.MAX_VALUE;
    }

    public void push(int x) {
        if (x < min) min = x;
        stack.push(x);
    }

    public void pop() {
        if (stack.pop() == min) {
            min = Integer.MAX_VALUE;
            for (int i = 0; i < stack.size(); i++) {
                if (stack.get(i) < min) min = stack.get(i);
            }
        }
    }

    public int top() {
        return stack.getFirst();
    }

    public int getMin() {
        return min;
    }
}
