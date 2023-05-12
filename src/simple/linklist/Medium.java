package simple.linklist;

import java.util.HashSet;
import java.util.Set;

public class Medium {
    public static void main(String[] args) {

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
}
