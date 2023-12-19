package data_structure.base_data_structures.tree.prefix_tree_trie.model;

public class TrieNodeWithWord {

    public String word;
    public TrieNodeWithWord[] children;

    public TrieNodeWithWord() {
        children = new TrieNodeWithWord[26];
    }
}
