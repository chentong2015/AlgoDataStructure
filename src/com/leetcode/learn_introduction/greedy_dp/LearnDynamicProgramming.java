package com.leetcode.learn_introduction.greedy_dp;

import java.util.Arrays;

/**
 * 动态编程(动态规划):
 * 1. Start with the recursive backtracking solution 使用递归拆分问题，递归解决
 * .    Divide and Conquer 分而治之 + 归并算法
 * .    先将复杂问题差发成若干个小问题SubProblems
 * .    重复且独立的解决每个小问题，最后再汇总结果解决原始问题
 * 2. Optimize by using a memoization table   开辟存储空间，记录迭代中每一步的结果
 * 3. Top-Down & Bottom-Up                    两种设计原则
 * 4. Apply final tricks to reduce complexity 追踪记录结果信息，判断关键点，减低时间空间复杂度
 */
public class LearnDynamicProgramming {

    // TODO: DP Top-Down 自定向下的设计
    // Coin Change 只用最少的零钱数目来凑出指定的值, 假设每种零钱的数目是足够的
    // An integer array coins representing coins, an integer amount representing a total amount of money
    // Return the fewest number of coins that you need to make up that amount
    // coins = {2, 5}       target = 6   ->  Answer: -1          Expected: 3 (2)
    // coins = {1, 3, 4, 5} target = 7   ->  Answer: 3 (5, 1, 1) Expected: 2 (3, 4)
    public int coinChange(int[] coins, int amount) {
        // 正确理解：1. Dynamic programming(Top down) 所有可能最终构成一个递归树, 答案是这个递归树的最低高度层级
        //            O(S*n) S是amount, n是coin的种类  O(S) 需要一个额外的数组来存储中间转换的剩余数
        if (amount < 1) return 0;
        int[] counts = new int[amount];
        return coinChange(coins, amount, counts);
    }

    //                 6 {1,2,3}
    //             (5   4   3)
    //    (4 3 2)   (3 2 1) (2 1 0)
    // ...                       计算到0的时候返回，完全展开含有多子结点的树
    private int coinChange(int[] coins, int remainder, int[] count) {
        if (remainder < 0) return -1;
        if (remainder == 0) return 0;
        // 如果某一个位置已经是最优的零钱分配，则直接返回它分配出来的最小种类的数目
        if (count[remainder - 1] != 0) return count[remainder - 1];

        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            // 判断分配不同零钱之后的结果(分配出来的最少零钱的种类数目)
            int fewestNumber = coinChange(coins, remainder - coin, count);
            if (0 <= fewestNumber && fewestNumber < min) {
                min = fewestNumber + 1;
            }
        }
        count[remainder - 1] = (min == Integer.MAX_VALUE) ? -1 : min;
        return count[remainder - 1];
    }

    // TODO: DP Bottom Up 自底向上的设计
    // F(3) = min{F(3−c1),F(3−c2),F(3−c3)}+1
    //      = min{F(3−1),F(3−2),F(3−3)}+1
    //      = min{F(2),F(1),F(0)}+1
    //      = min{1,1,0}+1
    //      = 1
    public int coinChange2(int[] coins, int amount) {
        // O(S*n) O(S)
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1); // 设置一个最大值，用于最小值的判断
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                // 判断i位置数目能否被coins中coin划分，如果可以，划分出来的结果又是否能够被划分(利用之前的结果)
                int coin = coins[j];
                if (coin <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1); // dp[1] = 1 再依次往后推
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }

    // TODO: DP Bottom Up 自底向上的设计
    // Perfect Squares
    // Given an integer n, return the "least number" of perfect square numbers that sum to n
    // A perfect square is an integer that is the square of an integer: 1 4 9 16 25 ...
    // Input: n = 12 -> 12 = 4 + 4 + 4 -> 3 最少需要3个perfect square数值组成
    //            12
    //      9           4        1   数值展开，记录增加的层级，同时对应不同的差值，在继续迭代(12-9), (12-4), (12-1)
    //  9   4    1     4  1      1   下层展开，计算(12-9-9)...(12-4-4)...
    // ..  4 1   1    4 1  1     1
    public int numSquares(int n) {
        // 测试理解：1. 展开的形式是一个任意的tree树，最小perfect square的数目，也就是树的最低深度 !!
        //           O(n*m) m是所有有效的perfect square的数目 O(n)
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);
        return dpCount(n, dp);
    }

    private int dpCount(int remainder, int[] dp) {
        if (remainder == 0) return 0;                         // 说明上一层的(n-index*index)刚好完全
        if (dp[remainder] != -1) return dp[remainder];        // 如果指定的数值已经推导出结果，则直接返回，避免循环
        int min = Integer.MAX_VALUE;
        for (int num = 1; num * num <= remainder; num++) {    // 使用index平方而不是index，能充分的降低时间复杂度
            int currentLevel = dpCount(remainder - num * num, dp) + 1;
            min = Math.min(min, currentLevel);
        }
        return dp[remainder] = min;
    }
}
