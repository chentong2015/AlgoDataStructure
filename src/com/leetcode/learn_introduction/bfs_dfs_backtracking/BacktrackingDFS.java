package com.leetcode.learn_introduction.bfs_dfs_backtracking;

import com.leetcode.learn_introduction.tree.prefix_tree_trie.model.TrieNodeWithWord;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class BacktrackingDFS {

    // Word Search II
    // Given an m x n board of characters and a list of strings words, return all words on the board
    // Each word must be constructed from letters of sequentially adjacent cells
    // The same letter cell may not be used more than once in a word    单词全部都是小写字符
    // board = [["o","a","b","n"],  words = ["oa","oaa"] words中要查找的单词都是唯一，可以构建HashKey
    //          ["o","t","a","e"],  Output: ["oa","oaa"] 这里必须要判断到单词的完整长度，不能重复添加 !!
    //          ["a","h","k","r"],
    //          ["a","f","l","v"]]

    // board = [["o","a","a","n"],  words = ["oath","pea","eat","rain","hklf", "hf"]
    //          ["e","t","a","e"],  注意取的单词构成了一个闭合的线路 !!
    //          ["i","h","k","r"],
    //          ["i","f","l","v"]]

    // 0. 基础思考: 能不能在二维数组中将一个单词取出来 !!
    // 1. 测试解法: 对二维数组每个点进行BFS广度优先遍历, 同时需要记录已经遍历过的字符(避免循环)         ==> KO
    // 2. 测试解法: 对二维数组的每个点字符构建HashKey, 遍历要查找的每个单词, 逐个判断字符是否存在且连续  ==> KO
    // 3. 高级解法: 单词数组构建Trie Tree + 以某个字符为起点DFS展开遍历 + Backtracking回溯
    //    TODO:   <快速查找单词的数据结构>  <以一个点遍历并展开>         <对元素值进行修改(标识已经遍历过，此路不通)，不需记录>

    private List<String> result = new ArrayList<>();
    private HashSet<String> wordsSet = new HashSet<>();

    public List<String> findWords(char[][] board, String[] words) {
        if (board == null || board.length < 1) return null;
        for (String word : words) wordsSet.add(word);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfsBacktracking(board, i, j, new StringBuilder());
            }
        }
        return result;
    }

    // TODO: 将递归过程中每一个word的构成做临时存储，使用Trie Tree来替代StringBuilder word的存储 !!
    // Set the cell value after reading to '#'
    // 1. 设置递归调用之后，需要将原来的值回复，回溯成最开始的值
    // 2. 同时将添加到的char从StringBuilder中去除，做好char的回退 !!
    private void dfsBacktracking(char[][] board, int row, int col, StringBuilder word) {
        if (row < 0 || row == board.length) return;
        if (col < 0 || col == board[0].length) return;
        if (board[row][col] == '#') return;

        char cellValue = board[row][col];
        word.append(cellValue);
        String currentWord = word.toString();
        if (wordsSet.contains(currentWord) && !result.contains(currentWord)) { // 这里的List查找元素会造成时间复杂度 !!
            result.add(currentWord);
            return;
        }
        board[row][col] = '#';
        dfsBacktracking(board, row - 1, col, word);
        dfsBacktracking(board, row, col - 1, word);
        dfsBacktracking(board, row + 1, col, word);
        dfsBacktracking(board, row, col + 1, word);
        board[row][col] = cellValue;
        word.deleteCharAt(word.length() - 1);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public List<String> findWords2(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();
        TrieNodeWithWord root = buildTrie(words);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs(board, i, j, root, res);
            }
        }
        return res;
    }

    public TrieNodeWithWord buildTrie(String[] words) {
        TrieNodeWithWord root = new TrieNodeWithWord();
        for (String word : words) {
            TrieNodeWithWord p = root;
            for (char c : word.toCharArray()) {
                int index = c - 'a';
                if (p.children[index] == null) {
                    p.children[index] = new TrieNodeWithWord();
                }
                p = p.children[index];
            }
            p.word = word;
        }
        return root;
    }

    // 每一层递归中，Trie Tree树的结点会进入不同的层次，以此来对应(查找)指定的单词
    public void dfs(char[][] board, int i, int j, TrieNodeWithWord p, List<String> res) {
        char c = board[i][j];
        if (c == '#' || p.children[c - 'a'] == null) return; // 已经读过的字符位置或者不在构建的Trie Tree结构中，则返回
        p = p.children[c - 'a'];
        if (p.word != null) {                                // 只要Trie Tree的节点位置上的word属性值非空，则说明是words中的单词
            res.add(p.word);
            p.word = null;
        }

        board[i][j] = '#';
        if (i > 0) dfs(board, i - 1, j, p, res);
        if (j > 0) dfs(board, i, j - 1, p, res);
        if (i < board.length - 1) dfs(board, i + 1, j, p, res);
        if (j < board[0].length - 1) dfs(board, i, j + 1, p, res);
        board[i][j] = c;
    }
}
