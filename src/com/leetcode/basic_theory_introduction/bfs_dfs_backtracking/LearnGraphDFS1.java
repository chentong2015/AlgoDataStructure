package com.leetcode.basic_theory_introduction.bfs_dfs_backtracking;

import org.w3c.dom.Node;

import java.util.Set;

// Find a path from the root node A to the target node G by DFS
//   -> B  ->  E
// A -> C  ->  F -> G
//   -> D
// 先将root结点入栈，然后选择它的其中一个子结点往下遍历，直到叶子结点
// 如果前面的路径中没有找到，则从叶子节点退回到上一层，然后找它的后续节点
// 在回退(Backtracking回溯算法)的过程中，遍历了每一条完整的通路
public class LearnGraphDFS1 {

    // TODO: 递归方式方便实现，递归的层级过大会造成stack overflow栈溢出 !!
    // DFS Template I - Recursion
    // Don't have to use any stacks when we implement DFS recursively
    // 递归DFS找到指定的节点Node / 找指定的路径通路，同时标记已经visited的节点
    public boolean DFS(Node cur, Node target, Set<Node> visited) {
        //  return true if cur is target;
        //  for (next : each neighbor of cur) {
        //      if (next is not in visited) {
        //          add next to visited;
        //          return true if DFS(next, target, visited) == true;
        //      }
        //  }
        return false;
    }

    // Number of Islands       以DFS深度优先遍历的方式对一个点进行展开
    // Input: grid = [         Output: 3
    //  ["1","1","0","0","0"],
    //  ["1","1","0","0","0"],
    //  ["0","0","1","0","0"],
    //  ["0","0","0","1","1"]
    // ]
    // O(n*m) 外层嵌套循环确定复杂度，内存DFSMarking递归算法最多只能更新所有的cell一次，因此时间复杂度是累加  O(1)
    public int numIslands(char[][] grid) {
        // 检测特殊条件 !!
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++)
                if (grid[i][j] == '1') {
                    DFSMarking(grid, i, j);
                    count++;
                }
        }
        return count;
    }

    // TODO: 通过修改二维数组的元素值来避免使用Hash Table记录标记, 避免DFS中的循环
    private void DFSMarking(char[][] grid, int i, int j) {
        int row = grid.length;
        int col = grid[0].length;
        // 判断grid[i][j] != '1', 避免无限循环导致的栈溢出
        if (i < 0 || j < 0 || i >= row || j >= col || grid[i][j] != '1') return;
        grid[i][j] = '0';
        DFSMarking(grid, i + 1, j);
        DFSMarking(grid, i - 1, j);
        DFSMarking(grid, i, j + 1);
        DFSMarking(grid, i, j - 1);
    }

    // Flood Fill
    // An image is represented by an m x n integer grid image, image[i][j] represents the pixel value of the image
    // You are also given three integers sr, sc, and newColor
    // Starting pixel, plus any pixels connected 4-directionally to the starting pixel of the same color as the starting pixel
    // image = [1,1,1],  sr = 1, sc = 1   ->  Output: [2,2,2]
    //         [1,1,0],  newColor = 2                 [2,2,0]
    //         [1,0,1]                                [2,0,1]
    // 从指定的像素位置点出发，将所有相连的同种像素颜色的位置点，全部设置成新的颜色
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        // Test: 从指定的位置，沿着四个方向展开，形成DFS深度优先遍历
        //       可以使用Stack栈来替换递归法，空间复杂度基本一致
        //       1. stack存储4个的方向的位置(坐标) Stack<int[]> stack;
        //       2. 同时使用set来记录已经存储过的数据 !!
        resetColor(image, sr, sc, image[sr][sc], newColor);
        return image;
    }

    // O(n*m) O(n*m) 最差的情况是所有的位置都会遍历到，递归调用n*m次的方法的，设置全部位置的值
    private void resetColor(int[][] image, int row, int col, int sourceColor, int newColor) {
        if (row >= image.length || row < 0 || col >= image[0].length || col < 0) return;
        // 检测设置颜色的条件，避免递归造成的栈溢出的情况 !!
        if (image[row][col] != sourceColor || image[row][col] == newColor) return;
        image[row][col] = newColor;
        resetColor(image, row - 1, col, sourceColor, newColor);
        resetColor(image, row, col - 1, sourceColor, newColor);
        resetColor(image, row + 1, col, sourceColor, newColor);
        resetColor(image, row, col + 1, sourceColor, newColor);
    }
}
