package com.leetcode.learn_introduction.graph;

import org.w3c.dom.Node;

/**
 * "图"是在树的基础上增加了一些节点的连接线路，构成图形网络
 * 使用BFS广度优先遍历来实现对树的遍历
 */
public class LearnGraph {

    //   -> B  -> E
    // A -> C ->
    //   -> D -> F
    //             -> G
    // 1. 遍历顺序: 首先遍历root，然后将它指向的结点都检加入到队列中，作为第二层，然后添加第二层所有指向的结点...
    // 2. FIFO:    最先被压入队列的将会优先出队列

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

    // BFS - Template II
    // Never visit a node twice. Otherwise, we might get stuck in an infinite loop in graph with cycle
    // 使用HashSet<>来标记已经访问过的node，避免二次访问造成的循环
    // 1. TODO: 如果确定没有循环的路径，则不需要使用Set标记，比如树的遍历 !!
    // 2. TODO: 不需要添加一个结点多次，如果可以，考虑直接修改原始结点，代替对于结点的其他"标记" !!
    public int BFS2(Node root, Node target) {
        //  Queue<Node> queue;  // store all nodes which are waiting to be processed
        //  Set<Node> visited;  // store all the nodes that we've visited
        //  int step = 0;       // number of steps neeeded from root to current node
        //  // initialize
        //  add root to queue;
        //  add root to visited;
        //  while (queue is not empty) {
        //      step = step + 1;
        //      int size = queue.size();
        //      for (int i = 0; i < size; ++i) {
        //          Node cur = the first node in queue;
        //          return step if cur is target;
        //          for (Node next : the neighbors of cur) {
        //              if (next is not in used) {
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
