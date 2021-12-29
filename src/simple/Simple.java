package simple;

import java.util.Stack;

/**
 * @author: cyli8
 * @date: 2021-12-19 16:07
 */
public class Simple {
    public static void main(String[] args) {
        ListNode third = new ListNode(3);
        third.setNext(null);
        ListNode second = new ListNode(2);
        second.setNext(third);
        ListNode head = new ListNode(1);
        head.setNext(second);
        int[] ints = reversePrint(head);
        for (int anInt : ints) {
            System.out.println(anInt);
        }

        System.out.println(fib(45));
        System.out.println(isValidSymbol("([}}])"));
        System.out.println(isValidSymbol("[(])"));
    }

    /**
     * 从尾到头打印链表
     */
    public static int[] reversePrint(ListNode head) {
        if (head == null) {
            return new int[]{};
        }
        Stack<Integer> stack = new Stack<>();
        ListNode cur = head;
        while (cur != null) {
            stack.push(cur.getValue());
            cur = cur.getNext();
        }
        int[] arr = new int[stack.size()];
        int size = stack.size();
        for (int i = 0; i < size; i++) {
            arr[i] = stack.pop();
        }
        return arr;
    }


    /**
     * 斐波那契数列
     *
     * @param n 第n项
     * @return
     */
    public static int fib(int n) {
        if (n <= 0) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        int one = 1;
        int two = 1;
        int curIndex = 3;
        int result = one + two;
        while (curIndex != n) {
            one = two;
            two = result;
            result = one + two;
            curIndex++;
        }
        return result;
    }

    /**
     * 旋转数组的最小数字
     *
     * @param numbers
     * @return
     */
    public int minArray(int[] numbers) {
        if (numbers.length == 0) {
            return -1;
        }
        if (numbers.length == 1) {
            return numbers[0];
        }
        int front = 0;
        int tail = numbers.length - 1;
        if (numbers[front] < numbers[tail]) {
            return numbers[0];
        } else {
            int mid = numbers.length / 2;
            while (front < tail - 1) {
                if (numbers[mid] > numbers[front]) {
                    front = mid;
                    mid = (front + tail) / 2;
                } else if (numbers[mid] < numbers[front]) {
                    tail = mid;
                    mid = (front + tail) / 2;
                } else {
                    // 只能按顺序查找了
                    int min = numbers[0];
                    for (int i = 1; i < numbers.length; i++) {
                        if (numbers[i] < min) {
                            return numbers[i];
                        }
                    }
                    break;
                }
            }
            return numbers[tail];
        }
    }


    /**
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
     * 有效字符串需满足：
     * 左括号必须用相同类型的右括号闭合。
     * 左括号必须以正确的顺序闭合。
     */
    public static boolean isValidSymbol(String s) {
        if (s.isEmpty() || (s.length() & 1) == 1) {
            return false;
        }
        Stack<Character> stack = new Stack<>();
        char[] chars = s.toCharArray();
        stack.push(chars[0]);
        for (int i = 1; i < chars.length; i++) {
            if (chars[i] == '(' || chars[i] == '[' || chars[i] == '{') {
                stack.push(chars[i]);
            } else {
                if (stack.isEmpty()) {
                    // 说明开头是右括号了
                    return false;
                }
                Character peek = stack.peek();
                if (peek == '(' && chars[i] == ')') {
                    stack.pop();
                } else if (peek == '[' && chars[i] == ']') {
                    stack.pop();
                } else if (peek == '{' && chars[i] == '}') {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}
