package data_structure.base_data_structures.tree;

import algorithms.datamodel.TreeNode;

import java.util.ArrayDeque;
import java.util.Queue;

public class BaseTrees2 {

    // 1 + 2 + 4 + 8 ... n = a1(2^n -1) => 2^n
    // Maximum Depth of Binary Tree 2叉树的深度
    // Given the root of a binary tree, return its maximum depth
    public int maxDepth(TreeNode root) {
        // 测试理解：1. 使用递归算法(1 + 左右子树的深度的最大值), 和具体的节点的值没有关系
        //            O(n) O(n) 递归方法造成栈空间的开销, 最差的情况是n个节点排在n层 !!
        if (root == null) {
            return 0;
        } else if (root.getLeft() == null) {
            return 1 + maxDepth(root.getRight());
        } else if (root.getRight() == null) {
            return 1 + maxDepth(root.getLeft());
        } else {
            return 1 + Math.max(maxDepth(root.getLeft()), maxDepth(root.getRight()));
        }
    }

    // Validate "Binary Search Tree" 判断是否是标准的"二叉搜索树"
    // Given the root of a binary tree, determine if it is a valid binary search tree (BST)
    // 左边nodes都比key小，右边的所有nodes都比key大，同时左右都都各自满足BST
    public boolean isValidBST(TreeNode root) {
        // 测试理解：1. 使用递归算法, "左边子树的最大值小于key，右边子树的最小值大于key"
        // O(n) O(n) 递归方法造成栈空间的开销, 最差的情况是n个节点排在n层 !!
        TreeNode node;
        TreeNode leftMaxNode;
        TreeNode rightMinNode;
        if (root == null) {
            return true;
        }

        // 拿到左边子树的最大值
        node = root.getLeft();
        leftMaxNode = node;
        while (node != null) {
            leftMaxNode = node;
            node = node.getRight();
        }

        // 拿到右边子树的最小值
        node = root.getRight();
        rightMinNode = node;
        while (node != null) {
            rightMinNode = node;
            node = node.getLeft();
        }

        if (leftMaxNode == null && rightMinNode == null) {
            // 说明只有一个节点
            return true;
        } else if (leftMaxNode == null) {
            if (root.getVal() < rightMinNode.getVal()) {
                return isValidBST(root.getRight());
            }
        } else if (rightMinNode == null) {
            if (root.getVal() > leftMaxNode.getVal()) {
                return isValidBST(root.getLeft());
            }
        } else {
            if (root.getVal() > leftMaxNode.getVal() && root.getVal() < rightMinNode.getVal()) {
                return isValidBST(root.getLeft()) && isValidBST(root.getRight());
            }
        }
        return false;
    }

    // Symmetric Tree 判断是否是对称树，左右镜像
    // Given the root of a binary tree, check whether it is a mirror of itself
    public boolean isSymmetricIteratively(TreeNode root) {
        // 测试理解: 1. 迭代算法, 使用中序遍历将节点取出，再判断值的数组是否成值对称            ===> 错误解法 !!
        //          2. 广度优先遍历, 取出值然后, 逐级判断(1, 2, 4...)每一层的数字是否是对称的 ===> 错误解法 !!

        // 正确理解: 1. 通过依次(广度优先)添加需要相互比较的两个node, 每次从队列中出两个来比较, 相同则继续
        //             O(n) O(n) 最差的情况是，所有的node都会进入队列然后出队列，所有开辟等量的内存空间
        if (root == null) {
            return true;
        }
        Queue<TreeNode> queues = new ArrayDeque<>();
        queues.add(root.getLeft());
        queues.add(root.getRight());
        while (!queues.isEmpty()) {
            TreeNode nodeLeft = queues.poll();
            TreeNode nodeRight = queues.poll();
            if (nodeLeft == null && nodeRight == null) {
                continue; // 继续比较队列中的其他一组值
            }
            if (nodeLeft == null || nodeRight == null) {
                return false;
            }
            if (nodeLeft.getVal() != nodeRight.getVal()) {
                return false;
            }
            queues.add(nodeLeft.getLeft()); // 往下扩展一层，添加相邻的两个node为镜像位置要比较的值 !!
            queues.add(nodeRight.getRight());
            queues.add(nodeLeft.getRight());
            queues.add(nodeRight.getLeft());
        }
        return true;
    }

    public boolean isSymmetricRecursively(TreeNode root) {
        // 正确理解: 1. 递归算法, 额外声明一个判断镜像的方法 !!
        //            O(n) O(n) 递归的调用的深度是tree的深度，最差是node的数目，造成O(n)的栈开销 !!
        return isMirror(root, root);
    }

    // 两个子树，镜像的三个条件：顶部值相等，左右相互对称
    public boolean isMirror(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) {
            return true;
        }
        if (t1 == null || t2 == null) {
            return false;
        }
        return (t1.getVal() == t2.getVal()) && isMirror(t1.getRight(), t2.getLeft()) && isMirror(t1.getLeft(), t2.getRight());
    }
}
