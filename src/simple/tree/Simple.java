package simple.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Simple {
    public static void main(String[] args) {

    }

    /**
     * 输入一个二叉树，该函数输出它的镜像。
     *
     * @param root
     * @return
     */
    public static TreeNode mirrorTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        // 是否是叶子节点
        if (root.left == null && root.right == null) {
            return root;
        }
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        mirrorTree(root.left);
        mirrorTree(root.right);
        return root;
    }

    /**
     * 判断一棵二叉树是不是对称的。如果一棵二叉树和它的镜像一样，那么它是对称的
     */
    public boolean isSymmetric(TreeNode root) {
        return isSymmetric(root, root);
    }

    /**
     * 对于树中 任意两个对称节点 L 和 R
     * L.val=R.val ：即此两对称节点值相等。
     * L.left.val = R.right.val：即 L 的 左子节点 和 R 的 右子节点 对称
     * L.right.val = R.left.val：即 L 的 右子节点 和 R 的 左子节点 对称
     */
    public boolean isSymmetric(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) {
            return true;
        }
        if (root1 == null || root2 == null) {
            return false;
        }
        if (root1.val != root2.val) {
            return false;
        }
        return isSymmetric(root1.left, root2.right) && isSymmetric(root1.right, root2.left);
    }

    /**
     * 给你二叉树的根节点 root ，返回它节点值的 前序 遍历。
     */
    public static List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> results = new ArrayList<>();
        if (root == null) {
            return results;
        }
        results.add(root.val);
        results.addAll(preorderTraversal(root.left));
        results.addAll(preorderTraversal(root.right));
        return results;
    }

    /**
     * 树的非递归中序遍历
     */
    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> results = new ArrayList<>();
        if (root == null) {
            return results;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curNode = root;
        stack.push(curNode);
        while (curNode != null || !stack.isEmpty()) {
            if (curNode != null && curNode.left != null) {
                stack.push(curNode.left);
                curNode = curNode.left;
            } else {
                TreeNode pop = stack.pop();
                results.add(pop.val);
                curNode = pop.right;
                if (curNode != null) {
                    stack.push(curNode);
                }
            }
        }
        return results;
    }
}
