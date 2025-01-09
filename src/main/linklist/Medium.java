package main.linklist;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Medium {
    public static void main(String[] args) {
        ComplexListNode head = new ComplexListNode(7);
        ComplexListNode second = new ComplexListNode(13);
        ComplexListNode third = new ComplexListNode(11);
        ComplexListNode _4th = new ComplexListNode(10);
        ComplexListNode _5th = new ComplexListNode(1);
        head.next = second;
        second.next = third;
        third.next = _4th;
        _4th.next = _5th;
        second.random = head;
        third.random = _5th;
        _4th.random = third;
        _5th.random = head;
        ComplexListNode result = copyRandomList(head);
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
    public static ComplexListNode copyRandomList(ComplexListNode head) {
        if (head == null) {
            return null;
        }
        // key是原节点，value是复制的节点
        Map<ComplexListNode, ComplexListNode> map = new HashMap<>();
        ComplexListNode cur = head;
        // 先将复制的节点放入map
        while (cur != null) {
            map.put(cur, new ComplexListNode(cur.val));
            cur = cur.next;
        }
        cur = head;
        ComplexListNode result = map.get(cur);
        ComplexListNode curCopy = result;
        while (cur != null) {
            curCopy.next = map.get(cur.next);
            curCopy.random = map.get(cur.random);
            cur = cur.next;
            curCopy = curCopy.next;
        }
        return result;
    }

    public static ComplexListNode copyRandomListQuick(ComplexListNode head) {
        if (head == null) {
            return null;
        }
        // key是原节点，value是复制的节点
        Map<ComplexListNode, ComplexListNode> map = new HashMap<>();
        ComplexListNode result = new ComplexListNode(head.val);
        // 将头节点放入map
        map.put(head, result);
        ComplexListNode cur = head;
        ComplexListNode curCopy = result;
        while (cur != null) {
            if (cur.next != null) {
                if (map.containsKey(cur.next)) {
                    curCopy.next = map.get(cur.next);
                } else {
                    ComplexListNode copy = new ComplexListNode(cur.next.val);
                    curCopy.next = copy;
                    map.put(cur.next, copy);
                }
            }
            if (cur.random != null) {
                if (map.containsKey(cur.random)) {
                    curCopy.random = map.get(cur.random);
                } else {
                    ComplexListNode copy = new ComplexListNode(cur.random.val);
                    curCopy.random = copy;
                    map.put(cur.random, copy);
                }
            }
            cur = cur.next;
            curCopy = curCopy.next;
        }
        return result;
    }

}
