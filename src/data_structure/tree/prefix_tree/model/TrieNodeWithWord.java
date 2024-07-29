package data_structure.tree.prefix_tree.model;

public class TrieNodeWithWord {

    public String word;
    public TrieNodeWithWord[] children;

    public TrieNodeWithWord() {
        children = new TrieNodeWithWord[26];
    }
}
