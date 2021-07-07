package com.leetcode.basic_theroy_introduction.greedy_dp;

// DP动态编程 高级实战与应用, 如何利用dp二维空间
public class DynamicProgramming3 {

    // TODO: Longest Common Subsequence 使用二位空间的DP数组，记录之前累积的计算结果 !!
    //       金典的CS问题，应用在数据比较(diff utility & winMerge)和Git版本比较与修订
    // https://en.m.wikipedia.org/wiki/Longest_common_subsequence_problem
    // Subsequence 子序列是按照顺序排序的一串子字符，通过删除某些字符后可以形成
    // text1="agcat", text2="gca"  完全复杂度为O(2^n * 2^m)中比较的可能
    //     Ø	A	G	C	A	T  在确定好字符后，两个字符串的比较是线性的
    // Ø   Ø	Ø	Ø	Ø	Ø   Ø
    // G   Ø	0   1   1   1   1
    // A   Ø    1   1   1   2   2
    // C   Ø	1   1   2   2   2
    // Three sequences,(AC),(GC),and(GA) 最终罗列所有的最长子字符串的组合
    public int longestCommonSubsequence(String str1, String str2) {
        int[][] dp = new int[str1.length() + 1][str2.length() + 1];
        for (int i = 0; i < str1.length(); ++i)
            for (int j = 0; j < str2.length(); ++j)
                if (str1.charAt(i) == str2.charAt(j)) {
                    dp[i + 1][j + 1] = dp[i][j] + 1; // 在去掉这个公共字符的前面字符串所具有的LCS的基础上添加1
                } else {
                    dp[i + 1][j + 1] = Math.max(dp[i][j + 1], dp[i + 1][j]);
                }
        return dp[str1.length()][str2.length()];
    }

    // TODO: 直接利用LCS计算一个字符串和它的反转字符串之间的最长公共子序列的数量
    //       剩下的长度差值就是要形成Palindrome所需要插入的字符数量
    // Minimum Insertion Steps to Make a String Palindrome
    // In one step you can insert any character at any index of the string 每一步可以在字符串的任何位置插入一个字符
    // Return the minimum number of steps to make s palindrome
    // s = "mbdadbm" -> "mbdadbm" or "mdbabdm" -> 2
    public int minInsertions(String s) {
        String reverseStr = new StringBuilder(s).reverse().toString();
        return s.length() - longestCommonSubsequence(s, reverseStr);
    }

    // TODO: 和LCS同样的算法，拿到最短公共SuperSequence的长度，并非字符组合的结果
    // Shortest Common SuperSequence
    // Given two strings str1 and str2, return the shortest string that has both str1 and str2 as subsequences
    // If multiple answers exist, you may return any of them
    // 1<= n,m <= 1000 其中都是小写字母
    // str1 = "abac", str2 = "cab"        -> "cabac" 如何从所有的结果中取出一个有效值 !!
    //          a       b     a       c
    //    0     1a     2ab   3aba   4abac
    // c  1c    ac/ca  cab   caba   abac
    // a  2ca   ca     cab   caba   cabac
    // b  3cab  cab    cab   caba   cabac  保留每一个位置的计算长度值, 从末尾往前面推导
    private int[][] getLengthOfShortestCommonSuperSequence(String str1, String str2) {
        int m = str1.length();
        int n = str2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0) dp[i][j] = j;                                // 第一行和第一例只需要补充指定字符串的长度
                else if (j == 0) dp[i][j] = i;
                else if (str1.charAt(i - 1) == str2.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1] + 1;                     // 如果相等，则在去掉公共字符的基础上补充一个共同字符
                else
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + 1; // 选择最小的合成长度上面添加一
            }
        }
        return dp;
    }

    // 根据两个原始的字符串来组成指定长度的Super Sequence
    public String shortestCommonSuperSequence(String str1, String str2) {
        int i = str1.length();
        int j = str2.length();
        int[][] dp = getLengthOfShortestCommonSuperSequence(str1, str2);
        int position = dp[i][j];
        char[] arr = new char[position];
        while (i > 0 && j > 0) {
            if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                arr[--position] = str1.charAt(i - 1);
                i--;
                j--;
            } else if (dp[i - 1][j] < dp[i][j - 1]) {  // 如果dp[i - 1][j]位置的值更小，则说明使用str1的字符会使得路径更加的短 !!
                arr[--position] = str1.charAt(i - 1);
                i--;
            } else {
                arr[--position] = str2.charAt(j - 1);
                j--;
            }
        }
        while (i > 0) {
            arr[--position] = str1.charAt(i - 1);
            i--;
        }
        while (j > 0) {
            arr[--position] = str2.charAt(j - 1);
            j--;
        }
        return new String(arr);
    }

    // TODO: DP二维空间金典算法，只利用一半的空间位置，从每次取一个长度字符到取全部的字符
    // Longest Palindromic Subsequence
    // Given a string s, find the longest palindromic subsequence's length in s
    // s = "axbdba" -> "abdba" -> 5
    //    a  x  b  d  b  a
    // a  1  1  1  1  3  3+2=5 最大长度的结果，逆向推导得出最后的字符串
    // x     1  1  1  3  3
    // b        1  1  3  3  (x  b  d  b)这4个字符的最大长度来源于(b  d  b)这3个字符
    // d           1  1  1  取3个字符(有相等则1+2，反之取前两个字符或者后两个字符组成的最大值)
    // b              1  1  取2个字符
    // a                 1  取1个字符 构成反斜线上数值
    public int longestPalindromeSubsequence(String s) {
        int[][] dp = new int[s.length()][s.length()];
        for (int i = s.length() - 1; i >= 0; i--) {
            dp[i][i] = 1;
            for (int j = i + 1; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j)) {
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
