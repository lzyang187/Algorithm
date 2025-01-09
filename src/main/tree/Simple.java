package main.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Simple {
    public static void main(String[] args) {

    }

    /**
     * 翻转二叉树：输入一个二叉树，该函数输出它的镜像。
     *
     * @param root
     * @return
     */
    public static TreeNode mirrorTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        mirrorTree(root.left);
        mirrorTree(root.right);
        return root;
    }

    /**
     * 判断一棵二叉树是不是对称的。
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

    /**
     * 相同的树：给你两棵二叉树的根节点 p 和 q ，编写一个函数来检验这两棵树是否相同。
     * 如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        } else if (p == null || q == null) {
            return false;
        }
        return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    /**
     * 二叉树的最大深度：给定一个二叉树，找出其最大深度。
     * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int count = 1;
        return count + Math.max(maxDepth(root.left), maxDepth(root.right));
    }

    /**
     * 二叉树的最小深度：给定一个二叉树，找出其最小深度。
     * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
     */
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int count = 1;
        if (root.left == null) {
            return count + minDepth(root.right);
        } else if (root.right == null) {
            return count + minDepth(root.left);
        }
        return count + Math.min(minDepth(root.left), minDepth(root.right));
    }

    /**
     * 左叶子之和：给定二叉树的根节点 root ，返回所有左叶子之和。
     * 输入: root = [3,9,20,null,null,15,7]
     * 输出: 24
     * 解释: 在这个二叉树中，有两个左叶子，分别是 9 和 15，所以返回 24
     */
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int count = 0;
        if (root.left != null && root.left.left == null && root.left.right == null) {
            // 是左叶子
            count += root.left.val;
        }
        return count + sumOfLeftLeaves(root.left) + sumOfLeftLeaves(root.right);
    }

    /**
     * 路径总和：给你二叉树的根节点root 和一个表示目标和的整数targetSum 。判断该树中是否存在 根节点到叶子节点 的路径，
     * 这条路径上所有节点值相加等于目标和targetSum 。如果存在，返回 true ；否则，返回 false 。
     */
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            // 是叶子节点
            return root.val == targetSum;
        }
        return hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);
    }

    /**
     * 将有序数组转换为二叉搜索树：给你一个整数数组 nums ，其中元素已经按 升序 排列，请你将其转换为一棵 高度平衡 二叉搜索树。
     * 高度平衡 二叉树是一棵满足「每个节点的左右两个子树的高度差的绝对值不超过 1 」的二叉树。
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length <= 0) {
            return null;
        }
        int mid = nums.length >>> 1;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = sortedArrayToBST(Arrays.copyOfRange(nums, 0, mid));
        if (mid + 1 < nums.length) {
            root.right = sortedArrayToBST(Arrays.copyOfRange(nums, mid + 1, nums.length));
        } else {
            root.right = null;
        }
        return root;
    }

    /**
     * 高度平衡二叉树：给定一个二叉树，判断它是否是高度平衡的二叉树。
     * 高度平衡 二叉树是一棵满足「每个节点的左右两个子树的高度差的绝对值不超过 1 」的二叉树。
     */
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        int abs = Math.abs(maxDepth(root.left) - maxDepth(root.right));
        if (abs > 1) {
            return false;
        }
        return isBalanced(root.left) && isBalanced(root.right);
    }

    /**
     * 二叉搜索树的第k大节点：给定一棵二叉搜索树，请找出其中第 k 大的节点的值。
     */
    public int kthLargest(TreeNode root, int k) {
        if (root == null || k <= 0) {
            throw new NullPointerException("root参数为空或k不合法");
        }
        // 先处理根和右子树
        List<Integer> rightList = inorderTraversal(root.right);
        rightList.add(0, root.val);
        if (rightList.size() >= k) {
            return rightList.get(rightList.size() - k);
        }
        // 再处理左子树
        List<Integer> leftList = inorderTraversal(root.left);
        rightList.addAll(0, leftList);
        if (rightList.size() >= k) {
            return rightList.get(rightList.size() - k);
        }
        throw new IllegalArgumentException("k比节点数大");
    }

}
