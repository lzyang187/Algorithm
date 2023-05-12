package simple.tree;

import java.util.ArrayList;
import java.util.List;

public class Medium {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(0);
        TreeNode right = new TreeNode(1);
        TreeNode leftLeft = new TreeNode(-4);
        TreeNode leftRight = new TreeNode(-3);
        left.left = leftLeft;
        left.right = leftRight;
        root.left = left;
        root.right = right;

        TreeNode second = new TreeNode(1);
        TreeNode secondLeft = new TreeNode(-4);
        second.left = secondLeft;
        System.out.println(isSubStructure(root, second));
    }

    /**
     * 二叉树的所有路径：给你一个二叉树的根节点 root ，按 任意顺序 ，返回所有从根节点到叶子节点的路径。
     */
    public static List<String> binaryTreePaths(TreeNode root) {
        if (root == null) {
            return null;
        }
        List<String> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        binaryTreePathsWithPath(root, list, sb);
        return list;
    }

    public static void binaryTreePathsWithPath(TreeNode node, List<String> list, StringBuilder sb) {
        if (node != null) {
            if (node.left == null && node.right == null) {
                // 叶子节点
                list.add(sb.append(node.val).toString());
            } else {
                binaryTreePathsWithPath(node.left, list, new StringBuilder(sb).append(node.val).append("->"));
                binaryTreePathsWithPath(node.right, list, new StringBuilder(sb).append(node.val).append("->"));
            }
        }
    }

    /**
     * 树的子结构：输入两棵二叉树A和B，判断B是不是A的子结构。(约定空树不是任意一个树的子结构)
     * B是A的子结构， 即 A中有出现和B相同的结构和节点值。
     */
    public static boolean isSubStructure(TreeNode A, TreeNode B) {
        if (A == null || B == null) {
            return false;
        }
        if (A.val == B.val) {
            if (equalsB(A.left, B.left) && equalsB(A.right, B.right)) {
                // B的子节点和A对应
                return true;
            }
        }
        return isSubStructure(A.left, B) || isSubStructure(A.right, B);
    }

    private static boolean equalsB(TreeNode A, TreeNode B) {
        if (B == null) {
            return true;
        }
        if (A == null) {
            return false;
        }
        return A.val == B.val && equalsB(A.left, B.left) && equalsB(A.right, B.right);
    }

}
