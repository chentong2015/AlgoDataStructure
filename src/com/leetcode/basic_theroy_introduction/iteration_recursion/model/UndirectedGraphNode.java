package com.leetcode.basic_theroy_introduction.iteration_recursion.model;

import java.util.ArrayList;
import java.util.List;

public class UndirectedGraphNode {

    public int val;
    public List<UndirectedGraphNode> neighbors;

    public UndirectedGraphNode() {
        this(0);
    }

    public UndirectedGraphNode(int val) {
        this.val = val;
        neighbors = new ArrayList<UndirectedGraphNode>();
    }

    public UndirectedGraphNode(int val, ArrayList<UndirectedGraphNode> neighbors) {
        this.val = val;
        this.neighbors = neighbors;
    }
}
