package questions.dfs_bfs;

import org.w3c.dom.Node;

import java.util.Set;

// Find a path from the root node A to the target node G by DFS
//   -> B  ->  E
// A -> C  ->  F -> G
//   -> D
// 先将root结点入栈，然后选择它的其中一个子结点往下遍历，直到叶子结点
// 如果前面的路径中没有找到，则从叶子节点"回退"到上一层，然后找它的后续节点
// 在回退(Backtracking回溯算法)的过程中，遍历了每一条完整的通路
public class TemplateDFS {

    // TODO: 递归方式方便实现，递归的层级过大会造成stack overflow栈溢出 !!
    // DFS Template I - Recursion
    // Don't have to use any stacks when we implement DFS recursively
    // 递归DFS找到指定的节点Node / 找指定的路径通路，同时标记已经visited的节点
    public boolean DFS(Node cur, Node target, Set<Node> visited) {
        //  if cur is target {
        //     return true;
        //  }
        //  for (next : each neighbor of cur) {
        //      if (next is not in visited) {
        //          add next to visited;
        //          if DFS(next, target, visited) == true {
        //             return true;
        //          }
        //      }
        //  }
        return false;
    }

    // TODO: 模板二，显式使用stack栈数据结构来实现DFS深度优先遍历
    //       应用场景：二叉树的前序，中序，后续遍历
    // DFS - Template II
    boolean DFS(int root, int target) {
        // Set<Node> visited;
        // Stack<Node> stack;
        // add root to stack;
        // while (stack is not empty) {
        //     Node cur = the top element in stack;     peek()
        //     remove the cur from the stack;           pop();
        //     return true if cur is target;
        //     for (Node next : the neighbors of cur) { 查找相邻的node结点
        //         if (next is not in visited) {
        //             add next to visited;
        //             add next to stack;
        //         }
        //     }
        // }
        return false;
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
