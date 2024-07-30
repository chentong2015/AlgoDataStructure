package questions.iteration_recursion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// Recursion递归法的本质是无法在一层方法中完整的实现逻辑
// 因此将后续或者部分的逻辑通过"调用自身来再次处理"，最后合成解决最终问题
public class LearnRecursion2 {

    // Clone Graph
    // Given a reference of a node in a connected undirected graph,
    // return a deep copy (clone) of the graph
    // Each node's value is the same as the node's index (1-indexed)
    // An adjacency list is a collection of unordered lists used to represent a finite graph
    // 1. The given node will always be the first node with val = 1
    // 2. The Graph is connected and all nodes can be visited starting from the given node
    //   1 - 2  从值为1的第一个结点开始，值和index位置值相同
    //   4 - 3  提供每个位置链接的相邻结点
    // adjList = [[2,4],[1,3],[2,4],[1,3]]
    private HashMap<Integer, UndirectedGraphNode> map = new HashMap<>();

    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        return clone(node);
    }

    // O(n) O(n) 极端的情况会递归调用一个node下面的(n-1)个链接的节点
    private UndirectedGraphNode clone(UndirectedGraphNode node) {
        // 1. 特殊的判断条件有那些
        if (node == null) return null;
        if (map.containsKey(node.val)) return map.get(node.val);

        // 2. 完整的clone一个结点该如何操作：创建新的对象
        UndirectedGraphNode clone = new UndirectedGraphNode(node.val);
        map.put(clone.val, clone);
        for (UndirectedGraphNode neighbor : node.neighbors) {
            // 3. 相邻的node结点必须要clone()之后才能添加到clone过的结点的neighbors中
            //    递归调用自身，完成neighbor的clone，再添加
            clone.neighbors.add(clone(neighbor));
        }
        return clone;
    }


    class UndirectedGraphNode {

        public int val;
        public List<UndirectedGraphNode> neighbors;

        public UndirectedGraphNode() {
            this(0);
        }

        public UndirectedGraphNode(int val) {
            this.val = val;
            neighbors = new ArrayList<UndirectedGraphNode>();
        }

        public UndirectedGraphNode(int val, ArrayList<UndirectedGraphNode> neighbors) {
            this.val = val;
            this.neighbors = neighbors;
        }
    }
}
