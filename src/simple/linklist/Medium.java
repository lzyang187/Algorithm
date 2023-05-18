package simple.linklist;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Medium {
    public static void main(String[] args) {
        Node head = new Node(7);
        Node second = new Node(13);
        Node third = new Node(11);
        Node _4th = new Node(10);
        Node _5th = new Node(1);
        head.next = second;
        second.next = third;
        third.next = _4th;
        _4th.next = _5th;
        second.random = head;
        third.random = _5th;
        _4th.random = third;
        _5th.random = head;
        Node result = copyRandomList(head);
        System.out.println(result);
    }

    /**
     * 链表中环的入口节点：给定一个链表，返回链表开始入环的第一个节点。 从链表的头节点开始沿着 next 指针进入环的第一个节点为环的入口节点。
     * 如果链表无环，则返回 null。
     */
    public ListNode detectCycle(ListNode head) {
        Set<ListNode> set = new HashSet<>();
        while (head != null) {
            if (set.contains(head)) {
                return head;
            } else {
                set.add(head);
                head = head.next;
            }
        }
        return null;
    }

    /**
     * 复杂链表的复制：请实现 copyRandomList 函数，深度复制一个复杂链表。在复杂链表中，每个节点除了有一个 next 指针指向下一个节点，
     * 还有一个 random 指针指向链表中的任意节点或者 null。
     */
    public static Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        // key是原节点，value是复制的节点
        HashMap<Node, Node> map = new HashMap<>();
        Node cur = head;
        // 先将复制的节点放入map
        while (cur != null) {
            map.put(cur, new Node(cur.val));
            cur = cur.next;
        }
        cur = head;
        Node result = map.get(cur);
        Node curCopy = result;
        while (cur != null) {
            curCopy.next = map.get(cur.next);
            curCopy.random = map.get(cur.random);
            cur = cur.next;
            curCopy = curCopy.next;
        }
        return result;
    }

}
