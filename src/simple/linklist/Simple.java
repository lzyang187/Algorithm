package simple.linklist;

import java.util.Stack;

public class Simple {
    public static void main(String[] args) {

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

}
