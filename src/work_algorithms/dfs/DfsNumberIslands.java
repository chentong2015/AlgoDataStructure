package work_algorithms.dfs;

public class DfsNumberIslands {

    // Number of Islands
    // Input: grid = [
    //  ["1","1","0","0","0"],
    //  ["1","1","0","0","0"],
    //  ["0","0","1","0","0"],
    //  ["0","0","0","1","1"]
    // ]
    // Output: 3
    //
    // O(n*m) 内层DFSMarking递归算法最多更新所有的cell一次
    // O(1)   空间复杂度降到最低
    public int numIslands(char[][] grid) {
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    searchIslandDFS(grid, i, j);
                    count++;
                }
            }
        }
        return count;
    }

    // 从一个点开始展开遍历完一个岛屿后，累加统计
    private void searchIslandDFS(char[][] grid, int i, int j) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length) {
            return;
        }
        // 判断grid[i][j] != '1', 避免无限循环导致的栈溢出
        if (grid[i][j] != '1') {
            return;
        }

        // 修改二维数组的元素值来避免DFS无限循环
        grid[i][j] = '0';

        // 从一个点延申到四个不同的方向进行Deap Search
        searchIslandDFS(grid, i + 1, j);
        searchIslandDFS(grid, i - 1, j);
        searchIslandDFS(grid, i, j + 1);
        searchIslandDFS(grid, i, j - 1);
    }
}
