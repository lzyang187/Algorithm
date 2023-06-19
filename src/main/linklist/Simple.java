package main.linklist;

import java.util.*;

public class Simple {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode second = new ListNode(2);
        head.next = second;
//        second = null;
//        System.out.println(head);
        System.out.println(deleteNode(head, second));
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
     * 给定单向链表的头指针和一个要删除的节点，定义一个函数删除该节点。
     * 返回删除后的链表的头节点
     */
    public static ListNode deleteNode(ListNode head, ListNode delNode) {
        if (head == null || delNode == null) {
            return head;
        }
        if (head == delNode) {
            return null;
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
        ListNode cur = new ListNode(head.val);
        cur.next = null;
        ListNode next = head.next;
        ListNode nextNext;
        while (next != null) {
            nextNext = next.next;
            next.next = cur;
            cur = next;
            next = nextNext;
        }
        return cur;
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
     * 给你一个链表的头节点 head ，判断链表中是否有环。
     */
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = slow.next;
        while (slow != fast) {
            slow = slow.next;
            if (fast.next == null || fast.next.next == null) {
                return false;
            }
            fast = fast.next.next;
        }
        return true;
    }

    /**
     * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
     */
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null || n <= 0) {
            return head;
        }
        ListNode front = head;
        int count = 0;
        while (count < n) {
            count++;
            if (front.next == null) {
                if (count == n) {
                    // 正好删除头结点
                    return head.next;
                } else {
                    // 长度不够n
                    return head;
                }
            }
            front = front.next;
        }
        ListNode back = head;
        while (front.next != null) {
            front = front.next;
            back = back.next;
        }
        back.next = back.next.next;
        return head;
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
     * 链表的中间结点：给你单链表的头结点 head ，请你找出并返回链表的中间结点。如果有两个中间结点，则返回第二个中间结点。
     */
    public ListNode middleNode(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (true) {
            if (fast.next == null) {
                break;
            }
            if (fast.next.next == null) {
                slow = slow.next;
                break;
            }
            fast = fast.next.next;
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
        while (head != null) {
            if (head.val == val) {
                head = head.next;
            } else {
                break;
            }
        }
        ListNode pre = head;
        ListNode cur = head;
        while (cur != null) {
            if (cur.val == val) {
                // 移除此项
                if (cur.next != null) {
                    cur.val = cur.next.val;
                    cur.next = cur.next.next;
                } else {
                    // 删除尾节点
                    pre.next = null;
                    break;
                }
            } else {
                pre = cur;
                cur = cur.next;
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
        ListNode front = head;
        ListNode next = head.next;
        while (next != null) {
            if (front.val != next.val) {
                front.next = next;
                front = next;
            }
            next = next.next;
            if (next == null) {
                front.next = null;
                break;
            }
        }
        return head;
    }

}
