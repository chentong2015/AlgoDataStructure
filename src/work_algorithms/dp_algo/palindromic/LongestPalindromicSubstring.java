package work_algorithms.dp_algo.palindromic;

// TODO. 问题的本质: 如何利用之前已经判断过的回文子字符串来判断下一个 ?
// 1. 使用暴力破解：Time很差，Space空间复杂度很好
// 2. 使用DP二维数组：Time较好，Space较差
public class LongestPalindromicSubstring {

    // Longest Palindromic Substring
    // Given a string s, return the longest palindromic substring in s.
    // s = "b a b a d" -> "bab"
    // s = "c b b d" -> "bb"
    public String longestPalindrome(String s) {
        int n = s.length();
        int max = 0;
        int start = -1;
        // DP数组存储判断的结果
        boolean[][] dp = new boolean[n][n];
        for(int i = n - 1; i >= 0; i--){
            for(int j = i; j < n; j++){
                int size = j - i + 1;
                if(size < 3) {
                    dp[i][j] = s.charAt(i) == s.charAt(j);
                } else {
                    dp[i][j] = dp[i+1][j-1] && s.charAt(i) == s.charAt(j);
                }

                if(dp[i][j] && size > max) {
                    max = size;
                    start = i;
                }
            }
        }
        return s.substring(start, start + max);
    }
}
