package com.leetcode.code_test_interview;

import java.util.List;

public class DemoQuestion1 {

    // TODO：根据不同的特征点，选择最恰当的数据结构 + 算法，共同构成一个复杂问题
    // Word Search II
    // Given an m x n board of characters and a list of strings words, return all words on the board
    // Each word must be constructed from letters of sequentially adjacent cells
    // The same letter cell may not be used more than once in a word
    // board = [["o","a","a","n"],  words = ["oath",  ==>  Output: ["eat","oath"]
    //          ["e","t","a","e"],           "pea",        words中要查找的单词都是唯一
    //          ["i","h","k","r"],           "eat",        单词全部都是小写字符
    //          ["i","f","l","v"]]           "rain"]

    // 0. 基础思考: 能不能在二维数组中将一个单词扣出来 !!
    // 1. 测试解法: 对二维数组每个点进行BFS广度优先遍历, 同时需要记录已经遍历过的字符(避免循环)         ==> KO
    // 2. 测试解法: 对二维数组的每个点字符构建HashKey, 遍历要查找的每个单词, 逐个判断字符是否存在且连续  ==> KO
    // 3. 高级解法: 根据单词数组，构建Trie Tree + 二分数组以某个字符做为起点DFS + Backtracking回溯
    //             构建快速查找单词的数据结构          以一个点遍历并展开        可以对元素值进行修改(标识已经遍历过，此路不通)，而不需要进行记录
    public List<String> findWords(char[][] board, String[] words) {

        return null;
    }
}
