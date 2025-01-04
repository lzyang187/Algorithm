package main.linklist;

import java.util.*;

public class Simple {
    public static void main(String[] args) {
        ListNode head = new ListNode(4);
        ListNode second = new ListNode(1);
        ListNode third = new ListNode(8);
        ListNode fourth = new ListNode(4);
        ListNode fifth = new ListNode(5);
        head.next = second;
        second.next = third;
        third.next = fourth;
        fourth.next = fifth;
//        second = null;
//        System.out.println(head);
//        System.out.println(deleteNode(head, second));
        System.out.println(getIntersectionNodeOptimize(fourth, head));
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
     * 删除链表的节点：给定单向链表的头指针和一个要删除的节点，定义一个函数删除该节点。
     * 返回删除后的链表的头节点
     */
    public static ListNode deleteNode(ListNode head, ListNode delNode) {
        if (head == null || delNode == null) {
            return head;
        }
        if (head == delNode) {
            // 要删除的是头节点
            return head.next;
        }
        if (delNode.next == null) {
            // 要删除的节点是尾节点，只能遍历才能找到前面一个节点了
            ListNode preNode = head;
            ListNode curNode = head.next;
            while (curNode != delNode) {
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
     * 输入一个链表，输出该链表中倒数第k个节点。为了符合大多数人的习惯，本题从1开始计数，即链表的尾节点是倒数第1个节点。
     * 多考虑鲁棒性
     */
    public static ListNode getKthFromEnd(ListNode head, int k) {
        if (head == null || k <= 0) {
            return null;
        }
        int count = 0;
        ListNode rightNode = head;
        ListNode leftNode = head;
        while (count < k) {
            rightNode = rightNode.next;
            count++;
            if (rightNode == null) {
                if (count == k) {
                    return head;
                } else {
                    return null;
                }
            }
        }
        while (rightNode != null) {
            rightNode = rightNode.next;
            leftNode = leftNode.next;
        }
        return leftNode;
    }

    /**
     * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
     */
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null || n <= 0) {
            return head;
        }
        ListNode right = head;
        int count = 0;
        while (count < n) {
            count++;
            if (right.next == null) {
                if (count == n) {
                    // 正好删除头结点
                    return head.next;
                } else {
                    // 长度不够n
                    return head;
                }
            }
            right = right.next;
        }
        ListNode left = head;
        while (right.next != null) {
            right = right.next;
            left = left.next;
        }
        left.next = left.next.next;
        return head;
    }

    /**
     * 反转链表：输入一个链表的头节点，反转该链表并输出反转后链表的头节点。
     */
    public static ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode left = null;
        ListNode cur = head;
        ListNode right;
        while (cur != null) {
            right = cur.next;
            cur.next = left;
            left = cur;
            cur = right;
        }
        return left;
    }

    /**
     * 合并两个有序链表：输入两个递增排序的链表，合并这两个链表并使新链表中的节点仍然是递增排序的。
     */
    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        } else if (list2 == null) {
            return list1;
        }
        ListNode head;
        if (list1.val <= list2.val) {
            head = list1;
            list1 = list1.next;
        } else {
            head = list2;
            list2 = list2.next;
        }
        head.next = mergeTwoLists(list1, list2);
        return head;
    }

    /**
     * 环形链表：给你一个链表的头节点 head ，判断链表中是否有环。
     */
    public boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head;
        do {
            slow = slow.next;
            if (fast.next == null) {
                return false;
            }
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        } while (slow != null && fast != null);
        return false;
    }

    /**
     * 相交链表：给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。如果两个链表不存在相交节点，返回 null 。
     * 题目数据 保证 整个链式结构中不存在环。
     * 注意，函数返回结果后，链表必须 保持其原始结构 。
     */
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        Set<ListNode> set = new HashSet<>();
        ListNode curA = headA;
        while (curA != null) {
            set.add(curA);
            curA = curA.next;
        }
        ListNode curB = headB;
        while (curB != null) {
            if (set.contains(curB)) {
                return curB;
            }
            curB = curB.next;
        }
        return null;
    }

    /**
     * 相交链表：时间复杂度O(n)，空间复杂度O(1)的解法
     */
    public static ListNode getIntersectionNodeOptimize(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        // 求A的长度
        int aLen = 0;
        ListNode temp = headA;
        while (temp != null) {
            aLen++;
            temp = temp.next;
        }
        // 求B的长度
        int bLen = 0;
        temp = headB;
        while (temp != null) {
            bLen++;
            temp = temp.next;
        }
        ListNode aIndex = headA;
        ListNode bIndex = headB;
        // 让长的先移动长度之差
        if (aLen > bLen) {
            int count = aLen - bLen;
            while (count > 0) {
                aIndex = aIndex.next;
                count--;
            }
        } else if (bLen > aLen) {
            int count = bLen - aLen;
            while (count > 0) {
                bIndex = bIndex.next;
                count--;
            }
        }
        // 再同步往后遍历
        while (aIndex != bIndex) {
            if (aIndex == null || bIndex == null) {
                return null;
            }
            aIndex = aIndex.next;
            bIndex = bIndex.next;
        }
        return aIndex;
    }

    /**
     * 链表的中间结点：给你单链表的头结点 head ，请你找出并返回链表的中间结点。如果有两个中间结点，则返回第二个中间结点。
     */
    public ListNode middleNode(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null) {
            fast = fast.next;
            if (fast == null) {
                return slow;
            }
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

    /**
     * 移除链表元素：给你一个链表的头节点 head 和一个整数 val ，请你删除链表中所有满足 Node.val == val 的节点，并返回 新的头节点 。
     */
    public ListNode removeElements(ListNode head, int val) {
        if (head == null) {
            return null;
        }
        // 找到头节点
        while (head.val == val) {
            head = head.next;
            if (head == null) {
                return null;
            }
        }
        ListNode pre = head;
        while (pre.next != null) {
            // 以前一个节点为基准，判断下一个节点的值是否相等
            if (pre.next.val == val) {
                // 移除此项
                pre.next = pre.next.next;
            } else {
                pre = pre.next;
            }
        }
        return head;
    }

    /**
     * 回文链表：给你一个单链表的头节点 head ，请你判断该链表是否为回文链表。如果是，返回 true ；否则，返回 false 。
     */
    public boolean isPalindrome(ListNode head) {
        if (head == null) {
            return false;
        }
        List<Integer> list = new ArrayList<>();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }
        int left = 0;
        int right = list.size() - 1;
        while (left < right) {
            if (list.get(left) != list.get(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    /**
     * 删除排序链表中的重复元素：给定一个已排序的链表的头 head ， 删除所有重复的元素，使每个元素只出现一次 。返回 已排序的链表 。
     */
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode cur = head;
        while (cur.next != null) {
            if (cur.val == cur.next.val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        return head;
    }

}
