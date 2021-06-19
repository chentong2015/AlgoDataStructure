package com.leetcode.learn_introduction.graph;

import com.leetcode.learn_introduction.graph.model.UndirectedGraphNode;

import java.util.HashMap;

public class LearnGraphDFS2 {

    // TODO: Recursion递归方法的典型运用，3步操作逻辑
    // Clone Graph
    // Given a reference of a node in a connected undirected graph, return a deep copy (clone) of the graph
    // Each node's value is the same as the node's index (1-indexed)
    // An adjacency list is a collection of unordered lists used to represent a finite graph
    // 1. The given node will always be the first node with val = 1
    // 2. The Graph is connected and all nodes can be visited starting from the given node
    //   1 - 2  从值为1的第一个结点开始，值和index位置值相同
    //   4 - 3  提供每个位置链接的相邻结点
    // adjList = [[2,4],[1,3],[2,4],[1,3]]
    private HashMap<Integer, UndirectedGraphNode> map = new HashMap<>();

    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        return clone(node);
    }

    // 正确理解：1. 使用map存储已经clone出来的每一个结点
    //            如果是新的节点，这用它的值来创建对象，然后递归clone它的neighbor: clone(neighbor)同时将该递归的部分加入到List中
    //            O(n) O(n) 极端的情况会递归调用一个node下面的(n-1)个链接的节点
    private UndirectedGraphNode clone(UndirectedGraphNode node) {
        // 特殊的判断条件有那些
        if (node == null) return null;
        if (map.containsKey(node.val)) {
            return map.get(node.val);
        }
        // 完整的clone一个结点该如何操作：创建新的对象 !!
        UndirectedGraphNode clone = new UndirectedGraphNode(node.val);
        map.put(clone.val, clone);
        for (UndirectedGraphNode neighbor : node.neighbors) {
            clone.neighbors.add(clone(neighbor));
        }
        return clone;
    }

    // TODO：实战代码和面试中，不使用hard coding硬编码 !!
    // Target Sum
    // You are given an integer array nums and an integer target
    // Build an expression out of nums by adding one of the symbols '+' and '-' before each integer
    // Return the number of different expressions that you can build, which evaluates to target
    // nums = [1,1,1,1,1], target = 3  -> 5 一共有5种表示式的组合, 需要罗列每一种组合的可能
    // -1 + 1 + 1 + 1 + 1 = 3               数学上一共有2^n种组合, 需要避免这样的复杂度
    // +1 - 1 + 1 + 1 + 1 = 3
    // +1 + 1 - 1 + 1 + 1 = 3
    // +1 + 1 + 1 - 1 + 1 = 3
    // +1 + 1 + 1 + 1 - 1 = 3
    public int findTargetSumWays(int[] nums, int S) {
        int[] dp = new int[2001];
        dp[nums[0] + 1000] = 1;
        dp[-nums[0] + 1000] += 1;
        for (int i = 1; i < nums.length; i++) {
            int[] next = new int[2001];
            for (int sum = -1000; sum <= 1000; sum++) {
                if (dp[sum + 1000] > 0) {
                    next[sum + nums[i] + 1000] += dp[sum + 1000];
                    next[sum - nums[i] + 1000] += dp[sum + 1000];
                }
            }
            dp = next;
        }
        return S > 1000 ? 0 : dp[S + 1000];
    }
}
