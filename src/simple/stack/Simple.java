package simple.stack;

import java.util.Stack;

public class Simple {
    public static void main(String[] args) {

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
}
