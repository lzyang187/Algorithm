package main.linklist;

/**
 * 复杂链表
 */
public class ComplexListNode {

    public int val;
    public ComplexListNode next;
    /**
     * random 指向链表中的任意节点或者 null
     */
    public ComplexListNode random;

    public ComplexListNode(int val) {
        this.val = val;
    }
}
