package simple.tree;

/**
 * 二叉树的定义
 *
 * @author: cyli8
 * @date: 2022-01-23 15:11
 */
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int x) {
        val = x;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(val);
        if (left != null) {
            sb.append(" ").append(left.toString());
        }
        if (right != null) {
            sb.append(" ").append(right.toString());
        }
        return sb.toString();
    }
}
