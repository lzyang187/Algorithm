package main.linklist;

/**
 * 单链表的定义
 *
 * @author: cyli8
 * @date: 2022-01-23 09:57
 */
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(val);
        ListNode nextNode = next;
        while (nextNode != null) {
            sb.append(" ");
            sb.append(nextNode.val);
            nextNode = nextNode.next;
        }
        return sb.toString();
    }
}
