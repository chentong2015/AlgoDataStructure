package data_structure.greedy_dynamic_programming;

// DP动态编程: 高级实战与应用, 如何利用dp二维空间
public class DynamicProgramming3 {

    // TODO: 直接利用LCS计算一个字符串和它的反转字符串之间的最长公共子序列的数量
    //  剩下的长度差值就是要形成Palindrome所需要插入的字符数量
    // Minimum Insertion Steps to Make a String Palindrome
    // In one step you can insert any character at any index of the string
    // Return the minimum number of steps to make s palindrome
    // s = "mbdadbm" -> "mbdadbm" or "mdbabdm" -> 2
    public int minInsertions(String s) {
        String reverseStr = new StringBuilder(s).reverse().toString();
        return s.length() - longestCommonSubsequence(s, reverseStr);
    }

    // TODO: Longest Common Subsequence使用二位空间的DP数组，记录之前累积的计算结果 !!
    //   金典的CS问题，应用在数据比较(diff utility & winMerge)和Git版本比较与修订
    // https://en.m.wikipedia.org/wiki/Longest_common_subsequence_problem
    // Subsequence 子序列是按照顺序排序的一串子字符，通过删除某些字符后可以形成
    // text1="agcat", text2="gac"  完全复杂度为O(2^n * 2^m)中比较的可能
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

                // 在去掉这个公共字符的前面字符串所具有的LCS的基础上添加1
                if (str1.charAt(i) == str2.charAt(j)) {
                    dp[i + 1][j + 1] = dp[i][j] + 1;
                } else {
                    dp[i + 1][j + 1] = Math.max(dp[i][j + 1], dp[i + 1][j]);
                }
        return dp[str1.length()][str2.length()];
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
                if (i == 0) dp[i][j] = j;                                // 第一行和第列只需要补充指定字符串的长度
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
    // b        1  1  3  3     (x  b  d  b)这4个字符的最大长度来源于(b  d  b)这3个字符
    //                TODO: b和b相等，则构成的最长palindromic子序列为: 这两个字符中间的所有字符所构成的最长长度 +2
    // d           1  1  1     取3个字符(有相等则1+2，反之取前两个字符或者后两个字符组成的最大值)
    // b              1  1     取2个字符
    // a                 1     取1个字符 构成反斜线上数值
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

    // TODO: 典型的DP二维数组的加法，从开始推理到最后一个值，每一步取最优，则最后一步一定是最优解 !!
    // Maximum value of gifts
    // A gift is placed on each grid of an m*n chessboard, and each gift has a certain value (value greater than 0). 
    // You can start from the upper left corner of the board to get the gifts in the grid,
    // and move to the right or down one square at a time until you reach the lower right corner of the board
    //  [1,3,1],  ==> 1 -> 3 -> 5 -> 2 -> 1 ==> 12 最大收获的路径
    //  [1,5,1],
    //  [4,2,1]
    public int maxValueGifts(int[][] gifts) {
        int row = gifts.length;
        int col = gifts[0].length;
        int[][] dp = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = gifts[0][0];
                } else if (j == 0) {
                    dp[i][j] = dp[i - 1][j] + gifts[i][j];
                } else if (i == 0) {
                    dp[i][j] = dp[i][j - 1] + gifts[i][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]) + gifts[i][j];
                }
            }
        }
        return dp[row - 1][col - 1];
    }

    // Interleaving String
    // Find whether s3 is formed by an interleaving of s1 and s2
    // s = s1 + s2 + ... + sn & t = t1 + t2 + ... + tm  以字符交叉的顺序组成最后的字符串
    // The interleaving is s1 + t1 + s2 + t2 + s3 + t3 + ... or t1 + s1 + t2 + s2 + t3 + s3 + ...
    // 顺次从s1和s2中提取字符，拼接构成s3的字符串
    // 如果s1和s2中都可以提取同一个字符，则有两个拼接的可能 ... O(2^m+n) 结果的每一个位置可能来自S或者T
    //    '' d  b  b  c  a
    // '' T  F  F  F  F  F      构建二维数组，从左上角到右下角找到一条完整的T通路，则说明能够组成S3
    // a  T  F  F  F  F  F      每一个坐标位置是否是True，取决于前面一个位置和上面一个位置
    // a  T  T  T  T  T  F      将二维存储改为一维存储，只需要每个位置前面累积的判断 !!
    // b  F  T  T  F  T  F
    // c  F  F  T  T  T  T
    // c  F  F  F  T  F  T
    // => a a d b b c b c a c   => 总的位置 i+j-1
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s3.length() != s1.length() + s2.length()) return false;
        boolean dp[][] = new boolean[s1.length() + 1][s2.length() + 1];
        for (int i = 0; i <= s1.length(); i++) {
            for (int j = 0; j <= s2.length(); j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = true;
                } else if (i == 0) {
                    dp[i][j] = dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1); // 第一列的判断
                } else if (j == 0) {
                    dp[i][j] = dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1); // 第一行的判断
                } else {
                    dp[i][j] = (dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1)) || (dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1));
                }
            }
        }
        return dp[s1.length()][s2.length()];
    }
}
