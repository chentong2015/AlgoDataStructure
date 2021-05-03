package com.leetcode.top_interview_questions.easy_collection;

import com.leetcode.top_interview_questions.easy_collection.model.TreeNode;

import java.lang.Math;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

public class Trees1 {

    // 1 + 2 + 4 + 8 ... n = a1(2^n -1) => 2^n
    // Maximum Depth of Binary Tree 2叉树的深度
    // Given the root of a binary tree, return its maximum depth
    public int maxDepth(TreeNode root) {
        // 测试理解：1. 使用递归算法(1 + 左右子树的深度的最大值), 和具体的节点的值没有关系
        //            O(n) O(n) 递归方法造成栈空间的开销, 最差的情况是n个节点排在n层 !!
        if (root == null) {
            return 0;
        } else if (root.getLeft() == null) { // 叶子节点
            return 1 + maxDepth(root.getRight());
        } else if (root.getRight() == null) {
            return 1 + maxDepth(root.getLeft());
        } else {
            return 1 + Math.max(maxDepth(root.getLeft()), maxDepth(root.getRight()));
        }
    }

    // Validate Binary Search Tree 判断是否是标准的"二叉搜索树 BST"
    // Given the root of a binary tree, determine if it is a valid binary search tree (BST)
    // 左边nodes都比key小，右边的所有nodes都比key大，同时左右都都各自满足BST
    public boolean isValidBST(TreeNode root) {
        // 测试理解：1. 使用递归算法, "左边子树的最大值小于key，右边子树的最小值大于key"
        //            O(n) O(n) 递归方法造成栈空间的开销, 最差的情况是n个节点排在n层 !!
        TreeNode node;
        TreeNode leftMaxNode;
        TreeNode rightMinNode;
        if (root == null) {
            return true;
        } else {
            node = root.getLeft(); // 拿到左边子树的最大值
            leftMaxNode = node;
            while (node != null) {
                leftMaxNode = node;
                node = node.getRight();
            }

            node = root.getRight(); // 拿到右边子树的最小值
            rightMinNode = node;
            while (node != null) {
                rightMinNode = node;
                node = node.getLeft();
            }

            if (leftMaxNode == null && rightMinNode == null) { // 说明只有一个节点
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
        }
        return false;
    }

    // TODO: 实现非递归的前序和后续遍历
    // 正确理解：1. 递归中序遍历，将所有的节点取出来，然后判断数字的大小是否按照顺序排列 !!
    //         2. 非递归中序遍历，使用Stack栈来暂存节点, 先进后出
    //            O(n) O(n) 最糟糕的情况是所有的node都排在root左边，栈中需要压入全部的node
    public void inOrderStackTraverse(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        TreeNode popNode;
        while (node != null || !stack.isEmpty()) {
            if (node != null) {
                stack.push(node);      // 先将root压栈，然后左边压栈，然后访问右边 !
                node = node.getLeft();
            } else {
                popNode = stack.pop();
                System.out.println(popNode.getVal());
                node = popNode.getRight();
            }
        }
    }

    // 补充理解：3. 非递归层次遍历, 使用queue队列一层一层遍历, 先入队列的先出来, 类似排队 ===> 也叫BFS广度优先遍历 !!
    //            O(n) O(n) 所有的node被会被添加到队列中，然后再出来
    public void levelTraverse(TreeNode root) {
        if (root == null) {
            return;
        }
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            System.out.println(node.getVal());
            if (node.getLeft() != null) {
                queue.add(node.getLeft());  // 先添加左边node到队列
            }
            if (node.getRight() != null) {
                queue.add(node.getRight()); // 后添加右边node到队列
            }
        }
    }
}
