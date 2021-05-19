package com.leetcode.top_interview_questions.hard_collection;

import com.leetcode.top_interview_questions.hard_collection.model.TrieNode;

/**
 * Prefix Tree(Implement Trie): 在String集中存储和从中检索key的一种数据结构
 * 1. store and retrieve keys in a dataset of strings / tree data structure
 * 2. 在存储相同的key with the same prefix, Trie比Hash table占用更小的Space, 它会将具有公共prefix的字符串进行整合 !!
 * /
 * Balance Tree 区别：------------------------------------------------------------------------------
 * 1. 在平衡树中搜索key的时间复杂度是O(mlog(n)) m为key的长度，n为key的数目
 * /
 * Hash Table 区别：------------------------------------------------------------------------------
 * 1. Hash table has O(1) time complexity for looking for a key: Hash表在查找key的时间复杂度上面是O(1)
 * 2. Hash table 查找具有相同前缀的key不够高效
 * 3. Hash table 在按照词典顺序枚举字符串集时不够高效
 * 4. Hash table 随着size的增加，可能会导致搜索的时间复杂度上升到O(n)
 */
// Prefix Tree 实战场景：
// 1. AutoComplete 搜索自动补全
// 2. SpellChecker 英语拼写检查
// 3. Longest prefix matching algorithm in IP routing to select an entry from a forwarding table
// 4. Solving word games
// 5. Text on key9 predictive text 手机9宫格输入器预测单词
class OtherPrefixTree {

    private TrieNode root = new TrieNode();

    // 如果在当前root的指定位置有找到对应的字符, 则取下一个node child, 最后表明key的结束
    // O(m) O(m) 最差情况下需要添加m个node(造成的空间)
    //      root
    //    c      l
    //   o         e -> end key "le" 共享的字符串只会存储一次(空间)
    //  d            e
    // e ->"code"      t -> end key "leet" 前面具有共享的字符段
    public void insert(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char currentChar = word.charAt(i);
            if (!node.containsKey(currentChar)) {
                node.put(currentChar, new TrieNode());
            }
            node = node.get(currentChar);
        }
        node.setEnd();
    }

    public boolean search(String word) {
        TrieNode node = searchPrefix(word);
        return node != null && node.isEnd();
    }

    // 如果没有依次按照字符串的顺序往后找到end位置，则该word不在Prefix tree中
    private TrieNode searchPrefix(String word) {
        TrieNode node = root;
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
        TrieNode node = searchPrefix(prefix);
        return node != null;
    }
}
