package main.stack;

import java.util.Stack;

public class Medium {
    public static void main(String[] args) {
        System.out.println(minRemoveToMakeValid("())()((("));
//        System.out.println(validateStackSequences(new int[]{1, 2, 3, 4, 5}, new int[]{4, 5, 3, 2, 1}));
    }

    /**
     * 移除无效的括号：给你一个由 '('、')' 和小写字母组成的字符串 s。
     * 你需要从字符串中删除最少数目的 '(' 或者 ')'（可以删除任意位置的括号)，使得剩下的「括号字符串」有效。
     * 请返回任意一个合法字符串。
     * 有效「括号字符串」应当符合以下任意一条要求：
     * 空字符串或只包含小写字母的字符串
     * 可以被写作AB（A连接B）的字符串，其中A和B都是有效「括号字符串」
     * 可以被写作(A)的字符串，其中A是一个有效的「括号字符串」
     * 示例1：
     * 输入：s = "lee(t(c)o)de)"
     * 输出："lee(t(c)o)de"
     * 解释："lee(t(co)de)" , "lee(t(c)ode)" 也是一个可行答案。
     * 示例2：
     * 输入：s = "a)b(c)d"
     * 输出："ab(c)d"
     * 示例3：
     * 输入：s = "))(("
     * 输出：""
     * 解释：空字符串也是有效的
     */
    public static String minRemoveToMakeValid(String s) {
        if (s == null || s.isEmpty()) {
            return s;
        }
        Stack<Character> stack = new Stack<>();
        Stack<Integer> indexStack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push('(');
                indexStack.push(i);
            } else if (s.charAt(i) == ')') {
                if (stack.isEmpty()) {
                    stack.push(')');
                    indexStack.push(i);
                } else if (stack.peek() == '(') {
                    stack.pop();
                    indexStack.pop();
                } else {
                    stack.push(')');
                    indexStack.push(i);
                }
            }
        }
        StringBuilder sb = new StringBuilder(s);
        while (!indexStack.isEmpty()) {
            sb.deleteCharAt(indexStack.pop());
        }
        return sb.toString();
    }

    /**
     * 栈的压入、弹出序列：输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否为该栈的弹出顺序。假设压入栈的所有数字均不相等。
     * 例如，序列 {1,2,3,4,5} 是某栈的压栈序列，序列 {4,5,3,2,1} 是该压栈序列对应的一个弹出序列，但 {4,3,5,1,2} 就不可能是该压栈序列的
     * 弹出序列。
     */
    public static boolean validateStackSequences(int[] pushed, int[] popped) {
        if (pushed == null || pushed.length <= 0 || popped == null || popped.length <= 0) {
            throw new RuntimeException("参数错误");
        }
        if (pushed.length != popped.length) {
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
