package data_structure.tree.red_back_tree;

// 红黑树结点的5个基本属性
public final class TreeNode<K, V> {

    boolean red;
    TreeNode<K, V> parent;  // red-black tree links
    TreeNode<K, V> left;
    TreeNode<K, V> right;
    TreeNode<K, V> prev;    // needed to unlink next upon deletion
}
