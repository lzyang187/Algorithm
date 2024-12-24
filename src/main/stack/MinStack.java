package main.stack;

import java.util.Stack;

/**
 * 定义栈的数据结构，请在该类中实现一个能够得到栈的最小元素的 min 函数。要求在该栈中，调用 min、push 及 pop 的时间复杂度都是 O(1)。
 *
 * @author: cyli8
 * @date: 2022-01-23 19:15
 */
public class MinStack {

    /**
     * 数据栈
     */
    private final Stack<Integer> mDataStack;
    /**
     * 辅助栈
     */
    private final Stack<Integer> mMinStack;

    public MinStack() {
        mDataStack = new Stack<>();
        mMinStack = new Stack<>();
    }

    public void push(int x) {
        mDataStack.push(x);
        if (mMinStack.empty()) {
            mMinStack.push(x);
        } else {
            int min = Math.min(mMinStack.peek(), x);
            mMinStack.push(min);
        }
    }

    public void pop() {
        mDataStack.pop();
        mMinStack.pop();
    }

    public int peek() {
        return mDataStack.peek();
    }

    public int min() {
        return mMinStack.peek();
    }
}
