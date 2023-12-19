package data_structure.dfs_bfs_traverse.tree;

import java.util.Stack;

public class LearnTreeDFS1 {

    // pre-order traversal 前序遍历 + DFS 深度优先遍历
    //
    // 直接在Tree上进行变换，将Tree的左边的节点依次甩到右边，然后在把root往右边移动
    // 1. root.right = root.left;
    // 2. root.left = null
    // 3. root = root.right;
    public void flatten2(TreeNode root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        while (true) {
            if (root.right != null)
                stack.push(root.right); // 将right右侧节点暂存到stack中，待后续的取出
            if (root.left == null) {    // left为空说明没有左侧节点了
                if (stack.isEmpty()) {
                    break;  // stack为空说明没有右侧节点了
                }
                root.right = stack.pop(); // root的右侧节点从Stack中取出来
            } else {
                root.right = root.left;
                root.left = null;
            }
            root = root.right;
        }
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
