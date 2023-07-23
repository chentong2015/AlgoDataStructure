package data_structure.greedy_dynamic_programming;

import java.util.Arrays;

// 动态编程解题方法论:
// 1. Start with the recursive backtracking solution
//    Divide and Conquer 使用递归拆分问题，归并算法
//    先将复杂问题差发成若干个小问题SubProblems, 独立解决问题, 最后汇总解决原始问题
// 2. Optimize by using a memoization table
//    开辟存储空间，记录迭代中每一步的结果
// 3. Top-Down设计, 需要用到递归
//    Bottom-Up设计, 需要用到dp数组(等效递归方式造成的空间复杂度)
// 4. Apply final tricks to reduce space complexity
//    追踪记录结果信息，用基础的几个变量替代所开辟的空间复杂度
public class DynamicProgramming1 {

    // TODO: DP Top-Down 自顶向下的设计
    //  展开形式为任意Tree树，最小perfect square的数目，也就是树的最低深度Level
    // Perfect Squares
    // Given an integer n, return the "least number" of perfect square numbers that sum to n
    // A perfect square is an integer that is the square of an integer: 1 4 9 16 25 ...
    // Input: n = 12 -> 12 = 4 + 4 + 4 -> 3 最少需要3个perfect square数值组成
    //            12
    //      9           4        1   数值展开，记录增加的层级，同时对应不同的差值，在继续迭代(12-9), (12-4), (12-1)
    //  9   4    1     4  1      1   下层展开，计算(12-9-9)...(12-4-4)...
    // ..  4 1   1    4 1  1     1
    //
    // O(n*m) m是所有有效的perfect square的数目 O(n)
    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);
        return dpCount(n, dp);
    }

    private int dpCount(int remainder, int[] dp) {
        if (remainder == 0) {
            return 0;
        }
        if (dp[remainder] != -1) {
            return dp[remainder];
        }
        int level = Integer.MAX_VALUE;
        for (int num = 1; num * num <= remainder; num++) {
            remainder -= num * num;
            int currentLevel = 1 + dpCount(remainder, dp);
            level = Math.min(level, currentLevel);
        }
        // 记录这个remainder拆分出来的最小level层次是多少，以便在递归层次的内部使用
        dp[remainder] = level;
        return level;
    }

    // TODO: Bottom-Up 自底向上设计(前面的值已经在遍历的过程中计算过)
    //  判断每一个位置值它的前面最少由多少的Perfect Squares数组成而来
    // O(n * n^0.5) 内存循环只关注它平方根的下面的数 O(n)
    public int numSquares2(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);

        // 这里设置为0，是为了(i - j * j)刚好整除的时候，取的基础值
        dp[0] = 0;
        // 开始遍历的时候，dp[1]的位置会讲计算成1
        for (int i = 1; i <= n; i++) {
            // 每一个位置，判断它前面是通过多少个有效的平方数组成而来
            for (int j = 1; j * j <= i; j++) {
                // 看dp[i - j * j]位置是否有已经计算的拆分值，然后再累加一个j平方数构成i值
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }
        // 返回最小组成的可能
        return dp[n];
    }
}
