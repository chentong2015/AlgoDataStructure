package data_structure.dfs_bfs_traverse.backtracking;

// N-Queens II 回溯算法典型案例
// Placing n queens on an n x n chessboard such that no two queens attack each other
// Given an integer n, return the number of distinct solutions to the n-queens puzzle
// 0 0 1 0    0 1 0 0
// 1 0 0 0    0 0 0 1 -> Queen能够攻击该位置的横竖列和斜方向上的所有位置
// 0 0 0 1    1 0 0 0
// 0 1 0 0    0 0 1 0 -> 给出n*n的方格中有几种不同的放置n个Queen的可能情况 ?

// 1 0 0 0
// 0 0 1 0    -> (1,2)位置对应出来的反斜对角位置为2-1+4=5, 正斜对角对应的位置是1+2=3
// 0 0 0 0
// 0 1 0 0    -> 左侧最多只能放置3个Queen
public class BacktrackingNQueens {

    private int count = 0;

    // O(n!) 递归n层，每一层中再递归(n-1)... !!
    // O(n)  额外开辟3个存储的数组，内存开销
    public int totalNQueens(int n) {
        boolean[] cols = new boolean[n];        // columns   |  列只需要占n个位置
        boolean[] diag1 = new boolean[2 * n];   // diagonals \  斜对角需要占(2n-1)个位置做判断 !
        boolean[] diag2 = new boolean[2 * n];   // diagonals /
        backtracking(0, cols, diag1, diag2, n);
        return count;
    }

    // 1. 填一个位置时，记录相关位置上的情况，做排除
    // 2. 从上往下，逐行判断，一行最多只能在一个位置放置，之后移动到下面一行，所有并不需要行上面的标记 !
    // 3. 当递归回溯判断row==n; 则说明将n个queen完整填入其中
    public void backtracking(int row, boolean[] cols, boolean[] diag1, boolean[] diag2, int n) {
        if (row == n) {
            count++;
        }
        // 在不同的row行，填充在每个列的位置，直到推导到row=n最后一层，则记成一个有效的答案 !!
        for (int col = 0; col < n; col++) {
            int id1 = col - row + n;
            int id2 = col + row;
            if (cols[col] || diag1[id1] || diag2[id2]) {
                continue;
            }

            cols[col] = true;  // 这3个位置的确定，标明了在n*n的位置中唯一确定了一个位置
            diag1[id1] = true;
            diag2[id2] = true;

            backtracking(row + 1, cols, diag1, diag2, n);

            cols[col] = false; // 在回溯回来的时候，撤回之前放置的位置造成的改变
            diag1[id1] = false;
            diag2[id2] = false;
        }
    }
}
