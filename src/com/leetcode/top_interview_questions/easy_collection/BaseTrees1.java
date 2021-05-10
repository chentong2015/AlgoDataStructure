package com.leetcode.top_interview_questions.easy_collection;

import com.leetcode.top_interview_questions.base.TreeNode;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

public class BaseTrees1 {

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

    // Binary Tree Traversal 三种遍历: preOrder前序, inorder中序, postOrder后续   ===> DFS深度优先遍历 !!
    // 1. 使用递归方式：O(n) O(n)
    // 2. 使用非递归方式, Stack栈暂存节点
    public void preOrderStackTraverse(TreeNode root) {
        // 测试复杂度：O(n) O(1) 最多只有两个节点在栈中
        Stack<TreeNode> nodes = new Stack<>();
        nodes.push(root);
        while (!nodes.isEmpty()) {
            TreeNode current = nodes.pop();
            System.out.println(current.getVal());
            if (current.getRight() != null) {
                nodes.push(current.getRight());
            }
            if (current.getLeft() != null) {
                nodes.push(current.getLeft());
            }
        }
    }

    // 测试复杂度: O(n) O(n) 最糟糕的情况是所有的node都排在root左边，栈中需要压入全部的node
    public void inOrderStackTraverse(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        TreeNode popNode;
        while (node != null || !stack.isEmpty()) {
            if (node != null) {
                stack.push(node);       // 先压root栈，然后持续压左边子树，直到压到叶子节点
                node = node.getLeft();
            } else {
                popNode = stack.pop();  // 从最后压入的左边子树的叶子节点开始出栈，如果该节点有右边的子树，将有节点压入，作为root之后出栈的node
                System.out.println(popNode.getVal());
                node = popNode.getRight();
            }
        }
    }

    // 测试复杂度:
    public void postOrderStackTraverse(TreeNode root) {
        Stack<TreeNode> nodes = new Stack<>();
        nodes.push(root);
        while (!nodes.isEmpty()) {
            TreeNode current = nodes.peek(); // Looks at the object at the top of this stack without removing it from the stack.
            if (current.isLeaf()) {
                TreeNode node = nodes.pop();
                System.out.println(node.getVal());
            } else {
                if (current.getRight() != null) {
                    nodes.push(current.getRight());
                    current.setRight(null);
                }
                if (current.getLeft() != null) {
                    nodes.push(current.getLeft());
                    current.setLeft(null);
                }
            }
        }
    }

    // 补充理解：3. 非递归层次遍历, 使用queue队列一层一层遍历, 先入队列的先出来, 类似排队 ===> BFS广度优先遍历 !!
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
