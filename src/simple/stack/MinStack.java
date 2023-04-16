package simple.stack;

import java.util.Stack;

/**
 * 定义栈的数据结构，请在该类型中实现一个能够得到栈的最小元素的 min 函数在该栈中，
 * 调用 min、push 及 pop 的时间复杂度都是 O(1)。
 *
 * @author: cyli8
 * @date: 2022-01-23 19:15
 */
public class MinStack {

    /**
     * 数据栈
     */
    private Stack<Integer> mDataStack = new Stack<>();
    /**
     * 辅助栈
     */
    private Stack<Integer> mMinStack = new Stack<>();

    public MinStack() {

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

    public int top() {
        return mDataStack.peek();
    }

    public int min() {
        return mMinStack.peek();
    }
}
