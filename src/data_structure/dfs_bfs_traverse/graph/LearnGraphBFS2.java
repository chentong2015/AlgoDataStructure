package data_structure.dfs_bfs_traverse.graph;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;

// Number of Islands
// Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water)
// return the number of islands
// An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically
// Assume all four edges of the grid are all surrounded by water
// Input: grid = [
//  ["1","1","0","0","0"], 一共形成了3个岛屿
//  ["1","1","0","0","0"],
//  ["0","0","1","0","0"],
//  ["0","0","0","1","1"]
// ]
// 测试理解：将每个岛独立出来，看成是一个图，从图的root位置出发进行BFS遍历，
//  如果这个树的周围已经是边界，或者都是由水包围，则是一个岛
//  遍历的时，如果遇到1，这将这个1进行展开遍历，直到完全遍历
//  O(n*m) 确保每一个位置值被访问一次，不管是0还是1  ==> 每一个位置都需要唯一的定位 !!
//  O(n*m) 考虑到使用HashSet<>的复杂度           ==> 同一个陆地只能被遍历一次，需要进行记录 !!
public class LearnGraphBFS2 {

    private int count;
    private int height;
    private int width;
    private char[][] grid;
    private Queue<Integer> queue = new ArrayDeque<>();
    private HashSet<Integer> set = new HashSet<>();

    public int numIslands(char[][] grid) {
        this.grid = grid;
        height = grid.length;
        width = grid[0].length;
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                if (isValidLand(row, col)) traverseOneCompleteIsland(row, col);
            }
        }
        return count;
    }

    private boolean isValidLand(int row, int col) {
        int position = getIndexPosition(row, col);
        return !set.contains(position) && grid[row][col] == '1';
    }

    // 每个位置唯一对应一个指定的position值
    private int getIndexPosition(int row, int col) {
        return row * width + col;
    }

    private void traverseOneCompleteIsland(int row, int col) {
        addRelatedIsland(row, col);
        while (!queue.isEmpty()) {
            int i = queue.peek() / width;
            int j = queue.peek() % width;
            if (i >= 1) addRelatedIsland(i - 1, j);
            if (j >= 1) addRelatedIsland(i, j - 1);
            if (i < height - 1) addRelatedIsland(i + 1, j);
            if (j < width - 1) addRelatedIsland(i, j + 1);
            queue.poll();
        }
        count++;
    }

    private void addRelatedIsland(int row, int col) {
        if (isValidLand(row, col)) {
            int position = getIndexPosition(row, col);
            queue.add(position);
            set.add(position);
        }
    }
}
