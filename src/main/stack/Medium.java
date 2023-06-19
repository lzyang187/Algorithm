package main.stack;

import java.util.Stack;

public class Medium {
    public static void main(String[] args) {
        System.out.println(validateStackSequences(new int[]{1, 2, 3, 4, 5}, new int[]{4, 5, 3, 2, 1}));
    }

    /**
     * 栈的压入、弹出序列：输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否为该栈的弹出顺序。假设压入栈的所有数字均不相等。
     * 例如，序列 {1,2,3,4,5} 是某栈的压栈序列，序列 {4,5,3,2,1} 是该压栈序列对应的一个弹出序列，但 {4,3,5,1,2} 就不可能是该压栈序列的
     * 弹出序列。
     */
    public static boolean validateStackSequences(int[] pushed, int[] popped) {
        if (pushed == null || popped == null) {
            return false;
        }
        Stack<Integer> stack = new Stack<>();
        int pushIndex = 0;
        int popIndex = 0;
        while (pushIndex <= pushed.length) {
            if (popIndex >= popped.length) {
                return true;
            }
            if (!stack.isEmpty() && stack.peek() == popped[popIndex]) {
                stack.pop();
                popIndex++;
            } else {
                if (pushIndex >= pushed.length) {
                    return false;
                }
                stack.push(pushed[pushIndex]);
                pushIndex++;
            }
        }
        return false;
    }

}
