package com.leetcode.learn_introduction.tree.base;

public class TreeLinkNode {
    public int val;
    public TreeLinkNode left;
    public TreeLinkNode right;
    public TreeLinkNode next;

    public TreeLinkNode() {
    }

    public TreeLinkNode(int value) {
        val = value;
    }

    public TreeLinkNode(int val, TreeLinkNode left, TreeLinkNode right, TreeLinkNode next) {
        this.val = val;
        this.left = left;
        this.right = right;
        this.next = next;
    }
}
