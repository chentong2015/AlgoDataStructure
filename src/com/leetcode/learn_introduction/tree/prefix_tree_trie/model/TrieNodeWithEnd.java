package com.leetcode.learn_introduction.tree.prefix_tree_trie.model;

//                     abcdefghijklmnopqrstuvwxyz
//                  link                    link
// abcdefghijklmnopqrstuvwxyz ...             abcdefghijklmnopqrstuvwxyz
//            link                          link                      link
// abcdefghijklmnopqrstuvwxyz ...abcdefghijklmnopqrstuvwxyz  ...   abcdefghijklmnopqrstuvwxyz
//       link
// abcdefghijklmnopqrstuvwxyz ...
public class TrieNodeWithEnd {

    private TrieNodeWithEnd[] links;
    private boolean isEnd;

    // 总共有26个字母的所展开的node children
    public TrieNodeWithEnd() {
        links = new TrieNodeWithEnd[26];
    }

    // char-'a'转换index位置，对应的node children位置上添加结点
    public void put(char ch, TrieNodeWithEnd node) {
        links[ch - 'a'] = node;
    }

    // 判断指定的位置上是否有node child
    public boolean containsKey(char ch) {
        return links[ch - 'a'] != null;
    }

    public TrieNodeWithEnd get(char ch) {
        return links[ch - 'a'];
    }

    public void setEnd() {
        isEnd = true;
    }

    public boolean isEnd() {
        return isEnd;
    }
}
