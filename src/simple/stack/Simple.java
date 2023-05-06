package simple.stack;

import java.util.Stack;

public class Simple {
    public static void main(String[] args) {
        System.out.println(minRemoveToMakeValid("())()((("));
    }

    /**
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
     * 有效字符串需满足：
     * 1、左括号必须用相同类型的右括号闭合。
     * 2、左括号必须以正确的顺序闭合。
     * 3、每个右括号都有一个对应的相同类型的左括号。
     */
    public static boolean isValidSymbol(String s) {
        if (s.isEmpty() || (s.length() & 1) == 1) {
            return false;
        }
        Stack<Character> stack = new Stack<>();
        stack.push(s.charAt(0));
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == '(' || s.charAt(i) == '[' || s.charAt(i) == '{') {
                stack.push(s.charAt(i));
            } else {
                if (stack.isEmpty()) {
                    // 说明开头是右括号了
                    return false;
                }
                Character peek = stack.peek();
                if (peek == '(' && s.charAt(i) == ')') {
                    stack.pop();
                } else if (peek == '[' && s.charAt(i) == ']') {
                    stack.pop();
                } else if (peek == '{' && s.charAt(i) == '}') {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    /**
     * 移除无效的括号：给你一个由 '('、')' 和小写字母组成的字符串 s。
     * 你需要从字符串中删除最少数目的 '(' 或者 ')'（可以删除任意位置的括号)，使得剩下的「括号字符串」有效。
     * 请返回任意一个合法字符串。
     * 有效「括号字符串」应当符合以下任意一条要求：
     * 空字符串或只包含小写字母的字符串
     * 可以被写作AB（A连接B）的字符串，其中A和B都是有效「括号字符串」
     * 可以被写作(A)的字符串，其中A是一个有效的「括号字符串」
     */
    public static String minRemoveToMakeValid(String s) {
        if (s == null) {
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
}
