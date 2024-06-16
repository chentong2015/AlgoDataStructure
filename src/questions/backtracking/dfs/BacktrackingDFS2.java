package questions.backtracking.dfs;

public class BacktrackingDFS2 {

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
    // board = [["a","a"]],         word = "aaa" -> false
    // board = [["A","B","C","E"],  word = "ABCE SEE EFS" ->
    //          ["S","F","E","S"],
    //          ["A","D","E","E"]]
    //
    // 遍历二维字符数组的每个字符，以每个字符作为起点，判断是否能找到word
    // O(n*m)  O(n)  二维数组每个index位置都可能遍历word长度的次数
    public boolean exist(char[][] board, String word) {
        int n = board.length;
        int m = board[0].length;
        boolean[][] visited = new boolean[n][m];

        for(int row = 0; row < n; row++){
            for(int col = 0; col < m; col++){
                if(board[row][col] == word.charAt(0)){
                    if(dfs(row, col, word, 0, visited, board)){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    // 在二维数组的某个点，只有四种可以移动的方向
    // 每移动一个字符之后，需要标记已经遍历过的位置
    public boolean dfs(int row, int col, String word, int lvl, boolean[][] visited, char[][] board){
        int n = board.length;
        int m = board[0].length;

        if(lvl == word.length()){
            return true;
        }
        // 直接在row和col上判断边界
        if(row < 0 || row >= n || col < 0 || col >= m
                || visited[row][col]
                || board[row][col] != word.charAt(lvl)){
            return false;
        }
        visited[row][col] = true;

        // 四个方法的移动和判断条件
        boolean didFindNextCharacter = dfs(row + 1, col, word, lvl + 1, visited, board)||
                        dfs(row - 1, col, word, lvl + 1, visited, board)||
                        dfs(row, col + 1, word, lvl + 1, visited, board)||
                        dfs(row, col - 1, word, lvl + 1, visited, board);

        visited[row][col] = false;  // Backtrack 回溯的核心，恢复数据
        return didFindNextCharacter;
    }
}
