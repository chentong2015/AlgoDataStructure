package com.leetcode.basic_theory_introduction.tree.prefix_tree_trie;

import com.leetcode.basic_theory_introduction.tree.prefix_tree_trie.model.TrieNodeWithWord;

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

    // TODO: 类型Prefix Tree的问题，解决的办法确没有使用Prefix Tree Node
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

    // TODO: 使用标记了prefix字符串的前缀树来替换单词 !!
    public String replaceWordsByPrefixTree(List<String> roots, String sentence) {
        TrieNodeWithWord rootTree = constructPrefixTreeNode(roots);
        return replaceWords(rootTree, sentence);
    }

    //   c
    // a    b => word = cb
    //   t => word = cat 在前缀树中构建出来的单词，最后在node节点中标识 !!
    // O(n) 空间复杂度和这里roots中字符串的长度有关
    private TrieNodeWithWord constructPrefixTreeNode(List<String> roots) {
        TrieNodeWithWord rootTree = new TrieNodeWithWord();
        for (String root : roots) {
            TrieNodeWithWord current = rootTree;
            for (char letter : root.toCharArray()) {
                if (current.children[letter - 'a'] == null)
                    current.children[letter - 'a'] = new TrieNodeWithWord();
                current = current.children[letter - 'a'];
            }
            current.word = root; // 最后节点位置标记root，因为root不会重复，所以word单词只会被设置一次且不被覆盖 !!
        }
        return rootTree;
    }

    // O(N) N为句子的长度，最差情况是除了空格以外，每个位置的字符都需要遍历
    //      其中在遍历word每个字符的时候，针对的定位到prefix tree的位置，所以不是乘积的复杂度关系
    private String replaceWords(TrieNodeWithWord rootTree, String sentence) {
        StringBuilder result = new StringBuilder();
        for (String word : sentence.split("\\s+")) {
            TrieNodeWithWord cur = rootTree;
            for (char letter : word.toCharArray()) {
                if (cur.children[letter - 'a'] == null || cur.word != null) break; // 如果在前缀树中，找不到对应的前缀，或者到达树的结尾
                cur = cur.children[letter - 'a']; // 继续根据字符找到下一层的node
            }
            // 追加替换后的每一个单词到结果字符串中
            if (result.length() > 0) result.append(" ");
            String replacedWord = cur.word != null ? cur.word : word;
            result.append(replacedWord);
        }
        return result.toString();
    }
}
