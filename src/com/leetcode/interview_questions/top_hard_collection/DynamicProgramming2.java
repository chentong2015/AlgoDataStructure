package com.leetcode.interview_questions.top_hard_collection;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DynamicProgramming2 {

    // Word Break
    // Given a string s and a dictionary of strings wordDict (all strings are unique)
    // Return true if s can be segmented into a space-separated sequence of one or more dictionary words
    // s = "cars", wordDict = ["car","ca","rs"]             -> true
    // "aaaaaaaaaaaaaaaaaaaaaaaab" ["a","aa","aaa","aaaa"]  -> false
    public boolean wordBreak(String str, List<String> wordDict) {
        // 测试理解：1. 从原始字符串出发，在每个位置截取，然后递归判断两个部分的字符串是否能够在wordDict中找到 ===> 暴力破解
        //            O(2^n) 可以差分成的子字符串的数目 O(n) 根据差分的子字符串，最多可能递归的(深度)复杂度 !!
        if (wordDict.contains(str)) {
            return true;
        }
        for (int i = 0; i < str.length() - 1; i++) {
            boolean result = false;
            if (wordDict.contains(str.substring(0, i + 1))) {
                result = wordBreak(str.substring(i + 1), wordDict);
            }
            if (result) {
                return true;
            }
        }
        return false;
    }

    // 正确理解：1. 动态编程, 对于字符串的任意的位置index, 如果能够通过前面的子字符串的组合(以某种方式到达index位置)
    //                    那么index位置就是true, 借助前面的记录推导到最后str.length()结束位置 !!
    //            O(n*n*n) .substring()方法会造成一定的时间复杂度  O(n) 记录的空间复杂度
    public boolean wordBreakDP(String str, List<String> wordDict) {
        Set<String> wordDictSet = new HashSet<>(wordDict);
        boolean[] dp = new boolean[str.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= str.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDictSet.contains(str.substring(j, i))) {
                    dp[i] = true;  // DP核心：保留了前面到达任意位置的true信息，以便后面字符串的截取和判断.contains()
                    break;
                }
            }
        }
        return dp[str.length()];
    }

    // Word Break II
    
}
