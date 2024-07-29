package data_structure.tree.prefix_tree.impl;

// 如果在当前root的指定位置有找到对应的字符, 则取下一个node child, 最后表明key的结束
// O(m) O(m) 最差情况下需要添加m个node(造成的空间)
//      root
//    c      l
//   o         e -> end key "le" 共享的字符串只会存储一次(空间)
//  d            e
// e ->"code"      t -> end key "leet" 前面具有共享的字符段
public class PrefixTreeTrieImpl {

    private TrieNodeWithEnd root = new TrieNodeWithEnd();

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
