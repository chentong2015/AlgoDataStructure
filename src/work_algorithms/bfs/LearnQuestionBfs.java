package work_algorithms.bfs;

import java.util.*;

public class LearnQuestionBfs {

    // TODO. 从每一个点往下展开，一层一层进行查找
    // Keys and Rooms
    // Each room has a distinct number in 0, 1, 2, ..., N-1,
    // each room may have some keys to access the next room
    // Each room i has a list of keys rooms[i], each key rooms[i][j] is an integer in [0, 1, ..., N-1]
    // A key rooms[i][j] = v opens the room with number v
    // Initially, all the rooms start locked (except for room 0)
    // You can walk back and forth between rooms freely
    // Return true if and only if you can enter every room
    // [[1],[2],[3],[]]        -> true
    // [[1,3],[3,0,1],[2],[0]] -> false 始终没有办法开启index=2的房间
    //
    // BFS广度优先遍历: 依次根据拿到的钥匙，开启后面的房间，然后获得钥匙...
    // O(N) N是总的key的数量  O(n) n是房间的数目
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        Set<Integer> openRooms = new HashSet<>();
        Queue<Integer> queue = new ArrayDeque<>();
        openRooms.add(0);
        for (int key : rooms.get(0)) {
            queue.add(key);
            openRooms.add(key);
        }

        // TODO. 通过queue队列的非空来实现层级遍历
        while (!queue.isEmpty()) {
            int index = queue.poll();
            for (int key : rooms.get(index)) {
                // 只有是新的房间才需要添加并遍历
                if (!openRooms.contains(key)) {
                    queue.add(key);
                    openRooms.add(key);
                }
            }
        }
        // 所有的钥匙都拿到，所有的门都打开
        return openRooms.size() == rooms.size();
    }

    // TODO: BFS特殊运用，借助Queue队列广度遍历到所有的值，统计从0移动到非0位置的步数，逐次累加
    // 01 Matrix 统计每个cell距离0的距离步数
    // Given an m x n binary matrix mat, return the distance of the nearest 0 for each cell
    // The distance between two adjacent cells is 1
    // mat = [0,0,0], ->  [0,  0,   0]    ->  [0,0,0]
    //       [0,1,0],     [0,  max, 0]        [0,1,0]
    //       [1,1,1]      [max,max, max]      [1,2,1]
    public List<List<Integer>> updateMatrix(List<List<Integer>> matrix) {
        int m = matrix.size();
        int n = matrix.get(0).size();
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix.get(i).get(j) == 0) {
                    queue.add(new int[]{i, j});
                } else {
                    matrix.get(i).set(j, Integer.MAX_VALUE);
                }
            }
        }

        // 对于队列中的位置(值为0)分别移动4个方向，找到提前标记的MAX_VALUE
        // 将其位置的值在前一个Cell值的基础上+1 (到达的步数会累积起来)
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            for (int[] direction : directions) {
                int row = cell[0] + direction[0];
                int col = cell[1] + direction[1];
                if (row < 0 || row >= m || col < 0 || col >= n) continue;

                // 只有当移动后的位置上的值比原来基础上的值+1大的时候，才添加到队列中，并在原来位置的基础上+1步数
                int stepCellValueBefore = matrix.get(cell[0]).get(cell[1]);
                if (matrix.get(row).get(col) > stepCellValueBefore + 1) {
                    queue.add(new int[]{row, col});
                    matrix.get(row).set(col, stepCellValueBefore + 1);
                }
            }
        }
        return matrix;
    }
}
