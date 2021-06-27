package com.leetcode.learn_introduction.tree.prefix_tree_trie;

import java.util.HashSet;
import java.util.List;

// Replace Words
// Given a dictionary consisting of many roots and a sentence consisting of words separated by spaces
// Replace all the successors in the sentence with the root forming it
// If a successor can be replaced by more than one root, replace it with the root that has the "shortest length"

// dictionary = ["cat","bat","rat"],
// sentence = "the cattle was rattled by the battery"          -> "the cat was rat by the bat"

// dictionary = ["a", "aa", "aaa", "aaaa"],
// sentence = "a aa a aaaa aaa aaa aaa aaaaaa bbb baba ababa"  -> "a a a a a a a a bbb baba a"
// dictionary和sentence都有长度限制(数目非空)，只包含英语小写字母，句子首位不包含空格
public class LearnPrefixTree3 {

    // 类型Prefix Tree的问题，但是解决的办法确没有使用Prefix Tree Node
    // O(S) O(L) 使用HashSet存储dictionary中的root
    public String replaceWords(List<String> dictionary, String sentence) {
        HashSet<String> set = new HashSet<>(dictionary);
        StringBuilder result = new StringBuilder();
        String[] words = sentence.split("\\s+");         // \s是正则表达式，匹配whitespace characters
        for (int index = 0; index < words.length; index++) {
            StringBuilder stringBuilder = new StringBuilder(); // 依次对每一个字符组合后的字符串进行判断
            for (int j = 0; j < words[index].length(); j++) {
                char c = words[index].charAt(j);
                stringBuilder.append(c);
                String rootStr = stringBuilder.toString();
                if (set.contains(rootStr)) break;             // 无需再去修改原始的字符串数组String[] words
            }
            if (result.length() > 0) result.append(" ");      // 直接在逐个处理word的过程中组成拼接最后的结果字符串 !!
            result.append(stringBuilder.toString());
        }
        return result.toString();
    }
}
