package com.leetcode.basic_theroy_introduction.bfs_dfs_backtracking;

import org.w3c.dom.Node;

import java.util.*;

//   -> B  -> E
// A -> C ->
//   -> D -> F
//             -> G
// 1. 遍历顺序: 首先遍历root，然后将它指向的结点都检加入到队列中，作为第二层，然后添加第二层所有指向的结点...
// 2. FIFO:    最先被压入队列的将会优先出队列
public class LearnGraphBFS1 {

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
        //          for (Node next : the neighbors of cur) {  在添加visited时，避免出现循环，导致下一轮漏掉一些体条件 !!
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

    /////////////////////////////////////////////////////////////////////////////////

    // Keys and Rooms
    // Each room has a distinct number in 0, 1, 2, ..., N-1, each room may have some keys to access the next room
    // Each room i has a list of keys rooms[i], each key rooms[i][j] is an integer in [0, 1, ..., N-1]
    // A key rooms[i][j] = v opens the room with number v
    // Initially, all the rooms start locked (except for room 0)
    // You can walk back and forth between rooms freely
    // Return true if and only if you can enter every room
    // [[1],[2],[3],[]]        -> true
    //   0    1   2
    //  0 1 2 3
    // [[1,3],[3,0,1],[2],[0]] -> false 始终没有办法开启index=2的房间
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        // Test: BFS广度优先遍历，依次根据拿到的钥匙，开启后面的房间，然后获得钥匙...
        //       O(N) N是总的key的数量  O(n) n是房间的数目
        Set<Integer> openRooms = new HashSet<>();
        Queue<Integer> queue = new ArrayDeque<>();
        openRooms.add(0);
        for (int key : rooms.get(0)) {
            queue.add(key);
            openRooms.add(key);
        }
        while (!queue.isEmpty()) {
            int index = queue.poll();
            for (int key : rooms.get(index)) {
                if (!openRooms.contains(key)) {
                    queue.add(key);
                    openRooms.add(key);
                }
            }
        }
        return openRooms.size() == rooms.size(); // 所有的钥匙都拿到，所有的门都打开
    }

    /////////////////////////////////////////////////////////////////////////////////

    // TODO: BFS的研究问题 ??
    // 01 Matrix
    // Given an m x n binary matrix mat, return the distance of the nearest 0 for each cell
    // The distance between two adjacent cells is 1
    // mat = [0,0,0],   ->  [0,0,0]   统计每个cell距离0的距离步数
    //       [0,1,0],       [0,1,0]   在递归的时候，在1的数字上面进行累加，每一步加一，避免通过递归方式返回数值 !!
    //       [1,1,1]        [1,2,1]   当一个位置上面所连通的所有位置都不为0时，才+1
    public List<List<Integer>> updateMatrix(List<List<Integer>> matrix) {
        int m = matrix.size();
        int n = matrix.get(0).size();
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix.get(i).get(j) == 0) {
                    queue.offer(new int[]{i, j});
                } else {
                    matrix.get(i).set(j, Integer.MAX_VALUE);
                }
            }
        }
        // 移动的4个方向的位置坐标间隔
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            for (int[] d : dirs) {
                int r = cell[0] + d[0];
                int c = cell[1] + d[1];
                if (r < 0 || r >= m || c < 0 || c >= n) continue;
                if (matrix.get(r).get(c) <= matrix.get(cell[0]).get(cell[1]) + 1) continue;
                queue.add(new int[]{r, c});
                matrix.get(r).set(c, matrix.get(cell[0]).get(cell[1]) + 1);
            }
        }
        return matrix;
    }
}
