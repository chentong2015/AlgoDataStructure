package com.leetcode.learn_all_problems.p1_array;

public class QuestionArray01 {

    // TODO: 本质上是一个"连续梯度上升"的问题，通过排序将这一个特征显现出来 !!
    //       通过DP + Binary解决连续梯度上升特征的问题 !! Longest Increasing Subsequence
    // Russian Doll Envelopes
    // envelopes[i] = [wi, hi] represents the width and the height of an envelope
    // One envelope can fit into another if and only if both the width and height of one envelope are greater than the other
    // Return the maximum number of envelopes you can Russian doll
    // 1. 作用范围，必须是长宽严格大于才能放入
    // 2. 同时不能够旋转envelope(把长当作宽来使用)
    // envelopes = [[5,4],[6,4],[6,7],[2,3]] -> [2,3] => [5,4] => [6,7] -> 3
    public int maxEnvelopesDP(int[][] envelopes) {
        // 思路1. 自定义比较器(先比较宽度，再比较高度)，将数组信息排序出来
        //       使用DP + Binary Search来找出最长的增长数 !! O(nlog(n)) O(n)
        return 0;
    }

    public int maxEnvelopesTree(int[][] envelopes) {
        // 思路2. 构建Nary-tree，其中子节点信封能够放入到父节点中，返回树的最大深度，则是最大的number
        // 问题.  需要创建Tree Node，每次添加的时候，都需要遍历树的结点以找到要添加的最深的长度位置 ?? 复杂度至少O(n*n)
        //         root
        //     6,7      6,4
        //  5,4  2,3   2,3
        // 2,3
        return 0;
    }

    // Sum Game
    // Alice and Bob replaces ? with one number (0-9), until there is not ? in the String
    // Alice go first, there are even character in the String
    // Check sums of the first half and the second half are equal
    // nums = "5023"     -> 5+0=2+3     -> Bob will win
    // nums = "25??"     -> "259?"      -> Alice will win
    // nums = "?3295???" -> "93295927"  -> Bob 后填，始终能够找到一种填法，使得前后的和相等
    public boolean sumGame(String str) {

        return false;
    }
}
