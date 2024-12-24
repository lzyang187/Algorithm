package main;

import main.linklist.ListNode;
import main.tree.TreeNode;

import java.util.HashSet;
import java.util.Set;

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
//        System.out.println(mySqrt(2147395599));
//        System.out.println(isUgly(60));
//        System.out.println(isHappy(19));
        System.out.println(nthUglyNumber(10));
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
        int left = 1;
        int right = 1;
        int result = 0;
        for (int i = 3; i <= n; i++) {
            result = left + right;
            left = right;
            right = result;
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
        int left = 1;
        int right = 2;
        int result = 0;
        for (int i = 3; i <= n; i++) {
            result = (left + right) % 1000000007;
            left = right;
            right = result;
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

    /**
     * 丑数：丑数（比如6） 就是只包含质因数 2、3 和 5 的正整数。给你一个整数 n ，请你判断 n 是否为 丑数 。如果是，返回 true ；否则，返回 false 。
     * 1 没有质因数，因此它的全部质因数是 {2, 3, 5} 的空集。习惯上将其视作第一个丑数。
     * 14 不是丑数，因为它包含了另外一个质因数 7 。
     */
    public static boolean isUgly(int n) {
        if (n <= 0) {
            return false;
        }
        while (n % 2 == 0) {
            n = n >>> 1;
        }
        while (n % 3 == 0) {
            n /= 3;
        }
        while (n % 5 == 0) {
            n /= 5;
        }
        return n == 1;
    }

    /**
     * 丑数：求按从小到大的顺序的第 n 个丑数。习惯上我们把1当作第一个丑数。
     */
    public static int nthUglyNumber(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n不能<= 0");
        }
        if (n == 1) {
            return 1;
        }
        int[] array = new int[n];
        array[0] = 1;
        int i2 = 0;
        int i3 = 0;
        int i5 = 0;
        for (int i = 1; i < n; i++) {
            int next2 = array[i2] * 2;
            int next3 = array[i3] * 3;
            int next5 = array[i5] * 5;
            int min = Math.min(Math.min(next2, next3), next5);
            array[i] = min;
            if (next2 == min) {
                i2++;
            }
            if (next3 == min) {
                i3++;
            }
            if (next5 == min) {
                i5++;
            }
        }
        return array[n - 1];
    }

    /**
     * 3 的幂：给定一个整数，写一个函数来判断它是否是 3的幂次方。如果是，返回 true ；否则，返回 false 。
     * 整数 n 是 3 的幂次方需满足：存在整数 x 使得 n == 3^x
     */
    public static boolean isPowerOfThree(int n) {
        if (n <= 0) {
            return false;
        }
        while (n != 1) {
            if (n % 3 == 0) {
                n /= 3;
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * 4的幂：给定一个整数，写一个函数来判断它是否是 4 的幂次方。如果是，返回 true ；否则，返回 false 。
     * 整数 n 是 4 的幂次方需满足：存在整数 x 使得 n == 4^x
     */
    public boolean isPowerOfFour(int n) {
        if (n <= 0) {
            return false;
        }
        while (n != 1) {
            if (n % 4 == 0) {
                n /= 4;
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * 快乐数：「快乐数」定义为：
     * 对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和。
     * 然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。
     * 如果这个过程 结果为1，那么这个数就是快乐数。
     */
    public static boolean isHappy(int n) {
        if (n <= 0) {
            return false;
        }
        Set<Integer> set = new HashSet<>();
        set.add(n);
        while (n != 1) {
            n = getHappyNext(n);
            if (set.contains(n)) {
                // 如果不是快乐数，最终会得到已经计算过的数
                return false;
            } else {
                set.add(n);
            }
        }
        return true;
    }

    private static int getHappyNext(int n) {
        int result = 0;
        while (n >= 10) {
            int num = n % 10;
            result += num * num;
            n = n / 10;
        }
        result += n * n;
        return result;
    }

    /**
     * Nim 游戏：你和你的朋友，两个人一起玩Nim 游戏：
     * 桌子上有一堆石头。你们轮流进行自己的回合，你作为先手。每一回合，轮到的人拿掉1 - 3 块石头。拿掉最后一块石头的人就是获胜者。
     * 假设你们每一步都是最优解。请编写一个函数，来判断你是否可以在给定石头数量为 n 的情况下赢得游戏。如果可以赢，返回 true；否则，返回 false 。
     * 输入：n = 4
     * 输出：false
     * 解释：以下是可能的结果:
     * 1. 移除1颗石头。你的朋友移走了3块石头，包括最后一块。你的朋友赢了。
     * 2. 移除2个石子。你的朋友移走2块石头，包括最后一块。你的朋友赢了。
     * 3. 你移走3颗石子。你的朋友移走了最后一块石头。你的朋友赢了。
     * 在所有结果中，你的朋友是赢家。
     */
    public boolean canWinNim(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("输入的n必须大于0");
        }
        if (n <= 3) {
            return true;
        }
        // 是4的倍数，则无法赢得游戏
        return n % 4 != 0;
    }


}
