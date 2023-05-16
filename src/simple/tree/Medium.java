package simple.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
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
        System.out.println(verifyPostorder(new int[]{5, 7, 6, 11, 8, 10}));
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

    /**
     * 从上到下打印二叉树 II：从上到下按层打印二叉树，同一层的节点按从左到右的顺序打印，每一层打印到一行。
     * BFS：广度优先搜索
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        // 借助队列
        LinkedList<TreeNode> linkedList = new LinkedList<>();
        int curCount = 0;
        int nextCount = 0;
        linkedList.offer(root);
        curCount++;
        List<Integer> list = new ArrayList<>();
        while (!linkedList.isEmpty()) {
            TreeNode poll = linkedList.poll();
            if (curCount <= 0) {
                curCount = nextCount;
                nextCount = 0;
                result.add(list);
                list = new ArrayList<>();
            }
            list.add(poll.val);
            curCount--;
            if (poll.left != null) {
                linkedList.offer(poll.left);
                nextCount++;
            }
            if (poll.right != null) {
                linkedList.offer(poll.right);
                nextCount++;
            }
            // 如果队列为空了，把最后的list添加到result
            if (linkedList.isEmpty()) {
                result.add(list);
            }
        }
        return result;
    }

    /**
     * 二叉搜索树的后序遍历序列：输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历结果。如果是则返回 true，否则返回 false。
     * 假设输入的数组的任意两个数字都互不相同。
     */
    public static boolean verifyPostorder(int[] postorder) {
        if (postorder == null) {
            return false;
        }
        if (postorder.length <= 1) {
            return true;
        }
        int root = postorder[postorder.length - 1];
        boolean isLeft = true;
        int rightStartIndex = 0;
        // 最后的root不用比较
        for (int i = 0; i < postorder.length - 1; i++) {
            if (isLeft && postorder[i] > root) {
                rightStartIndex = i;
                isLeft = false;
            } else if (!isLeft && postorder[i] < root) {
                return false;
            }
        }
        boolean leftVerify = verifyPostorder(Arrays.copyOfRange(postorder, 0, rightStartIndex));
        boolean rightVerify = verifyPostorder(Arrays.copyOfRange(postorder, rightStartIndex, postorder.length - 1));
        return leftVerify && rightVerify;
    }

}
