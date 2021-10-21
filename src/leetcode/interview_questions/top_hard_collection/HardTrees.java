package leetcode.interview_questions.top_hard_collection;

import leetcode.interview_questions.base.TreeNode;

import java.util.*;

public class HardTrees {

    // TODO: BBFS(Bidirectional BFS) https://leetcode.com/problems/word-ladder/solution/
    // Word Ladder
    // Given two words, beginWord and endWord, and a dictionary wordList, 
    // return the number of words in the shortest transformation sequence from beginWord to endWord, 
    // or 0 if no such sequence exists
    // beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
    // -> "hit" -> "hot" -> "dot" -> "dog" -> cog" -> 5
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // 测试理解: 如何判断两个字符串只有一个字符不同? 如何选择下一个字符串? 如何排序取出最短的路径?

        // 正确理解：1. 使用Graph图(结构)来解决最短线路和路径问题，最短的路径是构成图的levels中间的距离(在能够联通的情况下)
        Set<String> set = new HashSet<>(wordList); // 从List转成HashSet, 去除重复单词并快速查找
        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);
        int count = 1;
        while (!queue.isEmpty()) {
            for (int i = 0; i < queue.size(); i++) {
                char[] current = queue.poll().toCharArray(); // 出队列
                for (int j = 0; j < current.length; j++) {
                    char temp = current[j];                  // 每次只能变化一个位置上的单词
                    for (char c = 'a'; c <= 'z'; c++) {
                        current[j] = c;
                        String next = new String(current);   // 依次替换每个位置上的字符，构建新的字符串，查找是否具有构建出来的单词
                        if (set.contains(next)) {
                            if (next.equals(endWord)) {      // 当有路径到达list中的单词，并且是结尾单词，则结束 !!
                                return count + 1;
                            }
                            queue.add(next);  // 添加到队列中，作为下一个查找的单词的起点
                            set.remove(next); // 移除掉给的单词列表中的单词，并且count已经添加步数
                        }
                    }
                    current[j] = temp;
                }
            }
            count++; // Queue中一个单词查找完，换下一个新的单词，增加步数
        }
        return 0;
    }

    // LCA(Lowest Common Ancestor) of a Binary Tree 最低公共前继, 普通二叉树
    // Given a binary tree, find the lowest common ancestor of two given nodes in the tree
    // 1. All Node.val are unique
    // 2. p != q, p and q will exist in the tree
    // 3. 允许一个结点是另一个结点的后继
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 测试理解：1. 递归将二叉树处理成root结点和左右子树，左右子树中判断root结点和子树的关系
        //             O(n) O(n) 最大的递归(递归方法造成的栈开销)的高度是n层，所有的节点排列在一侧
        if (root == p || root == q) {
            return root;
        }
        TreeNode leftCommonNode = null;
        TreeNode rightCommonNode = null;
        if (root.getLeft() != null) {
            leftCommonNode = lowestCommonAncestor(root.getLeft(), p, q);
        }
        if (root.getRight() != null) {
            rightCommonNode = lowestCommonAncestor(root.getRight(), p, q);
        }
        if (leftCommonNode == null && rightCommonNode == null) { // 如果在"某root结点"的左右子树中都没有有效的结点(没有目标结点)
            return null;
        } else if (leftCommonNode == null) {
            return rightCommonNode;          // 返回一个非空的值，递归到上层去判断 !!
        } else if (rightCommonNode == null) {
            return leftCommonNode;
        } else {                             // 两个子树都非空，说明要找的结点分布在"某root结点"的两侧，该root节点就是它们的最小公共前继 !!
            return root;
        }
    }

    // 测试理解：2. 使用BFS广度优先遍历二叉树，同时使用HashMap存储每一个节点的父节点
    //            O(n) O(n) 额外开辟3个数据结构的内存空间，但是空间复杂度始终是O(n)
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
