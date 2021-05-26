package com.leetcode.learn_introduction;

import com.leetcode.learn_introduction.model.TreeLinkNode;
import com.leetcode.top_interview_questions.base.TreeNode;

// 1. N-Ary Tree
// 2. Binary Tree
// 3. Binary Search Tree
//      Balanced & Unbalanced BTS
//      Red–black Tree 自平衡二叉查找树, (对称, 降低树的深度, 节点有红黑之分) https://zh.wikipedia.org/wiki/红黑树
// 4. Complete Binary Tree 完全二叉树 / BTS / 堆(Heap)

// 高级数据结构
// 1. Segment Tree 片段树 / Binary Indexed Tree 二叉索引树
// 2. Prefix Tree 前缀树(含有多个子节点的树数据结构)
public class LearnTrees {

    /**
     * Binary Tree 遍历方式(测试)
     * 1. DFS(Depth first search)  ：递归遍历 + Stack栈入栈出
     * 2. BFS(Breadth First Search)：层次迭代(DummyHead辅助) + Queue & Deque双端队列
     * 3. BBFS(Bidirectional BFS)  : 从顶向下和从底向上同时遍历，如果在同一个Level层面有交集则是联通的
     * 4. Morris Traversal         : 非递归, 非Stack, 最佳复杂度 O(n) O(1)
     */

    // Search in a Binary Search Tree
    // Find the node in the BST that the node's value equals val and return the subtree rooted with that node
    // If such a node does not exist, return null.
    // root = [4,2,7,1,3], val = 2 -> [2,1,3]
    public TreeNode searchBST(TreeNode root, int val) {
        // 测试理解：1. BST树的递归查找法, 代码少且清晰，同时复杂度也比较优化
        //            O(n) O(1)
        if (root == null) return null;
        if (root.getVal() == val) {
            return root;
        }
        TreeNode leftNode = searchBST(root.getLeft(), val);
        if (leftNode == null) {
            return searchBST(root.getRight(), val);
        }
        return leftNode;
    }

    // TODO: 不使用队列实现广度优先遍历，同时设定node的.next指向 !!
    // Populating Next Right Pointers in Each Node II
    // Populate each next pointer to point to its next right node.
    // If there is no next right node, the next pointer should be set to NULL
    // Initially, all next pointers are set to NULL
    // root = [1,2,3,4,5,null,7] -> [1,#,2,3,#,4,5,7,#] 找到所有节点的next右边节点，然后设置node的.next指向

    // dummy = pre
    //                 1 -> null
    // dummy ->    2  ->  3 -> null
    // dummy ->  4   5       7 -> null
    // dummy -> ...
    public void connect(TreeLinkNode root) {
        TreeLinkNode dummyHead = new TreeLinkNode(0);
        TreeLinkNode pre = dummyHead;
        while (root != null) {
            if (root.left != null) {
                pre.next = root.left;  // dummy.next = 2    4   4.next -> 5
                pre = pre.next;        // pre = 2           4   pre = 5
            }
            if (root.right != null) {
                pre.next = root.right; // 2.next -> 3       5   5.next -> 7
                pre = pre.next;        // pre = 3           5   pre = 7
            }
            root = root.next;          // null              2   root = 3
            if (root == null) {
                pre = dummyHead;       // 恢复到开始设置，执行每一层的左边节点的前面
                root = dummyHead.next; // root设置成每一层左边的第一个节点
                dummyHead.next = null; // 避免在最后一层出现循环, 因为root始终不会为null, 导致无法跳出循环 !!!
            }
        }
    }
}
