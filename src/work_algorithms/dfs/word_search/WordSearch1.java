package work_algorithms.dfs.word_search;

public class WordSearch1 {

    // Word Search
    // Given an m x n grid of characters board and a string word,
    // return true if word exists in the grid.
    // The word can be constructed from letters of sequentially adjacent cells,
    // where adjacent cells are horizontally or vertically neighboring.
    // The same letter cell may not be used more than once.
    //
    // board = [["a","a"]],         word = "aaa" -> false
    // board = [["A","B","C","E"],  word = "ABCCED" -> true
    //          ["S","F","C","S"],
    //          ["A","D","E","E"]],
    //
    // TODO. 注意DFS算法时间复杂度的归纳计算
    // O(n*m*S) 从每个index起点位置，都需要经过一个4节点的递归树
    //   S=(4 + 4*3 + 4*3*3 + 4*3*3*3 + ... + 4*3^(n-1)) < n*m
    //     - 递归的数目不可能超过所有index位置的总和
    //     - 树的高度与word字符长度有关
    //     - 由于标记过遍历的Index位置，因此每个节点只有三种可能往下递归
    //
    // O(n*m)   需要开辟二维数组来存储遍历过的数据
    public boolean exist(char[][] board, String word) {
        int n = board.length;
        int m = board[0].length;
        if (m*n < word.length()) {
            return false;
        }

        boolean[][] visited = new boolean[n][m];
        for(int row = 0; row < n; row++){
            for(int col = 0; col < m; col++){
                if(board[row][col] != word.charAt(0)){
                    continue;
                }
                if(dfs(row, col, word, 0, visited, board)){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean dfs(int row, int col, String word, int position, boolean[][] visited, char[][] board){
        // 判断临界条件，从递归层级中返回
        if(position == word.length()){
            return true;
        }

        // 直接在row和col上判断边界
        if(row < 0 || row >= board.length || col < 0 || col >= board[0].length){
            return false;
        }
        if(visited[row][col] || board[row][col] != word.charAt(position)) {
            return false;
        }

        // 从某个点往四种移动，然后执行回溯
        visited[row][col] = true;
        boolean isFound = dfs(row + 1, col, word, position + 1, visited, board)
                || dfs(row - 1, col, word, position + 1, visited, board)
                || dfs(row, col + 1, word, position + 1, visited, board)
                || dfs(row, col - 1, word, position + 1, visited, board);
        visited[row][col] = false;

        // 每个节点递归完成后都需要返回结果
        return isFound;
    }
}
