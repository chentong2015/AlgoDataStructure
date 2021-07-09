package com.leetcode.interview_questions.easy_collection;

import com.leetcode.interview_questions.base.TreeNode;

import java.util.*;

public class BaseTrees1 {

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

    // 测试复杂度: O(n) O(n) 这里的空间复杂度和Tree的高度有关，也取决于是否是平衡树
    //           最糟糕的情况是所有的node都排在root左边，栈中需要压入全部的node
    public void inOrderStackTraverse(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while (node != null || !stack.isEmpty()) {
            if (node != null) {
                stack.push(node);       // 先压root栈，然后持续压左边子树，直到压到叶子节点
                node = node.getLeft();
            } else {
                // 从最后压入的左边子树的叶子节点开始出栈，如果该节点有右边的子树，将有节点压入，作为root之后出栈的node
                TreeNode popNode = stack.pop();
                System.out.println(popNode.getVal());
                node = popNode.getRight();
            }
        }
    }

    // TODO: 不使用递归的后续遍历
    // 正确理解: Keep track of the previously printed node in pre. Only print a node if its right child is null or equal to pre
    //          记录始终记录前一个输出的节点，当且仅当node的右边节点为null，或者右边节点是前一个输出，则最后才输出该node ==> 后继输出 !!
    //          Using 1 Stack. O(n), O(n)
    //          3
    //    1          5
    // null 2pre   4   6
    // => stack: 3 1
    // => out: 2 1
    public List<Integer> postOrderStackTraverse(TreeNode root) {
        List<Integer> results = new ArrayList<>();
        if (root == null) return results;
        TreeNode preRoot = null;
        Stack<TreeNode> stack = new Stack();
        while (root != null || !stack.empty()) {
            if (root != null) {
                stack.push(root);               // 0. root最先进栈
                root = root.getLeft();          // 1. 先压左边node
            } else {
                root = stack.peek();            // 查看stack顶部的node，使用root来引用
                if (root.getRight() == null || preRoot == root.getRight()) {
                    results.add(root.getVal()); // 直接取查看node的值
                    stack.pop();
                    preRoot = root;             // 纪录前面一个输出的节点，把遍历过的位置路径做标记 !!
                    root = null;                // 设置null，防止再次进入root = root.getLeft();左侧的子树中
                } else {
                    root = root.getRight();     // 2. 在移动到右边，循环再压入右边节点
                }
            }
        }
        return results;
    }

    // TODO: BFS广度优先遍历, 非递归模式 ==> 树的遍历，一定没有循环出现，使用"BFS - Template I"
    // 使用queue队列一层一层遍历, 先入队列的先出来, 类似排队
    // O(n) O(n) 如果是完全二叉树，队列中对多存储的node是最下面一层，具有和O(n/2)相当的空间复杂度
    public void levelTraverse(TreeNode root) {
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

    // BFS遍历强化：每轮Queue队列中存储的是一层node的信息，利用levelSize可以通过size统一批次读取
    //            O(n) O(n)
    public List<List<Integer>> levelTraverse2(TreeNode root) {
        List<List<Integer>> results = new ArrayList<>();
        Queue<TreeNode> queue2 = new ArrayDeque<>();
        queue2.add(root);
        while (!queue2.isEmpty()) {
            int levelSize = queue2.size();      // 充分利用队列的长度.size()
            List<Integer> levelValues = new ArrayList<>();
            while (levelSize > 0) {             // 每一轮处理完整的一层节点信息 !!
                TreeNode node = queue2.poll();
                levelValues.add(node.getVal());
                if (node.getLeft() != null) {
                    queue2.add(node.getLeft());
                }
                if (node.getRight() != null) {
                    queue2.add(node.getRight());
                }
                levelSize--;
            }
            results.add(levelValues);
        }
        return results;
    }

    // TODO: Morris Traversal: Using No Stacks, O(n) Time & O(1) Space
    // https://leetcode.com/problems/binary-tree-postorder-traversal/discuss/45648/three-ways-of-iterative-postorder-traversing-easy-explanation
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> out = new ArrayList<Integer>();
        if (root == null)
            return out;
        TreeNode dummy = new TreeNode(-1), pre = null;
        dummy.setLeft(root);
        root = dummy;
        while (root != null) {
            if (root.getLeft() != null) {
                pre = root.getLeft();
                while (pre.getRight() != null && pre.getRight() != root)
                    pre = pre.getRight();
                if (pre.getRight() == null) {
                    pre.setRight(root);
                    root = root.getLeft();
                } else {
                    TreeNode node = pre;
                    reverse(root.getLeft(), pre);
                    while (node != root.getLeft()) {
                        out.add(node.getVal());
                        node = node.getRight();
                    }
                    out.add(node.getVal());          // Print again since we are stopping at node=root.left
                    reverse(pre, root.getLeft());
                    pre.setRight(null);
                    root = root.getRight();
                }
            } else {
                root = root.getRight();
            }
        }
        return out;
    }

    public void reverse(TreeNode from, TreeNode to) {
        if (from == to)
            return;
        TreeNode prev = from, node = from.getRight();
        while (prev != to) {
            TreeNode next = node.getRight();
            node.setRight(prev);
            prev = node;
            node = next;
        }
    }
}