package com.leetcode.learn_introduction.stack;

// TODO: DFS深度解析 ==> 可以使用等效的BFS来实现
// 使用递归算法代替DFS深度优先遍历的展开，不需要使用stack数据结构
// 同时对于遍历过的位置，更新值作为"标记"
public class LearnDFS {

    // Number of Islands
    // Input: grid = [         Output: 3
    //  ["1","1","0","0","0"],
    //  ["1","1","0","0","0"],
    //  ["0","0","1","0","0"],
    //  ["0","0","0","1","1"]
    // ]
    private int row;
    private int col;

    public int numIslands(char[][] grid) {
        int count = 0;
        row = grid.length;
        if (row == 0) return 0;
        col = grid[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++)
                if (grid[i][j] == '1') {
                    DFSMarking(grid, i, j);
                    count++;
                }
        }
        return count;
    }

    // grid[i][j] = '0'; 如果这里的值没有被修改和标记，则需要使用Hash Table来存储并做好标记 !!
    private void DFSMarking(char[][] grid, int i, int j) {
        if (i < 0 || j < 0 || i >= row || j >= col || grid[i][j] != '1') return;
        grid[i][j] = '0';
        DFSMarking(grid, i + 1, j);
        DFSMarking(grid, i - 1, j);
        DFSMarking(grid, i, j + 1);
        DFSMarking(grid, i, j - 1);
    }
}
