package data_structure.base_data_structures.tree.node;

public class NodeWithPointer {

    // 将二叉树的查找方法定义在Node类型中
    class Node {
        private Node left;
        private Node right;
        private int value;

        public Node find(int v) {

            if (this.value == v) {
                return this;
            } else if (this.value < v) {
                if (this.right == null) {
                    return null;
                }
                return this.right.find(v);
            } else {
                if (this.left == null) {
                    return null;
                }
                return this.left.find(v);
            }
        }
    }
}
