package com.leetcode.learn_introduction.tree.prefix_tree_trie;

import com.leetcode.learn_introduction.tree.prefix_tree_trie.model.TrieNodeWithEnd;

/**
 * Prefix Tree(Trie): Store and retrieve keys in a dataset of strings / tree data structure
 * 1. 本质上是树的结构, 也可以看成是String集
 * 2. TODO: Prefix Tree有两种设计方法，在prefix结尾处标记结束，或者在prefix结尾处标记prefix完整字符串 !!
 * Balance Tree 区别 : 平衡树中搜索key的时间复杂度是O(mlog(n)) m为key的长度，n为key的数目
 * Hash Table 区别   :
 * 1. Hash Table 存储具有共同前缀的相同数量的key值，占的内存空间比较大
 * 2. Hash table 查找具有相同前缀的key不够高效
 * 3. Hash table 在按照词典顺序枚举字符串集时不够高效
 */
// Prefix Tree 实战场景：
// 1. AutoComplete 搜索自动补全
// 2. SpellChecker 英语拼写检查
// 3. Solving word games
// 4. Text on key9 predictive text 手机9宫格输入器预测单词
// 5. Longest prefix matching algorithm in IP routing to select an entry from a forwarding table
public class LearnPrefixTree1 {

    private TrieNodeWithEnd root = new TrieNodeWithEnd();

    // 如果在当前root的指定位置有找到对应的字符, 则取下一个node child, 最后表明key的结束
    // O(m) O(m) 最差情况下需要添加m个node(造成的空间)
    //      root
    //    c      l
    //   o         e -> end key "le" 共享的字符串只会存储一次(空间)
    //  d            e
    // e ->"code"      t -> end key "leet" 前面具有共享的字符段
    public void insert(String word) {
        TrieNodeWithEnd node = root;
        for (int i = 0; i < word.length(); i++) {
            char currentChar = word.charAt(i);
            if (!node.containsKey(currentChar)) {
                node.put(currentChar, new TrieNodeWithEnd());
            }
            node = node.get(currentChar);
        }
        node.setEnd(); // 作为单词结尾的位置，标记上结束，作为一个完整的word
    }

    public boolean search(String word) {
        TrieNodeWithEnd node = searchPrefix(word);
        return node != null && node.isEnd();
    }

    // 如果没有依次按照字符串的顺序往后找到end位置，则该word不在Prefix tree中
    private TrieNodeWithEnd searchPrefix(String word) {
        TrieNodeWithEnd node = root;
        for (int i = 0; i < word.length(); i++) {
            char currentChar = word.charAt(i);
            if (node.containsKey(currentChar)) {
                node = node.get(currentChar);
            } else {
                return null;
            }
        }
        return node;
    }

    public boolean startsWith(String prefix) {
        TrieNodeWithEnd node = searchPrefix(prefix);
        return node != null;
    }
}
