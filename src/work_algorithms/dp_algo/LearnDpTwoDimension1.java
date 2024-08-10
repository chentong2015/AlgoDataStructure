package work_algorithms.dp_algo;

public class LearnDpTwoDimension1 {

    // TODO: 和LCS同样的算法，拿到最短公共SuperSequence的长度，并非字符组合的结果
    // Shortest Common SuperSequence
    // Given two strings str1 and str2, return the shortest string
    // that has both str1 and str2 as subsequences
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
