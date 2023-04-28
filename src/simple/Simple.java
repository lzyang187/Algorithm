package simple;

import simple.linklist.ListNode;
import simple.tree.TreeNode;

import java.util.Stack;

/**
 * @author: cyli8
 * @date: 2021-12-19 16:07
 */
public class Simple {
    public static void main(String[] args) {
        ListNode third = new ListNode(3);
        third.next = null;
        ListNode second = new ListNode(2);
        second.next = third;
        ListNode head = new ListNode(1);
        head.next = second;

//        System.out.println(head);
//        ListNode listNode = deleteNode(head, third);
//        System.out.println(listNode);
//
//
//        int[] exchange = exchange(new int[]{2, 1, 3, 4});
//        for (int i : exchange) {
//            System.out.println(i);
//        }
//
//        ListNode kthFromEnd = getKthFromEnd(third, 1);
//        System.out.println(kthFromEnd);

//        System.out.println(reverseList(head));

//        System.out.println(mergeTwoLists(head, second));


        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(3);
        root.left = left;
        root.right = right;
//        System.out.println(root);
//        System.out.println(mirrorTree(root));

//        List<Integer> integers = preorderTraversal(root);
//        for (Integer integer : integers) {
//            System.out.print(integer);
//        }

//        List<Integer> integers = inorderTraversal(root);
//        for (Integer integer : integers) {
//            System.out.print(integer);
//        }


//        MinStack minStack = new MinStack();
//        minStack.push(3);
//        minStack.push(2);
//        minStack.push(4);
//        System.out.println(minStack.min());
//        minStack.pop();
//        System.out.println(minStack.min());

//        System.out.println(validateStackSequences(new int[]{1, 0}, new int[]{1, 0}));

//        int[][] m = {{4, 5, 6, 8}, {6, 2, 7, 3}, {6, 8, 2, 8}, {1, 6, 7, 3}};
//        int[] ints = spiralOrder(m);
//        for (int anInt : ints) {
//            System.out.print(anInt);
//        }

//        System.out.println(addStrings("0", "0"));

//        ListNode listNode = removeNthFromEnd(third, 1);
//        System.out.println(listNode);

        System.out.println(mySqrt(2147395599));
    }

    /**
     * 斐波那契数列
     * F(0) = 0,   F(1) = 1
     * F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
     *
     * @param n 第n项
     * @return 求斐波那契数列的第 n 项
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
     * 一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个 n 级的台阶总共有多少种跳法
     * 答案需要取模 1e9+7（1000000007）
     */
    public static int numWays(int n) {
        if (n <= 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else if (n == 2) {
            return 2;
        }
        int one = 1;
        int two = 2;
        int result = 0;
        int index = 2;
        while (index < n) {
            result = (one + two) % 1000000007;
            one = two;
            two = result;
            index++;
        }
        return result;
    }

    /**
     * 输入数字 n，按顺序打印出从 1 到最大的 n 位十进制数。比如输入 3，则打印出 1、2、3 一直到最大的 3 位数 999
     * 解法没有考虑大数问题
     */
    public static int[] printNumbers(int n) {
        if (n <= 0) {
            return new int[0];
        }
        int v = (int) (Math.pow(10, n) - 1);
        int[] array = new int[v];
        for (int i = 0; i < v; i++) {
            array[i] = i + 1;
        }
        return array;
    }

    /**
     * 输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否为该栈的弹出顺序。
     * 假设压入栈的所有数字均不相等。例如，序列 {1,2,3,4,5} 是某栈的压栈序列，序列 {4,5,3,2,1} 是该压栈序列对应的一个弹出序列，
     * 但 {4,3,5,1,2} 就不可能是该压栈序列的弹出序列。
     */
    public static boolean validateStackSequences(int[] pushed, int[] popped) {
        if (pushed.length == 0 && popped.length == 0) {
            return true;
        }
        if (pushed.length <= 0 || popped.length <= 0) {
            return false;
        }
        Stack<Integer> stack = new Stack<>();
        int pushIndex = 0;
        stack.push(pushed[pushIndex]);
        int popIndex = 0;
        while (pushIndex < pushed.length) {
            while (stack.peek() != popped[popIndex]) {
                pushIndex++;
                if (pushIndex == pushed.length) {
                    break;
                }
                stack.push(pushed[pushIndex]);
            }
            if (pushIndex == pushed.length) {
                break;
            }
            stack.pop();
            popIndex++;
            if (popIndex == popped.length) {
                break;
            }
            if (stack.empty()) {
                pushIndex++;
                stack.push(pushed[pushIndex]);
            }
        }
        return popIndex == popped.length;
    }

    /**
     * 数值的整数次方
     */
    public static double myPow(double x, int n) {
        if (x == 0) {
            return 0;
        } else if (x == 1 || n == 0) {
            return 1;
        }
        int absN = Math.abs(n);
        double result = 1.0;
        for (int i = 0; i < absN; i++) {
            result *= x;
        }
        if (n < 0) {
            return 1 / result;
        }
        return result;
    }

    /**
     * x的平方根：给你一个非负整数 x ，计算并返回 x 的 算术平方根。由于返回类型是整数，结果只保留 整数部分 ，小数部分将被 舍去 。
     * 注意：不允许使用任何内置指数函数和算符，例如 pow(x, 0.5) 或者 x ** 0.5 。
     */
    public static int mySqrt(int x) {
        if (x <= 0) {
            return 0;
        }
        int left = 1;
        int right = x;
        int mid;
        // 二分法
        while (true) {
            mid = (left + right) >>> 1;
            if (mid == left) {
                return mid;
            }
            // 要考虑越界
            long midPow = ((long) mid) * ((long) mid);
            if (midPow > x) {
                right = mid;
            } else if (midPow < x) {
                left = mid;
            } else {
                return mid;
            }
        }
    }

}
