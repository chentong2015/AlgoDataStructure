package questions.dfs_bfs.backtracking;

public class LearnBacktracking5 {

    // Word Search
    // Given an m x n grid of characters board and a string word, return true if word exists in the grid.
    //
    // The word can be constructed from letters of sequentially adjacent cells,
    // where adjacent cells are horizontally or vertically neighboring.
    // The same letter cell may not be used more than once.
    //
    // board = [["A","B","C","E"],  word = "ABCCED" -> true
    //          ["S","F","C","S"],
    //          ["A","D","E","E"]],
    // board = [["a","a"]], word = "aaa" -> false
    //
    // 典型的回溯算法，在递归的过程中找到符合条件的特征

    // 遍历二维字符数组的每个字符，以每个字符作为起点，判断是否能找到word
    public boolean exist(char[][] board, String word) {
        for (int i=0; i < board.length; i++) {
            for (int j=0; j < board[0].length; j++) {
                if (board[i][j] == word.charAt(0)) {
                    if (findWord(board, word, 1, i, j)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    // TODO. 从一个点开始递归到最后如果没有找到则需要将修改的字符恢复
    // 在二维数组的某个点，只有四种可以移动的方向
    // 每移动一个字符之后，需要将该位置的字符设置为空，作为标记
    private boolean findWord(char[][] board, String word, int index, int row, int col) {
        if (index == word.length()) {
            return true;
        }
        char c = word.charAt(index);
        if (row < board.length - 1 && board[row + 1][col] != ' ' && board[row + 1][col] == c) {
            board[row + 1][col] = ' ';
            return findWord(board, word, index + 1, row + 1, col);
        }
        if (row > 0 && board[row - 1][col] != ' ' && board[row - 1][col] == c) {
            board[row - 1][col] = ' ';
            return findWord(board, word, index + 1, row - 1, col);
        }
        if (col < board[0].length - 1 && board[row][col + 1] != ' ' && board[row][col + 1] == c) {
            board[row][col + 1] = ' ';
            return findWord(board, word, index +1, row, col + 1);
        }
        if (col > 0 && board[row][col - 1] != ' ' && board[row][col - 1] == c) {
            board[row][col - 1] = ' ';
            return findWord(board, word, index +1, row, col - 1);
        }
        return false;
    }
}
