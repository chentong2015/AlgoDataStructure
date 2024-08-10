package work_algorithms.dp_algo.palindromic;

// TODO: DP二维空间金典算法: Longest Palindromic Subsequence
// Subsequence是非连续的字符序列，不能使用滑动窗口算法
public class LongestPalindromicSubsequence {

    // Longest Palindromic Subsequence
    // Given a string s, find the longest palindromic subsequence's length in s
    // s = "axbdba" -> "abdba" -> 5
    //    a  x  b  d  b  a
    // a  1  1  1  1  3  5 a和a相等，则累加在(axbdb)和(abdbx)比较结果上
    // x     1  1  1  3  3
    // b        1  1  3  3
    // d           1  1  1
    // b              1  1
    // a                 1 循环起点
    public int longestPalindromeSubsequence(String s) {
        int[][] dp = new int[s.length()][s.length()];
        for (int i = s.length() - 1; i >= 0; i--) {
            dp[i][i] = 1;
            for (int j = i + 1; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    // 如果相等，则累加[左下角]位置值
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    // 判断之前计算过的[左边]或者[下边]的最大值
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[0][s.length() - 1];
    }
}
