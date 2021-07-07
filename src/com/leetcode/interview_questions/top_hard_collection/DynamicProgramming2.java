package com.leetcode.interview_questions.top_hard_collection;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DynamicProgramming2 {

    // TODO: 动态编程的标准用法，归避掉由于暴力破解带来的原始时间复杂度(数字上的可能情况)
    //       1. 首先使用递归拆分，解决问题
    //       2. 使用存储空间memoization table，记录迭代的结果
    // Word Break
    // Given a string s and a dictionary of strings wordDict (all strings are unique)
    // Return true if s can be segmented into a space-separated sequence of one or more dictionary words
    // s = "cars"         wordDict = ["car","ca","rs"]             -> true
    // s = "aaaaaaaaaaab" wordDict = ["a","aa","aaa","aaaa"]       -> false
    public boolean wordBreak(String str, List<String> wordDict) {
        // 测试理解：1. 从原始字符串出发，在每个位置截取，然后递归判断两个部分的字符串是否能够在wordDict中找到
        //            O(2^n) 可以拆分成的子字符串的数目，每个位置可能有，可能没有
        //            O(n)   根据拆分的子字符串，最多可能递归的(深度)复杂度，栈空间的开销
        if (wordDict.contains(str)) return true;
        for (int i = 0; i < str.length() - 1; i++) {
            boolean result = false;
            if (wordDict.contains(str.substring(0, i + 1))) {
                result = wordBreak(str.substring(i + 1), wordDict);
            }
            if (result) return true;
        }
        return false;
    }

    // 1. 对于字符串的任意位置index, 如果能够通过前面的子字符串的组合(以某种方式到达index位置)
    //    那么index位置就是true, 借助前面的记录推导到最后str.length()结束位置 !!
    //    O(n*n*n) .substring()方法会造成一定的时间复杂度
    //    O(n)     空间复杂度由Hash Table和dp数组构成
    public boolean wordBreakDP(String str, List<String> wordDict) {
        Set<String> wordDictSet = new HashSet<>(wordDict);  // 直接用List构建Hash Table
        boolean[] dp = new boolean[str.length() + 1];       // 默认length+1里面的初始值均为false
        dp[0] = true;
        for (int i = 1; i <= str.length(); i++) {           // 利用DP数组来完成迭代和累计
            // 判断该index位置是否能够通过前面某个位置到达，做好标记
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDictSet.contains(str.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[str.length()];
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // Word Break II


    // TODO：不推荐使用hard coding硬编码
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
