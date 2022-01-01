package core_algo.learn_all_problems.p4_dp.unique_path;

public class UniquePathQuestion {

    // Unique Paths
    // grid[0][0] -> grid[m - 1][n - 1]
    // The robot can only move either down or right at any point in time
    // 数学解法: Total permutations = (m+n)! / (m! * n!)
    // O(1) O(n^2) 可以去掉空间复杂度
    public static int countUniquePath(int row, int col) {
        // 测试理解：使用dp数组来纪录之前走过的累积的步数
        //         每一个位置只有两种移动选择，同时有两种的来源
        //         根据每个位置的来源，逐层进行遍历，累积到达每个位置的可能的情况数目
        int[][] dp = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 1; // 第一层和第一列到达的可能性只有一种
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[row - 1][col - 1];
    }
}
