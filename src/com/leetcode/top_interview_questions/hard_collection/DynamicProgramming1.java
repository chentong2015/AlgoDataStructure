package com.leetcode.top_interview_questions.hard_collection;

import com.leetcode.top_interview_questions.base.ResultPatternMatch;

/**
 * Dynamic Programming 动态编程，核心解析
 * 1. Recursive backtracking
 * 2. Top-down 自顶向下
 * 3. Bottom-up 自底向上
 */
// Regular Expression Matching ==> Java底层正则表达式的实现
// Implement regular expression matching with support for '.' 任意多个字符 and '*' 前字符重复0到n次
// The matching should cover the entire input string (not partial)
// s = "ab", p = ".*"      -> true
// s = "aab", p = "c*a*b"  -> true 第一个c字符有可能重复0次
public class DynamicProgramming1 {

    public boolean isMatchRecursive(String text, String pattern) {
        // 测试理解：1. 根据pattern列举出所有的字符串的可能，找到其中匹配的目标字符串

        // 正确理解: 1. 从第一个字符和第二个字符开始递归，每次往后截取text的子字符串，并且带上前一轮判断的结果
        //            <复杂度和text & pattern字符串长度均有关>
        if (pattern.isEmpty()) {
            return text.isEmpty();
        }
        boolean firstMatch = !text.isEmpty() && (text.charAt(0) == pattern.charAt(0) || pattern.charAt(0) == '.');
        if (pattern.length() >= 2 && pattern.charAt(1) == '*') {
            // text比较完第一个字符之后，需要往后截取(*将会继续作用在开头的字符，一直匹配到不能再匹配为止)
            // 或者不截取text, 从第3个字符开始截取pattern(相当于*取0次)
            return firstMatch && isMatchRecursive(text.substring(1), pattern) || isMatchRecursive(text, pattern.substring(2));
        } else {
            return firstMatch && isMatchRecursive(text.substring(1), pattern.substring(1));
        }
    }

    // Up-Bottom Variation 自定向下 ///////////////////////////////////////////////////////////////
    // 算法逻辑：dp(i, j): does text[i:] and pattern[j:] match
    //         和上面的递归一致，这里使用二维数组来存储判断的结果值
    //         O(TP) O(TP)
    public boolean isMatchUpBottom(String text, String pattern) {
        boolean[][] dp = new boolean[text.length() + 1][pattern.length() + 1];
        dp[text.length()][pattern.length()] = true;
        for (int i = text.length(); i >= 0; i--) {
            for (int j = pattern.length() - 1; j >= 0; j--) {
                boolean first_match = (i < text.length() && (text.charAt(i) == pattern.charAt(j) || pattern.charAt(j) == '.'));
                if (j + 1 < pattern.length() && pattern.charAt(j + 1) == '*') {
                    dp[i][j] = dp[i][j + 2] || first_match && dp[i + 1][j];
                } else {
                    dp[i][j] = first_match && dp[i + 1][j + 1];
                }
            }
        }
        return dp[0][0];
    }

    // Bottom-Up Variation 自底向上 ///////////////////////////////////////////////////////////////
    private ResultPatternMatch[][] memo;

    public boolean isMatchBottomUp(String text, String pattern) {
        memo = new ResultPatternMatch[text.length() + 1][pattern.length() + 1];
        return dp(0, 0, text, pattern);
    }

    public boolean dp(int i, int j, String text, String pattern) {
        if (memo[i][j] != null) {
            return memo[i][j] == ResultPatternMatch.TRUE;
        }
        boolean ans;
        if (j == pattern.length()) {
            ans = i == text.length();
        } else {
            boolean first_match = (i < text.length() && (pattern.charAt(j) == text.charAt(i) || pattern.charAt(j) == '.'));
            if (j + 1 < pattern.length() && pattern.charAt(j + 1) == '*') {
                ans = (dp(i, j + 2, text, pattern) || first_match && dp(i + 1, j, text, pattern));
            } else {
                ans = first_match && dp(i + 1, j + 1, text, pattern);
            }
        }
        memo[i][j] = ans ? ResultPatternMatch.TRUE : ResultPatternMatch.FALSE;
        return ans;
    }
}
