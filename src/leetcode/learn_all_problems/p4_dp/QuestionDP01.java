package leetcode.learn_all_problems.p4_dp;

public class QuestionDP01 {

    // TODO: 如何想到DP算法，前面的提取结果后面的选择有影响 + 二维划分分布的问题 + 找整个的最大值条件数目
    // Ones and Zeroes
    // You are given an array of binary strings strs and two integers m and n
    // Return the size of the "largest subset" of strs such that there are "at most" m 0's and n 1's in the subset
    // A set x is a subset of a set y if all elements of x are also elements of y
    // strs = ["10","0001","111001","1","0"], m = 5/0, n = 3/1  -> {"10", "0001", "1", "0"} 最大的组合子集，可以取到4个Element
    // strs = ["10","0","1"],                 m = 1/0, n = 1/1  -> {"0", "1"}               要取最多的子集元素
    // strs = ["111","1000","1000","1000"]    m = 9/0, n = 1/1  -> {"1000","1000","1000"}
    // 每取到一个str都需要统计0和1个数目，在不超过m, n个个数的前提下，选最多的str  => 暴力破解，两轮遍历  O(n*n) 找出最大可能的组成形式
    // 如何提取最佳的组合方式，让选出来的字元素最多 ? 从长度小的元素开始选          => 自定义排序的比较器 O(nlog(n))
    // 排序之后也不一定是从长度小的开始选，一定字符占满之后则无法再添加 !!
    public int findMaxForm(String[] strs, int m, int n) {
        int[][] dp = new int[m + 1][n + 1];
        for (String str : strs) {
            int[] count = countZeroOne(str);
            for (int i = m; i >= count[0]; i--) {      // 横坐标为0的统计位置
                for (int j = n; j >= count[1]; j--) {  // 纵坐标为1的统计位置
                    int recordedValue = dp[i - count[0]][j - count[1]]; // 从历史累积的数据中提取判断过程中的最大值的组合
                    dp[i][j] = Math.max(recordedValue + 1, dp[i][j]);   // 从前面纪录的位置+str统计的0和1，到组成i，j位置
                }
            }
        }
        return dp[m][n];
    }

    public int[] countZeroOne(String str) {
        int[] res = new int[2];
        for (int index = 0; index < str.length(); index++) {
            res[str.charAt(index) - '0']++;
        }
        return res;
    }
}
