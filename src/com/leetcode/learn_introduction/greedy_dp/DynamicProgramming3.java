package com.leetcode.learn_introduction.greedy_dp;

public class DynamicProgramming3 {

    // TODO：实战代码和面试中，不使用hard coding硬编码 !!
    // Target Sum
    // You are given an integer array nums and an integer target
    // Build an expression out of nums by adding one of the symbols '+' and '-' before each integer
    // Return the number of different expressions that you can build, which evaluates to target
    // nums = [1,1,1,1,1], target = 3  -> 5 一共有5种表示式的组合, 需要罗列每一种组合的可能
    // -1 + 1 + 1 + 1 + 1 = 3               数学上一共有2^n种组合, 需要避免这样的复杂度
    // +1 - 1 + 1 + 1 + 1 = 3
    // +1 + 1 - 1 + 1 + 1 = 3
    // +1 + 1 + 1 - 1 + 1 = 3
    // +1 + 1 + 1 + 1 - 1 = 3
    public int findTargetSumWays(int[] nums, int S) {
        int[] dp = new int[2001];
        dp[nums[0] + 1000] = 1;
        dp[-nums[0] + 1000] += 1;
        for (int i = 1; i < nums.length; i++) {
            int[] next = new int[2001];
            for (int sum = -1000; sum <= 1000; sum++) {
                if (dp[sum + 1000] > 0) {
                    next[sum + nums[i] + 1000] += dp[sum + 1000];
                    next[sum - nums[i] + 1000] += dp[sum + 1000];
                }
            }
            dp = next;
        }
        return S > 1000 ? 0 : dp[S + 1000];
    }
}
