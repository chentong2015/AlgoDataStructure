package work_interview.murex;

import beans.TreeNode;

import java.util.*;

public class LCAQuestion {

    // LCA(Lowest Common Ancestor) of a Binary Tree 最低公共前继, 普通二叉树
    // Given a binary tree, find the lowest common ancestor of two given nodes in the tree
    // 1. All Node.val are unique
    // 2. p != q, p and q will exist in the tree
    // 3. 允许一个结点是另一个结点的后继
    //
    // O(n) O(n) 最大的递归(递归方法造成的栈开销)高度是n层，所有的节点排列在一侧
    //           在每次递归时都创建新的Node节点对象
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == p || root == q) {
            return root;
        }

        // TODO. 只需要将p q节点放到左右子树中查找，如果找到则说明节点中有一个为p或q
        //  如果在"某root结点"的左右子树中都没有有效的结点(没有目标结点)
        TreeNode leftCommonNode = null;
        TreeNode rightCommonNode = null;
        if (root.getLeft() != null) {
            leftCommonNode = lowestCommonAncestor(root.getLeft(), p, q);
        }
        if (root.getRight() != null) {
            rightCommonNode = lowestCommonAncestor(root.getRight(), p, q);
        }

        // 两个子树都非空，说明要找的结点分布在"某root结点"的两侧
        // 该root节点就是它们的最小公共前继，共同的前继
        if (leftCommonNode == null && rightCommonNode == null) {
            return null;
        } else if (leftCommonNode == null) {
            return rightCommonNode;
        } else if (rightCommonNode == null) {
            return leftCommonNode;
        } else {
            return root;
        }
    }

    // 测试理解：2. 使用BFS广度优先遍历二叉树，同时使用HashMap存储每一个节点的父节点
    // O(n) O(n) 额外开辟3个数据结构的内存空间，但是空间复杂度始终是O(n)
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        Deque<TreeNode> queue = new ArrayDeque<>();
        Map<TreeNode, TreeNode> parent = new HashMap<>();
        Set<TreeNode> pAncestors = new HashSet<>(); // 从下往上依次存储P的父节点
        parent.put(root, null);
        queue.add(root);
        while (!parent.containsKey(p) || !parent.containsKey(q)) {
            TreeNode node = queue.poll();
            if (node.getLeft() != null) {
                parent.put(node.getLeft(), node);
                queue.add(node.getLeft());
            }
            if (node.getRight() != null) {
                parent.put(node.getRight(), node);
                queue.add(node.getRight());
            }
        }
        while (p != null) {
            pAncestors.add(p);
            p = parent.get(p);
        }
        while (!pAncestors.contains(q)) { // 从下往上，找到第一个和Q的父节点相同的节点 !!
            q = parent.get(q);
        }
        return q; // 最终停止的位置就是共同点
    }
}
