package com.leetcode.learn_introduction.tree.prefix_tree_trie;

import java.util.HashMap;

// TODO: Prefix Hashmap 将前缀树和HashMap结合使用，优化公共Prefix的查找
public class LearnPrefixTree2 {

    private TrieNode root;
    private HashMap<String, Integer> map;

    public LearnPrefixTree2() {
        root = new TrieNode();
        map = new HashMap<>();
    }

    // Inserts the key-val pair into the map
    // If the key already existed, the original key-value pair will be overridden to the new one
    // 使用HashMap快速查找，如果存在key，则没有必要对Prefix做任何的修改
    public void insert(String key, int val) {
        if (!map.containsKey(key)) {

        }
        map.put(key, val);
    }

    // TODO: 使用前缀树，最快的找到所有具有公共前缀的Key/Word
    // Returns the sum of all the pairs' value whose key starts with the prefix
    // 统计所有具有公共前缀的key的值的总和，如果使用HashMap来存储，则必须遍历所有的key，时间复杂度为O(n*l) l为prefix的长度
    // 使用Prefix Tree，时间复杂度将会降为O(l)
    public int sum(String prefix) {

        return 0;
    }
}
