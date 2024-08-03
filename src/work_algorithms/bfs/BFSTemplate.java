package work_algorithms.bfs;

import org.w3c.dom.Node;

// Use queue to store level nodes, read it for next loop
//   -> B  -> E
// A -> C  -> K  -> M
//   -> D  -> F
//               -> G
// 首先遍历root，然后将它指向的结点都检加入到队列中作为第二层
// 然后添加第二层所有指向的结点, 最先被压入队列的将会优先出队列
public class BFSTemplate {

    // BFS - Template I
    // Determine the nodes and the edges before doing BFS
    // Node will be an actual node or a status, the edge will be an actual edge or a possible transition
    // 使用BFS时，注意结点的形式和边界约束条件(transition过渡指向条件)
    public int BFS(Node root, Node target) {
        // Queue<Node> queue;
        // int step = 0;
        // add root to queue;
        // while (queue is not empty){
        //     step = step + 1;                            // 遍历的访问路径的层次
        //     int size = queue.size();                    // iterate the nodes which are already in the queue
        //     for (int i = 0; i < size; ++i) {
        //         Node cur = the first node in queue;     // 判断是否是要找的target node
        //         return step if cur is target;
        //         for (Node next : the neighbors of cur){
        //             add next to queue;
        //         }
        //         remove the first node from queue;       // 将first node出队列，减少队列中的node
        //     }
        // }
        return -1;                                         // 表示没有联通的路径
    }

    // TODO: 如果确定没有循环的路径，则不需要使用Set标记，比如树的遍历 !!
    // TODO: 不需要添加一个结点多次，如果可以，考虑直接修改原始结点，代替对于结点的其他"标记" !!
    // BFS - Template II
    // Never visit a node twice. Otherwise, we might get stuck in an infinite loop in graph with cycle
    // 使用HashSet<>来标记已经访问过的node，避免二次访问造成的循环
    public int BFS2(Node root, Node target) {
        //  Queue<Node> queue;  // store all nodes which are waiting to be processed
        //  Set<Node> visited;  // store all the nodes that we've visited
        //
        //  int step = 0;       // number of steps neeeded from root to current node
        //  initialize
        //  add root to queue;
        //  add root to visited;
        //  while (queue is not empty) {
        //      step = step + 1;
        //      int size = queue.size();  // 控制一层的遍历读取
        //      for (int i = 0; i < size; ++i) {
        //          Node cur = the first node in queue;
        //          return step if cur is target;
        //          for (Node next : the neighbors of cur) {  // 在添加visited时，避免出现循环，导致下一轮漏掉一些体条件 !!
        //              if (next is not in used) {  // 确保这里添加到Queue中的node是新的
        //                  add next to queue;
        //                  add next to visited;
        //              }
        //          }
        //          remove the first node from queue;
        //      }
        //  }
        return -1;
    }
}
