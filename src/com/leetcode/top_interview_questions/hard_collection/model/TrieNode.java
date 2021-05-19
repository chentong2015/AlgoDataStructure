package com.leetcode.top_interview_questions.hard_collection.model;

// abcdefghijklmnopqrstuvwxyz
//    link
// abcdefghijklmnopqrstuvwxyz ...
//            link
// abcdefghijklmnopqrstuvwxyz ...
//       link
// abcdefghijklmnopqrstuvwxyz ...
public class TrieNode {

    private TrieNode[] links;
    private boolean isEnd;

    // 总共有26个字母的所展开的node children
    public TrieNode() {
        links = new TrieNode[26];
    }

    // 在对应的node children位置上添加结点
    public void put(char ch, TrieNode node) {
        links[ch - 'a'] = node;
    }

    // 判断指定的位置上是否有node child
    public boolean containsKey(char ch) {
        return links[ch - 'a'] != null;
    }

    public TrieNode get(char ch) {
        return links[ch - 'a'];
    }

    public void setEnd() {
        isEnd = true;
    }

    public boolean isEnd() {
        return isEnd;
    }
}
