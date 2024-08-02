package questions.recursion.dfs;

// Number of Islands       以DFS深度优先遍历的方式对一个点进行展开
// Input: grid = [         Output: 3
//  ["1","1","0","0","0"],
//  ["1","1","0","0","0"],
//  ["0","0","1","0","0"],
//  ["0","0","0","1","1"]
// ]
public class NumberIslandsDFS {

    // O(n*m) 外层嵌套循环确定复杂度，内层DFSMarking递归算法最多更新所有的cell一次，时间复杂度是累加
    // O(1)   空间复杂度降到最低
    public int numIslands(char[][] grid) {
        // 检测特殊条件 !!
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                // 从一个点开始展开遍历完一个岛屿后，算作一次统计
                if (grid[i][j] == '1') {
                    searchIslandDFS(grid, i, j);
                    count++;
                }
            }
        }
        return count;
    }

    // 判断grid[i][j] != '1', 避免无限循环导致的栈溢出
    // 如果不满足深度搜寻的条件，则退出当前Node
    private void searchIslandDFS(char[][] grid, int i, int j) {
        // 不处理边界以外的左边
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] != '1') {
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
