package simple;

import java.util.ArrayList;
import java.util.List;
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

        List<Integer> integers = preorderTraversal(root);
        for (Integer integer : integers) {
            System.out.print(integer);
        }


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
            stack.push(cur.val);
            cur = cur.next;
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
     * 输入是一个无符号整数（以二进制串的形式），返回其二进制表达式中数字位数为 '1' 的个数
     */
    public static int hammingWeight(int n) {
        int count = 0;
        while (n != 0) {
            n = n & (n - 1);
            count++;
        }
        return count;
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
     * 给定单向链表的头指针和一个要删除的节点，定义一个函数删除该节点。
     * 返回删除后的链表的头节点
     */
    public static ListNode deleteNode(ListNode head, ListNode delNode) {
        if (head == null || delNode == null) {
            return head;
        }
        if (delNode.next == null) {
            // 要删除的节点是尾节点，只能遍历才能找到前面一个节点了
            ListNode preNode = head;
            ListNode curNode = head.next;
            while (curNode.val != delNode.val) {
                preNode = curNode;
                curNode = curNode.next;
            }
            preNode.next = null;
        } else {
            delNode.val = delNode.next.val;
            delNode.next = delNode.next.next;
        }
        return head;
    }


    /**
     * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有奇数在数组的前半部分，所有偶数在数组的后半部分。
     */
    public static int[] exchange(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return nums;
        }
        int leftindex = 0;
        int rightIndex = nums.length - 1;
        while (leftindex < rightIndex) {
            if ((nums[leftindex] & 1) == 0) {
                if ((nums[rightIndex] & 1) == 1) {
                    // 交换
                    int temp = nums[rightIndex];
                    nums[rightIndex] = nums[leftindex];
                    nums[leftindex] = temp;
                    leftindex++;
                    rightIndex--;
                } else {
                    rightIndex--;
                }
            } else {
                leftindex++;
            }
        }
        return nums;
    }


    /**
     * 输入一个链表，输出该链表中倒数第k个节点。为了符合大多数人的习惯，本题从1开始计数，即链表的尾节点是倒数第1个节点。
     */
    public static ListNode getKthFromEnd(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        int frontIndex = 0;
        ListNode frontNode = head;
        ListNode lastNode = head;
        while (frontIndex < k) {
            frontNode = frontNode.next;
            frontIndex++;
            if (frontNode == null) {
                if (frontIndex == k) {
                    return head;
                } else {
                    return null;
                }
            }
        }
        while (frontNode != null) {
            frontNode = frontNode.next;
            lastNode = lastNode.next;
        }
        return lastNode;
    }

    /**
     * 定义一个函数，输入一个链表的头节点，反转该链表并输出反转后链表的头节点。
     */
    public static ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode preNode = null;
        ListNode curNode = head;
        ListNode nextNode;
        while (curNode != null) {
            nextNode = curNode.next;
            curNode.next = preNode;
            preNode = curNode;
            curNode = nextNode;
        }
        return preNode;
    }

    /**
     * 输入两个递增排序的链表，合并这两个链表并使新链表中的节点仍然是递增排序的。
     */
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        }
        int l1Val = l1.val;
        int l2Val = l2.val;
        ListNode result;
        if (l1Val <= l2Val) {
            result = new ListNode(l1Val);
            result.next = mergeTwoLists(l1.next, l2);
        } else {
            result = new ListNode(l2Val);
            result.next = mergeTwoLists(l1, l2.next);
        }
        return result;
    }


    /**
     * 输入一个二叉树，该函数输出它的镜像。
     *
     * @param root
     * @return
     */
    public static TreeNode mirrorTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        // 是否是叶子节点
        if (root.left == null && root.right == null) {
            return root;
        }
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        mirrorTree(root.left);
        mirrorTree(root.right);
        return root;
    }


    /**
     * 判断一棵二叉树是不是对称的。如果一棵二叉树和它的镜像一样，那么它是对称的
     */
    public boolean isSymmetric(TreeNode root) {
        return isSymmetric(root, root);
    }

    /**
     * 对于树中 任意两个对称节点 L 和 R
     * L.val=R.val ：即此两对称节点值相等。
     * L.left.val = R.right.val：即 L 的 左子节点 和 R 的 右子节点 对称
     * L.right.val = R.left.val：即 L 的 右子节点 和 R 的 左子节点 对称
     */
    public boolean isSymmetric(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) {
            return true;
        }
        if (root1 == null || root2 == null) {
            return false;
        }
        if (root1.val != root2.val) {
            return false;
        }
        return isSymmetric(root1.left, root2.right) &&
                isSymmetric(root1.right, root2.left);
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
     * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字
     */
    public static int[] spiralOrder(int[][] matrix) {
        if (matrix == null || matrix.length <= 0 || matrix[0].length <= 0) {
            return new int[]{};
        }
        int[] resultArray = new int[matrix.length * matrix[0].length];
        int curArrayIndex = 0;
        int startRow = 0;
        int startCol = 0;
        int endRow = matrix.length - 1;
        int endCol = matrix[0].length - 1;
        while (startRow <= endRow && startCol <= endCol) {
            int curRow = startRow;
            int curCol = startCol;
            if (startRow == endRow) {
                // 只有一行了
                while (curCol <= endCol) {
                    resultArray[curArrayIndex] = matrix[startRow][curCol];
                    curArrayIndex++;
                    curCol++;
                }
            } else if (startCol == endCol) {
                // 只有一列了
                while (curRow <= endRow) {
                    resultArray[curArrayIndex] = matrix[curRow][startCol];
                    curArrayIndex++;
                    curRow++;
                }
            } else {
                // 一周
                // 上
                while (curCol <= endCol) {
                    resultArray[curArrayIndex] = matrix[startRow][curCol];
                    curArrayIndex++;
                    curCol++;
                }
                // 右
                curRow++;
                while (curRow <= endRow) {
                    resultArray[curArrayIndex] = matrix[curRow][endCol];
                    curArrayIndex++;
                    curRow++;
                }
                // 下
                curCol -= 2;
                while (curCol >= startCol) {
                    resultArray[curArrayIndex] = matrix[endRow][curCol];
                    curArrayIndex++;
                    curCol--;
                }
                // 左
                curRow -= 2;
                while (curRow > startRow) {
                    resultArray[curArrayIndex] = matrix[curRow][startCol];
                    curArrayIndex++;
                    curRow--;
                }
            }
            startRow++;
            startCol++;
            endRow--;
            endCol--;
        }
        return resultArray;
    }

    /**
     * 给定两个字符串形式的非负整数num1和num2，计算它们的和并同样以字符串形式返回。
     * 你不能使用任何內建的用于处理大整数的库（比如 BigInteger），也不能直接将输入的字符串转换为整数形式。
     */
    public static String addStrings(String num1, String num2) {
        if (num1 == null || num1.isEmpty()) {
            return num2;
        } else if (num2 == null || num2.isEmpty()) {
            return num1;
        }
        char[] num1Chars = new char[num1.length()];
        for (int i = 0; i < num1.length(); i++) {
            num1Chars[i] = num1.charAt(num1.length() - 1 - i);
        }
        char[] num2Chars = new char[num2.length()];
        for (int i = 0; i < num2.length(); i++) {
            num2Chars[i] = num2.charAt(num2.length() - 1 - i);
        }
        // 结果数组的最大长度是较大整数的位数+1
        int maxSize = Math.max(num1Chars.length, num2Chars.length);
        int[] results = new int[maxSize + 1];
        for (int i = 0; i < maxSize; i++) {
            int result = results[i];
            if (i < num1Chars.length) {
                // 将字符转换为对应的int，'9' 转换为9
                result += (num1Chars[i] - '0');
            }
            if (i < num2Chars.length) {
                result += (num2Chars[i] - '0');
            }
            // 进位
            if (result > 9) {
                results[i + 1] = 1;
                result = result % 10;
            }
            results[i] = result;
        }
        StringBuilder sb = new StringBuilder();
        boolean flag = true;
        for (int i = results.length - 1; i >= 0; i--) {
            if (flag && results[i] == 0) {
                continue;
            }
            flag = false;
            sb.append(results[i]);
        }
        if (sb.length() == 0) {
            return "0";
        }
        return sb.toString();
    }

    /**
     * 给你二叉树的根节点 root ，返回它节点值的 前序 遍历。
     */
    public static List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> results = new ArrayList<>();
        if (root == null) {
            return results;
        }
        results.add(root.val);
        results.addAll(preorderTraversal(root.left));
        results.addAll(preorderTraversal(root.right));
        return results;
    }

}
