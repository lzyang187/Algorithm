package main.linklist;

/**
 * 复杂链表
 */
public class Node {

    public int val;
    public Node next;
    /**
     * random 指向链表中的任意节点或者 null
     */
    public Node random;

    public Node(int val) {
        this.val = val;
    }
}
